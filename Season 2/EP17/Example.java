package example;

import net.minecraft.server.v1_7_R4.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void exampleJson(Player player) {
        IChatBaseComponent comp = ChatSerializer.a("{\"text\":\"Welcome to my server! \",\"extra\":[{\"text\":\"§bClick Here\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§cThis §dIs §aSo §bCool!\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/list\"}}]}");
        PacketPlayOutChat packet = new PacketPlayOutChat(comp, true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        exampleJson(event.getPlayer());
    }

}