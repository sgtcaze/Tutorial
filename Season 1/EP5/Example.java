package example;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
        if (cmd.getName().equalsIgnoreCase("test")) {
            if (sender.hasPermission("example.permission")) {
                sender.sendMessage(ChatColor.GREEN + "You have permission!");
            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission!");
            }
        }
        return false;
    }

}