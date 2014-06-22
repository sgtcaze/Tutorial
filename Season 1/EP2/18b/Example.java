package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {

	Random r = new Random();

	List<String> list = new ArrayList<>();

	public void onEnable() {		
		list.add("§0Much Custom. So Tutorial. Wow.");
        list.add("§1Much Custom. So Tutorial. Wow.");
        list.add("§2Much Custom. So Tutorial. Wow.");
        list.add("§3Much Custom. So Tutorial. Wow.");
        list.add("§4Much Custom. So Tutorial. Wow.");
        list.add("§5Much Custom. So Tutorial. Wow.");
        list.add("§6Much Custom. So Tutorial. Wow.");
        list.add("§7Much Custom. So Tutorial. Wow.");
        list.add("§8Much Custom. So Tutorial. Wow.");
        list.add("§9Much Custom. So Tutorial. Wow.");
        list.add("§aMuch Custom. So Tutorial. Wow.");
        list.add("§bMuch Custom. So Tutorial. Wow.");
        list.add("§cMuch Custom. So Tutorial. Wow.");
        list.add("§dMuch Custom. So Tutorial. Wow.");
		list.add("§eMuch Custom. So Tutorial. Wow.");
		list.add("§fMuch Custom. So Tutorial. Wow.");
		
		showBarChanging();
	}

	public void showBarChanging(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run(){
			
				String message = (String) list.get(r.nextInt(list.size()));
				
				for(Player p : Bukkit.getOnlinePlayers()){
					BarAPI.setMessage(p, message);
				}
            }		
		}, 0, 1 * 20);
	}
}