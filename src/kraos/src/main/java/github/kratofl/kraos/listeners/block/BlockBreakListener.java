package github.kratofl.kraos.listeners.block;

import github.kratofl.kraos.deathchest.DeathChestHandler;
import github.kratofl.kraos.deathchest.DeathChestModel;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (block.getType() != DeathChestModel.MATERIAL)
            return;

        event.setCancelled(true);
        DeathChestHandler.destroyDeathChest(block.getLocation(), event.getPlayer());
    }
}
