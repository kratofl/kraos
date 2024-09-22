package github.kratofl.kraosspigot.features.home.config;

import github.kratofl.kraosspigot.config.BaseConfig;
import github.kratofl.kraosspigot.logging.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;

public final class HomeConfig {
    private static final ConfigurationSection SECTION = BaseConfig.CONFIGURATION.getConfigurationSection("home");

    public static boolean validateConfig() throws InvalidConfigurationException {
        try {
            if (Integer.signum(getHomeLimit()) < 0) {
                throw new InvalidConfigurationException("Plugin config (config.yml) is invalid");
            }

        } catch (Exception e) {
            Logger.error("Plugin config (config.yml) is invalid", e);
            throw e;
        }
        return true;
    }

    public static int getHomeLimit() {
        return SECTION.getInt("limit");
    }
}
