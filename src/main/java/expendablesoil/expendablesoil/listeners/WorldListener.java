package expendablesoil.expendablesoil.listeners;

import com.palmergames.bukkit.towny.event.NewDayEvent;

import expendablesoil.expendablesoil.ExpendableSoil;
import expendablesoil.expendablesoil.scripts.ChunkManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WorldListener implements Listener {
    @EventHandler
    public void newDay(NewDayEvent event) {
        for (var location : ExpendableSoil.ChunksData.ChunkData.keySet()) {
            var chunk = Bukkit.getWorlds().get(0).getChunkAt(location);
            if (!chunk.isLoaded()) continue;

            var healthPoints = ExpendableSoil.ChunksData.ChunkData.get(location);
            if (healthPoints <= 0) {
                ExpendableSoil.ChunksData.ChunkData.remove(location);
                ExpendableSoil.ChunksData.DiedChunks.add(location);

                ChunkManager.changeBiome(chunk, Biome.DESERT);
                return;
            }

            ExpendableSoil.ChunksData.ChunkData.put(location, healthPoints + 1);
        }

        for (var location : ExpendableSoil.ChunksData.DiedChunks)
            ChunkManager.replaceChunkBlock(Bukkit.getWorlds().get(0).getChunkAt(location), Material.GRASS_BLOCK, Material.COARSE_DIRT, 1);
    }
}
