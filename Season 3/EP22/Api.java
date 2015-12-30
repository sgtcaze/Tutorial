package example;

import org.bukkit.entity.Player;

import java.util.UUID;

public class Api {

    private Example plugin;

    public Api(Example plugin) {
        this.plugin = plugin;
    }

    public void giveSilver(Player player, int i) {
        UUID uuid = player.getUniqueId();
        plugin.getMoney().put(uuid, plugin.getMoney().get(uuid) + i);
        player.sendMessage("$" + i + " silver received!");
    }

    public void takeSilver(Player player, int i) {
        UUID uuid = player.getUniqueId();
        plugin.getMoney().put(uuid, plugin.getMoney().get(uuid) - i);
        player.sendMessage("$" + i + " silver taken!");
    }

    public boolean hasEnough(Player player, int i) {
        return plugin.getMoney().get(player.getUniqueId()) >= i;
    }

}