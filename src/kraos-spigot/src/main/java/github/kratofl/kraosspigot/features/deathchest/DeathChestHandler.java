package github.kratofl.kraosspigot.features.deathchest;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Optional;

public class DeathChestHandler {

    private static final ArrayList<DeathChest> DEATH_CHESTS = new ArrayList<>();
    public static void addChest(DeathChest deathChest) {
        DEATH_CHESTS.add(deathChest);
    }
    public static Optional<DeathChest> getChest(Location location) {
        return DEATH_CHESTS.stream().filter(x -> x.getLocation().equals(location)).findFirst();
    }
    public static ArrayList<DeathChest>  getDeathChests() {
        return DEATH_CHESTS;
    }
    public static void removeChest(DeathChest deathChestModel) {
        DEATH_CHESTS.removeIf(x -> x.getLocation().equals(deathChestModel.getLocation()) && x.getOwner().getUniqueId().equals(deathChestModel.getOwner().getUniqueId()));
    }

    public static boolean blockIsDeathChest(Location location) {
        return DEATH_CHESTS.stream().anyMatch(x -> x.getLocation().equals(location));
    }
}
