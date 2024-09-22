package github.kratofl.kraosspigot.features.deathchest.listeners.player;

import github.kratofl.kraosspigot.features.deathchest.DeathChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        DeathChest dc = new DeathChest(player.getLocation(), player);
        dc.spawn(player.getLocation());
        event.getDrops().removeAll(event.getDrops());
    }
}
