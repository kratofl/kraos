package github.kratofl.kraosspigot;

import github.kratofl.kraosspigot.config.BaseConfig;
import github.kratofl.kraosspigot.features.home.commands.BackCommand;
import github.kratofl.kraosspigot.features.home.commands.HomeCommand;
import github.kratofl.kraosspigot.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kraos extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Logger.debug("Debug mode active");

        Bukkit.getPluginCommand("home").setExecutor(new HomeCommand());
        Bukkit.getPluginCommand("back").setExecutor(new BackCommand());
        Logger.debug("Commands registered");

        getServer().getPluginManager().registerEvents(new github.kratofl.kraosspigot.features.deathchest.listeners.player.PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new github.kratofl.kraosspigot.features.deathchest.listeners.player.PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new github.kratofl.kraosspigot.features.home.listeners.player.PlayerDeathListener(), this);
        Logger.debug("Events registered");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPluginInstance() {
        return Kraos.getPlugin(Kraos.class);
    }
}
