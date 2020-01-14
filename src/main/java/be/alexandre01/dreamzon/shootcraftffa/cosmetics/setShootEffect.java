package be.alexandre01.dreamzon.shootcraftffa.cosmetics;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.listsOfCosmetics.ShootEffectKill;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class setShootEffect {
    public setShootEffect( Player player, String shootEffect){
        if(shootEffect.equals(ShootEffectKill.DEFAULT.toString())){
            Main.getInstance().effect().trailEffect(player, Effect.FIREWORKS_SPARK);
            player.playSound(player.getLocation(), Sound.FIREWORK_BLAST,0.5f,1.5f);
        }
        if(shootEffect.equals(ShootEffectKill.FIRE.toString())){
            Main.getInstance().effect().trailEffect(player, Effect.FLAME);
            player.playSound(player.getLocation(), Sound.GHAST_FIREBALL
                    ,0.5f,1.5f);
        }
        if(shootEffect.equals(ShootEffectKill.SNOW.toString())){
            Main.getInstance().effect().trailEffect(player, Effect.SNOW_SHOVEL);
            Main.getInstance().npcManager().createNPCOnKill(player,player,"bla");
        }
        if(shootEffect.equals(ShootEffectKill.HEARTH.toString())){
            Main.getInstance().effect().trailEffect(player, Effect.HEART);
        }
        if(shootEffect.equals(ShootEffectKill.CLOUD.toString())){
            Main.getInstance().effect().trailEffect(player, Effect.CLOUD);
        }
        if(shootEffect.equals(ShootEffectKill.MAGIC.toString())){
            Main.getInstance().effect().trailEffect(player, Effect.WITCH_MAGIC);
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT,0.5f,1.5f);
        }
        if(shootEffect.equals(ShootEffectKill.HAPPY.toString())){
            Main.getInstance().effect().trailEffect(player, Effect.HAPPY_VILLAGER);
            player.playSound(player.getLocation(), Sound.NOTE_STICKS,0.5f,1.5f);
        }
        if(shootEffect.equals(ShootEffectKill.BLAZE.toString())){
            Main.getInstance().effect().trailEffect(player, Effect.CRIT);
            player.playSound(player.getLocation(), Sound.ZOMBIE_WOODBREAK,0.5f,1.5f);
        }
    }
}
