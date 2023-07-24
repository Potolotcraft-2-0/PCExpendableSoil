package expendablesoil.expendablesoil.data;

import com.google.gson.GsonBuilder;

import org.bukkit.block.Biome;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public Map<Long, Integer> ChunkData;

    public List<Long> DiedChunks;

    /**
     * Saves bank into .json file
     * @param fileName File name
     * @throws IOException If something goes wrong
     */
    public void saveChunksData(String fileName) throws IOException {
        var writer = new FileWriter(fileName + ".json", false);
        new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create()
                .toJson(this, writer);
        writer.close();
    }

    /**
     * Loads chunks data from .json
     * @param fileName File name (without format)
     * @return Borrowers manager object
     * @throws IOException If something goes wrong
     */
    public static ChunksData loadChunksData(String fileName) throws IOException {
        return new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create()
                .fromJson(new String(Files.readAllBytes(Paths.get(fileName + ".json"))), ChunksData.class);
    }
}
