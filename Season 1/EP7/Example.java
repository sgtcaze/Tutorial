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

	private void firework(Player player){
        Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwmeta = fw.getFireworkMeta();
        FireworkEffect.Builder builder = FireworkEffect.builder();
        builder.withTrail().withFlicker().withFade(Color.GREEN).withColor(Color.WHITE).withColor(Color.YELLOW)
        .withColor(Color.BLUE).withColor(Color.FUCHSIA).withColor(Color.PURPLE).withColor(Color.MAROON).withColor(Color.LIME)
		.withColor(Color.ORANGE).with(FireworkEffect.Type.BALL_LARGE);
        fwmeta.addEffect(builder.build());
        fwmeta.setPower(1);
        fw.setFireworkMeta(fwmeta);
   }

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a){
	   
	    if(!(sender instanceof Player)) {
		    return false;
		}

		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("test")){
			firework(player);
		}
		return false;
	}
}