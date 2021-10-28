package hadences.projectdemonslayer.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class Damage {
    public static void damageList(Player p, ArrayList<Entity> entities, double hearts){
        entities = (ArrayList<Entity>) cleanTargetList(p,entities,true);
        for(Entity e : entities){
            if(e instanceof Player){
                ((LivingEntity) e).damage(hearts,p);

            }else{
                try{
                    ((LivingEntity) e).damage(hearts,p);
                }catch (Exception exception){
                }
            }
        }
    }


    public static List<Entity> cleanTargetList(Player p, List<Entity> target, boolean includeTeam){
        List<Entity> toRemove = new ArrayList<>();
        if(target.contains(p)) toRemove.add(p);
        if(!target.isEmpty())
            for(Entity e : target){
                if(e instanceof Player){
                    if(((Player) e).getGameMode() == GameMode.SPECTATOR || ((Player) e).getGameMode() == GameMode.CREATIVE){
                        toRemove.add(e);
                    }
                    if(includeTeam == false && playerdata.get(p.getUniqueId()).getTEAM() == playerdata.get(e.getUniqueId()).getTEAM()){
                        toRemove.add(e);
                    }
                }
            }
        target.removeAll(toRemove);
        toRemove.clear();
        return target;
    }
}
