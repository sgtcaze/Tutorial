package example;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin {

    private void firework(Player player) {
        Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
        FireworkMeta meta = firework.getFireworkMeta();
        FireworkEffect.Builder builder = FireworkEffect.builder();
        builder.withTrail().withFlicker().withFade(Color.GREEN, Color.WHITE, Color.YELLOW, Color.BLUE,
                Color.FUCHSIA, Color.PURPLE, Color.MAROON, Color.LIME, Color.ORANGE)
                .with(FireworkEffect.Type.BALL_LARGE);
        meta.addEffect(builder.build());
        meta.setPower(1);
        firework.setFireworkMeta(meta);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("test")) {
            firework((Player) sender);
        }
        return false;
    }

}