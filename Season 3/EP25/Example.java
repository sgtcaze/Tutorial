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
import org.bukkit.scoreboard.*;

import example.commands.*;
import example.events.*;
import example.utils.*;

public class Example extends JavaPlugin implements Listener {

	private API api;
	private GameManager gm;
	private SpawnHandler spawnHandler;
	
	private static Example plugin;

	public void onEnable() {
	    plugin = this;
		
		api = new API();
		gm = new GameManager();
		spawnHandler = new SpawnHandler();
		
        registerEvents();
        registerCommands();
	}
	
	public static Example getInstance() {
	    return plugin;
	}
	
	public API getApi() {
	    return api;
	}
	
	public GameManager getGameManger() {
	    return gm;
	}
	
	public SpawnHandler getSpawnHandler() {
	    return spawnHandler;
	}
	
	private void registerEvents() {
	    PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EntityDamage(), this);
		pm.registerEvents(new EntityDamageByEntity(), this);
		pm.registerEvents(new EntityDeath(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new PlayerDropItem(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerPickupItem(), this);
		pm.registerEvents(new PlayerQuit(), this);
	}
	
	private void registerCommands() {
		getCommand("setspawn").setExecutor(new SetSpawn());
		getCommand("start").setExecutor(new Start());	
	}
}