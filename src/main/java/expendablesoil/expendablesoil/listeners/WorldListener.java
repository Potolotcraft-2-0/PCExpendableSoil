package expendablesoil.expendablesoil.listeners;

import com.palmergames.bukkit.towny.event.NewDayEvent;

import expendablesoil.expendablesoil.scripts.ChunkManager;

import lombok.experimental.ExtensionMethod;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


@ExtensionMethod({ChunkManager.class})
public class WorldListener implements Listener {
    @EventHandler
    public void newDay(NewDayEvent event) {
        for (var world : Bukkit.getWorlds()) {
            var chunks = world.getLoadedChunks();
            for (var chunk : chunks) {
                var healthPoints = chunk.getChunkHealths();
                if (healthPoints == -1) return;

                if (healthPoints <= 0) {
                    if (chunk.getBiome() != Biome.DESERT) chunk.changeBiome(Biome.DESERT);
                    chunk.replaceChunkBlock(Material.GRASS_BLOCK, Material.COARSE_DIRT, 1);
                    return;
                }

                chunk.changeChunkHealths(1);
            }
        }
    }
}
