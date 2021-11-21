package hadences.projectdemonslayer.gui;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.chat.Chat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static hadences.projectdemonslayer.arena.Arena.arenalist;
import static hadences.projectdemonslayer.config.ArenaConfig.getMaps;
import static hadences.projectdemonslayer.item.GameItems.addDescription;
import static hadences.projectdemonslayer.player.PlayerManager.playerdata;
import static org.bukkit.Bukkit.getServer;

public class GUIMenuManager {

    ProjectDemonSlayer ds = JavaPlugin.getPlugin(ProjectDemonSlayer.class);

    //Character Related
    private Inventory CharacterMenu;
    private Inventory BreathingMenu;
    private Inventory AbilityMenu;
    private Inventory SlotMenu;

    //Game Related
    private Inventory MainMenu;
    private Inventory MapMenu;
    private Inventory GamemodeMenu;


    public void createInventories(){
        updateCharacterMenu();
        updateBreathingMenu();
        updateSlotMenu();
        updateMainMenu();
        updateGamemodeMenu();
    }

    public void updateGamemodeMenu(){
        GamemodeMenu = Bukkit.createInventory(null,27, Chat.getConsoleName() + "");
        List<Component> lore = new ArrayList<>();
        ItemMeta meta;

        ItemStack ITEM_CLOSE = new ItemStack(Material.BARRIER);
        ItemStack BLACK_GLASS_PANE = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack GAMEMODE;

        meta = BLACK_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(" "));
        BLACK_GLASS_PANE.setItemMeta(meta);

        for(int i = 0; i < 27; i++){
            GamemodeMenu.setItem(i,BLACK_GLASS_PANE);
        }

        int j = 0;
        try {
            if (!ds.getConfig().getConfigurationSection("Game.Gamemodes").getKeys(false).isEmpty()){
                for (String key : ds.getConfig().getConfigurationSection("Game.Gamemodes").getKeys(false)) {
                    GAMEMODE = new ItemStack(Material.getMaterial((String) ds.getConfig().get("Game.Gamemodes." + key + ".Item")));
                    meta = GAMEMODE.getItemMeta();
                    meta.displayName(Component.text(ChatColor.AQUA + key));
                    lore.clear();
                    lore.add(Component.text(Chat.format("&f+ &cClick to select gamemode!")));
                    meta.lore(lore);
                    meta.setLocalizedName(key);
                    GAMEMODE.setItemMeta(meta);
                    GamemodeMenu.setItem(j,GAMEMODE);
                    j++;
                }
            }
        }catch (Exception e){
            getServer().broadcastMessage("Gamemode GUI");
        }

        //ITEM_CLOSE
        meta = ITEM_CLOSE.getItemMeta();
        meta.displayName(Component.text("[Close]").color(TextColor.color(255, 0, 0)));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &cClick to close menu")));
        meta.lore(lore);
        ITEM_CLOSE.setItemMeta(meta);

