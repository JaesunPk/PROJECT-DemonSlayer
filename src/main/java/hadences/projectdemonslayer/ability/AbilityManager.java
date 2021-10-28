package hadences.projectdemonslayer.ability;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.chat.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class AbilityManager {
    private static ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    public static String getInitialAbilityMessage() {
        return ChatColor.DARK_AQUA + "You used ";
    }




}