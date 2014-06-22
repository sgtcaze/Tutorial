package example.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import example.Example;

public class PlayerPickupItem implements Listener {

	private Example plugin;

	public PlayerPickupItem(Example plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDrop(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();

		if (plugin.spectators.contains(p.getName())) {
			e.setCancelled(true);
		}
	}
}