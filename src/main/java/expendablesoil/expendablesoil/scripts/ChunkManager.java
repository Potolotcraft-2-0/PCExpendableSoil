package expendablesoil.expendablesoil.scripts;

import expendablesoil.expendablesoil.ExpendableSoil;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.scheduler.BukkitRunnable;

public class ChunkManager {
    /**
     * Switch biome of chunk
     * @param chunk Chunk where should be changed biome
     * @param biome New biome
     */
    public static void changeBiome(Chunk chunk, Biome biome) {
        int cX = chunk.getX() * 16;
        int cZ = chunk.getZ() * 16;
        var world = chunk.getWorld();

        for(int x = 0; x < 16; x++)
            for(int z = 0; z < 16; z++)
                world.setBiome(cX + x, cZ+ z, biome);
    }

    /**
     * Replace all specified blocks with chance percent
     * @param chunk Chunk
     * @param oldBlock Old block
     * @param newBlock New block
     */
    public static void replaceChunkBlock(Chunk chunk, Material oldBlock, Material newBlock, double chancePercent) {
        new BukkitRunnable() {
            @Override
            public void run() {
                var world = chunk.getWorld();
                int chunkX = chunk.getX() << 4;
                int chunkZ = chunk.getZ() << 4;

                for (int x = 0; x < 16; x++)
                    for (int z = 0; z < 16; z++)
                        for (int y = 0; y < world.getMaxHeight(); y++) {
                            var block = world.getBlockAt(chunkX + x, y, chunkZ + z);
                            if (block.getType().equals(oldBlock))
                                if (Math.random() <= chancePercent)
                                    block.setType(newBlock, false);
                        }
            }
        }.runTask(ExpendableSoil.getPlugin(ExpendableSoil.class));
    }
}

