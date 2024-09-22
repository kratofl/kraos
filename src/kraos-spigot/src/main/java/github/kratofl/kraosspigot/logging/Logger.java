package github.kratofl.kraosspigot.logging;

import github.kratofl.kraosspigot.Kraos;
import github.kratofl.kraosspigot.config.BaseConfig;
import org.bukkit.ChatColor;

import java.util.logging.Level;

public class Logger {

    private static final java.util.logging.Logger LOGGER = Kraos.getPluginInstance().getLogger();

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    public static void info(String msg) {
        LOGGER.info(msg);
    }
    public static void warning(String msg) {
        LOGGER.warning(YELLOW + msg + RESET);
    }
    public static void error(String msg) {
        LOGGER.severe(RED + msg + RESET);
    }
    public static void error(String msg, Throwable exception) {
        LOGGER.log(Level.SEVERE, RED + msg + RESET, exception);
    }
    public static void debug(String msg) {
        if (BaseConfig.debugModeEnabled()) {
            LOGGER.info(BLUE + "[DEBUG] " + msg + RESET);
        }
    }
}
