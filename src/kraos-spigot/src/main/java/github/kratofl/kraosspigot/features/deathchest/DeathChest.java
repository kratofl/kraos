package github.kratofl.kraosspigot.features.deathchest;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import github.kratofl.kraosspigot.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.block.data.Rotatable;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.UUID;

public class DeathChest {

    private static final String CHEST_TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWYyMjFiMzNmNWIzOWU5OWVlNmZkMzQzYWJhYWE5YWJkZjY2ZDkzZDQzMDZjZjAxY2NhOWYyMDJlODc3M2ZkNiJ9fX0=";

    private final Player player;
    private final Inventory inventory;
    private final Location deathLocation;
    private final Location chestLocation;

    public DeathChest(Player player) {
        this.player = player;
        Inventory deathChestInventory = Bukkit.createInventory(null, 5*9, player.getName());
        deathChestInventory.setContents(player.getInventory().getContents());
        deathChestInventory.addItem(this.getPlayerHead());
        this.inventory = deathChestInventory;

        Location playerLocation = player.getLocation();
        this.deathLocation = new Location(player.getWorld(),
                playerLocation.getX(),
                playerLocation.getY(),
                playerLocation.getZ(),
                playerLocation.getYaw(),
                0
        );
        this.chestLocation = LocationValidator.getSafeLocation(this.deathLocation);
    }

    public Location getChestLocation() {
        return this.chestLocation;
    }

    public Player getOwner() {
        return this.player;
    }

    public static Material getChestMaterial() {
        return Material.PLAYER_HEAD;
    }

    public void spawn() {
        this.setChestHead();
        DeathChestHandler.addChest(this);
    }

    private void setChestHead() {
        Block block = this.chestLocation.getBlock();
        block.setType(Material.PLAYER_HEAD);

        Logger.debug("Chest Loc: " + this.chestLocation);
        Skull skull = (Skull) block.getState();
        Rotatable skullRotate = (Rotatable) block.getBlockData();

        skullRotate.setRotation(DeathChest.getYawBlockFace(this.chestLocation.getYaw()).getOppositeFace());
        skull.setBlockData(skullRotate);

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "");
        gameProfile.getProperties().put("textures", new Property("textures", CHEST_TEXTURE));
        Logger.debug(gameProfile.toString());

        try {
            Field profileField = skull.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);

            Class<?> profileType = profileField.getType();
            if (profileType.getSimpleName().equals("ResolvableProfile")) {
                Constructor<?> constructor = profileType.getConstructor(GameProfile.class);
                Object profile = constructor.newInstance(gameProfile);
                profileField.set(skull, profile);
            }
            else {
                profileField.set(skull, gameProfile);
            }
        }
        catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException exception) {
            exception.printStackTrace();
            Logger.debug(exception.toString());
        }
        skull.update();
    }
    public static BlockFace getYawBlockFace(float yaw) {
        float direction = yaw % 360;

        if (direction < 0) {
            direction += 360;
        }

        return switch (Math.round(direction / 45)) {
            case 1 -> BlockFace.SOUTH_WEST;
            case 2 -> BlockFace.WEST;
            case 3 -> BlockFace.NORTH_WEST;
            case 4 -> BlockFace.NORTH;
            case 5 -> BlockFace.NORTH_EAST;
            case 6 -> BlockFace.EAST;
            case 7 -> BlockFace.SOUTH_EAST;
            default -> BlockFace.SOUTH;
        };
    }

    private void spawnHologram() {

    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }

    public void destroy() {
        Optional<DeathChest> deathChest = DeathChestHandler.getChest(chestLocation);
        if (deathChest.isEmpty()) {
            return;
        }

        DeathChest deathChestModel = deathChest.get();
        if (!player.getUniqueId().equals(deathChestModel.getOwner().getUniqueId())) {
            player.sendMessage("This is not your death chest");
            return;
        }

        this.chestLocation.getBlock().setType(Material.AIR);
        DeathChestHandler.removeChest(deathChestModel);
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

    private ItemStack getPlayerHead() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setOwningPlayer(this.player);
        item.setItemMeta(skull);
        return item;
    }
}
