package example;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.kumpelblase2.remoteentities.EntityManager;
import de.kumpelblase2.remoteentities.RemoteEntities;
import de.kumpelblase2.remoteentities.api.RemoteEntity;
import de.kumpelblase2.remoteentities.api.RemoteEntityType;

public class Example extends JavaPlugin implements Listener {

	public void onEnable() {
		
		getServer().getPluginManager().registerEvents(this, this);
		
		for(Entity e : Bukkit.getWorld("world").getEntities()){
			if(e instanceof Villager){
				e.remove();
			}
		}
		
		ourEntities();
	}
	
	public void ourEntities(){
		Location loc = new Location(Bukkit.getWorld("world"), 294.5, 64, 281.5);
		
		EntityManager manager = RemoteEntities.createManager(this);
		RemoteEntity entity = manager.createEntity(RemoteEntityType.Villager, loc, false);	
		entity.setStationary(true);
		entity.setPushable(false);
		entity.getBukkitEntity().setCustomName("§d§lTutorial");
		entity.getBukkitEntity().setCustomNameVisible(true);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		e.setCancelled(true);
	}
}