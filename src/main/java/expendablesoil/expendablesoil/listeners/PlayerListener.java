package expendablesoil.expendablesoil.listeners;

import expendablesoil.expendablesoil.ExpendableSoil;
import expendablesoil.expendablesoil.scripts.ChunkManager;
import lombok.experimental.ExtensionMethod;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;


@ExtensionMethod({ChunkManager.class})
public class PlayerListener implements Listener {
    @EventHandler
    public void killChunk(PlayerInteractEvent event) {
        var block = event.getClickedBlock();
        if (block == null) return;

        var chunk = block.getChunk();
        var handItem = event.getItem();
        if (handItem == null) return;

        if (chunk.getChunkHealths() == ChunkManager.NotFound) {
            var defaultHealthPoints = ExpendableSoil.Config.getInt("healths." + chunk.getBiome().toString(), 100);
            chunk.setChunkHealths(defaultHealthPoints);
        }

        if (handItem.getType().equals(Material.BONE_MEAL))
            chunk.changeChunkHealths(-10);
    }
}
