package me.simondumalski.brokentools.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemDamageListener implements Listener {

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent e) {

        ItemStack item = e.getItem();
        ItemMeta meta = item.getItemMeta();

        if (meta instanceof Damageable) {

            short maxDurability = item.getType().getMaxDurability();
            int currentDamage = ((Damageable) meta).getDamage();

            if (currentDamage == maxDurability - 1) {
                e.setCancelled(true);
            }

        }

    }

}
