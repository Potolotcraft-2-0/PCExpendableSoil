package expendablesoil.expendablesoil;

import expendablesoil.expendablesoil.commands.CommandManager;
import expendablesoil.expendablesoil.listeners.ChunkListener;
import expendablesoil.expendablesoil.listeners.PlayerListener;
import expendablesoil.expendablesoil.listeners.WorldListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;


public final class ExpendableSoil extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.print("[ExpendableSoil] ExpendableSoil starting...");

        for (var listener : Arrays.asList(new ChunkListener(), new WorldListener(), new PlayerListener()))
            Bukkit.getPluginManager().registerEvents(listener, this);

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
