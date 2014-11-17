package example;

import java.util.UUID;

import org.bukkit.entity.Player;

import example.Example;

public class Api {

	private Example plugin = Example.getInstance();

	public void giveSilver(Player p, int i) {
		UUID uuid = p.getUniqueId();
		plugin.getMoney().put(uuid, plugin.getMoney().get(uuid) + i);
		p.sendMessage("§2§l$" + i + " silver received!");
	}

	public void takeSilver(Player p, int i) {
		UUID uuid = p.getUniqueId();
		plugin.getMoney().put(uuid, plugin.getMoney().get(uuid) - i);
		p.sendMessage("§c§l$" + i + " silver taken!");
	}

	public boolean hasEnough(Player p, int i) {
		return plugin.getMoney().get(p.getUniqueId()) >= i);
	}
}