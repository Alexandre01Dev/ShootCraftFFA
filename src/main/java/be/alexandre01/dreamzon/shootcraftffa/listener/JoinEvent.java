package be.alexandre01.dreamzon.shootcraftffa.listener;

import be.alexandre01.dreamzon.shootcraftffa.Ambiant.Light;
import be.alexandre01.dreamzon.shootcraftffa.Main;
import be.alexandre01.dreamzon.shootcraftffa.Packet;


import be.alexandre01.dreamzon.shootcraftffa.commands.Spawn;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.Operations;
import be.alexandre01.dreamzon.shootcraftffa.gameplay.DeadInventory;
import be.alexandre01.dreamzon.shootcraftffa.npc.DataBaseSkin;
import be.alexandre01.dreamzon.shootcraftffa.npc.Reflection;
import be.alexandre01.dreamzon.shootcraftffa.packet.PacketReader;
import be.alexandre01.dreamzon.shootcraftffa.packet.Reflections;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.libs.jline.console.Operation;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.TrapDoor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {
        PacketReader pr = new PacketReader(event.getPlayer());
        pr.inject();

        EntityPlayer playerNMS = ((CraftPlayer) event.getPlayer()).getHandle();


        Main.getInstance().getScoreboardManager().onLogin(event.getPlayer());
        Operations.setupPlayerCosmeticsData(event.getPlayer());
        new Light().setLight(event.getPlayer().getWorld());
        System.out.println(event.getPlayer().getUniqueId());
        //Main.getInstance().nameTag().SetNameTag(event.getPlayer());
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2), true);
        event.getPlayer().setGameMode(GameMode.ADVENTURE);
        Main.getInstance().nameTag().setonKill(event.getPlayer());
        Main.getInstance().deads.add(event.getPlayer());
        DeadInventory.setInventory(event.getPlayer());
        event.getPlayer().teleport(Spawn.sendHome());
        World world;
        Location npcLoc = new Location(Bukkit.getWorld("waiting"),405.887 ,4 ,-236.654, 168.5f,-1.9f);

       // Main.getInstance().npcManager().createNpc(npcLoc , event.getPlayer() ,"Guardien","Gardian" );

    }

   /* @EventHandler
    public void onMove(PlayerMoveEvent e) {

        Player player = e.getPlayer();
        Location loc = player.getLocation();
        Location loc2 = player.getLocation();
        loc2.add(0,0,0);
        if(loc2.getBlock().getType().equals(Material.DAYLIGHT_DETECTOR_INVERTED)){
            GiveTimer(player);
            loc2.getBlock().setType(Material.DAYLIGHT_DETECTOR);
            BlockTimer(loc2.getBlock());
            Location traploc = loc2.getBlock().getLocation();
            traploc.add(0,23,0);
            System.out.println(traploc.getX() +" "+ traploc.getY() + " " + traploc.getZ());
            System.out.println(traploc.getBlock().getType());
            if(traploc.getBlock().getType().equals(Material.TRAP_DOOR)){
                TrapDoor trapDoor = (TrapDoor) traploc.getBlock().getState().getData();
                trapDoor.setOpen(true);
                traploc.getBlock().setData(trapDoor.getData());
                TrapTimer(trapDoor, traploc.getBlock());
            }
            List<Block> blockslist = Packet.getNearbyBlocks(loc2,3);

            for(Block blocks : blockslist){
                if(blocks.getType().equals(Material.DAYLIGHT_DETECTOR_INVERTED)){
                   blocks.setType(Material.DAYLIGHT_DETECTOR);
                   BlockTimer(blocks);
                   Location blockloc = blocks.getLocation();
                   blockloc.add(0,0.2,0);

                    FallingBlock fb = loc2.getWorld().spawnFallingBlock(blockloc,Material.GLASS, (byte)0);
                    fb.setHurtEntities(true);
                    fb.setDropItem(false);
                    fb.setFallDistance(1);
                    fb.setTicksLived(100);
                    Vector vec2 = new Vector(0, 2.05  , 0);
                    fb.setVelocity(vec2);
                    Location traploc2 = blocks.getLocation();
                    traploc2.add(0,23,0);
                    System.out.println(traploc.getX() +" "+ traploc2.getY() + " " + traploc2.getZ());
                    System.out.println(traploc2.getBlock().getType());
                    if(traploc2.getBlock().getType().equals(Material.TRAP_DOOR)){
                        TrapDoor trapDoor = (TrapDoor) traploc2.getBlock().getState().getData();
                        trapDoor.setOpen(true);
                        traploc2.getBlock().setData(trapDoor.getData());
                        TrapTimer(trapDoor, traploc2.getBlock());
                    }
            }



        }
            loc2.add(0,0.2,0);
            FallingBlock fb = loc2.getWorld().spawnFallingBlock(loc2, Material.GLASS, (byte)0);
            fb.setHurtEntities(true);
            fb.setDropItem(false);
             fb.setFallDistance(1);

            fb.setHurtEntities(true);
            Vector vec2 = new Vector(0, 2.05  , 0);
            fb.setVelocity(vec2);
            Vector vec = new Vector(0, 2.3, 0);
            player.setVelocity(vec);

        }

    }
    public void BlockTimer(Block block){
        new BukkitRunnable() {
            int seconds = 4;
            @Override
            public void run() {
            seconds--;
            if(seconds==0){
                block.setType(Material.DAYLIGHT_DETECTOR_INVERTED);
                cancel();
            }
            }
        }.runTaskTimer(Main.getInstance(), 0,20L);
    }
    public void TrapTimer(TrapDoor trapDoor , Block block ){
        new BukkitRunnable() {
            int seconds = 2;
            @Override
            public void run() {
                seconds--;
                if(seconds==0){
                    trapDoor.setOpen(false);
                    block.setData(trapDoor.getData());
                    cancel();
                }
            }
        }.runTaskTimer(Main.getInstance(), 0,20L);
    }
    public void GiveTimer(Player player){
        new BukkitRunnable() {
            int seconds = 2;
            @Override
            public void run() {
                seconds--;
                if(seconds==0){
                    player.sendMessage("§e[§6WOW§e] §6Tu viens d'obtenir des elytras !");
                    Material type;
                    //ItemStack elytra = new ItemStack(Material.ELYTRA);
                   /* ItemMeta elytraM = elytra.getItemMeta();
                    elytraM.setDisplayName("Les elytra revards !");
                    elytraM.addEnchant(Enchantment.DURABILITY, 1, false);
                    elytraM.setLore(Arrays.asList("§6Vole","§6Reve","§6Explore"));
                    elytraM.spigot().setUnbreakable(true);
                    elytra.setItemMeta(elytraM);
                    player.getInventory().setChestplate(elytra);
                    player.updateInventory();
                    cancel();
                }
            }
        }.runTaskTimer(Main.getInstance(), 0,20L);
    }*/

}




