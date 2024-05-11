package expendablesoil.expendablesoil.listeners;

import expendablesoil.expendablesoil.data.Chunks;
import expendablesoil.expendablesoil.data.Friendly;
import expendablesoil.expendablesoil.data.Growable;

import expendablesoil.expendablesoil.scripts.ChunkManager;
import lombok.experimental.ExtensionMethod;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.world.StructureGrowEvent;


@ExtensionMethod({ChunkManager.class})
public class ChunkListener implements Listener {
    @EventHandler
    public void onChunkInteraction(BlockPlaceEvent event) {
        var block = event.getBlock();
        var chunk = block.getChunk();

        if (!Growable.Growable.contains(block.getType())) return;
        if (chunk.getChunkHealths() == -1) return;

        if (chunk.getChunkHealths() <= 0) {
            event.setCancelled(true);
            return;
        }

        var defaultHealthPoints = 100;
        chunk.setChunkHealths(Chunks.BiomeHealthPoints.get(block.getBiome()) == null ?
                defaultHealthPoints :
                Chunks.BiomeHealthPoints.get(block.getBiome())
        );
    }

    @EventHandler
    public void onGrown(BlockGrowEvent event) {
        var chunk = event.getBlock().getChunk();
        if (chunk.getChunkHealths() != -1) {
            chunk.changeChunkHealths(-1);
            if (chunk.getChunkHealths() <= 0) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onTreeGrown(StructureGrowEvent event) {
        var chunk = event.getLocation().getChunk();
        if (chunk.getChunkHealths() != -1) {
            chunk.changeChunkHealths(-10);
            if (chunk.getChunkHealths() <= 0) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        var chunk = event.getEntity().getChunk();
        if (chunk.getChunkHealths() == -1) return;

        if (Friendly.Friendly.contains(event.getEntity().getType())) {
            if (chunk.getChunkHealths() <= 0) event.setCancelled(true);
        }
    }
}
