package hadences.projectdemonslayer.game.gamemode;

import hadences.projectdemonslayer.ProjectDemonSlayer;
import hadences.projectdemonslayer.chat.Chat;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;



public class GamemodeManager {
    public static ArrayList<Listener> gamemodeListeners = new ArrayList<>();
    private ProjectDemonSlayer ds = ProjectDemonSlayer.getPlugin(ProjectDemonSlayer.class);
    private boolean endGame;
    private String Winner;
    private ArrayList<Player> playerlist;
    public void initialize(int StartingStamina, double StartingHealth, ArrayList<Player> playerlist){


    }

    public void start(){

    }

    public void end(){

    }

    public void endgame(){

    }

    public String printWinnerStatement(){
        if(Winner.isEmpty() || Winner.equalsIgnoreCase("")){
            return Chat.format("&cForce Ended");
        }else{
            return Chat.format("&6"+Winner + " &fWins!");
        }
    }


    public void checkForWinner(){

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
}
