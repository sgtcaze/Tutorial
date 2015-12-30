package example;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

public class Example extends JavaPlugin implements Listener {

    private Team team;
    private Scoreboard board;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        makeScoreboard();
    }

    public void makeScoreboard() {
        board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = board.registerNewObjective("Test", "Test2");
        objective.setDisplayName(ChatColor.AQUA + "Statistics");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        team = board.registerNewTeam("Team");
        team.setPrefix(ChatColor.RED + "[Test] ");

        Score score = objective.getScore(ChatColor.GOLD + "Online Players");
        score.setScore(Bukkit.getOnlinePlayers().size());

        Score score2 = objective.getScore(ChatColor.GOLD + "MS Limit");
        score2.setScore(Bukkit.getMonsterSpawnLimit());

        Score score3 = objective.getScore(ChatColor.GOLD + "Max Players");
        score3.setScore(Bukkit.getMaxPlayers());

        Score score4 = objective.getScore(ChatColor.GOLD + "View Distance");
        score4.setScore(Bukkit.getViewDistance());
    }

    @EventHandler
    public void onjoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        team.addPlayer(player);
        player.setScoreboard(board);
    }

}