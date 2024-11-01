package expendablesoil.expendablesoil;

import expendablesoil.expendablesoil.commands.CommandManager;
import expendablesoil.expendablesoil.listeners.ChunkListener;

import expendablesoil.expendablesoil.listeners.PlayerListener;
import expendablesoil.expendablesoil.listeners.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public final class ExpendableSoil extends JavaPlugin {
    public static FileConfiguration Config;

    @Override
    public void onEnable() {
        System.out.print("[ExpendableSoil] ExpendableSoil starting...");

        var file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) this.saveDefaultConfig();
        Config = ExpendableSoil.getPlugin(ExpendableSoil.class).getConfig();

        for (var listener : List.of(new ChunkListener(), new PlayerListener()))
            Bukkit.getPluginManager().registerEvents(listener, this);

        WorldManager.chunkRecover();

        System.out.print("[ExpendableSoil] Events registered...");

        for (var command : Arrays.asList("expendable_info", "kill_chunk"))
            Objects.requireNonNull(getCommand(command)).setExecutor(new CommandManager());

        System.out.print("[ExpendableSoil] Activated");
    }

    @Override
    public void onDisable() {
        System.out.print("[ExpendableSoil] Disabled");
    }
}
