package github.kratofl.kraos.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    public static FileConfiguration loadConfig(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {

            }
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void saveConfig(FileConfiguration config, File file) {
        try {
            config.save(file);
        } catch (IOException ignored) {

        }
    }
}
