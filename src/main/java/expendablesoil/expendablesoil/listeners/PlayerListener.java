package expendablesoil.expendablesoil.listeners;

import expendablesoil.expendablesoil.ExpendableSoil;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void killChunk(PlayerInteractEvent event) {
        var block = event.getClickedBlock();
        if (block == null) return;

        var chunk = block.getChunk();
        if (ExpendableSoil.ChunksData.ChunkData.get(chunk.getChunkKey()) == null) return;

        var handItem = event.getItem();
        if (handItem == null) return;

        if (handItem.getType().equals(Material.BONE_MEAL))
            ExpendableSoil.ChunksData.ChunkData.put(chunk.getChunkKey(), ExpendableSoil.ChunksData.ChunkData.get(chunk.getChunkKey() - 10));
    }
}
