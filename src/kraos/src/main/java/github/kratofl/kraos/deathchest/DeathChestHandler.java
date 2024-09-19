package github.kratofl.kraos.deathchest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Optional;

public class DeathChestHandler {

    private static final ArrayList<DeathChestModel> DEATH_CHESTS = new ArrayList<>();
    private static void addDeathChest(DeathChestModel deathChestModel) {
        DEATH_CHESTS.add(deathChestModel);
    }
    private static Optional<DeathChestModel> getDeathChest(Location location) {
        return DEATH_CHESTS.stream().filter(x -> x.getLocation() == location).findFirst();
    }
    private static void removeDeathChest(DeathChestModel deathChestModel) {
        DEATH_CHESTS.removeIf(x -> x.getLocation() == deathChestModel.getLocation() && x.getOwner() == deathChestModel.getOwner());
    }

    public static void spawnDeathChest(Player player) {
        // if death point is highest, then replace block
        // if died mid air
        Location deathLocation = player.getLocation();
        Location deathChestLocation = getHighestVerticalLocationPossible(deathLocation);
        deathChestLocation.getBlock().setType(DeathChestModel.MATERIAL);
        addDeathChest(new DeathChestModel(player));
        player.sendMessage("Your death chest has been spawned at: " + locationToString(deathChestLocation));
    }
    public static void destroyDeathChest(Location location, Player player) {
        Optional<DeathChestModel> deathChest = getDeathChest(location);
        if (deathChest.isEmpty()) {
            return;
        }

        DeathChestModel deathChestModel = deathChest.get();
        if (player.getUniqueId() != deathChestModel.getOwner().getUniqueId()) {
            player.sendMessage("This is not your death chest");
            return;
        }

        deathChestModel.getLocation().getBlock().setType(Material.AIR);
        removeDeathChest(deathChestModel);
    }

    private static Location getHighestVerticalLocationPossible(Location deathLocation) {
        int maxHeight = deathLocation.getWorld().getMaxHeight();
        Location possibleLocation = deathLocation.getWorld().getHighestBlockAt(deathLocation).getLocation();
        if (possibleLocation.getY() == maxHeight) {
            Bukkit.getConsoleSender().sendMessage("Highest block");
            return possibleLocation;
        }
        return possibleLocation.add(0, 1, 0);
    }

    private static String locationToString(Location location) {
        return String.format("X: %s, Y: %s, Z: %s", location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
