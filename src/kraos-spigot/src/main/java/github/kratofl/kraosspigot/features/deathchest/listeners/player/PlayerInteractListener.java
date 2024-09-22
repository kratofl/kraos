package github.kratofl.kraosspigot.features.deathchest.listeners.player;

import github.kratofl.kraosspigot.features.deathchest.DeathChest;
import github.kratofl.kraosspigot.features.deathchest.DeathChestHandler;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Optional;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onBlockBreak(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

        if (block == null)
            return;
        if (block.getType() != DeathChest.getChestMaterial())
            return;

        if (DeathChestHandler.blockIsDeathChest(block.getLocation())) {
            Optional<DeathChest> dc = DeathChestHandler.getChest(block.getLocation());
            dc.get().open(event.getPlayer());
        }
    }
}
