package github.kratofl.kraosspigot.features.deathchest;

import github.kratofl.kraosspigot.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class LocationValidator {

    public static Location getSafeLocation(Location deathLocation) {
        World locationWorld = deathLocation.getWorld();
        Block locationBlock = deathLocation.getBlock();
        Location location = new Location(locationWorld,
                roundToZero(deathLocation.getBlockX()),
                roundToZero(deathLocation.getBlockY()),
                roundToZero(deathLocation.getBlockZ()),
                deathLocation.getYaw(),
                0
        );
        Logger.debug("[LocationValidator] - Rounded: " + location);

        if (isBelowZero(location) && isVoid(location)) {
            Logger.debug("[LocationValidator] - Is below zero and in void");
            // Build platform at minHeight and place gravestone on top
            return new Location(locationWorld, location.getBlockX(), locationWorld.getMinHeight() + 2, location.getBlockZ());
        } else if (!isBelowZero(location) && isVoid(location)) {
            Logger.debug("[LocationValidator] - Is NOT below zero and in void");
            // Build platform at location and place gravestone on top
            return location;
        } else if (isBelowZero(location) && !isVoid(location)) {
            Logger.debug("[LocationValidator] - Is below zero and NOT in void");
            return getHighestLocation(location);
        }

        // Dies mid air - eg. mid jump/fall - CANNOT BE VOID
        if (diedMidAir(location)) {
            Logger.debug("[LocationValidator] - Died mid air");
            return getFirstAirBlockLocationBelow(location);
        }

        // Suffocates/Drowns/Burns
        if (isObstructed(location)) {
            return getHighestLocation(location);
        }

        return location;
    }

    private static boolean isVoid(Location location) {
        Block highestBlock = location.getWorld().getHighestBlockAt(location);
        Logger.debug("[LocationValidator] - Highest block - isVoid: " + highestBlock.getType());
        return highestBlock.getType() == Material.VOID_AIR;
    }

    private static boolean isBelowZero(Location location) {
        int y = location.getBlockY();
        int minHeight = location.getWorld().getMinHeight();
        Logger.debug("[LocationValidator] - Below zero: y: " + y + " | minHeight: " + minHeight);
        return y < minHeight;
    }

    private static boolean isObstructed(Location location) {
        // 1 = it will iterate 2 times, for the player height
        // Feet obstructed / Head obstructed
        for (int i = 1; i < 2; i++) {
            Logger.debug("[LocationValidator] - Is obstructed??: y: " + location.getBlockY());
            if (location.add(0, i, 0).getBlock().getType() != Material.AIR) {
                Logger.debug("[LocationValidator] - Is obstructed!! - " + location);
                return true;
            }
        }
        return false;
    }


    private static Location getFirstAirBlockLocationBelow(Location location) {
        Location blockLocation = new Location(location.getWorld(),
                location.getX(),
                location.getY(),
                location.getZ());
        for (int i = location.getBlockY(); i > location.getWorld().getMinHeight(); i--) {
            blockLocation.setY(i);
            if (blockLocation.getBlock().getType() != Material.AIR) {
                return blockLocation.add(0, 1, 0);
            }
        }
        // SHOULD never happen
        return location;
    }

    private static Location getHighestLocation(Location location) {
        Location highestBlockLoc = location.getWorld().getHighestBlockAt(location).getLocation();
        if (location.getBlockY() >= location.getWorld().getMaxHeight()) {
            Logger.debug("getHighestLocation - above max height: Y: " + highestBlockLoc.getBlockY() + " | maxheight: " + location.getWorld().getMaxHeight());
            return highestBlockLoc;
        }
        Logger.debug("getHighestLocation - below max height: Y: " + highestBlockLoc.getBlockY());
        return highestBlockLoc.add(0, 1, 0);
    }

    private static boolean diedMidAir(Location location) {
        return location.subtract(0, 1, 0).getBlock().getType() == Material.AIR;
    }

    private static double roundToHalf(double val) {
        return roundToZero(val) + 0.5;
    }

    private static double roundToZero(double val) {
        return Math.floor(val);
    }
}
