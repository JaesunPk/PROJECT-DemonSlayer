package hadences.projectdemonslayer;

import hadences.projectdemonslayer.chat.Chat;
import hadences.projectdemonslayer.commands.CommandManager;
import hadences.projectdemonslayer.commands.ToggleDev;
import hadences.projectdemonslayer.game.GameEvent;
import hadences.projectdemonslayer.gui.events.GUIEventManager;
import hadences.projectdemonslayer.scoreboard.BoardManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static hadences.projectdemonslayer.config.ArenaConfig.loadArenas;
import static hadences.projectdemonslayer.config.ArenaConfig.updateArenaConfig;
import static hadences.projectdemonslayer.config.BreathingConfig.loadBreathing;
import static hadences.projectdemonslayer.config.PlayerConfig.updatePlayerConfig;

public final class ProjectDemonSlayer extends JavaPlugin {
    public BoardManager board = new BoardManager();
    public ItemStack MenuItem = new ItemStack(Material.NETHER_STAR);
    @Override
    public void onEnable() {
        // Plugin startup logic


        //Events
        //getServer().getPluginManager().registerEvents(new debugEvent(),this);
        getServer().getPluginManager().registerEvents(new MainEvent(),this);
        getServer().getPluginManager().registerEvents(new GUIEventManager(), this);
        getServer().getPluginManager().registerEvents(new GameEvent(),this);

        //initialize ds command
        getCommand("ds").setExecutor(new CommandManager());
        getCommand("dev").setExecutor(new ToggleDev());
        //create Menu Item
        createMenuItem();

        //loads the config file
        loadConfig();

        //create scoreboard
        board.createScoreboard();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        updateConfig();
        HandlerList.unregisterAll(this);
    }

    public void updateConfig() {
        updateArenaConfig();
        updatePlayerConfig();
        //updatePlayerConfig();
        saveConfig();
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);

        //Populate the arenalist through config.
        loadArenas();
        loadBreathing();
    }

    //Load Menu Item
    public void createMenuItem(){
        List<Component> lore = new ArrayList<>();
        ItemMeta meta = MenuItem.getItemMeta();
        meta.setLocalizedName("menu");
        meta.displayName(Component.text(ChatColor.GREEN +"[Menu]"));
        lore.clear();
        lore.add(Component.text(Chat.format("&f+ &cClick to open menu")));
        meta.lore(lore);
        MenuItem.setItemMeta(meta);
    }

    public void resetInventory(Player p){
        p.getInventory().clear();
        p.getInventory().setItem(8,MenuItem);
    }

}
