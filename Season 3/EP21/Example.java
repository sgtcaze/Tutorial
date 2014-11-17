package example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	// Useful Symbol: &–ˆ

	private int num = 1;

	private Inventory inv;
	
	private List<ItemStack> items = new ArrayList<>();

	public void onEnable() {
		inv = Bukkit.createInventory(null, 9, "&0&nThe Magic Inventory");
		
		items.add(make(Material.APPLE, 1, 0, colorize("&aA&fnimation"), ""));
		items.add(make(Material.APPLE, 1, 0, colorize("&fA&an&fimation"), colorize("&2H")));
		items.add(make(Material.APPLE, 1, 0, colorize("&fAn&ai&fmation"), colorize("&2HE")));
		items.add(make(Material.APPLE, 1, 0, colorize("&fAni&am&fation"), colorize("&2HEL")));
		items.add(make(Material.APPLE, 1, 0, colorize("&fAnim&aa&ftion"), colorize("&2HELL")));
		items.add(make(Material.APPLE, 1, 0, colorize("&fAnima&at&fion"), colorize("&2HELLO")));
		items.add(make(Material.APPLE, 1, 0, colorize("&fAnimati&ao&fn"), colorize("&2HELLO!")));
		items.add(make(Material.APPLE, 1, 0, colorize("&fAnimatio&an"), colorize("&2HELLO! :D")));

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				if(num == 8) {
					num = 0;
				} else {
					num++;				
				}
				
				inv.setItem(0, items.get(num));
			}
		}, 0, 1 * 2);
	}

	private String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
	
	private ItemStack make(Material material, int amount, int shrt, String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {

		if (!(sender instanceof Player)) {
			return false;
		}

		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("test")) {
			player.openInventory(inv);
		}
		return false;
	}
}