package hadences.projectdemonslayer.ability;

import hadences.projectdemonslayer.ability.water.Water;

import java.util.ArrayList;

public class Breathing {
    public static ArrayList<Breathing> breathinglist = new ArrayList<>();

    private String name;
    private String displayName;
    private BreathingCastManager breathingCastManager;

    public Breathing(String name, String displayName){
        this.name = name;
        this.displayName = displayName;
        breathingCastManager = null;
        setBreathingCastManager();

    }

    public void setBreathingCastManager(){
        if(name.equalsIgnoreCase("Water"))breathingCastManager = new Water();
    }

    public static Breathing getBreathing(String breathing){
        for(int i = 0; i < breathinglist.size(); i++){
            if(breathinglist.get(i).getName().equalsIgnoreCase(breathing))
                return breathinglist.get(i);
        }
        return null;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public static ArrayList<Breathing> getBreathinglist() {
        return breathinglist;
    }

    public static void setBreathinglist(ArrayList<Breathing> breathinglist) {
        Breathing.breathinglist = breathinglist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BreathingCastManager getBreathingCastManager() {
        return breathingCastManager;
    }

    public void setBreathingCastManager(BreathingCastManager breathingCastManager) {
        this.breathingCastManager = breathingCastManager;
    }

    public static boolean selectBreathing(String breathing){
        for(Breathing b : breathinglist){
            if(b.getName().equalsIgnoreCase(breathing));
            return true;
        }
        return false;
    }
}
