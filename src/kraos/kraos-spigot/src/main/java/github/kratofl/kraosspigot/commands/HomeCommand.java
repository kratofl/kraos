package github.kratofl.kraosspigot.commands;

import github.kratofl.kraosspigot.Kraos;
import github.kratofl.kraosspigot.config.FileManager;
import github.kratofl.kraosspigot.player.PlayerData;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Invalid sender!");
            return false;
        }

        Player p = (Player) commandSender;

        if (args.length == 0) {
            PlayerData.setPlayersLastCoordinates(p, p.getLocation());
            Location location = goToHome(p.getUniqueId(), "", p);
            p.teleport(location);
            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            return true;
        }


        if (args.length != 1 && args.length != 2) {
            p.sendMessage(command.getName() + " <list>");
            p.sendMessage(command.getName() + " <add/delete> <Name>");
            return false;
        }
        String actionName = args[0];
        if (args.length == 1) {
            if (actionName.equalsIgnoreCase("add") || actionName.equalsIgnoreCase("delete")) {
                p.sendMessage(command.getName() + " <add/delete> <Name>");
                return false;
            }
            if (actionName.equalsIgnoreCase("list")) {
                List<String> homeNames = getAllHomes(p.getUniqueId());

                if (homeNames.isEmpty()) {
                    p.sendMessage("You have no homes!");
                    return true;
                }

                p.sendMessage("Your homes are:");
                for (String playerHomeName : homeNames) {
                    p.sendMessage(playerHomeName);
                }
                return true;
            }

            Location location = goToHome(p.getUniqueId(), actionName, p);
            if (location == null) {
                return true;
            }
            PlayerData.setPlayersLastCoordinates(p, p.getLocation());
            p.teleport(location);
            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            p.sendMessage("You have been teleported to: " + actionName);
            return true;

        }

        String homeName = args[1];
        if (actionName.equalsIgnoreCase("add")) {
            if (homeName.equalsIgnoreCase("list")) {
                p.sendMessage("You are not allowed to name your home list!");
                return true;
            }

            if (doesHomeExist(p.getUniqueId(), homeName)) {
                p.sendMessage("Home already exists!");
                return true;
            }

            saveHome(p, homeName, p.getLocation());
            return true;
        }

        if (actionName.equalsIgnoreCase("delete")) {
            if (!deleteHome(p.getUniqueId(), homeName)) {
                p.sendMessage("No home to delete");
                return true;
            }
            p.sendMessage("Home " + homeName + " has been deleted");
            return true;
        }
        return false;
    }

    private void saveHome(Player player, String homeName, Location location) {
        UUID playerUUID = player.getUniqueId();
        List<String> allHomes = this.getAllHomes(playerUUID);
        if (allHomes.size() >= 2) {
            player.sendMessage(ChatColor.RED + "You have reached the maximum amount of homes " + ChatColor.BOLD + "(2)");
            return;
        }

        File file = new File(Kraos.getPluginInstance().getDataFolder(), playerUUID.toString() + ".yml");
        FileConfiguration config = FileManager.loadConfig(file);

        config.set(homeName + ".x", location.getBlockX());
        config.set(homeName + ".y", location.getBlockY());
        config.set(homeName + ".z", location.getBlockZ());
        config.set(homeName + ".world", location.getWorld().getName());
        FileManager.saveConfig(config, file);
        player.sendMessage("Home " + homeName + " gesetzt!");
    }

    private boolean doesHomeExist(UUID playerUUID, String homeName) {
        File file = new File(Kraos.getPluginInstance().getDataFolder(), playerUUID.toString() + ".yml");
        FileConfiguration config = FileManager.loadConfig(file);
        return config.contains(homeName);
    }

    private List<String> getAllHomes(UUID playerUUID) {
        File file = new File(Kraos.getPluginInstance().getDataFolder(), playerUUID.toString() + ".yml");
        FileConfiguration config = FileManager.loadConfig(file);
        return config.getConfigurationSection("").getKeys(false).stream().toList();
    }

    private boolean deleteHome(UUID playerUUID, String homeName) {
        File file = new File(Kraos.getPluginInstance().getDataFolder(), playerUUID.toString() + ".yml");
        FileConfiguration config = FileManager.loadConfig(file);

        if (!doesHomeExist(playerUUID, homeName)) {
            return false;
        }

        config.set(homeName, null);
        FileManager.saveConfig(config, file);
        return true;
    }

    private Location goToHome(UUID playerUUID, String homeName, Player p) {
        File file = new File(Kraos.getPluginInstance().getDataFolder(), playerUUID.toString() + ".yml");
        FileConfiguration config = FileManager.loadConfig(file);

        if(homeName.equalsIgnoreCase("")) {
            homeName = getAllHomes(playerUUID).getFirst();
        }

        String worldName = config.getString(homeName + ".world");
        if (worldName == null) {
            p.sendMessage("You have no homes");
            return null;
        }

        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            p.sendMessage("The world  " + worldName + " does not exist");
            return null;
        }

        double x = config.getDouble(homeName + ".x");
        double y = config.getDouble(homeName + ".y");
        double z = config.getDouble(homeName + ".z");

        return new Location(world, x, y, z);
    }
}