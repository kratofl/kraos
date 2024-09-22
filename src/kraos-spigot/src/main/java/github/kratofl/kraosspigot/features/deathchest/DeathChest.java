package github.kratofl.kraosspigot.features.deathchest;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import github.kratofl.kraosspigot.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
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
    private final Location location;

    public DeathChest(Location location, Player player) {
        this.player = player;
        Inventory deathChestInventory = Bukkit.createInventory(null, 5 * 9);
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null)
                deathChestInventory.addItem(item);
        }
        deathChestInventory.addItem(this.getPlayerHead());
        this.inventory = deathChestInventory;
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public Player getOwner() {
        return this.player;
    }

    public static Material getChestMaterial() {
        return Material.PLAYER_HEAD;
    }

    public void spawn(Location location) {
        this.setChestHead(location);
        DeathChestHandler.addChest(this);
    }

    private void setChestHead(Location location) {
        Block block = location.getBlock();
        block.setType(Material.PLAYER_HEAD);

        Skull skull = (Skull) block.getState();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "");
        gameProfile.getProperties().put("textures", new Property("textures", CHEST_TEXTURE));
        Logger.debug(gameProfile.toString());

        try {
            Field profileField = skull.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            Logger.debug(profileField.toString());

            Class<?> profileType = profileField.getType();
            Logger.debug(profileType.toString());
            if (profileType.getSimpleName().equals("ResolvableProfile")) {
                Constructor<?> constructor = profileType.getConstructor(GameProfile.class);
                Logger.debug(constructor.toString());
                Object profile = constructor.newInstance(gameProfile);
                Logger.debug(profile.toString());
                profileField.set(skull, profile);
                Logger.debug(profileField.toString());
            }
            else {
                profileField.set(skull, gameProfile);
                Logger.debug("ELSE: " + profileField.toString());
            }
        }
        catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException exception) {
            exception.printStackTrace();
            Logger.debug(exception.toString());
        }
        skull.update();
    }

    private void spawnHologram() {

    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }

    public void destroy() {
        Optional<DeathChest> deathChest = DeathChestHandler.getChest(location);
        if (deathChest.isEmpty()) {
            return;
        }

        DeathChest deathChestModel = deathChest.get();
        if (!player.getUniqueId().equals(deathChestModel.getOwner().getUniqueId())) {
            player.sendMessage("This is not your death chest");
            return;
        }

        this.location.getBlock().setType(Material.AIR);
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
