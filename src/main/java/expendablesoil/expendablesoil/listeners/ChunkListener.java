package expendablesoil.expendablesoil.listeners;

import expendablesoil.expendablesoil.ExpendableSoil;
import expendablesoil.expendablesoil.scripts.ChunkManager;

import lombok.experimental.ExtensionMethod;

import org.bukkit.block.data.Ageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
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
        if (chunk.getChunkHealths() == ChunkManager.NotFound) {
            var defaultHealthPoints = ExpendableSoil.Config.getInt("healths." + chunk.getBiome().toString(), 10);
            chunk.setChunkHealths(defaultHealthPoints);
            return;
        }

        if (block.getBlockData() instanceof Ageable)
            if (chunk.isDead()) event.setCancelled(true);
    }

    @EventHandler
    public void onGrown(BlockGrowEvent event) {
        var chunk = event.getBlock().getChunk();
        if (chunk.getChunkHealths() != ChunkManager.NotFound) {
            chunk.changeChunkHealths(-1);
            if (chunk.isDead()) event.setCancelled(true);
        }
    }

    @EventHandler
    public void onTreeGrown(StructureGrowEvent event) {
        var chunk = event.getLocation().getChunk();
        if (chunk.getChunkHealths() != ChunkManager.NotFound) {
            chunk.changeChunkHealths(-10);
            if (chunk.isDead()) event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        var chunk = event.getEntity().getChunk();
        if (chunk.getChunkHealths() == ChunkManager.NotFound) return;

        if (event.getEntity() instanceof LivingEntity entity) {
            if (entity instanceof Monster) return;
            else {
                chunk.changeChunkHealths(-5);
                if (chunk.isDead()) event.setCancelled(true);
            }
        }
    }
}
