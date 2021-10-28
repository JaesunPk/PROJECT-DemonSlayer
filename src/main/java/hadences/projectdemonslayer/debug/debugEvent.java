package hadences.projectdemonslayer.debug;

import hadences.projectdemonslayer.ability.universal.UniversalAbilities;
import hadences.projectdemonslayer.item.GameItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class debugEvent implements Listener {
    UniversalAbilities universalAbilities = new UniversalAbilities();
    @EventHandler
    public void lc(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.LEFT_CLICK_AIR){
            universalAbilities.CastRCAbility(p);
            p.sendMessage("test");
        }
    }
}
