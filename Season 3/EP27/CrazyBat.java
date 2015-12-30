package example;

import net.minecraft.server.v1_7_R4.EntityBat;
import net.minecraft.server.v1_7_R4.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftBat;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.entity.Bat;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.util.Vector;

public class CrazyBat extends EntityBat {

    public CrazyBat(World world) {
        super(world);
    }

    @Override
    public String t() {
        return "";
    }

    @Override
    public void e() {

    }

    @Override
    public void move(double d0, double d1, double d2) {

    }

    @Override
    public void g(double x, double y, double z) {
        Vector vector = this.getBukkitEntity().getVelocity();
        super.g(vector.getX(), vector.getY(), vector.getZ());
    }

    public static Bat spawn(Location location) {
        World mcWorld = (World) ((CraftWorld) location.getWorld()).getHandle();
        final CrazyBat customEntity = new CrazyBat(mcWorld);
        customEntity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        ((CraftLivingEntity) customEntity.getBukkitEntity()).setRemoveWhenFarAway(false);
        mcWorld.addEntity(customEntity, SpawnReason.CUSTOM);
        return (CraftBat) customEntity.getBukkitEntity();
    }

}