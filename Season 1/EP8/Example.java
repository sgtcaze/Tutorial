package example;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin {

    public void onEnable() {
        ourTask();
    }

    public void ourTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(ChatColor.GREEN + "This is the runnable. Have a potato!");
                    p.getInventory().addItem(new ItemStack(Material.BAKED_POTATO, 1));
                }
            }
        }, 0, 5 * 20); // 20 ticks = 1 second. So 5 * 20 = 100 which is 5 seconds
    }
	
}