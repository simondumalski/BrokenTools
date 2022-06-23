package me.simondumalski.brokentools;

import me.simondumalski.brokentools.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    @Override
    public void onEnable() {

        //Initialize the instance variable
        instance = this;

        //Register the event listeners
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageEntityListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new ItemDamageListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);

    }

    @Override
    public void onDisable() {



    }

    /**
     * Returns the instance of the main plugin class
     * @return Main plugin class
     */
    public static Main getInstance() {
        return instance;
    }

}
