package example;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {

	private HashMap<String, Integer> money = new HashMap<>();
	private HashMap<String, String> message = new HashMap<>();
	private HashMap<String, ItemStack> item = new HashMap<>();

	public void onEnable() {
		money.put("sgtcazeyt", 35);
		message.put("sgtcazeyt", "This is an example string");
		item.put("sgtcazeyt", new ItemStack(Material.APPLE));
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {

		if (!(sender instanceof Player)) {
			return false;
		}

		Player player = (Player) sender;
		String name = player.getName();

		if (cmd.getName().equalsIgnoreCase("money")) {
			player.sendMessage("§aYour money is§f " + money.get(name));
		} else if (cmd.getName().equalsIgnoreCase("message")) {
			player.sendMessage("§cThe message is§f " + message.get(name));
		} else if (cmd.getName().equalsIgnoreCase("item")) {
			player.sendMessage("§bHere is the item in the map!");
			player.getInventory().addItem(item.get(name));
		} else if (cmd.getName().equalsIgnoreCase("add")) {
			player.sendMessage("§dAdded +5 to your money!");
			money.put(player.getName(), money.get(name) + 5);
		}
		return false;
	}
}