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
	
	public FileConfiguration config;
	
	public HashMap<UUID, Integer> money = new HashMap<>();
	
	private Api api;
	
	Inventory shop;

	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		
		config = getConfig();
		
		api = new Api(this);
		
		shop = Bukkit.createInventory(null, 9, "§0§nMy Custom Shop");

		shop.setItem(0, createItem(Material.APPLE, 1, (short) 0, "§3Apple", "§fPrice §6200 §fSilver"));
	}
	
	public Api getApi(){
		return api;
	}
	
	public void onDisable() {
		for(UUID u : money.keySet()){
			config.set(u + ".Silver", money.get(u));
		}
		
		saveConfig();
	}

	public ItemStack createItem(Material material, int amount, int shrt, String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] a) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("shop")) {
			player.openInventory(shop);
		}
		return false;
	}
}