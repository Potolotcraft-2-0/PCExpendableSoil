package expendablesoil.expendablesoil.commands;

import expendablesoil.expendablesoil.scripts.ChunkManager;
import lombok.experimental.ExtensionMethod;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
            case "chunk_standup_man" -> {
                var chunk = Bukkit.getServer().getWorlds().get(0).getChunkAt(((Player) sender).getLocation());
                chunk.setChunkHealths(Integer.parseInt(args[0]));
                chunk.replaceChunkBlock(Material.COARSE_DIRT, Material.GRASS_BLOCK, 1);
                chunk.replaceChunkBlock(Material.SAND, Material.GRASS_BLOCK, 1);
            }
            case "kill_chunk" -> {
                var chunk = Bukkit.getServer().getWorlds().get(0).getChunkAt(((Player) sender).getLocation());
                chunk.setChunkHealths(ChunkManager.Dead);
            }
        }
        return true;
    }
}
