package github.kratofl.kraosspigot;

import github.kratofl.kraosspigot.commands.BackCommand;
import github.kratofl.kraosspigot.commands.HomeCommand;
import github.kratofl.kraosspigot.listeners.player.PlayerDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kraos extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("home").setExecutor(new HomeCommand());
        Bukkit.getPluginCommand("back").setExecutor(new BackCommand());

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPluginInstance() {
        return Kraos.getPlugin(Kraos.class);
    }
}
