package github.kratofl.kraosspigot.features.inventorysort.listeners;

import github.kratofl.kraosspigot.features.inventorysort.InventorySort;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        ClickType clickType = event.getClick();

        if (clickType == InventorySort.BUTTON_TO_SORT) {

        }
    }
}
