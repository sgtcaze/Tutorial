package example;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import example.commands.SetSpawn;
import example.commands.Start;
import example.events.EntityDamage;
import example.events.EntityDamageByEntity;
import example.events.EntityDeath;
import example.events.PlayerDeath;
import example.events.PlayerDropItem;
import example.events.PlayerJoin;
import example.events.PlayerPickupItem;
import example.events.PlayerQuit;
import example.utils.GameManager;
import example.utils.SpawnHandler;

public class Example extends JavaPlugin implements Listener {

	World Arenaworld, Lobbyworld;

	// TO-DO JSON FORMATTING

	public Example plugin;

	public Boolean gameStarted, gameEnded;

	public ArrayList<String> players = new ArrayList<>();
	public ArrayList<String> spectators = new ArrayList<>();

	public ArrayList<Location> arenaSpawns = new ArrayList<>();

	public FileConfiguration config;

	public int totalAlive;

	public HashMap<String, Integer> points = new HashMap<>();
	public HashMap<String, Integer> kills = new HashMap<>();
	public HashMap<String, Integer> deaths = new HashMap<>();

	public API api;
	public GameManager gm;
	public SpawnHandler spawnHandler;

	public void onEnable() {

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EntityDamage(this), this);
		pm.registerEvents(new EntityDamageByEntity(this), this);
		pm.registerEvents(new EntityDeath(this), this);
		pm.registerEvents(new PlayerDeath(this), this);
		pm.registerEvents(new PlayerDropItem(this), this);
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new PlayerPickupItem(this), this);
		pm.registerEvents(new PlayerQuit(this), this);

		getCommand("setspawn").setExecutor(new SetSpawn(this));
		getCommand("start").setExecutor(new Start(this));

		getServer().createWorld(new WorldCreator("Arenaworld"));

		Lobbyworld = Bukkit.getWorld("world");
		Arenaworld = Bukkit.getWorld("Arenaworld");

		totalAlive = 0;

		plugin = this;

		api = new API(this);
		gm = new GameManager(this);
		spawnHandler = new SpawnHandler(this);

		config = getConfig();

		saveDefaultConfig();

		gameStarted = false;
		gameEnded = false;
	}

	@SuppressWarnings("deprecation")
	public void scoreboard(Player p) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();

		Objective objective = board.registerNewObjective("Test", "Test2");
		objective.setDisplayName("§f§lPVP");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		Score score = objective.getScore(Bukkit.getOfflinePlayer("§6Kills"));
		score.setScore(api.getKills(p));

		Score score2 = objective.getScore(Bukkit.getOfflinePlayer("§6Deaths"));
		score2.setScore(api.getDeaths(p));

		Score score3 = objective.getScore(Bukkit.getOfflinePlayer("§6Points"));
		score3.setScore(api.getPoints(p));

		p.setScoreboard(board);
	}
}