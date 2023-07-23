package expendablesoil.expendablesoil.listeners;

import expendablesoil.expendablesoil.ExpendableSoil;
import expendablesoil.expendablesoil.data.ChunksData;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.world.StructureGrowEvent;

public class ChunkListener implements Listener {
    @EventHandler
    public void onChunkInteraction(BlockPlaceEvent event) {
        var block = event.getBlock();
        if (!block.getBlockData().isRandomlyTicked()) return; //TODO: List of grown items

        var chunkKey = event.getBlock().getChunk().getChunkKey();
        if (ExpendableSoil.ChunksData.DiedChunks.contains(chunkKey))
            event.getBlock().setType(Material.DEAD_BUSH);

        if (ExpendableSoil.ChunksData.ChunkData.get(chunkKey) != null
        || ExpendableSoil.ChunksData.DiedChunks.contains(chunkKey)) return;

        ExpendableSoil.ChunksData.ChunkData.put(chunkKey, ChunksData.BiomeHealthPoints.get(block.getBiome()) == null ?
                0 : ChunksData.BiomeHealthPoints.get(block.getBiome()));
    }

    @EventHandler
    public void onGrown(BlockGrowEvent event) {
        var chunk = event.getBlock().getChunk().getChunkKey();
        if (ExpendableSoil.ChunksData.ChunkData.get(chunk) == null) return;

        ExpendableSoil.ChunksData.ChunkData.put(chunk, ExpendableSoil.ChunksData.ChunkData.get(chunk) - 1);
    }

    @EventHandler
    public void onTreeGrown(StructureGrowEvent event) {
        var chunk = event.getBlocks().get(0).getChunk().getChunkKey();
        if (ExpendableSoil.ChunksData.ChunkData.get(chunk) == null) return;

        ExpendableSoil.ChunksData.ChunkData.put(chunk, ExpendableSoil.ChunksData.ChunkData.get(chunk) - 10);
    }
}
