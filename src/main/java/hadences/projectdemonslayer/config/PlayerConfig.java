package hadences.projectdemonslayer.config;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class PlayerConfig {
    private static ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    public static void updatePlayerConfig(){
        String Name = "";
        String Wins = "";

        // Plugin shutdown logic
        for(UUID p : playerdata.keySet()) {

            Name = playerdata.get(p).getPLAYER_NAME();
            Wins = playerdata.get(p).getWINS() + "";

            ds.getConfig().set("Users." + p + ".NAME", Name);
            ds.getConfig().set("Users." + p + ".WINS", Wins);
            ds.saveConfig();
        }
    }

    public static boolean CheckPlayerInConfig(Player p){
        if (ds.getConfig().getConfigurationSection("Users").getKeys(false).isEmpty())
            return false;
        for (String key : ds.getConfig().getConfigurationSection("Users").getKeys(false)) {
            if (key.equals(p.getUniqueId().toString())) {
                return true;
            }
        }

        return false;
    }

    public static void AddPlayerToConfig(Player p){
        ds.getConfig().set("Users." + p.getUniqueId() + ".NAME", p.getName());
        ds.getConfig().set("Users." + p.getUniqueId() + ".WINS", "0");
    }

}
