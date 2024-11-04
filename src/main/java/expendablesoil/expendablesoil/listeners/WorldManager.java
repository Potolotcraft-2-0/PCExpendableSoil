package expendablesoil.expendablesoil.listeners;

import expendablesoil.expendablesoil.ExpendableSoil;
import expendablesoil.expendablesoil.scripts.ChunkManager;

import lombok.experimental.ExtensionMethod;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;


@ExtensionMethod({ChunkManager.class})
public class WorldManager {
    public static void chunkRecover() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (var world : Bukkit.getWorlds()) {
                    var chunks = world.getLoadedChunks();
                    Arrays.stream(chunks).toList().parallelStream().forEach(chunk -> {
                        var healthPoints = chunk.getChunkHealths();
                        if (healthPoints != ChunkManager.NotFound) {

                            if (chunk.isDead()) {
                                chunk.replaceChunkBlock(Material.FARMLAND, Material.GRASS_BLOCK, .05);
                                chunk.replaceChunkBlock(Material.PODZOL, Material.GRASS_BLOCK, .05);
                                chunk.replaceChunkBlock(Material.GRASS_BLOCK, Material.COARSE_DIRT, .05);
                                chunk.replaceChunkBlock(Material.DIRT, Material.COARSE_DIRT, .05);
                                chunk.replaceChunkBlock(Material.COARSE_DIRT, Material.SAND, .05);
                            } else {
                                var defaultHp = ExpendableSoil.getInstance().Config.getInt("healths." + chunk.getBiome().toString(), 100);
                                if (chunk.getChunkHealths() + 5 <= defaultHp) {
                                    chunk.changeChunkHealths(ExpendableSoil.getInstance().Config.getInt("setup.regeneration", 10));
                                }
                            }
                        }
                    });
                }
            }
        }.runTaskTimer(ExpendableSoil.getInstance(), 0, 1000);
    }
}
