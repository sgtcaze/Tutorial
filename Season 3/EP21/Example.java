package example;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Example extends JavaPlugin implements Listener {

    private Inventory animatedInventory;

    public void onEnable() {
        animatedInventory = Bukkit.createInventory(null, 9, "The Magic Inventory");

        List<ItemStack> items = new ArrayList<>();
        items.add(make(Material.APPLE, 1, 0, colorize("&aI am GREEN!")));
        items.add(make(Material.GOLDEN_APPLE, 1, 0, colorize("&cI am RED!")));

        new BukkitRunnable() {
            int currentIndex = 0;

            @Override
            public void run() {
                if (currentIndex == items.size()) {
                    currentIndex = 0;
                }

                animatedInventory.setItem(0, items.get(currentIndex++));
            }
        }.runTaskTimer(this, 0, 2);
    }

    private String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    private ItemStack make(Material material, int amount, int shrt, String displayname) {
        ItemStack item = new ItemStack(material, amount, (short) shrt);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setItemMeta(meta);
        return item;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
        if (sender instanceof Player && cmd.getName().equalsIgnoreCase("test")) {
            ((Player) sender).openInventory(animatedInventory);
        }
        return false;
    }

}