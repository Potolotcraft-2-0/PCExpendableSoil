package expendablesoil.expendablesoil.commands;

import expendablesoil.expendablesoil.scripts.ChunkManager;
import lombok.experimental.ExtensionMethod;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;


@ExtensionMethod({ChunkManager.class})
public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {
        switch (command.getName()) {
            case "expendable_info" -> sender.sendMessage("HP: " + Bukkit.getServer()
                        .getWorlds().get(0).getChunkAt(((Player) sender).getLocation()).getChunkHealths());
            case "kill_chunk" -> {
                var chunk = Bukkit.getServer().getWorlds().get(0).getChunkAt(((Player) sender).getLocation());
                chunk.setChunkHealths(-1);
            }
        }
        return true;
    }
}
