package expendablesoil.expendablesoil;

import expendablesoil.expendablesoil.commands.CommandManager;
import expendablesoil.expendablesoil.data.ChunksData;
import expendablesoil.expendablesoil.listeners.ChunkListener;
import expendablesoil.expendablesoil.listeners.WorldListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;

public final class ExpendableSoil extends JavaPlugin {
    public static ChunksData ChunksData;

    @Override
    public void onEnable() {
        ChunksData = new ChunksData();

        for (var listener : Arrays.asList(new ChunkListener(), new WorldListener()))
            Bukkit.getPluginManager().registerEvents(listener, this);

        for (var command : Arrays.asList("expendable_info", "kill_chunk"))
            Objects.requireNonNull(getCommand(command)).setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        // TODO: Implement saving and loading data
    }
}
