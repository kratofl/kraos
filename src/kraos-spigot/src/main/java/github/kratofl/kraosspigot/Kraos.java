package github.kratofl.kraosspigot;

import github.kratofl.kraosspigot.features.home.commands.BackCommand;
import github.kratofl.kraosspigot.features.home.commands.HomeCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kraos extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Bukkit.getPluginCommand("home").setExecutor(new HomeCommand());
        Bukkit.getPluginCommand("back").setExecutor(new BackCommand());

        getServer().getPluginManager().registerEvents(new github.kratofl.kraosspigot.features.deathchest.listeners.player.PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new github.kratofl.kraosspigot.features.deathchest.listeners.player.PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new github.kratofl.kraosspigot.features.home.listeners.player.PlayerDeathListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPluginInstance() {
        return Kraos.getPlugin(Kraos.class);
    }
}
