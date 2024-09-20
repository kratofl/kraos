package github.kratofl.kraos.deathchest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Optional;

public class DeathChestHandler {

    private static final ArrayList<DeathChestModel> DEATH_CHESTS = new ArrayList<>();
    private static void addDeathChest(DeathChestModel deathChestModel) {
        DEATH_CHESTS.add(deathChestModel);
    }
    public static Optional<DeathChestModel> getDeathChest(Location location) {
        return DEATH_CHESTS.stream().filter(x -> x.getLocation().equals(location)).findFirst();
    }
    public static ArrayList<DeathChestModel>  getDeathChests() {
        return DEATH_CHESTS;
    }
    private static void removeDeathChest(DeathChestModel deathChestModel) {
        DEATH_CHESTS.removeIf(x -> x.getLocation().equals(deathChestModel.getLocation()) && x.getOwner().getUniqueId().equals(deathChestModel.getOwner().getUniqueId()));
    }

    public static boolean blockIsADeathChest(Location location) {
        return DEATH_CHESTS.stream().anyMatch(x -> x.getLocation().equals(location));
    }

    public static void spawnDeathChest(Player player) {
        Location deathLocation = player.getLocation();
        World deahtLocationWorld = deathLocation.getWorld();
        Location deathChestLocation = getHighestVerticalLocationPossible(deathLocation);
        deathChestLocation.getBlock().setType(DeathChestModel.MATERIAL);

        Inventory deathChestInventory = Bukkit.createInventory(null, 5*9);
        for(ItemStack item : player.getInventory().getContents()) {
            if(item != null)
                deathChestInventory.addItem(item);
        }

        if (deathChestLocation.getWorld().getEnvironment() == World.Environment.NETHER) {
            //boolean belowPlayerIsBedrock = deahtLocationWorld
        }

        addDeathChest(new DeathChestModel(deathChestInventory, deathChestLocation, player));
        player.sendMessage("Your death chest has been spawned at: " + locationToString(deathChestLocation));
    }

    public static void destroyDeathChest(Location location, Player player) {
        Optional<DeathChestModel> deathChest = getDeathChest(location);
        if (deathChest.isEmpty()) {
            return;
        }

        DeathChestModel deathChestModel = deathChest.get();
        if (!player.getUniqueId().equals(deathChestModel.getOwner().getUniqueId())) {
            player.sendMessage("This is not your death chest");
            return;
        }

        deathChestModel.getLocation().getBlock().setType(Material.AIR);
        ItemStack[] items = deathChestModel.getInventory().getContents();
        for (ItemStack item : items) {
            if (item == null)
                continue;

            deathChestModel.getLocation().getWorld().dropItem(deathChestModel.getLocation(), item);
        }
        removeDeathChest(deathChestModel);
    }

    private static Location getHighestVerticalLocationPossible(Location deathLocation) {
        int maxHeight = deathLocation.getWorld().getMaxHeight() - 1;
        Location possibleLocation = deathLocation.getWorld().getHighestBlockAt(deathLocation).getLocation();
        // Go up until you see air
        // If highest block reached = deathLocation.getWorld().getHighestBlockAt(deathLocation).getLocation();
        if (possibleLocation.getY() >= maxHeight) {
            return possibleLocation;
        }
        return possibleLocation.add(0, 1, 0);
    }

    private static String locationToString(Location location) {
        return String.format("X: %s, Y: %s, Z: %s", location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
