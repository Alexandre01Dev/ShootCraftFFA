package be.alexandre01.dreamzon.shootcraftffa.listener;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import be.alexandre01.dreamzon.shootcraftffa.Others.Level;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Chat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
    if(event.getMessage().toLowerCase().contains("HubZon".toLowerCase())){
        int index = event.getMessage().toLowerCase().indexOf("hubzon");
        System.out.println(event.getMessage().substring(0,index));
        System.out.println(event.getMessage().substring(index+6));

        event.setMessage(event.getMessage().substring(0,index)+"DreamZon"+event.getMessage().substring(index+6));
    }
    event.setCancelled(true);

    for(Player players : Bukkit.getOnlinePlayers()){
        int totalchar = 0;
        TextComponent message = new TextComponent( "" );
        if(event.getPlayer().hasPermission("support.shootcraft")){
            TextComponent support = new TextComponent( "§e§l⭐");
            support.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("§6§l"+event.getPlayer().getName() +"\n à supporté le serveur" ).create()));
            message.addExtra(support);
            message.addExtra(" ");
            totalchar = totalchar + 1;
        }
        TextComponent level = new TextComponent(Level.getLevel(event.getPlayer()));
        if(players.getLevel() > event.getPlayer().getLevel()){
            int calcul = players.getLevel()-event.getPlayer().getLevel();
            level.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("§6§l"+event.getPlayer().getName() +":\n\n§6§l "+ getTime(event.getPlayer())+"\n\n§6§lTu as "+calcul+" de level de plus que lui :)" ).create()));

        }
        if(players.getLevel() < event.getPlayer().getLevel()){
            int calcul = event.getPlayer().getLevel()-players.getLevel();
            level.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("§6§l"+event.getPlayer().getName() +":\n\n§6§l " + getTime(event.getPlayer())+"\n\n§6§lTu as "+calcul+" de level de moins que lui :'(" ).create()));
        }
        if(players.getLevel() == event.getPlayer().getLevel()){

            level.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("§6§l"+event.getPlayer().getName() +":\n\n§6§l "+getTime(event.getPlayer())+"\n\n§6§lTu as le meme level que lui §nEXTRA !" ).create()));
        }
        totalchar = totalchar + level.getText().length()+1;
        message.addExtra(level);
        message.addExtra(" ");

        TextComponent name = new TextComponent(event.getPlayer().getName());
        name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("§7Cliquez ici pour envoyer un message à ce ce joueur"+"§6§l:\n\n§6§l "+event.getPlayer().getPlayerTime()+"\n\n§6§lKill: "+ event.getPlayer().getStatistic(Statistic.PLAYER_KILLS)).create()));
        message.addExtra(name);
        message.addExtra(" §7» ");
        totalchar = totalchar + 4;
        totalchar = totalchar + name.getText().length()+3;
        if(totalchar-10 + event.getMessage().length()>= 54){
            String[] parts = event.getMessage().substring(0,53-(totalchar-20)).split(" ");
            String lastWord = parts[parts.length - 1];
            System.out.println(lastWord);
            String[] partsmsg2 = event.getMessage().substring(53-(totalchar-20)).split(" ");
            String firstword = parts[0];
            int plus = 0;
            if(!lastWord.equals(" ")){
                if(!firstword.equals(" ")){
                    plus = lastWord.length();
                }
            }
            System.out.println(firstword);
            TextComponent msg1 = new TextComponent( event.getMessage().substring(0,(53-plus)-(totalchar-20)));
            TextComponent msg2 = new TextComponent( event.getMessage().substring((53-plus)-(totalchar-20)));
            if(msg1.getText().contains("&")){
                int index = 0;
                for (int i = 0; i < msg1.getText().length(); i++) {

                    if(msg1.getText().indexOf("&",i)!= -1){
                        index = msg1.getText().indexOf("&",i);
                        System.out.println(index);
                    }


                }
                String colors = null;
                System.out.println(index);
                if(index >1){
                    if(msg1.getText().substring(index-2,index-1).contains("&")){

                        colors = msg1.getText().substring(index-2,index) + msg1.getText().substring(index,index+2); ;
                        System.out.println(colors);
                    }else {
                        colors = msg1.getText().substring(index,index+2);
                        System.out.println(colors);
                    }
                    msg2 = new TextComponent(colors+event.getMessage().substring((53-plus)-(totalchar-20)));
                }else {

                    colors = msg1.getText().substring(index,index+2);
                    System.out.println(colors);
                    msg2 = new TextComponent(colors+event.getMessage().substring((53-plus)-(totalchar-20)));

                }

                }


            System.out.println(totalchar);
            msg1.setText(msg1.getText().replaceAll("&","§"));
            msg2.setText(msg2.getText().replaceAll("&","§"));
            message.addExtra(msg1);
            message.addExtra("\n");
            message.addExtra(msg2);



        }else {
            TextComponent msg = new TextComponent(event.getMessage());
            msg.setText(msg.getText().replaceAll("&","§"));
            message.addExtra(msg);
        }
        message.setClickEvent( new ClickEvent( ClickEvent.Action.SUGGEST_COMMAND, "/msg "+event.getPlayer().getName()));
        players.spigot().sendMessage(message);
    }
//54


    event.getMessage();
    }
    @EventHandler
    public void onTab(PlayerChatTabCompleteEvent event){
        System.out.println(event.getChatMessage());
        System.out.println(event.getLastToken());
        Main.getInstance().tabargs.put(event.getPlayer(),event.getChatMessage());
    }
    public String getTime(Player player){
        Date today = new Date();
        System.out.println("Today, the date is "+today.getDate());
        System.out.println("Today is the "+today.getDay()+" of the week");
        if(today.getSeconds()<10){
            return today.getHours()+":"+today.getMinutes()+":0"+today.getSeconds();
        }else {
            return today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
        }




    }




}
