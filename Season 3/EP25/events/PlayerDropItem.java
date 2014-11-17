package example.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import example.Example;

public class PlayerDropItem implements Listener {

	private Example plugin = Example.getInstance();

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if (plugin.getGameManager().getSpectators().contains(e.getPlayer().getUniqeId())) {
			e.setCancelled(true);
		}		
	}
}