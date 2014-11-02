package example;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {
	
	// Useful Symbol: █

	private int num = 1;
	
	private Inventory inv;

	public void onEnable() {		
		inv = Bukkit.createInventory(null, 9, "§0§nThe Magic Inventory");
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
				new Runnable() {
					public void run() {			
						switch(num){
							case 1: inv.setItem(0, make(Material.APPLE, 1, 0, "§aA§fnimation", "")); num++; break;
							case 2: inv.setItem(0, make(Material.APPLE, 1, 0, "§fA§an§fimation", "§2H")); num++; break;
							case 3: inv.setItem(0, make(Material.APPLE, 1, 0, "§fAn§ai§fmation", "§2HE")); num++; break;
							case 4: inv.setItem(0, make(Material.APPLE, 1, 0, "§fAni§am§fation", "§2HEL")); num++; break;
							case 5: inv.setItem(0, make(Material.APPLE, 1, 0, "§fAnim§aa§ftion", "§2HELL")); num++; break;
							case 6: inv.setItem(0, make(Material.APPLE, 1, 0, "§fAnima§at§fion", "§2HELLO")); num++; break;
							case 7: inv.setItem(0, make(Material.APPLE, 1, 0, "§fAnimati§ao§fn", "§2HELLO!")); num++; break;
							case 8: inv.setItem(0, make(Material.APPLE, 1, 0, "§fAnimatio§an", "§2HELLO! :D")); num = 1; break;
						}
					}
				}, 0, 1 * 2);
		}
	
	private ItemStack make(Material material, int amount, int shrt, String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] a) {
			
	    if(!(sender instanceof Player)) {
		    return false;
		}
		
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("test")) {
			player.openInventory(inv);
		}
		return false;
	}
}