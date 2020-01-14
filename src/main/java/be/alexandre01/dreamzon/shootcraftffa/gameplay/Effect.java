package be.alexandre01.dreamzon.shootcraftffa.gameplay;

import be.alexandre01.dreamzon.shootcraftffa.Main;

import be.alexandre01.dreamzon.shootcraftffa.Others.UniversalSkinFactory;
import be.alexandre01.dreamzon.shootcraftffa.commands.Spawn;

import be.alexandre01.dreamzon.shootcraftffa.npc.NPCManager;
import be.alexandre01.dreamzon.shootcraftffa.npc.SkinManager;
import com.mojang.authlib.GameProfile;
import me.confuser.barapi.BarAPI;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.apache.commons.io.IOUtils;
import org.bukkit.*;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.*;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

public class Effect {
   // BarAPI barAPI= new BarAPI();
    public void trailEffect(Player player, org.bukkit.Effect effect){

        Location loc = player.getLocation();
        loc.add(0,1.75,0);
        //Projectile projectile = player.launchProjectile(Egg.class, loc.getDirection().normalize());
        Vector v = loc.getDirection();
        double d = 0;
        for (double i = 0; i <= 200; i++) {
            d+=0.5;
            double x = v.getX()*d;
            double y = v.getY()*d;
            double z = v.getZ()*d;

            loc.add(x,y,z);
            loc.getWorld().spigot().playEffect(loc ,effect, 0,0,0,0,0,0,1,50 );
            if(loc.getBlock().getType()!= Material.AIR){
                System.out.println(loc.getBlock().getType());
                break;
            }
            loc.subtract(x,y,z);
                }





        }



    public void checkifhit(Player player) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Location loc = player.getLocation();
        loc.add(0,1.75,0);
        //Projectile projectile = player.launchProjectile(Egg.class, loc.getDirection().normalize());
        Vector v = loc.getDirection();
        double d = 0;
        int totalkill = 0;
        for (double i = 0; i <= 200; i++) {
            d+=0.5;
            double x = v.getX()*d;
            double y = v.getY()*d;
            double z = v.getZ()*d;

            loc.add(x,y,z);

            List<Entity> nearbyEntites = (List<Entity>) loc.getWorld().getNearbyEntities(loc, 0.75, 0.50, 0.75);

            for(Entity entities : nearbyEntites){
                if(entities instanceof LivingEntity){
                    if(!(entities == player)){
                        LivingEntity livingEntities = (LivingEntity) entities;
                        if(!entities.hasMetadata("NPC")){
                        System.out.println(totalkill);


                            ((CraftLivingEntity)livingEntities).getHandle().damageEntity(DamageSource.playerAttack((EntityHuman) player.getClass().getMethod("getHandle").invoke(player)), 30);

                        final Vector vec = new Vector();
                        totalkill++;

                        if(livingEntities instanceof Player){

                            Player human = (Player) livingEntities;
                            if(human.getGameMode().equals(GameMode.SURVIVAL)||human.getGameMode().equals(GameMode.ADVENTURE)){
                        //human.spigot().respawn();

                        new BukkitRunnable() {
                            int ticks = 50;
                            @Override
                            public void run() {
                                if(ticks==40){

                                    human.sendTitle("Tu respawn" , "dans quelques secondes");
                                    Main.getInstance().nameTag().RemoveNameTag(human);
                                    Main.getInstance().nameTag().setonKill(human);
                                    DeadInventory.setInventory(human);

                                }
                               // human.teleport(Spawn.sendHome());
                                human.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2), true);

                                 human.setWalkSpeed(0);

                                 ticks--;
                                 if (ticks==0){
                                     human.setWalkSpeed(0.5f);
                                     Main.getInstance().playersdead.remove(human);
                                     cancel();

                                 }
                            }
                        }.runTaskTimer(Main.getInstance(),0l,1l);
                    }
                        }
                    }
            }
                }


            }

            if(loc.getBlock().getType()!= Material.AIR){
                System.out.println(loc.getBlock().getType());
                break;
            }
            loc.subtract(x,y,z);

        }
        if(totalkill>2){
            Bukkit.broadcastMessage("§c! Attention ! §6[DOUBLE-LIFE] §e"+player.getName()+" à désormais une deuxiemes vie");


        }
        totalkill=0;
}

    public void InstantFirework(FireworkEffect fe, Location loc) {
        Firework f = (Firework) loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta fm = f.getFireworkMeta();
        fm.addEffect(fe);
        f.setFireworkMeta(fm);
        try {
            Class<?> entityFireworkClass = getClass("net.minecraft.server.", "EntityFireworks");
            Class<?> craftFireworkClass = getClass("org.bukkit.craftbukkit.", "entity.CraftFirework");
            Object firework = craftFireworkClass.cast(f);
            Method handle = firework.getClass().getMethod("getHandle");
            Object entityFirework = handle.invoke(firework);
            Field expectedLifespan = entityFireworkClass.getDeclaredField("expectedLifespan");
            Field ticksFlown = entityFireworkClass.getDeclaredField("ticksFlown");
            ticksFlown.setAccessible(true);
            ticksFlown.setInt(entityFirework, expectedLifespan.getInt(entityFirework) - 1);
            ticksFlown.setAccessible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Class<?> getClass(String prefix, String nmsClassString) throws ClassNotFoundException {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String name = prefix + version + nmsClassString;
        Class<?> nmsClass = Class.forName(name);
        return nmsClass;
    }
    public void setWeaponTimer(Player player){
        new BukkitRunnable() {


            int ticks = 0;
            @Override
            public void run() {

                float pourcentage = ticks * 100 / 4;

                System.out.println(pourcentage/100);

                if(ticks==0){

                    BarAPI.setMessage(player,"...", 3);
                    Material type;
                    ItemStack stick = new ItemStack(Material.STICK);
                    ItemMeta stickM = stick.getItemMeta();
                    stickM.setDisplayName("Bâton déchargé ");
                    stick.setItemMeta(stickM);
                    player.getInventory().setItem(4,stick);
                }
                if(ticks==10){

                }
                if(ticks == 4){
                    BarAPI.removeBar(player);
                    ItemStack stick = new ItemStack(Material.BLAZE_ROD);
                    ItemMeta stickM = stick.getItemMeta();
                    stickM.setDisplayName("§6§lBâton chargé ");
                    stick.setItemMeta(stickM);
                    player.getInventory().setItem(4, stick);
                   // bossBar.removePlayer(player);

                    cancel();
                }
               // float total = pourcentage/10;
              //  bossBar.setVisible(true);
              //  bossBar.setProgress(total);
                //BarAPI.setMessage(player,"...", pourcentage);
                ticks++;

            }
        }.runTaskTimer(Main.getInstance(),0L,5L);
    }
    public static String getUUID(String playerName) {
        try {
            String url = "https://api.mojang.com/users/profiles/minecraft/" + playerName;

            String UUIDJson = IOUtils.toString(new URL(url));

            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);

            String uuid = UUIDObject.get("id").toString();

            return uuid;
        } catch (Exception e) {
            return "";
        }
    }
}

