package example.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import example.Example;

public class EntityDamageByEntity implements Listener {

	private Example plugin;

	public EntityDamageByEntity(Example plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onEDD(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (e.getEntity() instanceof LivingEntity) {
				if (plugin.spectators.contains(p.getName())) {
					e.setCancelled(true);
				}
			}
		}
	}
}