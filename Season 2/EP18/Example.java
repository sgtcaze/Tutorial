package example;

import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Example extends JavaPlugin implements Listener {

    private final Random RANDOM = new Random();

    private List<String> list = new ArrayList<>();

    public void onEnable() {
        for (ChatColor chatColor : ChatColor.values()) {
            list.add(chatColor + "Much Custom. So Tutorial. Wow.");
        }

        showBarChanging();
    }

    public void showBarChanging() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                String message = list.get(RANDOM.nextInt(list.size()));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    BarAPI.setMessage(online, message);
                }
            }
        }, 0, 20);
    }

}