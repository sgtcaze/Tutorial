package example.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import example.Example;

public class PlayerQuit implements Listener {

	private Example plugin;

	public PlayerQuit(Example plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		if (plugin.gameStarted) {
			if (plugin.players.contains(p.getName())) {
				plugin.api.addDeaths(p, 1);
				plugin.totalAlive--;
				plugin.players.remove(p.getName());
			}
		}

		if (plugin.totalAlive <= 2) {
			if (plugin.gameEnded) {
				plugin.gm.endGame();
			}
		}
	}
}