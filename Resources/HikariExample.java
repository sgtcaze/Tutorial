import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class HikariExample extends JavaPlugin {
    //Create global Hikari instance
    private HikariDataSource hikari;
    //On server start, we connect to our database
    @Override
    public void onEnable() {
        connectToDatabase();
    }
    public void connectToDatabase() {
        //Database details
        String address = getConfig().getString("Database.Address");
        String name = getConfig().getString("Database.Name");
        String username = getConfig().getString("Database.Username");
        String password = getConfig().getString("Database.Password");
        //Initialise hikari instace
        hikari = new HikariDataSource();
        //Setting Hikari properties
        hikari.setMaximumPoolSize(10);
        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", address);
        hikari.addDataSourceProperty("port", "3306");
        hikari.addDataSourceProperty("databaseName", name);
        hikari.addDataSourceProperty("user", username);
        hikari.addDataSourceProperty("password", password);
    }

    public void runAsyncQuery(UUID playerUUID) {
        //Create connection instance
        Connection connection = null;
        //MySQL query: https://www3.ntu.edu.sg/home/ehchua/programming/sql/MySQL_Beginner.html
        String update = "INSERT INTO database VALUES(?, ?) ON DUPLICATE KEY UPDATE uuid=?";

        PreparedStatement p = null;

        try {
            //Initialise hikari connection, by getting the hikari connect if established
            connection = hikari.getConnection();
            //Preparing statement - INSERT INTO...
            p = connection.prepareStatement(update);
            //Setting parameters in MySQL query: i.e the question marks(?), where the first one has the index of 1.
            p.setString(1, playerUUID.toString());
            p.setInt(2, 0);
            //ON DUPLICATE UPDATE updates the current uuid, if it exists
            p.setString(3, playerUUID.toString());
            //Executes the statement
            p.execute();
        } catch (SQLException e) {
            //Print out any exception while trying to prepare statement
            e.printStackTrace();
        } finally {
            //After catching the statement, close connection if connection is established
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // If connection is established, close connection after query
            if(p != null) {
                try {
                    p.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
