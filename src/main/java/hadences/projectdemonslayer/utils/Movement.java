package hadences.projectdemonslayer.utils;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Movement {
    private static ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    public static void Dash(Player p, double multiplier){
        p.setVelocity(new Vector(p.getEyeLocation().getDirection().getX(), 0.2, p.getEyeLocation().getDirection().getZ()).normalize());
        new BukkitRunnable(){
            @Override
            public void run() {
                p.setVelocity(new Vector(p.getEyeLocation().getDirection().getX(), 0, p.getEyeLocation().getDirection().getZ()).normalize().multiply(multiplier));
            }
        }.runTaskLater(ds,2);
    }
}
