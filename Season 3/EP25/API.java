package example;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class API {

	private Example plugin;

	public API(Example plugin) {
		this.plugin = plugin;
	}

	public void setSpectator(Player p) {

		plugin.players.remove(p.getName());

		p.setAllowFlight(true);
		p.setFlying(true);

		for (Player pl : Bukkit.getOnlinePlayers()) {
			pl.hidePlayer(p);
		}

		p.sendMessage("§aYou are now a spectator!");
	}

	public void addPoints(Player p, int i) {
		plugin.points.put(p.getName(), plugin.points.get(p.getName()) + i);
	}

	public void removePoints(Player p, int i) {
		plugin.points.put(p.getName(), plugin.points.get(p.getName()) - i);
	}

	public void addKills(Player p, int i) {
		plugin.kills.put(p.getName(), plugin.kills.get(p.getName()) + i);
	}

	public void addDeaths(Player p, int i) {
		plugin.deaths.put(p.getName(), plugin.deaths.get(p.getName()) + i);
	}

	public int getKills(Player p) {
		return plugin.kills.get(p.getName());
	}

	public int getDeaths(Player p) {
		return plugin.deaths.get(p.getName());
	}

	public int getPoints(Player p) {
		return plugin.points.get(p.getName());
	}

	public void firework(Player p) {
		Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(),
				EntityType.FIREWORK);
		FireworkMeta fwmeta = fw.getFireworkMeta();
		FireworkEffect.Builder builder = FireworkEffect.builder();
		builder.withTrail().withFlicker().withFade(Color.GREEN)
				.withColor(Color.WHITE).withColor(Color.YELLOW)
				.with(FireworkEffect.Type.BALL_LARGE);
		fwmeta.addEffect(builder.build());
		fwmeta.setPower(1);
		fw.setFireworkMeta(fwmeta);
	}
}