package example.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import example.Example;

public class GameManager {

	private Example plugin = Example.getInstance();

	private World arenaworld, lobbyworld;

	private List<UUID> players = new ArrayList<>();
	private List<UUID> spectators = new ArrayList<>();
	private List<Location> arenaSpawns = new ArrayList<>();

	private HashMap<UUID, Integer> kills = new HashMap<>();
	private HashMap<UUID, Integer> points = new HashMap<>();
	private HashMap<UUID, Integer> deaths = new HashMap<>();

	public GameManager() {
		Bukkit.getServer().createWorld(new WorldCreator("Arenaworld"));

		lobbyworld = Bukkit.getWorld("world");
		arenaworld = Bukkit.getWorld("Arenaworld");

		GameState.state = GameState.LOBBY;
	}

	public World getArenaWorld() {
		return arenaworld;
	}

	public World getLobbyWorld() {
		return lobbyworld;
	}

	public List<UUID> getPlayers() {
		return players;
	}

	public List<UUID> getSpectators() {
		return spectators;
	}

	public List<Location> getSpawns() {
		return arenaSpawns;
	}

	public HashMap<UUID, Integer> getKills() {
		return kills;
	}

	public HashMap<UUID, Integer> getPoints() {
		return points;
	}

	public HashMap<UUID, Integer> getDeaths() {
		return deaths;
	}

	public void setArenaSpawns(List<Location> list) {
		this.arenaSpawns = list;
	}

	public enum GameState {

		LOBBY, STARTED, ENDED;

		public static GameState state;
	}

	public void startGame() {
		GameState.state = GameState.STARTED;

		plugin.getSpawnHandler().teleportToArena();

		for (Player p : Bukkit.getOnlinePlayers()) {
			UUID uuid = p.getUniqueId();
			players.add(uuid);
			points.put(uuid, 0);
			kills.put(uuid, 0);
			deaths.put(uuid, 0);
			plugin.scoreboard(p);
		}

		Bukkit.broadcastMessage("§cThe game has started! First to 5 kills wins!");
	}

	public void endGame() {
		GameState.state = GameState.ENDED;

		Player winner = null;

		for (UUID uuids : players) {
			winner = Bukkit.getPlayer(s);
			Bukkit.broadcastMessage("§cThe game has ended! Winner: " + s);
			break;
		}

		if (winner != null) {
			plugin.getApi().firework(winner);
		}
	}
}