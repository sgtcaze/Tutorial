package example;

import de.kumpelblase2.remoteentities.EntityManager;
import de.kumpelblase2.remoteentities.RemoteEntities;
import de.kumpelblase2.remoteentities.api.RemoteEntity;
import de.kumpelblase2.remoteentities.api.RemoteEntityType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        for (Entity entity : Bukkit.getWorld("world").getEntities()) {
            if (entity instanceof Villager) {
                entity.remove();
            }
        }

        spawnEntities();
    }

    private void spawnEntities() {
        Location location = new Location(Bukkit.getWorld("world"), 294.5, 64, 281.5);
        EntityManager manager = RemoteEntities.createManager(this);
        RemoteEntity entity = manager.createEntity(RemoteEntityType.Villager, location, false);
        entity.setStationary(true);
        entity.setPushable(false);
        entity.getBukkitEntity().setCustomName("§d§lTutorial");
        entity.getBukkitEntity().setCustomNameVisible(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

}