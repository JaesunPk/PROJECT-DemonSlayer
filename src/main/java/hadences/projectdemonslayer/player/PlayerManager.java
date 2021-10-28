package hadences.projectdemonslayer.player;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.ability.Ability;
import hadences.projectdemonslayer.ability.Breathing;
import hadences.projectdemonslayer.gui.GUIMenuManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {
    private ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);

    public static HashMap<UUID, PlayerManager> playerdata = new HashMap<>();

    //General Player Information
    private String PLAYER_NAME;
    private int WINS;
    private java.util.UUID UUID;

    //InGame Information
    private Breathing BREATHING;
    private int ABILITY1;
    private int ABILITY2;
    private int ABILITY3;
    private Ability A1;
    private Ability A2;
    private Ability A3;
    private String TEAM;

    //GUI Information
    private GUIMenuManager guiMenuManager;
    private int ABILITYSELECTION;


    //Game Variables
    private boolean DEV_MODE;
    private boolean IN_GAME;
    private boolean ALIVE;
    private boolean IS_READY;
    private boolean IN_LOBBY;
    private boolean ABILITY_USAGE;
    private int STAMINA;
    private boolean CAN_MOVE;


    public PlayerManager(Player player, int WINS) {
        this.PLAYER_NAME = player.getName();
        this.WINS = WINS;
        this.UUID = player.getUniqueId();
        this.DEV_MODE = false;
        this.IN_GAME = false;
        this.ALIVE = true;
        this.IS_READY = false;
        this.IN_LOBBY = false;
        this.ABILITY_USAGE = false;
        this.STAMINA = 0;
        this.ABILITY1 = 0;
        this.ABILITY2 = 0;
        this.ABILITY3 = 0;
        this.guiMenuManager = new GUIMenuManager();
        this.BREATHING = Breathing.getBreathing("None");
        this.ABILITYSELECTION = 0;
        guiMenuManager.createInventories();
        this.CAN_MOVE = true;
        this.TEAM = "";
    }

    public String getAbilityName(int ability){
        String name = "None";
        for(String form : ds.getConfig().getConfigurationSection("Ability."+BREATHING.getName() + ".Forms").getKeys(false)){
            if((Integer) ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Classification") == ability){
                return (String) ds.getConfig().get("Ability."+BREATHING.getName()+".Forms." + form + ".Name");
            }
        }

        return name;
    }

    public String getTEAM() {
        return TEAM;
    }

    public void setTEAM(String TEAM) {
        this.TEAM = TEAM;
    }

    public boolean isCAN_MOVE() {
        return CAN_MOVE;
    }

    public void setCAN_MOVE(boolean CAN_MOVE) {
        this.CAN_MOVE = CAN_MOVE;
    }

    public int getABILITYSELECTION() {
        return ABILITYSELECTION;
    }

    public void setABILITYSELECTION(int ABILITYSELECTION) {
        this.ABILITYSELECTION = ABILITYSELECTION;
    }

    public Breathing getBREATHING() {
        return BREATHING;
    }

    public void setBREATHING(Breathing BREATHING) {
        this.BREATHING = BREATHING;
    }

    public int getABILITY1() {
        return ABILITY1;
    }

    public void setABILITY1(int ABILITY1) {
        this.ABILITY1 = ABILITY1;
    }

    public int getABILITY2() {
        return ABILITY2;
    }

    public void setABILITY2(int ABILITY2) {
        this.ABILITY2 = ABILITY2;
    }

    public int getABILITY3() {
        return ABILITY3;
    }

    public void setABILITY3(int ABILITY3) {
        this.ABILITY3 = ABILITY3;
    }

    public GUIMenuManager getGuiMenuManager() {
        return guiMenuManager;
    }

    public void setGuiMenuManager(GUIMenuManager guiMenuManager) {
        this.guiMenuManager = guiMenuManager;
    }

    public static HashMap<java.util.UUID, PlayerManager> getPlayerdata() {
        return playerdata;
    }

    public static void setPlayerdata(HashMap<java.util.UUID, PlayerManager> playerdata) {
        PlayerManager.playerdata = playerdata;
    }

    public String getPLAYER_NAME() {
        return PLAYER_NAME;
    }

    public void setPLAYER_NAME(String PLAYER_NAME) {
        this.PLAYER_NAME = PLAYER_NAME;
    }

    public int getWINS() {
        return WINS;
    }

    public void setWINS(int WINS) {
        this.WINS = WINS;
    }

    public java.util.UUID getUUID() {
        return UUID;
    }

    public void setUUID(java.util.UUID UUID) {
        this.UUID = UUID;
    }

    public boolean isDEV_MODE() {
        return DEV_MODE;
    }

    public void setDEV_MODE(boolean DEV_MODE) {
        this.DEV_MODE = DEV_MODE;
    }

    public boolean isIN_GAME() {
        return IN_GAME;
    }

    public void setIN_GAME(boolean IN_GAME) {
        this.IN_GAME = IN_GAME;
    }

    public boolean isALIVE() {
        return ALIVE;
    }

    public void setALIVE(boolean ALIVE) {
        this.ALIVE = ALIVE;
    }

    public boolean IS_READY() {
        return IS_READY;
    }

    public void setIS_READY(boolean IS_READY) {
        this.IS_READY = IS_READY;
    }

    public boolean isIN_LOBBY() {
        return IN_LOBBY;
    }

    public void setIN_LOBBY(boolean IN_LOBBY) {
        this.IN_LOBBY = IN_LOBBY;
    }

    public boolean isABILITY_USAGE() {
        return ABILITY_USAGE;
    }

    public void setABILITY_USAGE(boolean ABILITY_USAGE) {
        this.ABILITY_USAGE = ABILITY_USAGE;
    }

    public int getSTAMINA() {
        return STAMINA;
    }

    public void setSTAMINA(int STAMINA) {
        this.STAMINA = STAMINA;
    }
}
