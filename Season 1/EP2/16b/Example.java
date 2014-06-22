package example;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void projectiles(ProjectileHitEvent event) {
		Projectile projectile = event.getEntity();

		if (projectile instanceof Snowball) {
			Snowball snowball = (Snowball) projectile;
			snowball.getWorld().createExplosion(snowball.getLocation(), 3F);
		} else if (projectile instanceof Arrow) {
			Arrow arrow = (Arrow) projectile;
			if (arrow.getShooter() instanceof Player) {
				Player player = (Player) arrow.getShooter();
				player.teleport(arrow);
			}
		}
	}
}