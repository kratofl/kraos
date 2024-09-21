package github.kratofl.kraosspigot.deathchest.events;

import github.kratofl.kraosspigot.deathchest.DeathChestModel;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DeathChestSpawnEvent extends Event {

    private final HandlerList HANDLER_LIST = new HandlerList();
    private final DeathChestModel DEATH_CHEST;

    public DeathChestSpawnEvent(DeathChestModel deathChest) {
        this.DEATH_CHEST = deathChest;
    }

    public DeathChestModel getDeathChest() {
        return DEATH_CHEST;
    }

    @Override
    public HandlerList getHandlers() {
        return this.HANDLER_LIST;
    }
}
