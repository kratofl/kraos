package github.kratofl.kraos;

import github.kratofl.kraos.commands.HomeCommand;
import github.kratofl.kraos.commands.PlayerFiles;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class Kraos extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("home").setExecutor(new HomeCommand());
        getServer().getPluginManager().registerEvents(new PlayerFiles(), this);

    }

    public static Plugin getPluginInstance() {
        return Kraos.getPlugin(Kraos.class);
    }

    @Override
    public void onDisable() {
    }
}
