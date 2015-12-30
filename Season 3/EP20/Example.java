package example;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

public class Example extends JavaPlugin implements Listener {

    private FileConfiguration config;
    private HashMap<UUID, Integer> money = new HashMap<>();

    public void onEnable() {
        config = getConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        for (Entry<UUID, Integer> entry : money.entrySet()) {
            config.set(entry.getKey() + ".Silver", entry.getValue());
        }

        saveConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (config.contains(uuid.toString())) {
            money.put(uuid, getConfig().getInt(uuid + ".Silver"));
        } else {
            config.set(uuid + ".Silver", 0);
            money.put(uuid, 0);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            if (event.getEntity() instanceof Monster) {
                giveSilver(event.getEntity().getKiller(), 200);
            } else if (event.getEntity() instanceof Villager) {
                takeSilver(event.getEntity().getKiller(), 200);
            }
        }
    }

    private void giveSilver(Player player, int money) {
        UUID uuid = player.getUniqueId();
        this.money.put(uuid, this.money.get(uuid) + money);
        player.sendMessage("$" + money + " silver received!");
    }

    private void takeSilver(Player player, int money) {
        UUID uuid = player.getUniqueId();
        this.money.put(uuid, this.money.get(uuid) - money);
        player.sendMessage("$" + money + " silver taken!");
    }

}