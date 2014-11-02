package example;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a){
	
	    if(!(sender instanceof Player)) {
		    return false;
		}
		
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("test")){
			if(a.length < 1){
				player.sendMessage(ChatColor.RED + "Too few arguments");
			} else if(a[0].equalsIgnoreCase("a")){
				player.sendMessage(ChatColor.RED + "You used the argument: A");
			}
		}
		return false;
	}
}