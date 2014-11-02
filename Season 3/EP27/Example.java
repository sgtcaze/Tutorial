package example;

import net.minecraft.server.v1_7_R4.EntityBat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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

	public void onEnable(){		
		new NMSUtils().registerEntity("Bat", 65, EntityBat.class, CrazyBat.class);
		
		for(Entity e : Bukkit.getWorld("world").getEntities()){
			if(!(e instanceof Player)){
				e.remove();
			}
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a){
		if(cmd.getName().equalsIgnoreCase("test")){			
			final Bat b = CrazyBat.spawn(yourLocation);
			b.setPassenger(Bukkit.getWorld("world").dropItem(b.getLocation(), new ItemStack(Material.SKULL_ITEM, 1, (short) 3)));
			b.setCustomName("Hacks");
			b.setCustomNameVisible(false);
									
			new BukkitRunnable(){
				@Override
				public void run() {
					((CraftBat)b).getHandle().setInvisible(true);
				}		
			}.runTaskLater(this, 3);
		}
		return false;
	}
}