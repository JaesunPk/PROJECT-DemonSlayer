package hadences.projectdemonslayer.arena;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class Arena {
    public static HashMap<String, Arena> arenalist = new HashMap<>();

    private Location t1spawn; //Team one spawn
    private Location t2spawn; // Team two spawn
    private Location lobbyspawn; // Lobby spawn
    private String arenaname; // Arena spawn
    private Boolean finalized; // if the arena is finalized and ready

    private ArrayList<Location> spawnpoints; // Random spawnpoints for ffa gamemodes

    public Arena(String arenaname){
        t1spawn = null;
        t2spawn = null;
        lobbyspawn = null;
        this.arenaname = arenaname;
        finalized = false;
        spawnpoints = new ArrayList<>();
    }

    public Arena(String arenaname, Location t1spawn, Location t2spawn, Location lobbyspawn, Boolean finalized, ArrayList<Location> spawnpoints){
        this.t2spawn = t2spawn;
        this.t1spawn = t1spawn;
        this.arenaname = arenaname;
        this.lobbyspawn = lobbyspawn;
        this.finalized = finalized;
        this.spawnpoints = spawnpoints;
    }

    public static HashMap<String, Arena> getArenalist() {
        return arenalist;
    }

    public static void setArenalist(HashMap<String, Arena> arenalist) {
        Arena.arenalist = arenalist;
    }

    public Location getT1spawn() {
        return t1spawn;
    }

    public void setT1spawn(Location t1spawn) {
        this.t1spawn = t1spawn;
    }

    public Location getT2spawn() {
        return t2spawn;
    }

    public void setT2spawn(Location t2spawn) {
        this.t2spawn = t2spawn;
    }

    public Location getLobbyspawn() {
        return lobbyspawn;
    }

    public void setLobbyspawn(Location lobbyspawn) {
        this.lobbyspawn = lobbyspawn;
    }

    public String getArenaname() {
        return arenaname;
    }

    public void setArenaname(String arenaname) {
        this.arenaname = arenaname;
    }

    public Boolean getFinalized() {
        return finalized;
    }

    public void setFinalized(Boolean finalized) {
        this.finalized = finalized;
    }

    public ArrayList<Location> getSpawnpoints() {
        return spawnpoints;
    }

    public void setSpawnpoints(ArrayList<Location> spawnpoints) {
        this.spawnpoints = spawnpoints;
    }
}
