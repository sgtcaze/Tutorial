package example;

import io.netty.channel.*;
import net.minecraft.server.v1_9_R1.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PacketAccessor extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    /**
     * Inject our custom ChannelDuplexHandler when the player joins the server
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        create(event.getPlayer());
    }

    /**
     * Remove our custom ChannelDuplexHandler explicitly, NOTE: If they're quitting
     * the server this data is cleared anyway.
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        remove(event.getPlayer());
    }

    /**
     * Submits a request to the pipeline to remove our ChannelDuplexHandler
     */
    private void remove(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });
    }

    /**
     * Creates a custom ChannelDuplexHandler for a player, and this serves
     * to intercept packets before the packet handler
     */
    private void create(Player player) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            /**
             * Reads packets
             */
            @Override
            public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
//                getLogger().info("READ>> " + packet.toString());
                super.channelRead(context, packet);
            }

            /**
             * Writes packets
             */
            @Override
            public void write(ChannelHandlerContext context, Object packet, ChannelPromise channelPromise) throws Exception {
                /**
                 * If the packet is instance "PacketPlayOutPlayerInfo", we can cast this directly!
                 */
                if (packet instanceof PacketPlayOutPlayerInfo) {
                    /**
                     * We can use reflection to access certain variables as they are
                     * encapsulated
                     */
                    PacketPlayOutPlayerInfo info = (PacketPlayOutPlayerInfo) packet;
                    getLogger().info("BLOCKED>> " + info.toString());
                    return;
                }
                super.write(context, packet, channelPromise);
            }
        };

        /**
         * Take the player's networking channel and add our custom DuplexHandler
         */
        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
    }

}
