package me.simondumalski.brokentools.listeners;

import me.simondumalski.brokentools.managers.MessageManager;
import me.simondumalski.brokentools.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {

        //Get the Entity that was damaged
        Entity entity = e.getEntity();

        //Check if the Entity is a player
        if (entity.getType() != EntityType.PLAYER) {
            return;
        }

        //Get the player being damaged
        Player player = (Player) entity;

        //Check if the player is wearing any broken armor
        for (ItemStack item : player.getInventory().getArmorContents()) {

            if (item == null) {
                continue;
            }

            if (!item.hasItemMeta()) {
                continue;
            }

            ItemMeta meta = item.getItemMeta();

            if (meta instanceof Damageable) {

                short maxDurability = item.getType().getMaxDurability();
                int currentDamage = ((Damageable) meta).getDamage();

                if (currentDamage == maxDurability - 1) {

                    switch (item.getType()) {

                        case LEATHER_HELMET, IRON_HELMET, GOLDEN_HELMET, DIAMOND_HELMET, NETHERITE_HELMET, CHAINMAIL_HELMET -> {
                            player.getInventory().setHelmet(null);
                        }

                        case LEATHER_CHESTPLATE, IRON_CHESTPLATE, GOLDEN_CHESTPLATE, DIAMOND_CHESTPLATE, NETHERITE_CHESTPLATE, CHAINMAIL_CHESTPLATE -> {
                            player.getInventory().setChestplate(null);
                            player.sendMessage(ChatColor.RED + "Your chestplate is too broken and fell off!");
                        }

                        case LEATHER_LEGGINGS, IRON_LEGGINGS, GOLDEN_LEGGINGS, DIAMOND_LEGGINGS, NETHERITE_LEGGINGS, CHAINMAIL_LEGGINGS -> {
                            player.getInventory().setLeggings(null);
                            player.sendMessage(ChatColor.RED + "Your leggings are too broken and fell off!");
                        }

                        case LEATHER_BOOTS, IRON_BOOTS, GOLDEN_BOOTS, DIAMOND_BOOTS, NETHERITE_BOOTS, CHAINMAIL_BOOTS -> {
                            player.getInventory().setBoots(null);
                            player.sendMessage(ChatColor.RED + "Your boots are too broken and fell off!");
                        }

                    }

                    MessageManager.message(player, Message.ARMOR_BREAK, new String[]{MessageManager.beautifyMaterialName(item.getType().name())});
                    player.getWorld().dropItemNaturally(player.getLocation(), item);

                }

            }

        }


    }

}
