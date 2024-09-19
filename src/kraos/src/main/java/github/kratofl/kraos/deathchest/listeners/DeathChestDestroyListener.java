package github.kratofl.kraos.deathchest.listeners;

import github.kratofl.kraos.deathchest.events.DeathChestDestroyEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DeathChestDestroyListener implements Listener {

    @EventHandler
    public void onDestroy(DeathChestDestroyEvent event) {
        //event.getDeathChest().getLocation().getWorld().drop
    }
}
