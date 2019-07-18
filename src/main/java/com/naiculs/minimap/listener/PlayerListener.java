package com.naiculs.minimap.listener;

import com.naiculs.minimap.items.MinimapItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        MinimapItem.addToPlayerInventory(event.getPlayer());
    }

    @EventHandler
    public void onPlayerDrop(final PlayerDropItemEvent event) {
        final ItemStack drop = event.getItemDrop().getItemStack();
        if (drop.getType() == Material.MAP && drop.getItemMeta().getDisplayName() != null
                && drop.getItemMeta().getDisplayName().equals(MinimapItem.getDisplayName())) {
            event.getItemDrop().remove();
        }
    }

    @EventHandler
    public void onPlayerClick(final PlayerInteractEvent event) {
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) {
            return;
        }

        ItemStack itemInHand = event.getItem();
        if (itemInHand != null && itemInHand.getType() == Material.MAP && itemInHand.getItemMeta().getDisplayName() != null
                && itemInHand.getItemMeta().getDisplayName().equals(MinimapItem.getDisplayName())) {

            itemInHand = MinimapItem.addPlayerCenterPositionToMap(event.getPlayer(), itemInHand);
        }
    }
}