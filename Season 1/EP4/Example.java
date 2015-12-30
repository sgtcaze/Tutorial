package example;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class Example extends JavaPlugin {

    private Inventory inv;

    public void onEnable() {
        inv = Bukkit.createInventory(null, 9, "Title");
        inv.addItem(make(Material.WOOL, 1, 0, "Name", Arrays.asList("Line 1", "Line 2")));
    }

    private ItemStack make(Material material, int amount, int shrt, String displayName, List<String> lore) {
        ItemStack item = new ItemStack(material, amount, (short) shrt);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("test")) {
            ((Player) sender).openInventory(inv);
        }
        return false;
    }
	
}