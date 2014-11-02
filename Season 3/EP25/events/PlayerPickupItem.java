package example.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import example.Example;

public class PlayerPickupItem implements Listener {

	private Example plugin = Example.getInstance();

	@EventHandler
	public void onDrop(PlayerPickupItemEvent e) {
		if (plugin.getGameManager().getSpectators().contains(e.getPlayer().getUniqueId()))
			e.setCancelled(true);
	}
}