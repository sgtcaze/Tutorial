package example;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin {

	private ItemStack make(Material material, int amount, int shrt, String displayName, List<String> lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a){
		
		if(!(sender instanceof Player)) {
		    return false;
		}
		
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("test")){
			player.getInventory().addItem(make(Material.WOOL, 1, 0, "Name", Arrays.asList("Line 1", "Line 2")));
		}
		return false;
	}
}