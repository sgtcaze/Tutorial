package example;

import net.minecraft.server.v1_7_R4.EntityBat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftBat;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Example extends JavaPlugin {

    public void onEnable() {
        new NMSUtils().registerEntity("Bat", 65, EntityBat.class, CrazyBat.class);

        for (Entity entity : Bukkit.getWorld("world").getEntities()) {
            if (!(entity instanceof Player)) {
                entity.remove();
            }
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
        if (cmd.getName().equalsIgnoreCase("test")) {
            Bat bat = CrazyBat.spawn(yourLocation);
            bat.setPassenger(Bukkit.getWorld("world").dropItem(bat.getLocation(), new ItemStack(Material.SKULL_ITEM, 1, (short) 3)));
            bat.setCustomName("Hacks");
            bat.setCustomNameVisible(false);

            new BukkitRunnable() {
                @Override
                public void run() {
                    ((CraftBat) bat).getHandle().setInvisible(true);
                }
            }.runTaskLater(this, 3);
        }
        return false;
    }

}