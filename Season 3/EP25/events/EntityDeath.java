package example.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import example.Example;

public class EntityDeath implements Listener {

	private Example plugin = Example.getInstance();

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player victim = (Player) e.getEntity();
			if (victim.getKiller() instanceof Player) {
				Player killer = (Player) victim.getKiller();

				plugin.getApi().addDeaths(victim, 1);
				plugin.getApi().addKills(killer, 1);
				plugin.getApi().addPoints(killer, 5);

				plugin.getApi().scoreboard(killer);
				plugin.getApi().scoreboard(victim);

				if (GameState.state != GameState.ENDED) {
					if (plugin.getApi().getKills(killer) >= 5) {
						plugin.getGameManager().endGame();
					}
				}
			}
		}
	}
}