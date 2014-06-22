package example.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import example.Example;

public class SetSpawn implements CommandExecutor {

	private Example plugin;

	public SetSpawn(Example plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] a) {

		if (!sender.hasPermission("game.spawn")) {
			sender.sendMessage("§cNo permission!");
			return false;
		}

		if (!(sender instanceof Player)) {
			sender.sendMessage("§cOnly ingame players can use this command!");
			return false;
		}

		if (a.length < 1) {
			sender.sendMessage("§c/setspawn <arenaname>");
		} else if (a.length == 1) {
			plugin.spawnHandler.setSpawn((Player) sender, a[0]);
		}

		return false;
	}
}