package hadences.projectdemonslayer.arena;

import net.md_5.bungee.api.ChatColor;

import static hadences.projectdemonslayer.arena.Arena.arenalist;

public class ArenaManager {

    //creates/adds a new arena to the arena list
    public static void addArena(String name){
        arenalist.put(name,new Arena(name));
    }

    //returns a string value of a list of all the arenas in the game.
    public static String getArenaList(){
        String list = " ";
        if(arenalist.isEmpty()){
            return ChatColor.GREEN + " So Empty...";
        }

        int i  = 0;
        for(String key : arenalist.keySet()){
            if(i != arenalist.keySet().size()-1)
                list += arenalist.get(key).getArenaname() + ", ";
            else
                list += arenalist.get(key).getArenaname() + " ";

            i++;
        }



        return list;
    }

    //checks if the arena name is in the arena list.
    public static boolean ArenaNameInList(String name) {
        if(arenalist.isEmpty()){
            return false;
        }
        for(String key : arenalist.keySet()){
            if(arenalist.get(key).getArenaname().equals(name)){
                return true;
            }
        }
        return false;
    }
}
