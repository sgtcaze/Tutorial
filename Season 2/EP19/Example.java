package example;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin {

	public void onEnable() {
		saveDefaultConfig();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] a) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("integer")) {
			player.sendMessage("The Integer is: " + getConfig().getInt("Integer"));
		}
		if (cmd.getName().equalsIgnoreCase("string")) {
			player.sendMessage("The String is: " + getConfig().getString("String").replaceAll("&", ""));
		}
		if (cmd.getName().equalsIgnoreCase("boolean")) {
			if (getConfig().getBoolean("Boolean")) {
				player.sendMessage("The Boolean is: 2Enabled");
			} else {
				player.sendMessage("The Boolean is: 4Disabled");
			}
		}
		return false;
	}
}