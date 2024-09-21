package github.kratofl.kraosspigot.deathchest.events;

import github.kratofl.kraosspigot.deathchest.DeathChestModel;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DeathChestDestroyEvent extends Event {

    private final HandlerList HANDLER_LIST = new HandlerList();
    private final DeathChestModel DEATH_CHEST;
    private final Player PLAYER;

    public DeathChestDestroyEvent(DeathChestModel deathChest, Player player) {
        this.DEATH_CHEST = deathChest;
        this.PLAYER = player;
    }

    //public Player getBlockDestroyer
    public DeathChestModel getDeathChest() {
        return DEATH_CHEST;
    }

    @Override
    public HandlerList getHandlers() {
        return this.HANDLER_LIST;
    }
}
