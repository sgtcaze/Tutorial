package example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {
	
    private Api api;
	
	private static Example plugin;
	
	private Inventory shop;
	
	private HashMap<UUID, Integer> money = new HashMap<>();

	public void onEnable() {
	    plugin = this;
	
	    api = new Api(this);
	
		getServer().getPluginManager().registerEvents(new Listeners(this), this);

		shop = Bukkit.createInventory(null, 9, "§0§nMy Custom Shop");
		shop.setItem(0, createItem(Material.APPLE, 1, 0, "§3Apple", "§fPrice §6200 §fSilver"));
	}
	
	public void onDisable() {
		for(UUID u : money.keySet()){
			getConfig().set(u + ".Silver", money.get(u));
		}
		
		saveConfig();
	}
	
	public static Example getInstance() {
	    return plugin;
	}
	
	public Api getApi(){
		return api;
	}
	
    public HashMap<UUID, Integer> getMoney() {
	    return money;
	}

	private ItemStack createItem(Material material, int amount, int shrt, String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
	
	    if(!(sender instanceof Player)) {
		    return false;
		}
	
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("shop")) {
			player.openInventory(shop);
		}
		return false;
	}
}