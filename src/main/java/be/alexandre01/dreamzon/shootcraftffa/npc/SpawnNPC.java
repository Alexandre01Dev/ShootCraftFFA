package be.alexandre01.dreamzon.shootcraftffa.npc;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import com.mojang.authlib.GameProfile;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.nms.v1_8_R3.util.NMSImpl;
import net.citizensnpcs.npc.skin.Skin;
import net.citizensnpcs.npc.skin.SkinPacketTracker;
import net.citizensnpcs.npc.skin.SkinnableEntity;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.VillagerProfession;
import net.citizensnpcs.util.NMS;
import net.citizensnpcs.util.NMSBridge;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class SpawnNPC {
    public void setSpawnNPC(){

        Main.getInstance().npcManager().createNpcCustomWithCitizens("§6§lRacaille","§6RACAIL",DataBaseSkin.getRacailleuuid(),DataBaseSkin.getRacailleTexture(),DataBaseSkin.getRacailleSign(),DataBaseSkin.getRacailleLocation(),true);
        Main.getInstance().npcManager().createNpcCustomWithCitizens("§6§lQuêtes","§6DOG",DataBaseSkin.getDogPumpkinuuid(),DataBaseSkin.getDogPumpkinTexture(),DataBaseSkin.getDogPumpkinSign(),DataBaseSkin.getQuestLocation(),true);
        NPC quest = NPCManager.getNpcWithName("§6§lQuêtes", Bukkit.getWorld("waiting"));

        ItemStack pumpkin = new ItemStack(Material.PUMPKIN);
        ItemMeta itemMeta = pumpkin.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY,1,false);
        pumpkin.setItemMeta(itemMeta);

        NPC npcDaily = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, "§6§lCadeaux quotidien");
        npcDaily.getTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET,pumpkin);
       npcDaily.getTrait(VillagerProfession.class).setProfession(Villager.Profession.LIBRARIAN);

        npcDaily.getTrait(LookClose.class).lookClose(true);
        npcDaily.spawn(DataBaseSkin.getDeliveryLocation());

        //Main.getInstance().npcManager().createNpcCustomSkinWithAPI(DataBaseSkin.getChangeLocation(), Arrays.asList("§6Echange","§6Crapuleux"),1450531324,2);
        //Main.getInstance().npcManager().createNpcCustomSkin(villagerLoc,event.getPlayer(),"§6Quest", DataBaseSkin.getDogPumpkinTexture(),DataBaseSkin.getDogPumpkinSign(),DataBaseSkin.getDogPumpkinuuid());
        //Main.getInstance().music().sendMusic(event.getPlayer(),DataBaseSkin.getQuestLocation());
        //Main.getInstance().npcManager().createNpcCustomSkinWithAPI(DataBaseSkin.getQuestLocation(), Arrays.asList("§6Quest","mhh"),174161795,2);
    }



}
