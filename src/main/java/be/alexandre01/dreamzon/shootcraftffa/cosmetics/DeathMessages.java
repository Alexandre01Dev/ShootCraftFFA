package be.alexandre01.dreamzon.shootcraftffa.cosmetics;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class DeathMessages {
    public static String NormalMsg(PlayerDeathEvent event){
        Random r = new Random();
        int choice = r.nextInt(3);
        String Name = event.getEntity().getName();
        String Killer = event.getEntity().getKiller().getName();
        if(choice == 1){
            return "§6§l";
        }

        return null;
    }
    public static String NourritureMsg(PlayerDeathEvent event){
        String Name = "☠"+event.getEntity().getName()+"☠";
        String Killer = event.getEntity().getKiller().getName();
        Random r = new Random();
        int choice = r.nextInt((4 - 1) + 1) + 1;
        String finalstring = null;
        if(choice == 1){
            finalstring= "§6§l"+Killer+" §7a croqué §c§l"+ Name;
        }
        if(choice == 2){
            finalstring= "§c§l"+Name+" §7a été passé à la friteusse par §6§l"+ Killer;
        }
        if(choice == 3){

            finalstring= "§c§l"+Name+" §7est §cROUGE§7 §cTOMATE§7 à cause de §6§l"+ Killer;


        }
        if(choice == 4){
            finalstring= " §6§l"+Killer+"§7 à bien mangé avant de tuer §c§l"+ Name;
        }

            return finalstring;


    }
}
