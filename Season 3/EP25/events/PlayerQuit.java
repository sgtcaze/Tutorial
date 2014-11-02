package example.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import example.Example;

public class PlayerQuit implements Listener {

	private Example plugin = Example.getInstance();

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		if (GameState.state == GameState.INGAME) {
			if (plugin.getGameManager().getPlayers().contains(p.getUniqueId())) {
				plugin.getApi().addDeaths(p, 1);
				plugin.players.remove(p.getUniqueId());
			}
		}

		if (plugin.totalAlive <= 2) {
			if (GameState.state != GameState.ENDED) {
				plugin.getGameManager().endGame();
			}
		}
	}
}