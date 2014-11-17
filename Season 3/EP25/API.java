package example;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class API {

	private Example plugin = Example.getInstance();

	public void addPoints(Player p, int i) {
	    UUID uuid = p.getUniqueId();
		plugin.getGameManager().getPoints().put(uuid, plugin.getGameManager().getPoints().get(uuid) + i);
	}

	public void removePoints(Player p, int i) {
	    UUID uuid = p.getUniqueId();
		plugin.getGameManager().getPoints().put(uuid, plugin.getGameManager().getPoints().get(uuid) - i);
	}

	public void addKills(Player p, int i) {
	    UUID uuid = p.getUniqueId();
		plugin.getGameManager().getKills().put(uuid, plugin.getGameManager().getKills().get(uuid) + i);
	}

	public void addDeaths(Player p, int i) {
	    UUID uuid = p.getUniqueId();
		plugin.getGameManager().getDeaths().put(uuid, plugin.getGameManager().getDeaths().get(uuid) + i);
	}

	public int getKills(Player p) {
		return plugin.getGameManager().getKills().get(p.getUniqueId());
	}

	public int getDeaths(Player p) {
		return plugin.getGameManager().getDeaths().get(p.getUniqueId());
	}

	public int getPoints(Player p) {
		return plugin.getGameManager().getPoints().get(p.getUniqueId());
	}	
	
	public void setSpectator(Player p) {
		plugin.getGameManager().getPlayers().remove(p.getUniqueId());
        plugin.getGameManager().getSpectators().add(p.getUniqueId());
		
		p.setAllowFlight(true);
		p.setFlying(true);

		for (Player pl : Bukkit.getOnlinePlayers()) {
			pl.hidePlayer(p);
		}

		p.sendMessage("&aYou are now a spectator!");
	}

	public void firework(Player p) {
		Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
		FireworkMeta fwmeta = fw.getFireworkMeta();
		FireworkEffect.Builder builder = FireworkEffect.builder();
		builder.withTrail().withFlicker().withFade(Color.GREEN).withColor(Color.WHITE).withColor(Color.YELLOW).with(FireworkEffect.Type.BALL_LARGE);
		fwmeta.addEffect(builder.build());
		fwmeta.setPower(1);
		fw.setFireworkMeta(fwmeta);
	}
	
	public void scoreboard(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

		Objective objective = board.registerNewObjective("Test", "Test2");
		objective.setDisplayName("&f&lPVP");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		Score score = objective.getScore("&6Kills");
		score.setScore(getKills(p));

		Score score2 = objective.getScore("&6Deaths");
		score2.setScore(getDeaths(p));

		Score score3 = objective.getScore("&6Points");
		score3.setScore(getPoints(p));

		p.setScoreboard(board);
	}
}