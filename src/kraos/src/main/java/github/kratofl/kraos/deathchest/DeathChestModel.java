package github.kratofl.kraos.deathchest;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class DeathChestModel {

    public static final Material MATERIAL = Material.PISTON;

    private final Player player;
    public DeathChestModel(@NotNull Player player) {
        this.player = player;
    }

    public Inventory getInventory() {
        return player.getInventory();
    }

    public Location getLocation() {
        return player.getLocation();
    }

    public Player getOwner() {
        return player;
    }
}
