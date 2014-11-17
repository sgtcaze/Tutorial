package example.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import example.Example;

public class PlayerJoin implements Listener {

	private Example plugin = Example.getInstance();

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (GameState.state == GameState.STARTED) {
			if (Bukkit.getOnlinePlayers().size() >= 2) {
				plugin.getGameManager().startGame();
			}
		}
	}
}