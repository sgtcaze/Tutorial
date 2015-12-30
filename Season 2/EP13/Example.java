package example;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
        if (!(sender instanceof Player)) {
            return false;
        }
		
        if (cmd.getName().equalsIgnoreCase("test")) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            player.playSound(location, Sound.GHAST_SCREAM, 1, 0);
            location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 2004);
        }
        return false;
    }

}