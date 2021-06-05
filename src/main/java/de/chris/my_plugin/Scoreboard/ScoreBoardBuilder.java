package de.chris.my_plugin.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public abstract class ScoreBoardBuilder {

    protected final Scoreboard scoreboard;
    protected final Objective objective;

    protected final Player player;

    // Constructor
    public ScoreBoardBuilder(Player player, String displayName){
        this.player = player;

        if (player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())){
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }

        this.scoreboard = player.getScoreboard();

        if (this.scoreboard.getObjective("display") != null){
            this.scoreboard.getObjective("display").unregister();
        }

        this.objective = this.scoreboard.registerNewObjective("display", "dummy", displayName);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        createScoreboard();
    }
     // Abstract Method to create the Scoreboard
    public abstract void createScoreboard();

    // Sets the Display Name
    public void setDisplayName(String name){
        this.objective.setDisplayName(name);
    }

    // Sets a Score
    public void setScore(String content, int score){
        this.objective.getScore(content).setScore(score);
    }

    // Removes a Score
    public void removeScore(String content){
        this.scoreboard.resetScores(content);
    }

}
