package github.kratofl.kraos.deathchest.events;

import github.kratofl.kraos.deathchest.DeathChestModel;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

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
    public @NotNull HandlerList getHandlers() {
        return this.HANDLER_LIST;
    }
}
