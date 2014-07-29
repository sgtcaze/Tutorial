package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Example extends JavaPlugin implements Listener {

	// Represents the MySQL Connection 
	private Connection connection;
	
	// The instance of our plugin
	private Example plugin;

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);

		plugin = this;

		// Run Async, then run something sync immediately after
		new BukkitRunnable() {
			@Override
			public void run() {
				openConnection();

				final List<String> temp = getColumn("name");
				
				new BukkitRunnable() {
					@Override
					public void run() {
						if(!temp.isEmpty()){
							for(String s : temp){
								System.out.println("I found the name: " + s);
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
	public synchronized void openConnection() {
		try {
			connection = DriverManager.getConnection(
					"jbdc:mysql://localhost:3306/youtube", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Check if MySQL contains an element
	public boolean containsElement(String field, String element){
		String query = "SELECT `" + field + "` FROM `tutorial` WHERE `" + field + "`='" + element + "`";
		
		try {
			ResultSet res = connection.prepareStatement(query).executeQuery();
			boolean temp = res.next();
			res.close();
			return temp;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	// Add an element to MySQL
	public void insertElement(String element, String uuid){
		// String query = "INSERT INTO `tutorial` VALUES('" + element + ", '" + uuid + "')";
		String query = "INSERT INTO `tutorial` (`name`, `uuid`) VALUES('" + element + ", '" + uuid + "')";
		
		try {
			PreparedStatement p = connection.prepareStatement(query);
			p.execute();
			p.close();
		} catch (SQLException e){
			e.printStackTrace();
		}	
	}
	
	// Get an entire column in MySQL
	public List<String> getColumn(String columnName) {
		List<String> temp = new ArrayList<>();

		String query = "SELECT `" + columnName + "` FROM `tutorial`";

		try {
			ResultSet res = connection.prepareStatement(query).executeQuery();

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