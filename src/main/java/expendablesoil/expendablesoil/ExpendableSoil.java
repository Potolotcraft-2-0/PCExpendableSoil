package expendablesoil.expendablesoil;

import expendablesoil.expendablesoil.commands.CommandManager;
import expendablesoil.expendablesoil.data.ChunksData;
import expendablesoil.expendablesoil.listeners.ChunkListener;
import expendablesoil.expendablesoil.listeners.WorldListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public final class ExpendableSoil extends JavaPlugin {
    public static ChunksData ChunksData;

    @Override
    public void onEnable() {
        ChunksData = new ChunksData();

        try {
            if (new File("expendable_chunks_data.json").exists())
                ChunksData = expendablesoil.expendablesoil.data.ChunksData.loadChunksData("expendable_chunks_data");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (var listener : Arrays.asList(new ChunkListener(), new WorldListener()))
            Bukkit.getPluginManager().registerEvents(listener, this);

        for (var command : Arrays.asList("expendable_info", "kill_chunk"))
            Objects.requireNonNull(getCommand(command)).setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        try {
            ChunksData.saveChunksData("expendable_chunks_data");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
