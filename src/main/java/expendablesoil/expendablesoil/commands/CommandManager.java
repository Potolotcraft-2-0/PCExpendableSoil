package expendablesoil.expendablesoil.commands;

import expendablesoil.expendablesoil.ExpendableSoil;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {
        switch (command.getName()) {
            case "expendable_info" -> sender.sendMessage("HP: " + ExpendableSoil.ChunksData.ChunkData.get(Bukkit.getServer()
                        .getWorlds().get(0).getChunkAt(((Player) sender).getLocation()).getChunkKey()));
            case "kill_chunk" -> {
                var chunk = Bukkit.getServer().getWorlds().get(0).getChunkAt(((Player) sender).getLocation()).getChunkKey();

                ExpendableSoil.ChunksData.ChunkData.remove(chunk);
                ExpendableSoil.ChunksData.DiedChunks.add(chunk);
            }
        }
        return true;
    }
}
