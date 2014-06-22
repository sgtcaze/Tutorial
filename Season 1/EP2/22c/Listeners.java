package example;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {

	private Example plugin;
	
	public Listeners(Example plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if (!plugin.config.contains(p.getUniqueId().toString())) {
			plugin.money.put(p.getUniqueId(), 0);
		} else {
			plugin.money.put(p.getUniqueId(), plugin.config.getInt(p.getUniqueId() + ".Silver"));
		}
	}

	@EventHandler
	public void onKill(EntityDeathEvent e) {

		if (e.getEntity() instanceof Monster) {
			Monster m = (Monster) e.getEntity();
			if (m.getKiller() instanceof Player) {
				Player p = m.getKiller();

				plugin.getApi().giveSilver(p, 200);
			}
		} else if (e.getEntity() instanceof Villager) {
			Villager v = (Villager) e.getEntity();
			if (v.getKiller() instanceof Player) {
				Player p = v.getKiller();
				plugin.getApi().takeSilver(p, 200);
			}
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		
		if (event.getInventory().getName().equals("§0My Custom Shop")) {
			event.setCancelled(true);

			if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
				if(item.getItemMeta().getDisplayName().equals("§3Apple")){
					if (plugin.getApi().hasEnough(p, 25)) {
						plugin.getApi().takeSilver(p, 25);
					} else {
						p.sendMessage("§cYou're too poor!");
					}
				}
			}
		}
	}
}