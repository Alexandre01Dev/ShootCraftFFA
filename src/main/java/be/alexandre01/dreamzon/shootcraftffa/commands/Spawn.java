package be.alexandre01.dreamzon.shootcraftffa.commands;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.spigotmc.SpigotConfig;

import java.util.Random;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("setSpawn")){
                if(player.hasPermission("dz.shootcraft")){
                    if(args.length==0){
                        player.sendMessage("Trop peu d'argument => /setspawn arene / /setspawn home");
                    }else {
                        if(args[0].equalsIgnoreCase("home")){
                            Main.getInstance().getConfig().set("spawn.home",player.getLocation());
                            Main.getInstance().saveConfig();

                            Main.getInstance().home = (Location) Main.getInstance().getConfig().get("spawn.home");
                        }
                        if(args[0].equalsIgnoreCase("gohome")){


                            player.teleport(sendHome());
                        }
                        if(args[0].equalsIgnoreCase("arene")){
                            player.sendMessage("AJOUTE");
                            int total = Main.getInstance().spawnpoints.size()+1;
                            Main.getInstance().getConfig().set("spawn.spawnpoints."+total,player.getLocation());
                            Main.getInstance().saveConfig();
                            Main.getInstance().spawnpoints.add(player.getLocation());

                        }if(args[0].equalsIgnoreCase("goarene")){
                            if(args.length==1){
                                player.sendMessage("veillez mettre un chiffre");
                            }else {
                                player.sendMessage("tp");
                                if(Main.getInstance().getConfig().contains("spawn.spawnpoints."+args[1])){
                                    player.teleport((Location) Main.getInstance().getConfig().get("spawn.spawnpoints."+args[1]));
                                }else {
                                    player.sendMessage("cela n'existe pas");
                                }
                            }


                        }
                        if(args[0].equalsIgnoreCase("randomtp")){
                        if(!Main.getInstance().spawnpoints.isEmpty()){
                            int size = Main.getInstance().spawnpoints.size()-1;
                            Random random = new Random();
                            int total = random.nextInt( size - 0 + 1) + 0;
                           player.teleport(Main.getInstance().spawnpoints.get(total));

                        }


                    }
                        if(args[0].equalsIgnoreCase("reload")){
                            Main.getInstance().reloadConfig();
                            player.sendMessage("La config viens bien d'être reload !");
                        }
                        /*if(args[0].equalsIgnoreCase("delarene")){
                            if(args.length==1){
                                player.sendMessage("veillez mettre un chiffre");
                            }else {
                                player.sendMessage("tp");
                                if(Main.getInstance().getConfig().contains("spawn.spawnpoints."+args[1])){
                                    if(Main.getInstance().getConfig().contains("spawn.spawnpoints")){
                                        System.out.println(Integer.parseInt(args[1]));
                                        for (int i = Integer.parseInt(args[1]); i <= 9999 * 9999; i++) {
                                            if(Main.getInstance().getConfig().contains("spawn.spawnpoints."+i)){
                                                int after = i++;
                                                Location loc = (Location) Main.getInstance().getConfig().get("spawn.spawnpoints."+after);
                                             Main.getInstance().getConfig().set("spawn.spawnpoints."+i,loc);

                                                Main.getInstance().saveConfig();

                                            }else {
                                                int after = i++;
                                                Main.getInstance().getConfig().set("spawn.spawnpoints."+after,null);
                                                Main.getInstance().saveConfig();
                                                break;
                                            }
                                        }
                                }else {
                                    player.sendMessage("cela n'existe pas");
                                }

                    }
                        }
                        }*/
                    }
                }else {
                    player.sendMessage(SpigotConfig.unknownCommandMessage);
                }

            }
        }
        return false;
    }
    public Location readLocation(String path){
        if(Main.getInstance().getConfig().contains(path)){
            String world = Main.getInstance().getConfig().getString(path+".world");
            Double x = Main.getInstance().getConfig().getDouble(path+".x");
            Double y = Main.getInstance().getConfig().getDouble(path+".x");
            Double z = Main.getInstance().getConfig().getDouble(path+".z");
            float pitch = (float) Main.getInstance().getConfig().getDouble(path+".pitch");
            float yaw = (float) Main.getInstance().getConfig().getDouble(path+".yaw");
            return new Location(Bukkit.getWorld(world),x,y,z,pitch,yaw);
        }else {
            return null;
        }


    }
    public static Location sendRandomLocation(){
        if(!Main.getInstance().spawnpoints.isEmpty()){
            int size = Main.getInstance().spawnpoints.size()-1;
            Random random = new Random();
            int total = random.nextInt( size - 0 + 1) + 0;
            return Main.getInstance().spawnpoints.get(total);


    }else {

            setTP();
            if(!Main.getInstance().spawnpoints.isEmpty()){
                int size = Main.getInstance().spawnpoints.size()-1;
                Random random = new Random();
                int total = random.nextInt( size - 0 + 1) + 0;
                return Main.getInstance().spawnpoints.get(total);

        }else {
                return null;
            }
        }




    }
    public static Location sendHome(){
        if(Main.getInstance().home!= null){
           return Main.getInstance().home;
        }else {
            if(Main.getInstance().getConfig().contains("spawn.home")){
                Main.getInstance().home = (Location) Main.getInstance().getConfig().get("spawn.home");
                return Main.getInstance().home;
            }else {
                return null;
            }

        }



    }
    public static void setTP(){
        if(Main.getInstance().getConfig().contains("spawn.spawnpoints")){
            for (int i = 1; i <= 9999 * 9999; i++) {
                if(Main.getInstance().getConfig().contains("spawn.spawnpoints."+i)){
                    System.out.println("OUI");
                    Main.getInstance().spawnpoints.add((Location) Main.getInstance().getConfig().get("spawn.spawnpoints."+i));
                }else {
                    System.out.println("NAN");
                    break;
                }
            }
            if(Main.getInstance().getConfig().contains("spawn.home")){
                Main.getInstance().home = (Location) Main.getInstance().getConfig().get("spawn.home");
            }

        }
    }
}
