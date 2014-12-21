package me.YourName.tutorial.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ParticleUtils {

    private static String version = "";

    private static Object packet;

    private static Method getHandle;
    private static Method sendPacket;
    private static Field playerConnection;
    private static Method valueOf;

    private static Class<?> packetType;

    static {
        try {
            version = Bukkit.getServer().getClass().getPackage().getName()
                    .split("\\.")[3];

            packetType = Class.forName(getPacketPlayOutParticles());

            Class<?> typeEnumParticle = Class.forName(getEnumParticleClasspath());

            valueOf = typeEnumParticle.getMethod("valueOf", String.class);

            Class<?> typeCraftPlayer = Class.forName(getCraftPlayerClasspath());
            Class<?> typeNMSPlayer = Class.forName(getNMSPlayerClasspath());
            Class<?> typePlayerConnection = Class
                    .forName(getPlayerConnectionClasspath());
            getHandle = typeCraftPlayer.getMethod("getHandle");
            playerConnection = typeNMSPlayer.getField("playerConnection");
            sendPacket = typePlayerConnection.getMethod("sendPacket",
                    Class.forName(getPacketClasspath()));
        } catch (ClassNotFoundException | NoSuchMethodException |
                SecurityException | NoSuchFieldException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Error {0}", ex);
        }
    }

    private static void setField(String field, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        Field f = packet.getClass().getDeclaredField(field);
        f.setAccessible(true);
        f.set(packet, value);
    }

    // Particle Effects 
    public static void spawnParticles(Location loc, Player receivingPacket, int amount, String... packetName) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException,
            NoSuchMethodException, NoSuchFieldException,
            IllegalArgumentException, InvocationTargetException {

        packet = packetType.newInstance();

        if (Integer.valueOf(version.split("_")[1]) > 7
                && Integer.valueOf(version.toLowerCase().split("_")[0].replace("v", "")) == 1) {
            setField("a", valueOf.invoke(null, packetName[1])); //packetName[1] is the name of the packet as defined in EnumParticle (it is case sensitive)
            setField("b", loc.getBlockX());
            setField("c", loc.getBlockY());
            setField("d", loc.getBlockZ());
            setField("e", 1);
            setField("f", 1);
            setField("g", 1);
            setField("h", 0);
            setField("i", amount);
            setField("j", true);
        } else {
            setField("a", packetName[0]); // Particle name
            setField("b", loc.getBlockX()); // Block X
            setField("c", loc.getBlockY()); // Block Y
            setField("d", loc.getBlockZ()); // Block Z
            setField("e", 1); // Random X Offset
            setField("f", 1); // Random Y Offset
            setField("g", 1); // Random Z Offset
            setField("h", 0); // Speed/data of particles
            setField("i", amount); // Amount of particles
        }
        Object player = getHandle.invoke(receivingPacket);

        Object connection = playerConnection.get(player);

        sendPacket.invoke(connection, packet);
    }

    private static String getCraftPlayerClasspath() {
        return "org.bukkit.craftbukkit." + version + ".entity.CraftPlayer";
    }

    private static String getPlayerConnectionClasspath() {
        return "net.minecraft.server." + version + ".PlayerConnection";
    }

    private static String getNMSPlayerClasspath() {
        return "net.minecraft.server." + version + ".EntityPlayer";
    }

    private static String getEnumParticleClasspath() {
        return "net.minecraft.server." + version + ".EnumParticle";
    }

    private static String getPacketClasspath() {
        return "net.minecraft.server." + version + ".Packet";
    }

    private static String getPacketPlayOutParticles() {
        if (Integer.valueOf(version.split("_")[1]) < 7
                && Integer.valueOf(version.toLowerCase().split("_")[0].replace(
                                "v", "")) == 1) {
            return "net.minecraft.server." + version
                    + ".Packet63WorldParticles";
        } else {
            return "net.minecraft.server." + version
                    + ".PacketPlayOutWorldParticles";
        }
    }
}
