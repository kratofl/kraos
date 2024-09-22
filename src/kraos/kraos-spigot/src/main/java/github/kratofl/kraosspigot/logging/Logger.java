package github.kratofl.kraosspigot.logging;

import github.kratofl.kraosspigot.Kraos;
import github.kratofl.kraosspigot.config.BaseConfig;
import org.bukkit.ChatColor;

import java.util.logging.Level;

public class Logger {

    private static final java.util.logging.Logger LOGGER = Kraos.getPluginInstance().getLogger();

    public static void info(String msg) {
        LOGGER.info(msg);
    }
    public static void warning(String msg) {
        LOGGER.warning(ChatColor.GOLD + msg);
    }
    public static void error(String msg) {
        LOGGER.severe(ChatColor.RED + msg);
    }
    public static void error(String msg, Throwable exception) {
        LOGGER.log(Level.SEVERE, ChatColor.RED + msg, exception);
    }
    public static void debug(String msg) {
        if (BaseConfig.debugModeEnabled()) {
            LOGGER.info(ChatColor.BLUE + "[DEBUG] " + msg);
        }
    }
}
