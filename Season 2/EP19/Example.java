package example;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin {

    public void onEnable() {
        saveDefaultConfig();
    }

    private String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
        if (cmd.getName().equalsIgnoreCase("integer")) {
            sender.sendMessage("The Integer is: " + getConfig().getInt("Integer"));
        } else if (cmd.getName().equalsIgnoreCase("string")) {
            sender.sendMessage("The String is: " + colorize(getConfig().getString("String")));
        } else if (cmd.getName().equalsIgnoreCase("boolean")) {
            if (getConfig().getBoolean("Boolean")) {
                sender.sendMessage(colorize("The Boolean is: &2Enabled"));
            } else {
                sender.sendMessage(colorize("The Boolean is: &4Disabled"));
            }
        }
        return false;
    }

}