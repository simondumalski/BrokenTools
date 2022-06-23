package me.simondumalski.brokentools.managers;

import me.simondumalski.brokentools.Main;
import me.simondumalski.brokentools.utils.Message;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {

    private static final Main plugin = Main.getInstance();

    public static void message(Player player, Message configValue, String[] args) {

        //Get the message from the config.yml
        String message = plugin.getConfig().getString(configValue.getConfigValue());

        //Check if the message is null
        if (message == null) {
            switch (configValue.name()) {
                case "TOOL_BREAK" -> message = "&cYour &e%item% &cis broken! Repair it before using it again.";
                case "ARMOR_BREAK" -> message = "&cYour &e%item% &cfell off because it''s broken! Repair it before wearing it again.";
                default -> message = "MESSAGE NOT SET";
            }
        }

        //Replace the %item% placeholder
        if (message.contains("%item%") && args != null && args.length > 0) {
            message = message.replace("%item%", args[0]);
        }

        //Translate the colour codes
        message = ChatColor.translateAlternateColorCodes('&', message);

        //Send the message
        player.sendMessage(message);

    }

    /**
     * Takes in a material name and removes the _ and capitalizes the letters
     * @param name Material name
     * @return Beautified material name
     */
    public static String beautifyMaterialName(String name) {

        if (name.contains("_")) {
            name = name.replace("_", " ");
        }

        return WordUtils.capitalizeFully(name);
    }

}
