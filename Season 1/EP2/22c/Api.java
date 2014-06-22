package example;

import java.util.UUID;

import org.bukkit.entity.Player;

public class Api {

	private Example plugin;
	
	public Api(Example plugin){
		this.plugin = plugin;
	}

	public void giveSilver(Player p, int i) {
		UUID uuid = p.getUniqueId();
		plugin.money.put(uuid, plugin.money.get(uuid) +i);
		p.sendMessage("§2§l$" + i + " silver received!");
	}

	public void takeSilver(Player p, int i) {
		UUID uuid = p.getUniqueId();
		plugin.money.put(uuid, plugin.money.get(uuid) -i);
		p.sendMessage("§c§l$" + i + " silver taken!");
	}

	public boolean hasEnough(Player p, int i) {
		if (plugin.config.getInt(p.getName() + ".Silver") >= i)
			return true;
		return false;
	}
}