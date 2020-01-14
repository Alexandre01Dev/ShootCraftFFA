package be.alexandre01.dreamzon.shootcraftffa.box;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.spigotmc.SpigotConfig;

import java.util.List;
import java.util.Set;

public class CommandsBox implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if(sender instanceof Player){

            Player player = (Player) sender;
            if(cmd.getName().equals("box")){
                if(player.hasPermission("shootcraft.box")){
                if(args.length == 0){
                    player.sendMessage("Veuillez utiliser des commandes adequates");
                    player.sendMessage("/box set");
                    player.sendMessage("/box remove");
                    return true;
                }
                if(args[0].equals("set")){


                            Block block = player.getTargetBlock((Set<Material>) null, 5);
                            Location loc = block.getLocation();
                    if(block.getType().equals(Material.CHEST)){


                        List<Entity> nearbyEntites = (List<Entity>) loc.getWorld().getNearbyEntities(loc, 0.5D, -0.8D, 0.5D);
                        for(Entity ent : nearbyEntites){
                            if(ent instanceof ArmorStand){
                             player.sendMessage("Vous ne pouvez pas set à nouveau une meme box");
                            return true;
                            }

                        }
                        ArmorStand s0 = (ArmorStand) block.getLocation().getWorld().spawnEntity( block.getLocation().add(0.5D, -0.8D, 0.5D), EntityType.ARMOR_STAND);
                        s0.setArms(false);
                        s0.setGravity(false);
                        s0.setSmall(false);
                        s0.setVisible(false);
                        s0.setCustomName("§6§lBox");
                        s0.setCustomNameVisible(true);
                        s0.setCanPickupItems(false);
                        player.sendMessage("La box a bien été ajouté");
                    }else {
                        player.sendMessage("Tu dois regarder un coffre");
                    }

                }
                if(args[0].equals("remove")){
                    Block block = player.getTargetBlock((Set<Material>) null, 5);
                    if(block.getType().equals(Material.CHEST)){
                        Location loc = block.getLocation();
                        List<Entity> nearbyEntites = (List<Entity>) loc.getWorld().getNearbyEntities(loc, 0.5D, -0.8D, 0.5D);
                        for(Entity ent : nearbyEntites){
                            if(ent instanceof ArmorStand){
                                ent.remove();
                                block.setType(Material.AIR);
                                player.sendMessage("La box a bien été delete");
                    }else{
                                player.sendMessage("Ceci est juste un coffre normal");
                        }
                        }
                        }else {
                        player.sendMessage("tu dois regarder la box");
                    }
                }

                }else {
                    player.sendMessage(SpigotConfig.unknownCommandMessage);
                }
                return true;
            }

        }
        return false;
    }
}
