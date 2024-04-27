package expendablesoil.expendablesoil.data;

import org.bukkit.block.Biome;
import java.util.HashMap;


public class ChunksData {
    public static final HashMap<Biome, Integer> BiomeHealthPoints = new HashMap<>();
    static {
        BiomeHealthPoints.put(Biome.DESERT, 100); // TODO: Complete list
    }
}
