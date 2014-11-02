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

	private Example plugin = Example.getInstance();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		UUID uuid = e.getPlayer().getUniqueId();

		if (!plugin.getConfig().contains(uuid.toString())) {
			plugin.getMoney().put(uuid, 0);
		} else {
			plugin.getMoney().put(uuid, plugin.getConfig().getInt(p.getUniqueId() + ".Silver"));
		}
	}

	@EventHandler
	public void onKill(EntityDeathEvent e) {
		if (e.getEntity() instanceof Monster) {
			if (e.getEntity().getKiller() instanceof Player) {
				Player p = e.getEntity().getKiller()
				plugin.getApi().giveSilver(p, 200);
			}
		} else if (e.getEntity() instanceof Villager) {
			if (e.getEntity().getKiller() instanceof Player) {
				Player p = e.getEntity().getKiller()
				plugin.getApi().takeSilver(p, 200);
			}
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {		
		ItemStack item = event.getCurrentItem();
		
		if (event.getInventory().getName().equals("§0My Custom Shop")) {
			event.setCancelled(true);

			if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
				if(item.getItemMeta().getDisplayName().equals("§3Apple")){
      				Player p = (Player) event.getWhoClicked();
					
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