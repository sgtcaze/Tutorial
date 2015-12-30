package example;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Example extends JavaPlugin implements Listener {

    private HashMap<String, Integer> money = new HashMap<>();
    private HashMap<String, String> message = new HashMap<>();
    private HashMap<String, ItemStack> item = new HashMap<>();

    public void demo() {
        money.put("sgtcazeyt", 35);
        message.put("sgtcazeyt", "This is an example string");
        item.put("sgtcazeyt", new ItemStack(Material.APPLE));

        String exampleEco = "Your money is " + money.get("sgtcazeyt");
        String exampleMsg = "Your message is " + message.get("sgtcazeyt");
        String exampleItem = "The stored stack (material) is " + item.get("sgtcazeyt").getType().name();
    }

}