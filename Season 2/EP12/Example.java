package example;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    private String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    @EventHandler
    public void onSign(SignChangeEvent e) {
        e.setLine(0, colorize(e.getLine(0)));
        e.setLine(1, colorize(e.getLine(1)));
        e.setLine(2, colorize(e.getLine(2)));
        e.setLine(3, colorize(e.getLine(3)));
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (event.getLine(0).equals("Test")) {
            event.setLine(0, "Line 1");
            event.setLine(1, ChatColor.GREEN + "Line 2");
        } else {
            event.setCancelled(true);
            event.getBlock().breakNaturally();
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && block != null && (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
            Sign sign = (Sign) block.getState();
            if (sign.getLine(0).equals("Line 1") && sign.getLine(1).equals(ChatColor.GREEN + "Line 2")) {
                event.getPlayer().sendMessage(ChatColor.RED + "That's a colored sign!");
            }
        }
    }
    
}