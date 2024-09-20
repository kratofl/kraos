package github.kratofl.kraos;

import github.kratofl.kraos.commands.BackCommand;
import github.kratofl.kraos.commands.HomeCommand;
import github.kratofl.kraos.listeners.block.BlockBreakListener;
import github.kratofl.kraos.listeners.player.PlayerDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kraos extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("home").setExecutor(new HomeCommand());
        Bukkit.getPluginCommand("back").setExecutor(new BackCommand());

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        //getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public static Plugin getPluginInstance() {
        return Kraos.getPlugin(Kraos.class);
    }
}
