package example;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a){
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("test")){
			if(player.hasPermission("example.permission")){
				player.sendMessage(ChatColor.GREEN + "You have permission!");
			} else {
				player.sendMessage(ChatColor.RED + "You don't have permission!");
			}
		}
		return false;
	}
}