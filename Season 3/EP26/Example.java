package example;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Example extends JavaPlugin implements Listener {

    // Represents the MySQL Connection
    private Connection connection;

    // The instance of our plugin
    private Example plugin;

    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(this, this);
        printData();
    }

    private void printData() {
        // Run Async, then run something sync immediately after
        new BukkitRunnable() {
            @Override
            public void run() {
                openConnection();

                List<String> temp = getNameColumn("name");

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!temp.isEmpty()) {
                            for (String name : temp) {
                                System.out.println("I found the name: " + name);
                            }
                        } else {
                            System.out.println("There were no names :(");
                        }
                    }
                }.runTask(plugin);
            }
        }.runTaskAsynchronously(this);
    }

    // Open the MySQL Connection
    private synchronized void openConnection() {
        try {
            connection = DriverManager.getConnection("jbdc:mysql://localhost:3306/youtube", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add an element to MySQL
    private void insertElement(String key, String element) {
        try {
            String query = "INSERT INTO some_database VALUES(?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, element);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get an entire column in MySQL
    private List<String> getNameColumn(String columnName) {
        List<String> temp = new ArrayList<>();
        final String QUERY = "SELECT name FROM some_database";

        try {
            ResultSet res = connection.prepareStatement(QUERY).executeQuery();
            while (res.next()) {
                temp.add(res.getString(columnName));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return temp;
    }

}