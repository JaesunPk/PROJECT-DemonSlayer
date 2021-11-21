package hadences.projectdemonslayer.config;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.arena.Arena;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static hadences.projectdemonslayer.arena.Arena.arenalist;
import static org.bukkit.Bukkit.getServer;

public class ArenaConfig {
    private static ProjectDemonSlayer ds = JavaPlugin.getPlugin(ProjectDemonSlayer.class);

    //updates the config file with all the valid arenas in the arena list.
    public static void updateArenaConfig(){
        for(String arena : arenalist.keySet()){
            ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".NAME",String.valueOf(arenalist.get(arena).getArenaname()));

            ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".LOBBYSPAWN.X", Double.valueOf(arenalist.get(arena).getLobbyspawn().getX()));
            ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".LOBBYSPAWN.Y", Double.valueOf(arenalist.get(arena).getLobbyspawn().getY()));
            ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".LOBBYSPAWN.Z", Double.valueOf(arenalist.get(arena).getLobbyspawn().getZ()));

            ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".TEAM1SPAWN.X", Double.valueOf(arenalist.get(arena).getT1spawn().getX()));
            ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".TEAM1SPAWN.Y", Double.valueOf(arenalist.get(arena).getT1spawn().getY()));
            ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".TEAM1SPAWN.Z", Double.valueOf(arenalist.get(arena).getT1spawn().getZ()));

            ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".TEAM2SPAWN.X", Double.valueOf(arenalist.get(arena).getT2spawn().getX()));
            ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".TEAM2SPAWN.Y", Double.valueOf(arenalist.get(arena).getT2spawn().getY()));
            ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".TEAM2SPAWN.Z", Double.valueOf(arenalist.get(arena).getT2spawn().getZ()));

            for(int i = 0; i < arenalist.get(arena).getSpawnpoints().size(); i++){ //saves all the locations of ffa spawnpoints
                ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".SPAWNPOINTS.sp" + i + ".X", Double.valueOf(arenalist.get(arena).getSpawnpoints().get(i).getX()));
                ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".SPAWNPOINTS.sp" + i + ".Y", Double.valueOf(arenalist.get(arena).getSpawnpoints().get(i).getY()));
                ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".SPAWNPOINTS.sp" + i + ".Z", Double.valueOf(arenalist.get(arena).getSpawnpoints().get(i).getZ()));
            }


            ds.getConfig().set("Arenas." + arenalist.get(arena).getArenaname() + ".COMPLETE", Boolean.valueOf(arenalist.get(arena).getFinalized()));

        }
    }

    //populates the arenalist hashmap from the config.
    public static void loadArenas(){
        String Name;
        Location LobbySpawn;
        Location t1spawn;
        Location t2spawn;
        ArrayList<Location> spawnpoints;
        boolean Finalized;
        try {
            if (!ds.getConfig().getConfigurationSection("Arenas").getKeys(false).isEmpty()){
                for (String key : ds.getConfig().getConfigurationSection("Arenas").getKeys(false)) {
                    Name = ds.getConfig().get("Arenas." + key + ".NAME").toString();
                    LobbySpawn = new Location(getServer().getWorlds().get(0),(Double) ds.getConfig().get("Arenas." + key + ".LOBBYSPAWN.X"),(Double) ds.getConfig().get("Arenas." + key + ".LOBBYSPAWN.Y"),(Double) ds.getConfig().get("Arenas." + key + ".LOBBYSPAWN.Z"));
                    t1spawn = new Location(getServer().getWorlds().get(0),(Double) ds.getConfig().get("Arenas." + key + ".TEAM1SPAWN.X"),(Double) ds.getConfig().get("Arenas." + key + ".TEAM1SPAWN.Y"),(Double) ds.getConfig().get("Arenas." + key + ".TEAM1SPAWN.Z"));
                    t2spawn = new Location(getServer().getWorlds().get(0),(Double) ds.getConfig().get("Arenas." + key + ".TEAM2SPAWN.X"),(Double) ds.getConfig().get("Arenas." + key + ".TEAM2SPAWN.Y"),(Double) ds.getConfig().get("Arenas." + key + ".TEAM2SPAWN.Z"));
                    spawnpoints = new ArrayList<>();
                    Finalized = (Boolean) ds.getConfig().get("Arenas." + key + ".COMPLETE");

                    //populate spawnpoints
                    for(String sp : ds.getConfig().getConfigurationSection("Arenas."+key+".SPAWNPOINTS").getKeys(false)){
                        spawnpoints.add(new Location(getServer().getWorlds().get(0),(Double) ds.getConfig().get("Arenas." + key + ".SPAWNPOINTS."+sp+".X"),(Double) ds.getConfig().get("Arenas." + key + ".SPAWNPOINTS."+sp+".Y"),(Double) ds.getConfig().get("Arenas." + key + ".SPAWNPOINTS."+sp+".Z")));
                    }

                    arenalist.put(Name,new Arena(Name, t1spawn, t2spawn,LobbySpawn,Finalized,spawnpoints));
                }
            }
        }catch (Exception e){
            getServer().broadcastMessage("arena error");
        }

    }

    public static ArrayList<Arena> getMaps(){
        ArrayList<Arena> arenas = new ArrayList<>();
        if(arenalist.keySet().isEmpty())
            return null;
        for(String key : arenalist.keySet()){
            arenas.add(arenalist.get(key));
        }
        return arenas;
    }

}
