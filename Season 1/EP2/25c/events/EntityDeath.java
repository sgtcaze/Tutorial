package example.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import example.Example;

public class EntityDeath implements Listener {

	private Example plugin;

	public EntityDeath(Example plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player victim = (Player) e.getEntity();
			if (victim.getKiller() instanceof Player) {
				Player killer = (Player) victim.getKiller();

				plugin.api.addDeaths(victim, 1);
				plugin.api.addKills(killer, 1);
				plugin.api.addPoints(killer, 5);

				plugin.scoreboard(killer);
				plugin.scoreboard(victim);

				if (!plugin.gameEnded) {
					if (plugin.api.getKills(killer) >= 5) {
						plugin.gm.endGame();
					}
				}
			}
		}
	}
}