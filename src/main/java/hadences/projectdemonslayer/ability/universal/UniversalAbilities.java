package hadences.projectdemonslayer.ability.universal;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.utils.Damage;
import hadences.projectdemonslayer.utils.RayTrace;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static hadences.projectdemonslayer.utils.Damage.cleanTargetList;


public class UniversalAbilities {
    ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    private ArrayList<Entity> hitbox = new ArrayList<>();
    private ArrayList<Location> particles = new ArrayList<>();

    public void CastRCAbility(Player p) {
        ability(p);
    }

    public void ability(Player p) {
        Location peyeloc = p.getEyeLocation();
        Location teleport = RayTrace(p, peyeloc);
        //Movement.Dash(p,1,true,peyeloc);
        playsound(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                p.teleport(teleport);

            }
        }.runTaskLater(ds, 1);
        particleEffect();
    }

    public Location RayTrace(Player p, Location loc) {
        RayTrace rayTrace = new RayTrace(loc.toVector(), loc.getDirection());
        ArrayList<Vector> positions = rayTrace.traverse(8, 0.01);

        for (int i = 0; i < positions.size(); i++) {
            Location position = positions.get(i).toLocation(p.getWorld());
            Block block = p.getWorld().getBlockAt(position);

            List<Entity> target = (List<Entity>) position.getNearbyEntities(1.2, 1.2, 1.2);
            target = cleanTargetList(p, target, false);
            if (target.size() >= 1) {
                dealdamage(p, (ArrayList<Entity>) target);
            }
            particles.add(position);

            if (block != null && block.getType() != Material.AIR) {
                position.setYaw(loc.getYaw());
                position.setPitch(loc.getPitch());
                return position;
            }
            if (i == positions.size() - 1) {
                position.setYaw(loc.getYaw());
                position.setPitch(loc.getPitch());
                return position;
            }
        }
        return null;
    }

    public void dealdamage(Player p, ArrayList<Entity> entitylist) {
        Damage.damageList(p, entitylist, (Double) ds.getConfig().get("Universal.Skills.Slash.Damage"));
    }

    public void playsound(Player p) {
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 0.8f);
    }

    public void particleEffect() {
        for (Location location : particles) {
            location.getWorld().spawnParticle(Particle.REDSTONE, location, 5, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(164, 164, 164), 0.4f));
        }
        particles.clear();
    }

}
