package be.alexandre01.dreamzon.shootcraftffa.listener;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import be.alexandre01.dreamzon.shootcraftffa.Packet;
import be.alexandre01.dreamzon.shootcraftffa.commands.Spawn;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.setWeapon;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.TrapDoor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class JoinGame implements Listener {

    @EventHandler
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
            Vector vec = new Vector(0, 3, 0);
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
                    player.sendMessage("§e[§6WOUSH§e] §6Tu viens d'être tp dans l'arene !");
                    Main.getInstance().deads.remove(player);
                    player.teleport(Spawn.sendRandomLocation());
                    setWeapon.setChargedWeapon(player,Main.getInstance().getCosmeticConfig().getString("players."+player.getName()+".Weapon"));
                    player.setGameMode(GameMode.ADVENTURE);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999,2),true);
                    player.setWalkSpeed(0.5f);
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);
                    Main.getInstance().nameTag().remonKill(player);
                    if(!player.hasPermission("dz.vip")){
                        Main.getInstance().nameTag().SetNameTag(player);
                    }else {
                        Main.getInstance().nameTag().SetNameTagForPremium(player);
                    }


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
                    cancel();*/
                }
            }
        }.runTaskTimer(Main.getInstance(), 0,20L);
    }
    public void setTP(){
        if(Main.getInstance().getConfig().contains("spawn.spawnpoints")){
            for (int i = 1; i <= 9999 * 9999; i++) {
                if(Main.getInstance().getConfig().contains("spawn.spawnpoints."+i)){
                    Main.getInstance().spawnpoints.add((Location) Main.getInstance().getConfig().get("spawn.spawnpoints."+i));
                }else {
                    break;
                }
            }
            if(Main.getInstance().getConfig().contains("spawn.home")){
                Main.getInstance().home = (Location) Main.getInstance().getConfig().get("spawn.home");
            }

        }
    }
}
