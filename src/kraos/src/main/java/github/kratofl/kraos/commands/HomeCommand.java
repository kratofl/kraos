package github.kratofl.kraos.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Invalid sender!");
            return false;
        }

        Player p = (Player) sender;
        if (args.length == 0) {
            //TODO: First home
            return true;
        }

        String actionName = args[0];
        if (args.length != 1 && args.length != 2) {
            p.sendMessage(cmd.getName() + " <list>");
            p.sendMessage(cmd.getName() + " <add/delete> <Name>");
            return false;
        }
        if (args.length == 1) {
            if(actionName.equalsIgnoreCase("add") || actionName.equalsIgnoreCase("delete")){
                p.sendMessage(cmd.getName() + " <add/delete> <Name>");
                return false;
            }

            if (actionName.equalsIgnoreCase("list")) {
                //TODO: homes auflisten
                return true;
            }

            String homeName = actionName;
            //TODO: Tp to home

            return true;
        }

        String homeName = args[1];
        if (actionName.equalsIgnoreCase("add")) {
            if (homeName.equalsIgnoreCase("list")) {
                p.sendMessage("You are not allowed to name your home list!");
                return false;
            }
            //TODO: homes adden
            return true;
        }
        if (actionName.equalsIgnoreCase("delete")) {
            //TODO: homes deleten
            return true;
        }

        p.sendMessage("Invalid arguments!");
        return false;
    }
}
