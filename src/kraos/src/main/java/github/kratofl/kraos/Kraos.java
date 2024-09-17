package github.kratofl.kraos;

import github.kratofl.kraos.commands.HomeCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class Kraos extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("home").setExecutor(new HomeCommand());
    }

    @Override
    public void onDisable() {
    }
}
