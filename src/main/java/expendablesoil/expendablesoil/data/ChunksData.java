package expendablesoil.expendablesoil.data;

import org.bukkit.block.Biome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChunksData {
    public ChunksData() {
        ChunkData  = new HashMap<>();
        DiedChunks = new ArrayList<>();
    }

    public static final HashMap<Biome, Integer> BiomeHealthPoints = new HashMap<>();
    static {
        BiomeHealthPoints.put(Biome.DESERT, 100);
    }

    /**
     * Long is a coordinates of chunk
     * Integer is HP of chunk
     */
    public Map<Long, Integer> ChunkData;

    /**
     * List of died chunks
     */
    public List<Long> DiedChunks;
}
