package github.kratofl.kraos.deathchest.events;

import github.kratofl.kraos.deathchest.DeathChestModel;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

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
    public @NotNull HandlerList getHandlers() {
        return this.HANDLER_LIST;
    }
}
