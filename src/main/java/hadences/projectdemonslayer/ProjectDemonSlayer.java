package hadences.projectdemonslayer;

import hadences.projectdemonslayer.arena.ArenaManager;
import hadences.projectdemonslayer.commands.CommandManager;
import hadences.projectdemonslayer.config.ArenaConfig;
import org.bukkit.plugin.java.JavaPlugin;

import static hadences.projectdemonslayer.config.ArenaConfig.loadArenas;
import static hadences.projectdemonslayer.config.ArenaConfig.updateArenaConfig;

public final class ProjectDemonSlayer extends JavaPlugin {
    //public static ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);

    @Override
    public void onEnable() {
        // Plugin startup logic

        //initialize ds command
        getCommand("ds").setExecutor(new CommandManager());

        //loads the config file
        loadConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        updateConfig();
    }

    public void updateConfig() {
        updateArenaConfig();
        //updatePlayerConfig();
        saveConfig();
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();

        //Populate the arenalist through config.
        loadArenas();
    }





}
