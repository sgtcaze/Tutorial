package example.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import example.Example;

public class PlayerJoin implements Listener {

	private Example plugin;

	public PlayerJoin(Example plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		if (!plugin.gameStarted) {
			if (Bukkit.getOnlinePlayers().length >= 2) {
				plugin.gm.startGame();
			}
		}
	}
}