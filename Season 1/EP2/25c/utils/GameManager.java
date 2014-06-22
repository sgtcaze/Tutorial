package example.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import example.Example;

public class GameManager {

	private Example plugin;

	public GameManager(Example plugin) {
		this.plugin = plugin;
	}

	public void startGame() {

		plugin.gameStarted = true;

		plugin.spawnHandler.teleportToArena();

		for (Player p : Bukkit.getOnlinePlayers()) {
			plugin.players.add(p.getName());
			plugin.points.put(p.getName(), 0);
			plugin.kills.put(p.getName(), 0);
			plugin.deaths.put(p.getName(), 0);
			plugin.totalAlive++;
			plugin.scoreboard(p);
		}

		Bukkit.broadcastMessage("§cThe game has started! First to 5 kills wins!");
	}

	@SuppressWarnings("deprecation")
	public void endGame() {

		plugin.gameEnded = true;

		Player winner = null;

		for (String s : plugin.players) {
			winner = Bukkit.getPlayer(s);
			Bukkit.broadcastMessage("§cThe game has ended! Winner: " + s);
		}

		if (winner != null) {
			plugin.api.firework(winner);
		}
	}
}