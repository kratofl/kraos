package github.kratofl.kraosspigot.config;

import github.kratofl.kraosspigot.Kraos;
import org.bukkit.configuration.file.FileConfiguration;

public final class BaseConfig {

    public static final FileConfiguration CONFIGURATION = Kraos.getPluginInstance().getConfig();

    public static boolean debugModeEnabled() {
        return CONFIGURATION.getBoolean("debug");
    }
}
