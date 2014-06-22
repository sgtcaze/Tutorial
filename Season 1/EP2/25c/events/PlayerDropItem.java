package example.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import example.Example;

public class PlayerDropItem implements Listener {

	private Example plugin;

	public PlayerDropItem(Example plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();

		if (plugin.spectators.contains(p.getName())) {
			e.setCancelled(true);
		}
	}
}