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

	private Example plugin = Example.getInstance();

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();

		plugin.getApi().addDeaths(p, 1);

		if (plugin.getApi().getDeaths(p) == 5) {
			plugin.getApi().setSpectator(p);
		}
		
		plugin.getApi().scoreboard(p);

		p.setHealth(20D);
		p.teleport(plugin.getGameManager().getSpawns().get(1));
	}
}