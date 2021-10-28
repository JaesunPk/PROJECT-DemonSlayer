package hadences.projectdemonslayer.game;

import hadences.projectdemonslayer.ability.Cooldown;
import hadences.projectdemonslayer.ability.universal.UniversalAbilities;
import hadences.projectdemonslayer.player.Stamina;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

import java.util.HashMap;

import static hadences.projectdemonslayer.ability.Cooldown.*;
import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class GameEvent implements Listener {
    @EventHandler
    public void LeftClickKatana(PlayerInteractEvent e){
        UniversalAbilities universalAbilities = new UniversalAbilities();
        Player p = e.getPlayer();
        if(!playerdata.get(p.getUniqueId()).isABILITY_USAGE()) return;
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
            universalAbilities.CastRCAbility(p);
            return;
        }
    }

    @EventHandler
    public void CastAbility(PlayerItemHeldEvent e){
        Player p = e.getPlayer();
        if(p.getInventory().getItem(e.getNewSlot()) == null) return;
        if(playerdata.get(p.getUniqueId()).getBREATHING() == null) return;
        if(playerdata.get(p.getUniqueId()).isIN_LOBBY() == true) return;
        if(playerdata.get(p.getUniqueId()).isIN_LOBBY()== false && playerdata.get(p.getUniqueId()).isIN_GAME() == false) return;
        if(playerdata.get(p.getUniqueId()).isIN_GAME() == false || playerdata.get(p.getUniqueId()).isABILITY_USAGE() == false){
            p.sendMessage(ChatColor.GOLD + "[" +ChatColor.RED  + "!" + ChatColor.GOLD + "] " + ChatColor.WHITE + "You cannot use abilities!");
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5f, 1f);
            e.setCancelled(true);
            return;
        }
        if(e.getNewSlot() == 0 || e.getNewSlot() == 1 || e.getNewSlot() == 2){
            if(!e.getPlayer().getInventory().getItem(e.getNewSlot()).getItemMeta().getLocalizedName().equalsIgnoreCase("skill")){
                e.setCancelled(true);
                return;
            }

            if(e.getNewSlot() == 0)
                CastAbility(p,cooldowns,e.getNewSlot());
            if(e.getNewSlot() == 1)
                CastAbility(p,cooldowns2,e.getNewSlot());
            if(e.getNewSlot() == 2)
                CastAbility(p,cooldowns3,e.getNewSlot());

            e.setCancelled(true);
        }
    }

    public void CastAbility(Player p, HashMap<String,Long> cd, int slot) {
        if (!Cooldown.checkCD(p, cd)) return;
        if (!Stamina.checkStamina(p, slot)) return;
        if (!playerdata.get(p.getUniqueId()).isABILITY_USAGE()) return;
        if (useAbility(slot, p)) {
            cd.put(p.getName(), System.currentTimeMillis() + (getCooldown(p,slot) * 1000));
            playerdata.get(p.getUniqueId()).setSTAMINA(playerdata.get(p.getUniqueId()).getSTAMINA() - (int) getStamina(p,slot));
        }
    }

    public int getCooldown(Player p,int slot){
        if(slot == 0) return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY1()).getCOOLDOWN();
        else if(slot == 1) return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY2()).getCOOLDOWN();
        else if (slot == 2) return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY3()).getCOOLDOWN();
        else return 0;

    }

    public double getStamina(Player p,int slot){
        if(slot == 0) return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY1()).getSTAMINACOST();
        else if(slot == 1) return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY2()).getSTAMINACOST();
        else if(slot == 2) playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getAbility(playerdata.get(p.getUniqueId()).getABILITY3()).getSTAMINACOST();

        return 0.0;
    }

    public boolean useAbility(int slot, Player p){
        if(slot == 0)
            return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().CastAbility(p,playerdata.get(p.getUniqueId()).getABILITY1());
        if(slot == 1)
            return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().CastAbility(p,playerdata.get(p.getUniqueId()).getABILITY2());
        if(slot == 2)
            return playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().CastAbility(p,playerdata.get(p.getUniqueId()).getABILITY3());
        return false;

    }

}
