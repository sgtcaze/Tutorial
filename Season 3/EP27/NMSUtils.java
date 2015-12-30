package example;

import net.minecraft.server.v1_7_R4.BiomeBase;
import net.minecraft.server.v1_7_R4.BiomeMeta;
import net.minecraft.server.v1_7_R4.EntityInsentient;
import net.minecraft.server.v1_7_R4.EntityTypes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author @BigTeddy98
 * Used for tutorial purposes
 * https://forums.bukkit.org/threads/tutorial-register-your-custom-entities-nms-reflection.258542/
 */

public class NMSUtils {

    public void registerEntity(String name, int id, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass) {
        try {
 
            /*
            * First, we make a list of all HashMap's in the EntityTypes class
            * by looping through all fields. I am using reflection here so we
            * have no problems later when minecraft changes the field's name.
            * By creating a list of these maps we can easily modify them later
            * on.
            */
            List<Map<?, ?>> dataMaps = new ArrayList<Map<?, ?>>();
            for (Field f : EntityTypes.class.getDeclaredFields()) {
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())) {
                    f.setAccessible(true);
                    dataMaps.add((Map<?, ?>) f.get(null));
                }
            }
 
            /*
            * since minecraft checks if an id has already been registered, we
            * have to remove the old entity class before we can register our
            * custom one
            *
            * map 0 is the map with names and map 2 is the map with ids
            */
            if (dataMaps.get(2).containsKey(id)) {
                dataMaps.get(0).remove(name);
                dataMaps.get(2).remove(id);
            }
 
            /*
            * now we call the method which adds the entity to the lists in the
            * EntityTypes class, now we are actually 'registering' our entity
            */
            Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
            method.setAccessible(true);
            method.invoke(null, customClass, name, id);
 
            /*
            * after doing the basic registering stuff , we have to register our
            * mob as to be the default for every biome. This can be done by
            * looping through all BiomeBase fields in the BiomeBase class, so
            * we can loop though all available biomes afterwards. Here, again,
            * I am using reflection so we have no problems later when minecraft
            * changes the fields name
            */
            for (Field f : BiomeBase.class.getDeclaredFields()) {
                if (f.getType().getSimpleName().equals(BiomeBase.class.getSimpleName())) {
                    if (f.get(null) != null) {
 
                        /*
                        * this peace of code is being called for every biome,
                        * we are going to loop through all fields in the
                        * BiomeBase class so we can detect which of them are
                        * Lists (again, to prevent problems when the field's
                        * name changes), by doing this we can easily get the 4
                        * required lists without using the name (which probably
                        * changes every version)
                        */
                        for (Field list : BiomeBase.class.getDeclaredFields()) {
                            if (list.getType().getSimpleName().equals(List.class.getSimpleName())) {
                                list.setAccessible(true);
                                @SuppressWarnings("unchecked")
                                List<BiomeMeta> metaList = (List<BiomeMeta>) list.get(f.get(null));
 
                                /*
                                * now we are almost done. This peace of code
                                * we're in now is called for every biome. Loop
                                * though the list with BiomeMeta, if the
                                * BiomeMeta's entity is the one you want to
                                * change (for example if EntitySkeleton matches
                                * EntitySkeleton) we will change it to our
                                * custom entity class
                                */
                                for (BiomeMeta meta : metaList) {
                                    Field clazz = BiomeMeta.class.getDeclaredFields()[0];
                                    if (clazz.get(meta).equals(nmsClass)) {
                                        clazz.set(meta, customClass);
                                    }
                                }
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}