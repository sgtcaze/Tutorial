package example;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {

    private void spawnEntity(Location location, String name, Class someEntity) {
        Entity entity = location.getWorld().spawn(location, someEntity);
        entity.setCustomName(name);
        entity.setCustomNameVisible(true);
        if (entity instanceof Ageable) {
            Ageable ageable = (Ageable) entity;
            ageable.setBaby();
            ageable.setAgeLock(true);
        }

        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).getEquipment().setItemInHand(new ItemStack(Material.CACTUS));
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
        if (sender instanceof Player && cmd.getName().equalsIgnoreCase("test")) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            spawnEntity(location, ChatColor.AQUA + "Epic Mob", Cow.class);
        }
        return false;
    }

}