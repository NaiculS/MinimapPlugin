import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import java.util.ArrayList;


public class MapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
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
                if(player.getInventory().firstEmpty() != -1) {
                    if (player.getInventory().getItem(8) == null) {
                        player.getInventory().setItem(8, map);
                    } else {
                        player.getInventory().addItem(map);
                    }
                }
            }

        }

        return true;
    }
}
