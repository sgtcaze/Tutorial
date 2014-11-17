package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {

	private Random r = new Random();

	private List<String> list = new ArrayList<>();

	public void onEnable() {
		list.add(colorize("&0Much Custom. So Tutorial. Wow."));
		list.add(colorize("&1Much Custom. So Tutorial. Wow."));
		list.add(colorize("&2Much Custom. So Tutorial. Wow."));
		list.add(colorize("&3Much Custom. So Tutorial. Wow."));
		list.add(colorize("&4Much Custom. So Tutorial. Wow."));
		list.add(colorize("&5Much Custom. So Tutorial. Wow."));
		list.add(colorize("&6Much Custom. So Tutorial. Wow."));
		list.add(colorize("&7Much Custom. So Tutorial. Wow."));
		list.add(colorize("&8Much Custom. So Tutorial. Wow."));
		list.add(colorize("&9Much Custom. So Tutorial. Wow."));
		list.add(colorize("&aMuch Custom. So Tutorial. Wow."));
		list.add(colorize("&bMuch Custom. So Tutorial. Wow."));
		list.add(colorize("&cMuch Custom. So Tutorial. Wow."));
		list.add(colorize("&dMuch Custom. So Tutorial. Wow."));
		list.add(colorize("&eMuch Custom. So Tutorial. Wow."));
		list.add(colorize("&fMuch Custom. So Tutorial. Wow."));

		showBarChanging();
	}

	private String colorize(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}

	public void showBarChanging() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				String message = (String) list.get(r.nextInt(list.size()));

				for (Player p : Bukkit.getOnlinePlayers()) {
					BarAPI.setMessage(p, message);
				}
			}
		}, 0, 1 * 20);
	}
}