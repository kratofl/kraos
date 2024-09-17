package github.kratofl.kraos;

import github.kratofl.kraos.commands.HomeCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Kraos extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginCommand("home").setExecutor(new HomeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
