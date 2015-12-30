package example;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityShootBowEvent(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            event.getProjectile().setPassenger(event.getEntity());
        }
    }

    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        event.getRightClicked().setPassenger(event.getPlayer());
    }

    @EventHandler
    public void onBlockPhysicsEvent(BlockPhysicsEvent event) {
        if (event.getBlock().getType() == Material.PORTAL) {
            event.setCancelled(true);
        }
    }

}