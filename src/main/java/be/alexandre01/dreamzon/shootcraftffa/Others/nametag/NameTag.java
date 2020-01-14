package be.alexandre01.dreamzon.shootcraftffa.Others.nametag;

import be.alexandre01.dreamzon.shootcraftffa.Others.Level;
import be.alexandre01.dreamzon.shootcraftffa.scoreboard.ScoreboardTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class NameTag {
    public void SetNameTag(Player player){
        Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();
        Team t = score.getTeam(Level.InversePoint(player)+Level.getLevel(player).replaceAll("§",""));

        if(t == null){
            t = score.registerNewTeam(Level.InversePoint(player)+Level.getLevel(player).replaceAll("§",""));
        }
        System.out.println(Level.InversePoint(player));
        t.setPrefix(Level.getLevel(player)+" ");
        t.addPlayer(player);

        for(Player players : Bukkit.getOnlinePlayers()){

            players.setScoreboard(score);
        }
    }
    public void SetNameTagForPremium(Player player){
        Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();
        Team t = score.getTeam(Level.InversePoint(player)+Level.getLevel(player).replaceAll("§","")+"S");

        if(t == null){
            t = score.registerNewTeam(Level.InversePoint(player)+Level.getLevel(player).replaceAll("§","")+"S");
        }
        System.out.println(Level.InversePoint(player));
        t.setPrefix(Level.getLevel(player)+" ");
        t.setSuffix(" §e§l⭐");
        t.addPlayer(player);

        for(Player players : Bukkit.getOnlinePlayers()){

            players.setScoreboard(score);
        }
    }
    public void RemoveNameTag(Player player){
        Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();
        Team t = score.getTeam(Level.getLevel(player));
        if(t == null){
            t = score.registerNewTeam(Level.getLevel(player));
        }
        t.setPrefix(Level.getLevel(player));
        t.removePlayer(player);

        for(Player players : Bukkit.getOnlinePlayers()){

            players.setScoreboard(score);
        }
    }
    public void setonKill(Player player){
        Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();
        String name;

        if(score.getTeam(Level.getLevel(player))!=null){
            score.getTeam(Level.getLevel(player)).unregister();
        }
            Team t = null;
            if(  score.getTeam(993+"dead")== null){
                t = score.registerNewTeam(993+"dead");
            }
            t = score.getTeam(993+"dead");

            t.setPrefix("§7[MORT] ");
            t.setDisplayName(player.getName());
            t.setCanSeeFriendlyInvisibles(true);
            //t.setOption(Option.NAME_TAG_VISIBILITY  , Team.OptionStatus.ALWAYS);
            //t.setOption(Option.COLLISION_RULE,Team.OptionStatus.NEVER);


        t.addPlayer(player);
        for(Player players : Bukkit.getOnlinePlayers()){

            players.setScoreboard(score);

}
    }
    public void remonKill(Player player){
        Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();
        String name;

        if(score.getTeam(Level.getLevel(player))!=null){
            score.getTeam(Level.getLevel(player)).unregister();
        }
        Team t = null;
        if(  score.getTeam("dead")== null){
            t = score.registerNewTeam("dead");
        }
        t = score.getTeam("dead");

        t.setPrefix("§7[MORT] ");
        t.setDisplayName(player.getName());
        t.setCanSeeFriendlyInvisibles(true);
        //t.setOption(Option.NAME_TAG_VISIBILITY  , Team.OptionStatus.ALWAYS);
        //t.setOption(Option.COLLISION_RULE,Team.OptionStatus.NEVER);


        t.removePlayer(player);
        for(Player players : Bukkit.getOnlinePlayers()){

            players.setScoreboard(score);

        }
    }
}
