package me.simondumalski.brokentools.listeners;

import me.simondumalski.brokentools.managers.MessageManager;
import me.simondumalski.brokentools.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.concurrent.ThreadLocalRandom;

public class EntityDamageEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent e) {

        //Get the attacker entity
        Entity attacker = e.getDamager();

        //Check if the attacker is a player
        if (attacker.getType() != EntityType.PLAYER) {
            return;
        }

        if (attacker instanceof Player) {

            Player player = (Player) attacker;

            //Get the item and item meta
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();

            //Check if the item is damageable
            if (meta instanceof Damageable) {

                short maxDurability = item.getType().getMaxDurability();
                int currentDamage = ((Damageable) meta).getDamage();

                if (currentDamage == maxDurability - 1) {
                    int damage = ThreadLocalRandom.current().nextInt(0, 2);
                    e.setDamage(damage);
                    player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
                    MessageManager.message(player, Message.TOOL_BREAK, new String[]{MessageManager.beautifyMaterialName(item.getType().name())});
                }

            }

        }

    }

}
