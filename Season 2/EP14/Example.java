package example;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {

	private void spawnCow(Location loc) {
		Cow cow = (Cow) loc.getWorld().spawn(loc, Cow.class);
		cow.setBaby();
		cow.setAgeLock(true);
		cow.setCustomName(ChatColor.GREEN + "Baby Cow");
		cow.setCustomNameVisible(true);
	}

	private void spawnVillager(Location loc) {
		Villager v = (Villager) loc.getWorld().spawn(loc, Villager.class);
		v.setCustomName(ChatColor.GREEN + "Tom");
		v.setCustomNameVisible(true);
		v.setBaby();
		v.setAgeLock(true);
		v.setProfession(Profession.LIBRARIAN);
	}

	private void spawnSkeleton(Location loc) {
		Skeleton s = (Skeleton) loc.getWorld().spawn(loc, Skeleton.class);
		s.setCustomName(ChatColor.AQUA + "9001");
		s.setCustomNameVisible(true);
		s.setSkeletonType(SkeletonType.WITHER);
		s.getEquipment().setItemInHand(new ItemStack(Material.BOAT));
		s.getEquipment().setHelmet(new ItemStack(Material.CACTUS));
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {

		if (!(sender instanceof Player)) {
			return false;
		}

		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("test")) {
			Location loc = player.getLocation();

			// Your method
		}
		return false;
	}
}