        GamemodeMenu.setItem(26,ITEM_CLOSE);
    }

    public void updateMapMenu(){
        MapMenu = Bukkit.createInventory(null,54, Chat.getConsoleName() + " Maps");
        ItemMeta meta;
        ItemStack item_close = new ItemStack(Material.BARRIER);
        ItemStack item_map = new ItemStack(Material.MAP);
        ItemStack BLACK_GLASS_PANE = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        meta = BLACK_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(" "));
        BLACK_GLASS_PANE.setItemMeta(meta);
        List<String> lore = new ArrayList<>();

        for(int i = 0; i < 54; i++){
            MapMenu.setItem(i,BLACK_GLASS_PANE);
        }

        //item_close data
        meta = item_close.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "[Close Menu]");
        lore.clear();
        lore.add(ChatColor.WHITE + "* " + ChatColor.RED + "Click to Exit!");
        meta.setLore(lore);
        item_close.setItemMeta(meta);
        MapMenu.setItem(53,item_close);

        if(!arenalist.isEmpty())
            for(int i = 0; i < getMaps().size(); i++){
                //item_map data
                meta = item_map.getItemMeta();
                meta.setDisplayName(ChatColor.WHITE + getMaps().get(i).getArenaname());
                lore.clear();
                lore.add(ChatColor.WHITE + "* " + ChatColor.GREEN + "Click to Start!");
                meta.setLore(lore);
                item_map.setItemMeta(meta);
                MapMenu.setItem(i,item_map);
            }
    }

    public void updateMainMenu(){
        MainMenu = Bukkit.createInventory(null,27,Chat.getConsoleName());
        List<Component> lore = new ArrayList<>();
        ItemMeta meta;

        ItemStack ITEM_CLOSE = new ItemStack(Material.BARRIER);
        ItemStack START = new ItemStack(Material.BEACON);
        ItemStack BLACK_GLASS_PANE = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        meta = BLACK_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(" "));
        BLACK_GLASS_PANE.setItemMeta(meta);

        //ITEM_CLOSE
        meta = ITEM_CLOSE.getItemMeta();
        meta.displayName(Component.text("[Close]").color(TextColor.color(255, 0, 0)));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &cClick to close menu")));
        meta.lore(lore);
        ITEM_CLOSE.setItemMeta(meta);

        //START
        meta = START.getItemMeta();
        meta.displayName(Component.text(Chat.format("&8[&aStart Game&8]")));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &aClick to Start Game!")));
        meta.lore(lore);
        meta.setLocalizedName("start");
        START.setItemMeta(meta);

        for(int i = 0; i < 27; i++){
            MainMenu.setItem(i,BLACK_GLASS_PANE);
        }

        MainMenu.setItem(26,ITEM_CLOSE);
        MainMenu.setItem(13,START);
    }

    public void updateCharacterMenu(){
        CharacterMenu = Bukkit.createInventory(null,27, Chat.getConsoleName() + ChatColor.BLACK + "");
        List<Component> lore = new ArrayList<>();
        ItemMeta meta;

        ItemStack ITEM_CLOSE = new ItemStack(Material.BARRIER);
        ItemStack SELECT_BREATHING = new ItemStack(Material.DRAGON_BREATH);
        ItemStack CONFIGURE_ABILITIES = new ItemStack(Material.NAME_TAG);
        ItemStack READY = new ItemStack(Material.EMERALD);
        ItemStack GAMEMODE = new ItemStack(Material.FIREWORK_ROCKET);

        ItemStack BLACK_GLASS_PANE = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack WHITE_GLASS_PANE = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemStack RED_GLASS_PANE = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        meta = WHITE_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(" "));
        WHITE_GLASS_PANE.setItemMeta(meta);

        meta = BLACK_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(" "));
        BLACK_GLASS_PANE.setItemMeta(meta);

        meta = RED_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(" "));
        RED_GLASS_PANE.setItemMeta(meta);

        //ITEM_CLOSE
        meta = ITEM_CLOSE.getItemMeta();
        meta.displayName(Component.text("[Close]").color(TextColor.color(255, 0, 0)));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &cClick to close menu")));
        meta.lore(lore);
        ITEM_CLOSE.setItemMeta(meta);

        //SELECT_BREATHING
        meta = SELECT_BREATHING.getItemMeta();
        meta.displayName(Component.text(Chat.format("&8[&fBreathing Selection&8]")));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &aClick to select your breathing style!")));
        meta.lore(lore);
        SELECT_BREATHING.setItemMeta(meta);

        //CONFIGURE ABILITIES
        meta = CONFIGURE_ABILITIES.getItemMeta();
        meta.displayName(Component.text(Chat.format("&8[&aAbility Configuration&8]")));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &aClick to assign you abilities!")));
        meta.lore(lore);
        CONFIGURE_ABILITIES.setItemMeta(meta);

        //READY
        meta = READY.getItemMeta();
        meta.displayName(Component.text(Chat.format("&8[&aReady Up&8]")));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &aClick to Lock in!")));
        meta.lore(lore);
        meta.setLocalizedName("ready");
        READY.setItemMeta(meta);

        //Gamemode
        meta = GAMEMODE.getItemMeta();
        meta.displayName(Component.text(Chat.format("&8[&6Select Gamemode&8]")));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &aClick to select gamemode!")));
        meta.lore(lore);
        meta.setLocalizedName("gamemode");
        GAMEMODE.setItemMeta(meta);

        //Glass Pane Initializer
        for(int i = 0; i < 27; i++){
            if(i < 9 || i == 9 || i == 17 || (i >= 18 && i != 26)){
                CharacterMenu.setItem(i,BLACK_GLASS_PANE);
            }
            else if((i >= 10 && i < 12) || (i >= 15 && i < 17))
                CharacterMenu.setItem(i,RED_GLASS_PANE);
        }

        CharacterMenu.setItem(26,ITEM_CLOSE);
        CharacterMenu.setItem(14,SELECT_BREATHING);
        CharacterMenu.setItem(12,CONFIGURE_ABILITIES);
        CharacterMenu.setItem(13,READY);
        CharacterMenu.setItem(4,GAMEMODE);


    }

    public void updateBreathingMenu(){
        BreathingMenu = Bukkit.createInventory(null,27,ChatColor.BLACK + "Select your breathing style!");
        List<Component> lore = new ArrayList<>();
        ItemMeta meta;

        ItemStack ITEM_CLOSE = new ItemStack(Material.BARRIER);
        ItemStack BREATHING_STYLE;
        ItemStack BLACK_GLASS_PANE = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        meta = BLACK_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(" "));
        BLACK_GLASS_PANE.setItemMeta(meta);

        //ITEM_CLOSE
        meta = ITEM_CLOSE.getItemMeta();
        meta.displayName(Component.text("[Close]").color(TextColor.color(255, 0, 0)));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &cClick to close menu")));
        meta.lore(lore);
        ITEM_CLOSE.setItemMeta(meta);

        BreathingMenu.setItem(26,ITEM_CLOSE);

        //Fill the inventory with config breathings
        int i = 0;
        try {
            if (!ds.getConfig().getConfigurationSection("Ability").getKeys(false).isEmpty()){
                for (String key : ds.getConfig().getConfigurationSection("Ability").getKeys(false)) {
                    //Item
                    BREATHING_STYLE = new ItemStack(Material.getMaterial((String) ds.getConfig().get("Ability."+key+".Item")));
                    meta = BREATHING_STYLE.getItemMeta();
                    meta.displayName(Component.text(Chat.format((String) ds.getConfig().get("Ability."+key+".Name"))));
                    lore.clear();
                    lore.add(Component.text(Chat.format((String) ds.getConfig().get("Ability."+key+".Description"))));
                    meta.lore(lore);
                    meta.setLocalizedName(key);
                    BREATHING_STYLE.setItemMeta(meta);

                    BreathingMenu.setItem(i,BREATHING_STYLE);

                    i++;
                }
            }
        }catch (Exception e){
            getServer().broadcastMessage("Breathing Exception");
        }

        for(int j = i; j < 26; j++){
            BreathingMenu.setItem(j,BLACK_GLASS_PANE);
        }

    }

    public void updateAbilityMenu(Player p, Material material){
        AbilityMenu = Bukkit.createInventory(null,27,ChatColor.BLACK + "<Ability Configuration>");
        ArrayList<Component> lore = new ArrayList<>();
        ItemMeta meta;

        ItemStack ITEM_CLOSE = new ItemStack(Material.BARRIER);
        ItemStack ABILITY;
        ItemStack BLACK_GLASS_PANE = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack SEE_BINDS = new ItemStack(Material.SPYGLASS);

        meta = BLACK_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(" "));
        BLACK_GLASS_PANE.setItemMeta(meta);

        //ITEM_CLOSE
        meta = ITEM_CLOSE.getItemMeta();
        meta.displayName(Component.text("[Close]").color(TextColor.color(255, 0, 0)));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &cClick to close menu")));
        meta.lore(lore);
        ITEM_CLOSE.setItemMeta(meta);

        AbilityMenu.setItem(26,ITEM_CLOSE);

        //ITEM_CLOSE
        meta = SEE_BINDS.getItemMeta();
        meta.displayName(Component.text(ChatColor.GREEN + "[View Configuration]"));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &bClick to see your slot configuration!")));
        meta.lore(lore);
        meta.setLocalizedName("binds");
        SEE_BINDS.setItemMeta(meta);

        AbilityMenu.setItem(25,SEE_BINDS);


        String configpath = "Ability."+playerdata.get(p.getUniqueId()).getBREATHING().getName()+".Forms.";
        //Populate
        int i = 0;
        try {
            if (!ds.getConfig().getConfigurationSection("Ability."+playerdata.get(p.getUniqueId()).getBREATHING().getName()+".Forms").getKeys(false).isEmpty()){
                for (String key : ds.getConfig().getConfigurationSection("Ability."+playerdata.get(p.getUniqueId()).getBREATHING().getName()+".Forms").getKeys(false)) {
                    //Item
                    ABILITY = new ItemStack(material);
                    meta = ABILITY.getItemMeta();
                    meta.displayName(Component.text(Chat.format((String) ds.getConfig().get(configpath+key+".Name"))));
                    lore.clear();

                    lore.add(Component.text(Chat.format("&7―――――――「 &fINFO &7」―――――――")));
                    lore.add(Component.text(Chat.format(" ")));
                    lore.add(Component.text(Chat.format("&4▸ Ability Damage: &f"+ds.getConfig().get(configpath + key + ".Damage"))));
                    lore.add(Component.text(Chat.format("&6▸ Stamina Cost: &f"+ds.getConfig().get(configpath + key + ".StaminaCost"))));
                    lore.add(Component.text(Chat.format("&3▸ Cooldown: &f"+ds.getConfig().get(configpath + key + ".Cooldown"))));
                    lore.add(Component.text(Chat.format(" ")));
                    lore.add(Component.text(Chat.format("&7―――――「 &fDESCRIPTION &7」―――――")));
                    addDescription(lore, (String) ds.getConfig().get(configpath + key + ".Description"));

                    meta.lore(lore);
                    meta.setLocalizedName(key);
                    ABILITY.setItemMeta(meta);

                    AbilityMenu.setItem(i,ABILITY);

                    i++;
                }
            }
        }catch (Exception e){
            getServer().broadcastMessage("ability menu error");
            p.sendMessage(playerdata.get(p.getUniqueId()).getBREATHING().getName());
        }

        for(int j = i; j < 25; j++){
            AbilityMenu.setItem(j,BLACK_GLASS_PANE);
        }

    }

    public void updateSlotMenu(){
        SlotMenu = Bukkit.createInventory(null,27, Chat.getConsoleName() + ChatColor.BLACK + "");
        List<Component> lore = new ArrayList<>();
        ItemMeta meta;

        ItemStack ITEM_CLOSE = new ItemStack(Material.BARRIER);

        ItemStack BLACK_GLASS_PANE = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack LIME_GLASS_PANE = new ItemStack(Material.LIME_STAINED_GLASS_PANE);

        meta = BLACK_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(" "));
        BLACK_GLASS_PANE.setItemMeta(meta);

        meta = LIME_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(" "));
        LIME_GLASS_PANE.setItemMeta(meta);

        for(int i = 0; i < 27; i++){
            SlotMenu.setItem(i,BLACK_GLASS_PANE);
        }

        //ITEM_CLOSE
        meta = ITEM_CLOSE.getItemMeta();
        meta.displayName(Component.text("[Close]").color(TextColor.color(255, 0, 0)));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &cClick to close menu")));
        meta.lore(lore);
        ITEM_CLOSE.setItemMeta(meta);
        SlotMenu.setItem(26,ITEM_CLOSE);

        // /Slot 1
        meta = LIME_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(Chat.format("&8[&aSlot 1&8]")));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &aClick to add ability to slot 1!")));
        meta.lore(lore);
        meta.setLocalizedName("one");
        LIME_GLASS_PANE.setItemMeta(meta);
        SlotMenu.setItem(10,LIME_GLASS_PANE);

        // /Slot 2
        meta = LIME_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(Chat.format("&8[&aSlot 2&8]")));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &aClick to add ability to slot 2!")));
        meta.lore(lore);
        meta.setLocalizedName("two");
        LIME_GLASS_PANE.setItemMeta(meta);
        SlotMenu.setItem(13,LIME_GLASS_PANE);

        // /Slot 3
        meta = LIME_GLASS_PANE.getItemMeta();
        meta.displayName(Component.text(Chat.format("&8[&aSlot 3&8]")));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &aClick to add ability to slot 3!")));
        meta.lore(lore);
        meta.setLocalizedName("three");
        LIME_GLASS_PANE.setItemMeta(meta);
        SlotMenu.setItem(16,LIME_GLASS_PANE);





    }

    public Inventory getGamemodeMenu() {
        return GamemodeMenu;
    }

    public Inventory getMainMenu() {
        return MainMenu;
    }

    public Inventory getMapMenu() {
        return MapMenu;
    }

    public Inventory getCharacterMenu() {
        return CharacterMenu;
    }

    public Inventory getBreathingMenu() {
        return BreathingMenu;
    }

    public Inventory getAbilityMenu() {
        return AbilityMenu;
    }

    public Inventory getSlotMenu() {
        return SlotMenu;
    }
}
