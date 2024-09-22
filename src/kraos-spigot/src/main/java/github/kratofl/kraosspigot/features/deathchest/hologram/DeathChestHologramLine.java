package github.kratofl.kraosspigot.features.deathchest.hologram;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.UUID;

public class DeathChestHologramLine {

    private final UUID armorStand;
    private final Location location;

    public DeathChestHologramLine(Location location, String text) {
        ArmorStand stand = location.getWorld().spawn(location, ArmorStand.class);
        stand.setMarker(true);
        stand.setInvisible(true);
        stand.setCustomName(text);
        stand.setCustomNameVisible(true);
        stand.setPersistent(false);
        this.armorStand = stand.getUniqueId();
        this.location = location;
    }

    public void remove() {
        ArmorStand stand = getArmorStand();
        if (stand == null || !stand.isValid()) return;
        stand.remove();
    }

    public Location getLocation() {
        return this.location.clone();
    }

    public ArmorStand getArmorStand() {
        return (ArmorStand) Bukkit.getEntity(armorStand);
    }
}
