package example.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import example.Example;

public class SpawnHandler {

	private Example plugin = Example.getInstance();

	public void teleportToArena() {
		int counter = 0;

		for (Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(plugin.arenaSpawns.get(counter++));
		}
	}

	public void loadSpawns(String arenaName) {
	    FileConfiguration config = plugin.getConfig();
		
		List<Location> temp = new ArrayList<>();

		int amount = config.getInt("Spawns." + arenaName + ".amount");

		World world = Bukkit.getWorld(config.getString("Spawns." + arenaName + ".world"));

		for (int i = 1; i <= amount; i++) {
			double x = config.getDouble("Spawns." + arenaName + "." + i + ".x");
			double y = config.getDouble("Spawns." + arenaName + "." + i + ".y");
			double z = config.getDouble("Spawns." + arenaName + "." + i + ".z");

			temp.add(new Location(world, x, y, z));
		}

		plugin.getGameManager().getArenaSpawns(temp);
	}

	public void setSpawn(Player p, String arenaName) {
	    FileConfiguration config = plugin.getConfig();
	
		int amount = config.getInt("Spawns." + arenaName + ".amount");

		int next = amount + 1;

		String world = p.getWorld().getName();

		config.set("Spawns." + arenaName + "amount", next);
		config.set("Spawns." + arenaName + "." + next + ".world", world);

		Location loc = p.getLocation();

		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();

		config.set("Spawns." + arenaName + "." + next + ".x", x);
		config.set("Spawns." + arenaName + "." + next + ".y", y);
		config.set("Spawns." + arenaName + "." + next + ".z", z);

		plugin.saveConfig();
	}
}