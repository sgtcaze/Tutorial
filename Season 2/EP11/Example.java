package example;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Example extends JavaPlugin implements Listener {
	
	Scoreboard board;
	Team team;

	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		
		makeScoreboard();
	}
	
	@SuppressWarnings("deprecation")
	public void makeScoreboard(){
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
		
		Objective objective = board.registerNewObjective("Test", "Test2");
		objective.setDisplayName(ChatColor.AQUA + "Statistics");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		team = board.registerNewTeam("Team");
		team.setPrefix(ChatColor.RED + "");

		Score score = objective.getScore(ChatColor.GOLD + "Online Players");
		score.setScore(Bukkit.getOnlinePlayers().length);
		
		Score score2 = objective.getScore(ChatColor.GOLD + "MS Limit");
		score2.setScore(Bukkit.getMonsterSpawnLimit());
		
		Score score3 = objective.getScore(ChatColor.GOLD + "Max Players");
		score3.setScore(Bukkit.getMaxPlayers());
		
		Score score4 = objective.getScore(ChatColor.GOLD + "View Distance");
		score4.setScore(Bukkit.getViewDistance());
	}
	
	@EventHandler
	public void onjoin(PlayerJoinEvent e){
		team.addPlayer(e.getPlayer());
		e.getPlayer().setScoreboard(board);
	}	
}