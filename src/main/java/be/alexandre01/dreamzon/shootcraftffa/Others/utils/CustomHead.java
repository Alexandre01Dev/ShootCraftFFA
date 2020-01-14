package be.alexandre01.dreamzon.shootcraftffa.Others.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class CustomHead {
    public static ItemStack CustomHead(String texture){
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullM = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", texture));
        Field profileField = null;
        try {
            profileField = skullM.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullM, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullM);
        return skull;
    }

}
