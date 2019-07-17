import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import java.util.ArrayList;

public class MyListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int playerX = player.getLocation().getBlockX();
        int playerZ = player.getLocation().getBlockZ();
        World playerWorld = player.getWorld();
        ItemStack map = new ItemStack(Material.MAP);
        MapMeta myMapMeta = ((MapMeta)map.getItemMeta());
        final MapView myMapView = Bukkit.createMap(player.getWorld());
        //MapView myMapView = ((MapView)map.getItemMeta());
        myMapMeta.setDisplayName(ChatColor.BLUE + "Project Korra Map");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Left Click to recenter map");
        myMapMeta.setLore(lore);
        myMapMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        myMapMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        //TODO uncomment if in 1.13 | myMapMeta.setColor(Color.BLUE);
        // TODO find a way to load whole map prior to giving to player so that the map given can be scale farthest
        myMapView.setScale(MapView.Scale.CLOSEST);
        myMapView.setCenterX(playerX);
        myMapView.setCenterZ(playerZ);
        myMapView.setWorld(playerWorld);
        map.setItemMeta(myMapMeta);
        map.setDurability((short) myMapView.getId());
        if (player.getInventory().contains(Material.MAP)){

        }
        else{
            if(event.getPlayer().getInventory().firstEmpty() != -1) {
                if (player.getInventory().getItem(8) == null) {
                    player.getInventory().setItem(8, map);
                } else {
                    player.getInventory().addItem(map);
                }
            }
        }
    }
    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        Item drop = event.getItemDrop();
        //TODO remove this line ItemStack map = new ItemStack(Material.MAP);
        if(drop.getItemStack().getTypeId() == 358){
            event.getItemDrop().remove();
        }
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        HumanEntity humanEntity = event.getPlayer();
        ItemStack itemInHand = event.getItem();
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK){
            if (event.hasItem() && event.getPlayer().getItemInHand().getType().equals(Material.MAP)){
                int playerX = player.getLocation().getBlockX();
                int playerZ = player.getLocation().getBlockZ();
                World playerWorld = player.getWorld();
                ItemStack map = new ItemStack(Material.MAP);
                MapMeta myMapMeta = ((MapMeta)map.getItemMeta());
                final MapView myMapView = Bukkit.createMap(player.getWorld());
                //MapView myMapView = ((MapView)map.getItemMeta());
                myMapMeta.setDisplayName(ChatColor.BLUE + "Project Korra Map");
                ArrayList<String> lore = new ArrayList<String>();
                lore.add("Left Click to recenter map");
                myMapMeta.setLore(lore);
                myMapMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                myMapMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                //TODO uncomment if in 1.13 | myMapMeta.setColor(Color.BLUE);
                // TODO find a way to load whole map prior to giving to player so that the map given can be scale farthest
                myMapView.setScale(MapView.Scale.CLOSEST);
                myMapView.setCenterX(playerX);
                myMapView.setCenterZ(playerZ);
                myMapView.setWorld(playerWorld);
                map.setItemMeta(myMapMeta);
                map.setDurability((short) myMapView.getId());
                player.getInventory().setItemInHand(map);
            }
        }
    }

    /*
    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        HumanEntity player = event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        if (clicked.getTypeId() == 358 && !(player.getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void updateMap(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location originalLocation = event.getFrom();
        Location newLocation = event.getTo();
        if (originalLocation != newLocation) {
            int playerX = player.getLocation().getBlockX();
            int playerZ = player.getLocation().getBlockZ();
            World playerWorld = player.getWorld();
            ItemStack map = new ItemStack(Material.MAP);
            MapMeta myMapMeta = ((MapMeta) map.getItemMeta());
            final MapView myMapView = Bukkit.createMap(player.getWorld());
            //MapView myMapView = ((MapView)map.getItemMeta());
            myMapMeta.setDisplayName(ChatColor.BLUE + "Project Korra Map");
            //TODO uncomment if in 1.13 | myMapMeta.setColor(Color.BLUE);
            myMapView.setScale(MapView.Scale.FAR);
            myMapView.setCenterX(playerX);
            myMapView.setCenterZ(playerZ);
            myMapView.setWorld(playerWorld);
            map.setItemMeta(myMapMeta);
            map.setDurability((short) myMapView.getId());
            for(int i = 0; i<player.getInventory().getSize()-1; ++i) {
                ItemStack item = player.getInventory().getItem(i);
                if(item.getType().equals(Material.MAP)){
                    player.getInventory().setItem(i, map);
                }
                else if (item.getType().equals(null)){
                    System.out.println("empty slotttttttt");
                }
            }
        }
    }*/
}