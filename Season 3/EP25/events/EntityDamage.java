package example.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import example.Example;

public class EntityDamage implements Listener {

	private Example plugin = Example.getInstance();

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (plugin.getGameManager().getSpectators().contains(p.getUniqueId())) {
				e.setCancelled(true);
			}
		}
	}
}