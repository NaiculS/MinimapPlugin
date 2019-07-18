package com.naiculs.minimap.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import java.util.Collections;

public class MinimapItem {
    private static final String MAP_DISPLAY_NAME = ChatColor.BLUE + "Project Korra Map";

    private static final ItemStack item;

    static {
        final ItemStack map = new ItemStack(Material.MAP);
        final MapMeta mapMeta = (MapMeta) map.getItemMeta();

        mapMeta.setDisplayName(MAP_DISPLAY_NAME);
        mapMeta.setLore(Collections.singletonList("Left Click to recenter map"));
        mapMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        mapMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        //TODO uncomment if in 1.13 | mapMeta.setColor(Color.BLUE);
        // TODO find a way to load whole map prior to giving to player so that the map given can be scale farthest

        map.setItemMeta(mapMeta);

        item = map;
    }

    public static ItemStack getItem() {
        return item;
    }

    private static ItemStack getItemWithPlayerCentered(final Player pl) {
        return addPlayerCenterPositionToMap(pl, item);
    }

    public static String getDisplayName() {
        return MAP_DISPLAY_NAME;
    }

    public static ItemStack addPlayerCenterPositionToMap(final Player pl, final ItemStack map) {
        if (map.getType() != Material.MAP) {
            return map;
        }

        final MapView mapView = Bukkit.createMap(pl.getWorld());

        mapView.setScale(MapView.Scale.CLOSEST);
        mapView.setCenterX(pl.getLocation().getBlockX());
        mapView.setCenterZ(pl.getLocation().getBlockZ());
        mapView.setWorld(pl.getWorld());

        map.setDurability(mapView.getId());
        return map;
    }

    public static boolean addToPlayerInventory(final Player pl) {
        boolean foundMinimap = false;
        for (final ItemStack itemStack : pl.getInventory().getContents()) {
            if (itemStack != null && itemStack.getType() == Material.MAP && itemStack.getItemMeta().getDisplayName() != null
                    && itemStack.getItemMeta().getDisplayName().equals(MinimapItem.getDisplayName())) {
                foundMinimap = true;
                break;
            }
        }

        if (!foundMinimap && pl.getInventory().firstEmpty() != -1) {
            final ItemStack map = MinimapItem.getItemWithPlayerCentered(pl);

            if (pl.getInventory().getItem(8) == null) {
                pl.getInventory().setItem(8, map);
            } else {
                pl.getInventory().addItem(map);
            }
            return true;
        }
        return false;
    }
}


