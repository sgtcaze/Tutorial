package example;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Listeners implements Listener {

    private Example plugin;

    public Listeners(Example plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if (!plugin.getConfig().contains(uuid.toString())) {
            plugin.getMoney().put(uuid, 0);
        } else {
            plugin.getMoney().put(uuid, plugin.getConfig().getInt(event.getPlayer().getUniqueId() + ".Silver"));
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            if (event.getEntity() instanceof Monster) {
                plugin.getApi().giveSilver(event.getEntity().getKiller(), 200);
            } else if (event.getEntity() instanceof Villager) {
                plugin.getApi().takeSilver(event.getEntity().getKiller(), 200);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (event.getInventory().getName().equals("My Custom Shop")) {
            event.setCancelled(true);
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                if (item.getItemMeta().getDisplayName().endsWith("Apple")) {
                    Player player = (Player) event.getWhoClicked();
                    if (plugin.getApi().hasEnough(player, 25)) {
                        plugin.getApi().takeSilver(player, 25);
                    } else {
                        player.sendMessage("You're too poor!");
                    }
                }
            }
        }
    }

}