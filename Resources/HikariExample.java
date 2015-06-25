import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class HikariExample extends JavaPlugin {

    private HikariDataSource hikari;

    @Override
    public void onEnable() {
        connectToDatabase();
    }

    public void connectToDatabase() {
        String address = getConfig().getString("Database.Address");
        String name = getConfig().getString("Database.Name");
        String username = getConfig().getString("Database.Username");
        String password = getConfig().getString("Database.Password");

        hikari = new HikariDataSource();
        hikari.setMaximumPoolSize(10);
        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", address);
        hikari.addDataSourceProperty("port", "3306");
        hikari.addDataSourceProperty("databaseName", name);
        hikari.addDataSourceProperty("user", username);
        hikari.addDataSourceProperty("password", password);
    }

    public void runAsyncQuery(UUID playerUUID) {
        Connection connection = null;

        String update = "INSERT INTO database VALUES(?, ?) ON DUPLICATE KEY UPDATE uuid=?";

        PreparedStatement p = null;

        try {
            connection = hikari.getConnection();

            p = connection.prepareStatement(update);
            p.setString(1, playerUUID.toString());
            p.setInt(2, 0);
            p.setString(3, playerUUID.toString());
            p.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Organize
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
