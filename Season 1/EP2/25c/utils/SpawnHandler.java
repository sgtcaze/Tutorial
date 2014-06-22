package example.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import example.Example;

public class SpawnHandler {

	private Example plugin;

	public SpawnHandler(Example plugin) {
		this.plugin = plugin;
	}

	public void teleportToArena() {

		int counter = 0;

		for (Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(plugin.arenaSpawns.get(counter++));
		}
	}

	public void loadSpawns(String arenaName) {
		ArrayList<Location> temp = new ArrayList<>();

		int amount = plugin.config.getInt("Spawns." + arenaName + ".amount");

		World world = Bukkit.getWorld(plugin.config.getString("Spawns."
				+ arenaName + ".world"));

		for (int i = 1; i <= amount; i++) {
			double x = plugin.config.getDouble("Spawns." + arenaName + "." + i
					+ ".x");
			double y = plugin.config.getDouble("Spawns." + arenaName + "." + i
					+ ".y");
			double z = plugin.config.getDouble("Spawns." + arenaName + "." + i
					+ ".z");

			temp.add(new Location(world, x, y, z));
		}

		plugin.arenaSpawns = temp;
	}

	public void setSpawn(Player p, String arenaName) {
		int amount = plugin.config.getInt("Spawns." + arenaName + ".amount");

		int next = amount + 1;

		String world = p.getWorld().getName();

		plugin.config.set("Spawns." + arenaName + "amount", next);
		plugin.config.set("Spawns." + arenaName + "." + next + ".world", world);

		Location loc = p.getLocation();

		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();

		plugin.config.set("Spawns." + arenaName + "." + next + ".x", x);
		plugin.config.set("Spawns." + arenaName + "." + next + ".y", y);
		plugin.config.set("Spawns." + arenaName + "." + next + ".z", z);

		plugin.plugin.saveConfig();
	}
}