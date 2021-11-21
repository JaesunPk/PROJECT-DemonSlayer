package hadences.projectdemonslayer.game.gamemode;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.chat.Chat;
import hadences.projectdemonslayer.item.GameItems;
import hadences.projectdemonslayer.player.PlayerManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

import static hadences.projectdemonslayer.game.GameManager.console;
import static hadences.projectdemonslayer.gui.events.GUIEventManager.sendTitleToAll;
import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class Playground extends GamemodeManager implements Listener {
    private ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    private boolean endGame;
    private String Winner;
    private ArrayList<Player> playerlist;
    GameItems gameItems = new GameItems();


    public void start() {
        PlayerManager pm;
        for(Player p : playerlist){
            p.getInventory().setHeldItemSlot(3);
            pm = playerdata.get(p.getUniqueId());
            pm.setALIVE(true);
            pm.setIN_GAME(true);
            pm.setIN_LOBBY(false);
            pm.setABILITY_USAGE(true);
            pm.setCAN_MOVE(true);
            gameItems.givePlayerGameItems(p, playerdata.get(p.getUniqueId()).getBREATHING().getBreathingCastManager().getCustomModelData());
        }
    }

    public void end(){
        PlayerManager pm;
        for(Player p : playerlist){
            pm = playerdata.get(p.getUniqueId());
            pm.setALIVE(false);
            pm.setIN_GAME(false);
            pm.setABILITY1(0);
            pm.setABILITY2(0);
            pm.setABILITY3(0);
            pm.setABILITY_USAGE(false);
            pm.setIN_LOBBY(false);
            pm.setIS_READY(false);
            ds.resetInventory(p);
            p.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
            p.setGameMode(GameMode.ADVENTURE);
            ds.board.showMainBoard();
        }
    }

    public void endgame(){
        //shows a title saying that the game ended
        sendTitleToAll(Chat.format("&4[&fGame Ended&4]"),printWinnerStatement());
        //set everyone to gamemode spectator and play sound
        for(Player p : playerlist){
            p.setGameMode(GameMode.SPECTATOR);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL,0.5f, 1.0f);
        }
        //after 5 seconds teleport players back to spawn
        //reset all player values
        new BukkitRunnable(){
            @Override
            public void run() {
                end();
            }
        }.runTaskLater(ds,5*20L);
    }

    public String printWinnerStatement(){
        if(Winner.isEmpty() || Winner.equalsIgnoreCase("")){
            return Chat.format("&cForce End");
        }else{
            return Chat.format("&6"+Winner + " &fWins!");
        }
    }

    public void initialize(int StartingStamina, double StartingHealth, ArrayList<Player> playerlist){
        this.playerlist = playerlist;
        endGame = false;
        Winner = "";

        teleportPlayersSpawnpoint();

        for(Player p: playerlist){
            playerdata.get(p.getUniqueId()).setSTAMINA(StartingStamina);
            p.setMaxHealth(StartingHealth);
            p.setHealth(StartingHealth);
            playerdata.get(p.getUniqueId()).setCAN_MOVE(false);
        }

    }

    public void teleportPlayersSpawnpoint(){
        for(Player p : playerlist){
            p.teleport(console.getT1spawnpoint());
        }

    }

    public void checkForWinner(){
        //no winner must force end to stop this gamemode
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public Location getRandomSpawnpoint() {
        int random = new Random().nextInt(console.getSpawnpoints().size());
        return console.getSpawnpoints().get(random);
    }

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = e.getEntity();
            if (playerdata.get(p.getUniqueId()).isIN_GAME()) {
                e.setCancelled(true);
                p.sendTitle(ChatColor.DARK_RED + "[" + ChatColor.RED + "You have been slain.." + ChatColor.DARK_RED + "]", "");
                p.setGameMode(GameMode.SPECTATOR);
                playerdata.get(p.getUniqueId()).setABILITY_USAGE(false);
            } else {
                return;
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    p.getInventory().setHeldItemSlot(3);
                    p.setMaxHealth(console.getStartingHealth());
                    p.setHealth(console.getStartingHealth());
                    p.setFoodLevel(20);
                    p.setGameMode(GameMode.ADVENTURE);
                    p.teleport(getRandomSpawnpoint());
                    playerdata.get(p.getUniqueId()).setABILITY_USAGE(true);
                    playerdata.get(p.getUniqueId()).setSTAMINA(console.getStartingStamina());
                    p.sendTitle(ChatColor.DARK_RED + "[" + ChatColor.GREEN + "Respawned" + ChatColor.DARK_RED + "]", "");
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 2);
                }


            }.runTaskLater(ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class), 100);

        }
    }

}
