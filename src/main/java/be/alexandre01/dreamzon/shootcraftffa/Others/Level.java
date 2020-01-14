package be.alexandre01.dreamzon.shootcraftffa.Others;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import org.bukkit.entity.Player;

public class Level {


    public static String getLevel(Player player){
        int level = player.getLevel();
        String levelst = null;
        if(level < 10){

            levelst = "§7["+ level +"]";
        }
        if(level >=10 && level < 20){
            levelst = "§7[§8"+ level +"§7]";
        }
        if(level >=20 && level < 30){
            levelst = "§7[§a"+ level +"§7]";
        }
        if(level >=30 && level < 40){
            levelst = "§7[§2"+ level +"§7]";
        }
        if(level >= 40 && level < 50){
            levelst = "§7[§3"+ level +"§7]";
        }
        if(level >= 50 && level < 60){
            levelst = "§7[§9"+ level +"§7]";
        }
        if(level >= 60 && level < 70){
            levelst = "§7[§e"+ level +"§7]";
        }
        if(level >= 70 && level < 80){
            levelst = "§7[§6"+ level +"§7]";
        }
        if(level >= 80 && level < 90){
            levelst = "§7[§c"+Integer.toString(level)+"§7]";
        }
        if(level >= 90 && level <= 99){
            levelst = "§7[§4"+Integer.toString(level)+"§7]";
        }
        if(level == 100){
            levelst = "§7[§4§lGOD§7]";
        }





        return levelst;
    }

    public static String getLevelWithInt(int level){

        String levelst = null;
        if(level < 10){

            levelst = "§7["+ level +"]";
        }
        if(level >=10 && level < 20){
            levelst = "§7[§8"+ level +"§7]";
        }
        if(level >=20 && level < 30){
            levelst = "§7[§a"+ level +"§7]";
        }
        if(level >=30 && level < 40){
            levelst = "§7[§2"+ level +"§7]";
        }
        if(level >= 40 && level < 50){
            levelst = "§7[§3"+ level +"§7]";
        }
        if(level >= 50 && level < 60){
            levelst = "§7[§9"+ level +"§7]";
        }
        if(level >= 60 && level < 70){
            levelst = "§7[§e"+ level +"§7]";
        }
        if(level >= 70 && level < 80){
            levelst = "§7[§6"+ level +"§7]";
        }
        if(level >= 80 && level < 90){
            levelst = "§7[§c"+Integer.toString(level)+"§7]";
        }
        if(level >= 90 && level <= 99){
            levelst = "§7[§4"+Integer.toString(level)+"§7]";
        }
        if(level == 100){
            levelst = "§7[§f"+Integer.toString(level)+"§7]";
        }





        return levelst;
    }
    public static void setPoint(Player killer , Player player){
        if (killer != null) {
            if (killer.getLevel() <= 95) {
                if(player.getLevel()!= 100){
                    killer.giveExp(3);
                    Main.getInstance().getConfig().set("exp." + killer.getName(), killer.getTotalExperience());
                }else {
                    killer.giveExp(3*4);
                    Main.getInstance().getConfig().set("exp." + killer.getName(), killer.getTotalExperience());
                }

            }else{
                if(killer.getLevel() > 95 && killer.getLevel() != 100 ) {
                    if(player.getLevel()!= 100){
                        killer.giveExp(1);;
                        Main.getInstance().getConfig().set("exp." + killer.getName(), killer.getTotalExperience());
                    }else {
                        killer.giveExp(1*4);
                        Main.getInstance().getConfig().set("exp." + killer.getName(), killer.getTotalExperience());
                    }
                }



            }


        }
        Main.getInstance().saveConfig();
    }
    public static int InversePoint(Player player){
        int level = player.getLevel();
        int totallevel = 0;
        int value = 0;
        for (int i = level; i <= 100; i++){

            if(i == 100){
            if(value <= 100){
                value = value+801;
            }

        }
            value++;
    }
        return value;
    }
}
