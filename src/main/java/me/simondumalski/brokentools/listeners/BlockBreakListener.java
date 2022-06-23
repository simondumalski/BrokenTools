package me.simondumalski.brokentools.listeners;

import me.simondumalski.brokentools.managers.MessageManager;
import me.simondumalski.brokentools.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        //Get the player
        Player player = e.getPlayer();

        //Get the item the player is holding
        ItemStack item = player.getInventory().getItemInMainHand();

        //Check if the held item is a block
        if (item.getType().isBlock()) {
            return;
        }

        //Check if the held item is a flint and steel
        if (item.getType() == Material.FLINT_AND_STEEL) {
            return;
        }

        //Get the item meta
        ItemMeta meta = item.getItemMeta();

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
