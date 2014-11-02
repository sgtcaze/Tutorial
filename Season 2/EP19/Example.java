package example;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin {

	public void onEnable() {
		saveDefaultConfig();
	}

	private String colorize(String input) {
	    return ChatColor.translateAlternateColorCodes("&", input);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] a) {
		
		if(!(sender instanceof Player)) {
		    return false;
		}
			
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("integer")) {
			player.sendMessage("The Integer is: " + getConfig().getInt("Integer"));
		}
		if (cmd.getName().equalsIgnoreCase("string")) {
			player.sendMessage("The String is: " + colorize(getConfig().getString("String")));
		}
		if (cmd.getName().equalsIgnoreCase("boolean")) {
			if (getConfig().getBoolean("Boolean")) {
				player.sendMessage(colorize("The Boolean is: &2Enabled"));
			} else {
				player.sendMessage(colorize("The Boolean is: &4Disabled"));
			}
		}
		return false;
	}
}