package be.alexandre01.dreamzon.shootcraftffa.commands;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import be.alexandre01.dreamzon.shootcraftffa.npc.SkinManager;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Stairs;

import java.io.IOException;

public class Stats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(cmd.getName().equalsIgnoreCase("stats")){
            if(sender.hasPermission("dz.shootcraft")){
            if(args.length ==0) {
                sender.sendMessage("§4Veuillez faire /stats reset [Player]");
                sender.sendMessage("§4/stats set [Stats] [Player] [Valeur]");
            }else {

                    if(args[0].equalsIgnoreCase("reset")){
                        if(args.length == 2){

                            if(isOnline(args[1])){
                                Player player = Bukkit.getPlayer(args[1]);
                                for (int i = 0; i <= 5; i++) {


                                   player.setStatistic(Statistic.values()[i],0);
                                    player.sendMessage(Statistic.values()[i].toString()+" a été reset");
                                }

                            }

                            }
                        }else {

                        if(args[0].equalsIgnoreCase("migrate")){
                            if(args.length == 3){

                                if(isOnline(args[1])){
                                    Player player = Bukkit.getPlayer(args[1]);
                                    if(Main.getInstance().getStatsConfig().contains("statistics."+args[2])){
                                    for (int i = 0; i <= 5; i++) {


                                        player.setStatistic(Statistic.values()[i],Main.getInstance().getStatsConfig().getInt("statistics."+args[2]+"."+Statistic.values()[i].toString()));

                                    }
                                    }else {
                                        sender.sendMessage("etes vous sur que ce joueur existe ?");
                                    }
                                }

                            }
                        }
                        if(args[0].equalsIgnoreCase("DreamCoins")){
                            DBObject r = new BasicDBObject("nickname", sender.getName());
                            DBObject found = Main.getInstance().collection.findOne(r);

                            sender.sendMessage(String.valueOf(found.get("COINS")));
                        }

                    }
                    }

            }


    }
        return false;
    }
    public static boolean isOnline(String player){
        boolean isOnline = false;

        for(Player all : Bukkit.getOnlinePlayers()){
            if(all.getName().equalsIgnoreCase(player)){
                isOnline=true;
                return isOnline;
            }

        }



        return isOnline;
    }

}
