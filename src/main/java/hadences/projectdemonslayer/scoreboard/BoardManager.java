package hadences.projectdemonslayer.scoreboard;

import hadences.projectdemonslayer.chat.Chat;
import net.kyori.adventure.text.format.NamedTextColor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

import static hadences.projectdemonslayer.game.GameManager.console;
import static hadences.projectdemonslayer.player.PlayerManager.playerdata;

public class BoardManager {
   private Scoreboard scoreboard;
    ArrayList<String> scores = new ArrayList<>();


   //Main Board
    private int OnlinePlayers;

    //Lobby Board
    private boolean PlayersReady;
    private boolean GamemodeSelected;

   public void createScoreboard(){
       ScoreboardManager manager = Bukkit.getScoreboardManager();
       scoreboard = manager.getNewScoreboard();
       //Health Scoreboard
       TextComponent text = new TextComponent("Health ❤");
       text.setColor(ChatColor.RED);

       Objective Health = scoreboard.registerNewObjective(Criterias.HEALTH, String.valueOf(text));
       Health.setDisplaySlot(DisplaySlot.BELOW_NAME);

       //PlayerData Board
       Objective MainBoard = scoreboard.registerNewObjective("MainBoard", "dummy");
       MainBoard.setDisplayName("  " +Chat.getConsoleName()+ "     ");
       MainBoard.setDisplaySlot(DisplaySlot.SIDEBAR);

       //GameLobby Board
       Objective LobbyBoard = scoreboard.registerNewObjective("LobbyBoard", "dummy");
       LobbyBoard.setDisplayName(Chat.getConsoleName());

       //inGame Board
       Objective GameBoard = scoreboard.registerNewObjective("GameBoard", "dummy");
       GameBoard.setDisplayName(Chat.getConsoleName());
   }

   public void showMainBoard(){
       resetScores();
       updateMainBoard();
       scoreboard.getObjective("MainBoard").setDisplaySlot(DisplaySlot.SIDEBAR);
   }

   public void showLobbyBoard(){
       resetScores();
       updateLobbyBoard();
       scoreboard.getObjective("LobbyBoard").setDisplaySlot(DisplaySlot.SIDEBAR);
   }

   public void showGameBoard(){
       resetScores();
       updateGameBoard();
       scoreboard.getObjective("GameBoard").setDisplaySlot(DisplaySlot.SIDEBAR);
   }

   public void updateMainBoard(){
       OnlinePlayers = Bukkit.getOnlinePlayers().size();

       Objective objective = scoreboard.getObjective("MainBoard");
       objective.getScore(Chat.format("&0--&4------&f-<->-&4------&0--")).setScore(10);
       scores.add(Chat.format("&0&l--&4------&f-<->-&4------&0--"));

       objective.getScore("").setScore(9);

       objective.getScore(Chat.format("  &4[&b✦&4] &fOnline Players :" + "&f " +OnlinePlayers)).setScore(8);
       scores.add(Chat.format("  &4[&b✦&4] &fOnline Players :" + "&f " +OnlinePlayers));

       objective.getScore(" ").setScore(7);

       objective.getScore(Chat.format("&0--&4------&f-[-]-&4------&0--")).setScore(0);
       scores.add(Chat.format("&0--&4------&f-[-]-&4------&0--"));

   }

   public void updateLobbyBoard(){
        PlayersReady = allPlayersReady();
        GamemodeSelected = GamemodeSelected();
       Objective objective = scoreboard.getObjective("LobbyBoard");
       objective.setDisplaySlot(DisplaySlot.SIDEBAR);
       objective.getScore(ChatColor.WHITE + "" + ChatColor.BOLD + " -" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "------------------" + ChatColor.WHITE + "" + ChatColor.BOLD + "-").setScore(10);

       objective.getScore("").setScore(9);
       scores.add("");

       objective.getScore("  " + ChatColor.WHITE + "[" + ChatColor.GRAY + "♣" + ChatColor.WHITE + "]" + ChatColor.WHITE + " GM Selected : " + ChatColor.WHITE + GamemodeSelected).setScore(8);
       scores.add("  " + ChatColor.WHITE + "[" + ChatColor.GRAY + "♣" + ChatColor.WHITE + "]" + ChatColor.WHITE + " GM Selected : " + ChatColor.WHITE + GamemodeSelected);

       objective.getScore(" ").setScore(7);
       scores.add(" ");
       objective.getScore("  " + ChatColor.WHITE + "[" + ChatColor.GRAY + "♣" + ChatColor.WHITE + "]" + ChatColor.WHITE + " Players : " + ChatColor.WHITE + console.getPlayerList().size()).setScore(4);
       scores.add("  " + ChatColor.WHITE + "[" + ChatColor.GRAY + "♣" + ChatColor.WHITE + "]" + ChatColor.WHITE + " Players : " + ChatColor.WHITE + console.getPlayerList().size());

       objective.getScore("   ").setScore(3);
       scores.add("   ");
       objective.getScore("  " + ChatColor.WHITE + " All Players Ready : " + ChatColor.GOLD + PlayersReady).setScore(2);
       scores.add("  " + ChatColor.WHITE + " All Players Ready : " + ChatColor.GOLD + PlayersReady);

       objective.getScore("    ").setScore(1);

   }

   public void updateGameBoard(){
       int minutes = console.getMinutes();
       int seconds = console.getSeconds();
       Objective objective = scoreboard.getObjective("GameBoard");
      objective.setDisplaySlot(DisplaySlot.SIDEBAR);
      objective.getScore(ChatColor.WHITE + "" + ChatColor.BOLD + " -" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "------------------" + ChatColor.WHITE + "" + ChatColor.BOLD + "-").setScore(10);

      objective.getScore("").setScore(9);

       if(console.getSeconds() < 10){
          objective.getScore("  " + ChatColor.DARK_RED + "[" + ChatColor.WHITE + "✦" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + " Time : " + ChatColor.GREEN + minutes + ":0" + seconds).setScore(4);
           scores.add("  " + ChatColor.DARK_RED + "[" + ChatColor.WHITE + "✦" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + " Time : " + ChatColor.GREEN + minutes + ":0" + seconds);

       }else {
          objective.getScore("  " + ChatColor.DARK_RED + "[" + ChatColor.WHITE + "✦" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + " Time : " + ChatColor.GREEN + minutes + ":" + seconds).setScore(4);
           scores.add("  " + ChatColor.DARK_RED + "[" + ChatColor.WHITE + "✦" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + " Time : " + ChatColor.GREEN + minutes + ":" + seconds);
       }
      objective.getScore("   ").setScore(3);
      objective.getScore("    ").setScore(2);
   }

   public boolean GamemodeSelected(){
       if(console.getGamemodeManager() == null)
           return false;
       return true;
   }

   public boolean allPlayersReady(){
       for(Player p : console.getPlayerList()){
           if(!playerdata.get(p.getUniqueId()).IS_READY()){
              return false;
           }
       }
       return true;
   }

   public void resetScores(){
       for(String score : scores){
           scoreboard.resetScores(score);
       }
       scores.clear();
   }


    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
