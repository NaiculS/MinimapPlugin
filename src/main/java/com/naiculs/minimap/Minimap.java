package com.naiculs.minimap;

import com.naiculs.minimap.commands.MapCommand;
import com.naiculs.minimap.listener.PlayerListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Minimap extends JavaPlugin{

    @Override
    public void onEnable() {
        this.getCommand("map").setExecutor(new MapCommand());

        registerEvents(
                new PlayerListener()
        );
    }

    @Override
    public void onDisable() {

    }

    private void registerEvents(final Listener... events) {
        for (final Listener event : events) {
            getServer().getPluginManager().registerEvents(event, this);
        }
    }
}