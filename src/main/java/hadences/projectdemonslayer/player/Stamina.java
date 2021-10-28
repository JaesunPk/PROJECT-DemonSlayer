package hadences.projectdemonslayer.player;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class Stamina {
    public static boolean checkStamina(Player p, int ability){



        int Stamina = playerdata.get(p.getUniqueId()).getSTAMINA();
        int StaminaReq = 0;
        if(ability == 0){
            StaminaReq = playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY1()).getSTAMINACOST();
        }else if(ability == 1){
            StaminaReq = playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY2()).getSTAMINACOST();
        }else if(ability == 2){
            StaminaReq = playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY3()).getSTAMINACOST();
        }
        if(Stamina < StaminaReq){
            p.sendTitle(" ",ChatColor.RED + "Not Enough Stamina!");
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            return false;
        }

        return true;
    }
}
