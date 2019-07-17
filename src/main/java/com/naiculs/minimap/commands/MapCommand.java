package com.naiculs.minimap.commands;

import com.naiculs.minimap.items.MinimapItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            return MinimapItem.addToPlayerInventory((Player) sender);
        }
        return false;
    }
}
