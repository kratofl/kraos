package github.kratofl.kraosspigot.features.deathchest;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DeathChestModel {

    public static final Material MATERIAL = Material.PISTON;

    private final Player player;
    private final Inventory inventory;
    private final Location location;
    public DeathChestModel(Inventory inventory, Location location, Player player) {
        this.player = player;
        this.inventory = inventory;
        this.location = location;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Location getLocation() {
        return this.location;
    }

    public Player getOwner() {
        return this.player;
    }
}
