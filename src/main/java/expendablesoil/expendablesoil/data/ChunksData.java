package expendablesoil.expendablesoil.data;

import org.bukkit.Chunk;

import java.util.HashMap;
import java.util.Map;

public class ChunksData {
    public ChunksData() {
        ChunkData = new HashMap<>();
    }

    public Map<Chunk, Integer> ChunkData;
}
