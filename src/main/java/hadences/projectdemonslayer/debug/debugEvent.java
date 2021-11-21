package hadences.projectdemonslayer.debug;

import hadences.projectdemonslayer.ability.universal.debugAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class debugEvent implements Listener {
    hadences.projectdemonslayer.ability.universal.debugAbility debugAbility = new debugAbility();
    @EventHandler
    public void lc(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.LEFT_CLICK_AIR){
            debugAbility.CastRCAbility(p);
            p.sendMessage("test");
        }
    }
}
