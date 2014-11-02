package example.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import example.Example;

public class EntityDamageByEntity implements Listener {

	private Example plugin = Example.getInstance();

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (e.getEntity() instanceof LivingEntity) {
				if (plugin.getGameManager().getSpectators().contains(p.getUniqueId())) {
					e.setCancelled(true);
				}
			}
		}
	}
}