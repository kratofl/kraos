package github.kratofl.kraos.commands;

import github.kratofl.kraos.player.PlayerData;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Invalid sender!");
            return false;
        }

        if (args.length > 0) {
            player.sendMessage("Too many arguments");
            return false;
        }

        Location lastCoordinates = PlayerData.getPlayersLastCoordinates(player);
        if (lastCoordinates == null) {
            player.sendMessage("You haven't teleported anywhere yet");
            return true;
        }
        PlayerData.setPlayersLastCoordinates(player, player.getLocation());
        player.teleport(lastCoordinates);
        return true;
    }
}
