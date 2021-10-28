package hadences.projectdemonslayer.ability;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Cooldown {
    public static HashMap<String, Long> cooldowns = new HashMap<>();
    public static HashMap<String, Long> cooldowns2 = new HashMap<>();
    public static HashMap<String, Long> cooldowns3 = new HashMap<>();

    public static boolean checkCD(Player p,HashMap<String ,Long> cd){
        return Check(p, cd);
    }

    private static boolean Check(Player p, HashMap<String, Long> cooldowns) {
        if(cooldowns.containsKey(p.getName())) {
            if (cooldowns.get(p.getName()) > System.currentTimeMillis()) {
                long timeleft = (cooldowns.get(p.getName()) - System.currentTimeMillis()) / 1000;
                p.sendTitle("", ChatColor.RED + "Ability is on cooldown : " + ChatColor.GOLD + timeleft);
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5f, 1f);
                return false;
            }
        }
        return true;
    }


    public static void init(Player p){
        cooldowns.put(p.getName(), System.currentTimeMillis() + (1* 1000));
        cooldowns2.put(p.getName(), System.currentTimeMillis() + (1 * 1000));
        cooldowns3.put(p.getName(), System.currentTimeMillis() + (1 * 1000));
    }
}
