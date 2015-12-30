package example;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
        if (sender instanceof Player && cmd.getName().equalsIgnoreCase("test")) {
            Player player = (Player) sender;
            player.sendMessage("You will have to wait 5 seconds for the item!");
            giveDelay(player);
        }
        return false;
    }

    public void giveDelay(final Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                player.sendMessage(ChatColor.GREEN + "This is the delay.");
                player.getInventory().addItem(new ItemStack(Material.APPLE));
            }
        }, 100L); // 20 ticks = 1 second. So 100 ticks = 5 seconds.
    }

}