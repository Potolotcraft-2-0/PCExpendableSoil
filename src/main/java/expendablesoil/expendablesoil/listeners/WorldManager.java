package expendablesoil.expendablesoil.listeners;

import expendablesoil.expendablesoil.ExpendableSoil;
import expendablesoil.expendablesoil.scripts.ChunkManager;

import lombok.experimental.ExtensionMethod;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;


@ExtensionMethod({ChunkManager.class})
public class WorldManager {
    public static void chunkRecover() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (var world : Bukkit.getWorlds()) {
                    var chunks = world.getLoadedChunks();
                    for (var chunk : chunks) {
                        var healthPoints = chunk.getChunkHealths();
                        if (healthPoints == ChunkManager.NotFound) continue;

                        if (chunk.isDead()) {
                            chunk.replaceChunkBlock(Material.GRASS_BLOCK, Material.COARSE_DIRT, .05);
                            chunk.replaceChunkBlock(Material.DIRT, Material.COARSE_DIRT, .05);
                        }
                        else {
                            var defaultHp = ExpendableSoil.Config.getInt("healths." + chunk.getBiome().toString(), 100);
                            if (chunk.getChunkHealths() + 5 > defaultHp) continue;
                            chunk.changeChunkHealths(5);
                        }
                    }
                }
            }
        }.runTaskTimer(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ExpendableSoil")), 0, 1000);
    }
}
