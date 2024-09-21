package github.kratofl.kraosspigot.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

public class PlayerData {

    private final static HashMap<UUID, Location> lastPlayerCoordinates = new HashMap<>();

    public static @Nullable Location getPlayersLastCoordinates(Player player) {
        return lastPlayerCoordinates.get(player.getUniqueId());
    }

    public static void setPlayersLastCoordinates(Player player, Location location) {
        lastPlayerCoordinates.put(player.getUniqueId(), location);
    }
}
