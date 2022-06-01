package me.simondumalski.brokentools;

import me.simondumalski.brokentools.listeners.BlockBreakListener;
import me.simondumalski.brokentools.listeners.ItemDamageListener;
import me.simondumalski.brokentools.listeners.PlayerDamageListener;
import me.simondumalski.brokentools.listeners.PlayerInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        getServer().getPluginManager().registerEvents(new ItemDamageListener(), this);

    }

    @Override
    public void onDisable() {



    }

}
