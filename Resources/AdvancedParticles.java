import net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
 
public class AdvancedParticles extends JavaPlugin {
 
    /**
     * Example Functions:
     * <p/>
     * y = mx + b
     * y = x^2
     * y = x^3
     * x = 3*sqrt(y)
     */
 
    @Override
    public void onEnable() {
 
    }
 
    public void createParticle(String particle, Location loc, int x, int y, int z) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, (float) loc.getX() + x, (float) loc.getY() + y, (float) loc.getZ() + z, 0, 0, 0, 1, 15);
 
        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }
 
    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
        if (!(s instanceof Player)) {
            return false;
        }
 
        if (l.equalsIgnoreCase("test")) {
            Player player = (Player) s;
 
            for (double y = 0; y <= 30; y += 0.01) {
                double adjustedX = 3 * Math.cos(y);
                double adjustedZ = 3 * Math.sin(y);
 
                createParticle("heart", player.getLocation(), (int) adjustedX, (int) y, (int) adjustedZ);
            }
        }
        return false;
    }
}
