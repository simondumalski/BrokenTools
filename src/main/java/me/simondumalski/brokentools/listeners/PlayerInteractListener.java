package me.simondumalski.brokentools.listeners;

import me.simondumalski.brokentools.managers.MessageManager;
import me.simondumalski.brokentools.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        //Get the player
        Player player = e.getPlayer();

        //Check if it was a right-click
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        //Get the item and item meta
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        if (
                item.getType() != Material.WOODEN_HOE
                && item.getType() != Material.STONE_HOE
                && item.getType() != Material.IRON_HOE
                && item.getType() != Material.GOLDEN_HOE
                && item.getType() != Material.DIAMOND_HOE
                && item.getType() != Material.NETHERITE_HOE

                && item.getType() != Material.WOODEN_SHOVEL
                && item.getType() != Material.STONE_SHOVEL
                && item.getType() != Material.IRON_SHOVEL
                && item.getType() != Material.GOLDEN_SHOVEL
                && item.getType() != Material.DIAMOND_SHOVEL
                && item.getType() != Material.NETHERITE_SHOVEL
        ) {
            return;
        }

        if (meta instanceof Damageable) {

            short maxDurability = item.getType().getMaxDurability();
            int currentDamage = ((Damageable) meta).getDamage();

            if (currentDamage == maxDurability - 1) {
                e.setCancelled(true);
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
                MessageManager.message(player, Message.TOOL_BREAK, new String[]{MessageManager.beautifyMaterialName(item.getType().name())});
            }

        }

    }

}
