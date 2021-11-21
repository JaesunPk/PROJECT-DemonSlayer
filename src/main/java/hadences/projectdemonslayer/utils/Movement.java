package hadences.projectdemonslayer.utils;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Movement {
    private static ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);

    public static void Dash(Player p, double multiplier, boolean directional, Location eyeLoc) {
        p.setVelocity(new Vector(eyeLoc.getDirection().getX(), 0.2, eyeLoc.getDirection().getZ()).normalize());
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!directional)
                    p.setVelocity(new Vector(eyeLoc.getDirection().getX(), 0, eyeLoc.getDirection().getZ()).normalize().multiply(multiplier));
                else
                    p.setVelocity(new Vector(eyeLoc.getDirection().getX(), eyeLoc.getDirection().getZ(), eyeLoc.getDirection().getZ()).normalize().multiply(multiplier));

            }
        }.runTaskLater(ds, 2);
    }
}
