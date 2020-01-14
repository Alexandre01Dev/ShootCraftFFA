package be.alexandre01.dreamzon.shootcraftffa.cosmetics;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.listsOfCosmetics.KillEffectList;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.listsOfCosmetics.ShootEffectKill;
import be.alexandre01.dreamzon.shootcraftffa.cosmetics.listsOfCosmetics.WeaponsList;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Operations {
    public static void setupPlayerCosmeticsData(Player player) throws IOException {
        if(Main.getInstance().getCosmeticConfig().contains("players."+player.getName())){
            Main.getInstance().playersshoot.put(player,Main.getInstance().getCosmeticConfig().getString("players."+player.getName()));

        }else {
            player.sendMessage("§eHey tu es nouveau , ton compte viens d'être configuré tu peux jouer !");
            Main.getInstance().getCosmeticConfig().set("players."+player.getName()+".KillEffect", KillEffectList.DEFAULT.toString());
            Main.getInstance().getCosmeticConfig().set("players."+player.getName()+".Weapon", WeaponsList.DEFAULT.toString());
            Main.getInstance().getCosmeticConfig().set("players."+player.getName()+".ShootEffect", ShootEffectKill.DEFAULT.toString());
            Main.getInstance().getCosmeticConfig().save(Main.getInstance().getCosmeticFile());
        }
    }
    public static void setShootEffect(Player player, ShootEffectKill shootEffect) throws IOException {
        Main.getInstance().getCosmeticConfig().set("players."+player.getName()+".ShootEffect",shootEffect.toString());
        Main.getInstance().getCosmeticConfig().save(Main.getInstance().getCosmeticFile());
    }
    public static void setKillEffect(Player player, KillEffectList killEffectList) throws IOException {
        Main.getInstance().getCosmeticConfig().set("players."+player.getName()+".KillEffect",killEffectList.toString());
        Main.getInstance().getCosmeticConfig().save(Main.getInstance().getCosmeticFile());
    }
    public static void setWeapon(Player player, WeaponsList weaponsList) throws IOException {
        Main.getInstance().getCosmeticConfig().set("players."+player.getName()+".Weapon",weaponsList.toString());
        Main.getInstance().getCosmeticConfig().save(Main.getInstance().getCosmeticFile());
    }
    public static String getPlayerCosmeticsData(Player player){
       return Main.getInstance().playersshoot.get(player);
    }
}
