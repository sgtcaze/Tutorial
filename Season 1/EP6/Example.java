package example;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Example extends JavaPlugin {

    private ItemStack book;

    public void onEnable() {
        book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta) book.getItemMeta();
        bm.addPage("1", "2");
        bm.setPage(1, ChatColor.RED + "Page 1 epicness");
        bm.setPage(2, ChatColor.GREEN + "Line 1" + "\n" + "Line 2" + "\n" + "\n" + "Line 3");
        bm.setAuthor(ChatColor.GREEN + "Your Name");
        bm.setDisplayName(ChatColor.RED + "Tutorial");
        book.setItemMeta(bm);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("test")) {
            ((Player) sender).getInventory().addItem(book);
        }
        return false;
    }

}