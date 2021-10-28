package hadences.projectdemonslayer.commands;
import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.arena.ArenaManager;
import org.bukkit.ChatColor;

import hadences.projectdemonslayer.chat.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static hadences.projectdemonslayer.arena.Arena.arenalist;
import static hadences.projectdemonslayer.game.GameManager.console;

public class CommandManager implements CommandExecutor {
    ProjectDemonSlayer ds = JavaPlugin.getPlugin(ProjectDemonSlayer.class);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return true; // returns false if the user who send the command is not a player

        Player p = (Player) sender;
        if(!(label.equalsIgnoreCase("ds"))) return true; // checks if player typed '/ds'

        // /ds
        if(args.length == 0){
            p.sendMessage(Chat.getConsoleName() + ChatColor.RED + " Invalid Syntax. Usage : /ds <command>");
            return true;
        }

        // /ds create
        else if(args.length == 1 && args[0].equalsIgnoreCase("create")){
            p.sendMessage(Chat.getConsoleName() + ChatColor.RED + " Invalid Syntax. Usage : /ds create <arena name>");
            return true;
        }
        // /ds create <name>
        else if(args.length == 2 && args[0].equalsIgnoreCase("create")){
            if(ArenaManager.ArenaNameInList(args[1])){ // if the arena name is in the list of arenas then returns true
                p.sendMessage(Chat.getConsoleName() + ChatColor.RED + " This arena name is already in the list!");
                return true;
            }else { // arena name is not in the list of arenas; creates arena
                p.sendMessage(Chat.getConsoleName() + ChatColor.GREEN + " Arena " + ChatColor.WHITE + args[1] + ChatColor.GREEN + " created!");
                ArenaManager.addArena(args[1]);
                return true;
            }        }

        // /ds arenalist
        else if(args.length == 1 && args[0].equalsIgnoreCase("arenalist")){
            p.sendMessage(Chat.getConsoleName() + ChatColor.GREEN + ArenaManager.getArenaList());
            return true;
        }

        // /ds reloadconfig
        else if(args.length == 1 && args[0].equalsIgnoreCase("reloadconfig")){
            p.sendMessage(Chat.getConsoleName() + ChatColor.GREEN + " Config reloaded!");
            ds.reloadConfig();
            return true;
        }

        // /ds get <name>
        else if((args.length == 1 && args[0].equalsIgnoreCase("get")) || (args.length == 2 && ArenaManager.ArenaNameInList(args[1]))){
            p.sendMessage(Chat.getConsoleName() + ChatColor.RED + " Invalid Syntax. Usage : /ds get <name> setlobbyspawn/sett1spawn/sett2spawn/addspawnpoint/finalize");
            return true;
        }

        // /ds endgame
        else if(args.length == 1 && args[0].equalsIgnoreCase("endgame")){
            console.getGamemodeManager().setEndGame(true);

        }


        // /ds get <name> <command>
        else if(args.length == 3 && args[0].equalsIgnoreCase("get") && ArenaManager.ArenaNameInList(args[1])){
            // /ds get <name> setLobbySpawn
            if(args[2].equalsIgnoreCase("setlobbyspawn")) {
                arenalist.get(args[1]).setLobbyspawn(p.getLocation());
                p.sendMessage(Chat.getConsoleName() + ChatColor.GREEN + " Lobby Spawn Set for Arena " + ChatColor.WHITE + args[1]);
                return true;
            }
            // /ds get <name> setT1Spawn
            else if(args[2].equalsIgnoreCase("sett1spawn")) {
                p.sendMessage(Chat.getConsoleName() + ChatColor.GREEN + " Team 1 Spawn Set for Arena " + ChatColor.WHITE + args[1]);
                arenalist.get(args[1]).setT1spawn(p.getLocation());
                return true;
            }
            // /ds get <name> setT2Spawn
            else if(args[2].equalsIgnoreCase("sett2spawn")){
                p.sendMessage(Chat.getConsoleName() + ChatColor.GREEN + " Team 2 Spawn Set for Arena " + ChatColor.WHITE + args[1]);
                arenalist.get(args[1]).setT2spawn(p.getLocation());
                return true;
            }
            // /ds get <name> addspawnpoint
            else if(args[2].equalsIgnoreCase("addspawnpoint")){
                p.sendMessage(Chat.getConsoleName() + ChatColor.GREEN + " Spawnpoint added for Arena " +  ChatColor.WHITE + args[1]);
                arenalist.get(args[1]).getSpawnpoints().add(p.getLocation());
                return true;
            }
            // /ds get <name> finalize
            else if(args[2].equalsIgnoreCase("finalize")){
                if(arenalist.get(args[1]).getT1spawn() == null || arenalist.get(args[1]).getT2spawn() == null || arenalist.get(args[1]).getLobbyspawn() == null ){
                    p.sendMessage(Chat.getConsoleName() + ChatColor.RED + " Arena Cannot be finalized for " + ChatColor.WHITE + args[1]);
                    return true;
                }else{
                    arenalist.get(args[1]).setFinalized(true);
                    p.sendMessage(Chat.getConsoleName() + ChatColor.GREEN + " Arena is finalized!");
                    return true;
                }
            }

        }
        else{
            p.sendMessage(Chat.getConsoleName() + ChatColor.RED + " Arena does not exist!");
            return true;
        }



        return false;
    }
}
