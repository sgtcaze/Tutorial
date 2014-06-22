package example.events;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import example.Example;
import example.utils.ParticleUtils;

public class PlayerDeath implements Listener {

	private Example plugin;

	public PlayerDeath(Example plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();

		plugin.api.addDeaths(p, 1);

		if (plugin.api.getDeaths(p) == 5) {
			plugin.api.setSpectator(p);
		}

		for (Player pl : Bukkit.getOnlinePlayers()) {
			try {
				ParticleUtils.spawnParticles(p.getLocation(), pl, "flame", 80);
			} catch (ClassNotFoundException | IllegalAccessException
					| InstantiationException | NoSuchMethodException
					| NoSuchFieldException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}

		plugin.scoreboard(p);

		p.setHealth(20D);
		p.teleport(plugin.arenaSpawns.get(1));
	}
}