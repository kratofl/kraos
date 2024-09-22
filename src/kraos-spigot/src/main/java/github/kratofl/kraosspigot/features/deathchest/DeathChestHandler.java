package github.kratofl.kraosspigot.features.deathchest;

import github.kratofl.kraosspigot.logging.Logger;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Optional;

public class DeathChestHandler {

    private static final ArrayList<DeathChest> DEATH_CHESTS = new ArrayList<>();
    public static void addChest(DeathChest deathChest) {
        DEATH_CHESTS.add(deathChest);
    }
    public static Optional<DeathChest> getChest(Location location) {
        return DEATH_CHESTS.stream().filter(x -> compareLocations(x.getChestLocation(), location)).findFirst();
    }
    public static ArrayList<DeathChest>  getDeathChests() {
        return DEATH_CHESTS;
    }
    public static void removeChest(DeathChest deathChestModel) {
        DEATH_CHESTS.removeIf(x -> compareLocations(x.getChestLocation(), deathChestModel.getChestLocation()) && x.getOwner().getUniqueId().equals(deathChestModel.getOwner().getUniqueId()));
    }
    private static boolean compareLocations(Location source, Location target) {
        return source.getWorld().getName().equalsIgnoreCase(target.getWorld().getName())
                && source.getBlockX() == target.getBlockX()
                && source.getBlockY() == target.getBlockY()
                && source.getBlockZ() == target.getBlockZ();
    }

    public static boolean blockIsDeathChest(Location location) {
        return DEATH_CHESTS.stream().anyMatch(x -> compareLocations(x.getChestLocation(), location));
    }
}
