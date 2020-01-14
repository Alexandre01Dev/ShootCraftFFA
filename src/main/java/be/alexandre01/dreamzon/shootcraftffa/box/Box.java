package be.alexandre01.dreamzon.shootcraftffa.box;

import be.alexandre01.dreamzon.shootcraftffa.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Box {
    public void BuyingBox(Player player , Inventory[] inv){
        inv[0] = Bukkit.createInventory(null, 27, "§cMenu");
        ArrayList<Material> mat = new ArrayList<>();
        Material type;

        ItemStack chest = new ItemStack(Material.CHEST);
        ItemMeta itemMeta = chest.getItemMeta();
        itemMeta.setDisplayName("§6Box");
        itemMeta.setLore(Arrays.asList("§7§lCOUT [§6§l1000 DREAMCOINS§7§l]"));
        chest.setItemMeta(itemMeta);
        inv[0].setItem(13,chest);
        player.openInventory(inv[0]);
    }
    public void invokeBox(Player player , Inventory[] inv){
        /*final Inventory[]*/ inv[0] = Bukkit.createInventory(null, 27, "§cBox [Fouillage 0%]");
        final ItemStack[] GLASS = {new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0)};
        ItemMeta GLASSM = GLASS[0].getItemMeta();
        GLASSM.setDisplayName(" ");
        GLASSM.addEnchant(Enchantment.DURABILITY,1,false);
        GLASSM.setLore(Arrays.asList(" "));
        GLASSM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        GLASSM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        GLASS[0].setItemMeta(GLASSM);
        player.openInventory(inv[0]);
        new BukkitRunnable() {

            ItemStack it1 = getCosmetics();
            ItemStack it2 = getCosmetics();
            ItemStack it3 = getCosmetics();
            ItemStack it4 = getCosmetics();
            ItemStack it5 = getCosmetics();
            ItemStack it6 = getCosmetics();
            ItemStack it7 = getCosmetics();
            int soundcount = 1;
            float soundf = 0.8f;
            boolean soundnegatif=false;
            int seconds = 0;
            @Override
            public void run() {
               int pourcentage = seconds * 100 / 90;
               pourcentage = Math.round(pourcentage);
                if(pourcentage == 10 || pourcentage == 20|| pourcentage == 30  || pourcentage == 40 || pourcentage == 50 || pourcentage == 60 || pourcentage==70 || pourcentage== 80 ||
                        pourcentage==90 || pourcentage==100){
                    inv[0] = Bukkit.createInventory(null, 27,"§cBox [Fouillage " + pourcentage+ "%]");
                    player.openInventory(inv[0]);
                    if(seconds==90){

                        GLASS[0] = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
                        GLASS[0].setItemMeta(GLASSM);
                    }
                    for (int i = 0; i <= 26; i++) {
                        if(inv[0].getItem(i) == null){
                            inv[0].setItem(i, GLASS[0]);
                        }
                    }
                }

                if(seconds == 0){
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND , 1 , 1.7f);
                    inv[0].setItem(9,it1);
                    inv[0].setItem(10,it2);
                    inv[0].setItem(11,it3);
                    inv[0].setItem(12, GLASS[0]);
                    inv[0].setItem(13,it4);
                    inv[0].setItem(14, GLASS[0]);
                    inv[0].setItem(4, GLASS[0]);
                    inv[0].setItem(22, GLASS[0]);
                    inv[0].setItem(21, GLASS[0]);
                    inv[0].setItem(23, GLASS[0]);
                    inv[0].setItem(3, GLASS[0]);
                    inv[0].setItem(5, GLASS[0]);
                    inv[0].setItem(6, GLASS[0]);
                    inv[0].setItem(7, GLASS[0]);
                    inv[0].setItem(8, GLASS[0]);
                    inv[0].setItem(2, GLASS[0]);
                    inv[0].setItem(1, GLASS[0]);
                    inv[0].setItem(0, GLASS[0]);
                    inv[0].setItem(18, GLASS[0]);
                    inv[0].setItem(19, GLASS[0]);
                    inv[0].setItem(20, GLASS[0]);
                    inv[0].setItem(24, GLASS[0]);
                    inv[0].setItem(25, GLASS[0]);
                    inv[0].setItem(26, GLASS[0]);
                    inv[0].setItem(15,it5);
                    inv[0].setItem(16,it6);
                    inv[0].setItem(17,it7);
                }
                if(seconds >= 1 && seconds < 45){
                    if(soundnegatif == false){
                        soundcount++;
                    }
                    if(soundnegatif == true){
                        soundcount--;
                    }

                    if(soundcount == 1){
                        soundf= 0.8f;
                        if(soundnegatif=true){
                            soundnegatif= false;
                        }
                    }
                    if(soundcount== 2){
                        soundf= 0.9f;
                    }
                    if(soundcount== 3){
                        soundf= 1f;
                    }
                    if(soundcount== 4){
                        soundf= 1.1f;
                        soundnegatif= true;
                    }

                    player.playSound(player.getLocation(), Sound.NOTE_PLING , 1 , soundf);
                    it1 = it2;
                    it2 = it3;
                    it3 = it4;
                    it4 = it5;
                    it5 = it6;
                    it6 = it7;
                    it7 = getCosmetics();
                    inv[0].setItem(9,it1);
                    inv[0].setItem(10,it2);
                    inv[0].setItem(11,it3);

                    inv[0].setItem(13,it4);

                    inv[0].setItem(15,it5);
                    inv[0].setItem(16,it6);
                    inv[0].setItem(17,it7);

                }

                if(seconds >= 45 && seconds <60){
                    if ((seconds/2)*2 == seconds){
                        if(soundnegatif == false){
                            soundcount++;
                        }
                        if(soundnegatif == true){
                            soundcount--;
                        }

                        if(soundcount == 1){
                            soundf= 0.8f;
                            if(soundnegatif=true){
                                soundnegatif= false;
                            }
                        }
                        if(soundcount== 2){
                            soundf= 0.9f;
                        }
                        if(soundcount== 3){
                            soundf= 1f;
                        }
                        if(soundcount== 4){
                            soundf= 1.1f;
                            soundnegatif= true;
                        }

                        player.playSound(player.getLocation(), Sound.NOTE_PLING , 1 ,  soundf);
                        it1 = it2;
                        it2 = it3;
                        it3 = it4;
                        it4 = it5;
                        it5 = it6;
                        it6 = it7;
                        it7 = getCosmetics();
                        inv[0].setItem(9,it1);
                        inv[0].setItem(10,it2);
                        inv[0].setItem(11,it3);

                        inv[0].setItem(13,it4);

                        inv[0].setItem(15,it5);
                        inv[0].setItem(16,it6);
                        inv[0].setItem(17,it7);
                    }else {

                        inv[0].setItem(9,it1);
                        inv[0].setItem(10,it2);
                        inv[0].setItem(11,it3);

                        inv[0].setItem(13,it4);

                        inv[0].setItem(15,it5);
                        inv[0].setItem(16,it6);
                        inv[0].setItem(17,it7);
                }
                }

                    if(seconds >= 60){
                        if ((seconds/4)*4 == seconds && seconds<75 || seconds==60){
                            if(soundnegatif == false){
                                soundcount++;
                            }
                            if(soundnegatif == true){
                                soundcount--;
                            }

                            if(soundcount == 1){
                                soundf= 0.8f;
                                if(soundnegatif=true){
                                    soundnegatif= false;
                                }
                            }
                            if(soundcount== 2){
                                soundf= 0.9f;
                            }
                            if(soundcount== 3){
                                soundf= 1f;
                            }
                            if(soundcount== 4){
                                soundf= 1.1f;
                                soundnegatif= true;
                            }

                            player.playSound(player.getLocation(), Sound.NOTE_PLING , 1 , soundf);
                            it1 = it2;
                            it2 = it3;
                            it3 = it4;
                            it4 = it5;
                            it5 = it6;
                            it6 = it7;
                            it7 = getCosmetics();
                            inv[0].setItem(9,it1);
                            inv[0].setItem(10,it2);
                            inv[0].setItem(11,it3);

                            inv[0].setItem(13,it4);

                            inv[0].setItem(15,it5);
                            inv[0].setItem(16,it6);
                            inv[0].setItem(17,it7);
                            //speed = 1;
                            }else {

                            inv[0].setItem(9,it1);
                            inv[0].setItem(10,it2);
                            inv[0].setItem(11,it3);

                            inv[0].setItem(13,it4);

                            inv[0].setItem(15,it5);
                            inv[0].setItem(16,it6);
                            inv[0].setItem(17,it7);
                        }
                        if(seconds >= 75){
                            if ((seconds/6)*6 == seconds && seconds<=85 ){
                                if(soundnegatif == false){
                                    soundcount++;
                                }
                                if(soundnegatif == true){
                                    soundcount--;
                                }

                                if(soundcount == 1){
                                    soundf= 0.8f;
                                    if(soundnegatif=true){
                                        soundnegatif= false;
                                    }
                                }
                                if(soundcount== 2){
                                    soundf= 0.9f;
                                }
                                if(soundcount== 3){
                                    soundf= 1f;
                                }
                                if(soundcount== 4){
                                    soundf= 1.1f;
                                    soundnegatif= true;
                                }

                                player.playSound(player.getLocation(), Sound.NOTE_PLING , 1 ,  soundf);
                                it1 = it2;
                                it2 = it3;
                                it3 = it4;
                                it4 = it5;
                                it5 = it6;
                                it6 = it7;
                                it7 = getCosmetics();
                                inv[0].setItem(9,it1);
                                inv[0].setItem(10,it2);
                                inv[0].setItem(11,it3);

                                inv[0].setItem(13,it4);

                                inv[0].setItem(15,it5);
                                inv[0].setItem(16,it6);
                                inv[0].setItem(17,it7);
                                //speed = 1;
                            }else {

                                inv[0].setItem(9,it1);
                                inv[0].setItem(10,it2);
                                inv[0].setItem(11,it3);

                                inv[0].setItem(13,it4);

                                inv[0].setItem(15,it5);
                                inv[0].setItem(16,it6);
                                inv[0].setItem(17,it7);
                            }
                        }
                }

                if(seconds == 90 ){
                    if(it4.getType()==null){
                        player.sendMessage("ERROR / LA BOX A DECIDER DE TE CRACHER DESSUS MAIS ON TE REMBOURSE SANS SOUCIS");
                    cancel();

                    }
                    player.sendMessage("tu as ressus " + it4.toString());
                    if(it4.getItemMeta().getLore().get(0).equals("§7COMMUN")){
                        player.playSound(player.getLocation() , Sound.ORB_PICKUP , 1 , 0.8f);
                    }
                    if(it4.getItemMeta().getLore().get(0).equals("§9§lRARE")){
                        player.playSound(player.getLocation() , Sound.ORB_PICKUP , 1 , 0.8f);
                        player.playSound(player.getLocation() , Sound.WITHER_SPAWN , 2 , 0.6f);
                    }
                    if(it4.getItemMeta().getLore().get(0).equals("§d§lEPIQUE")){
                       player.getWorld().playSound(player.getLocation() , Sound.ENDERDRAGON_GROWL , 100 , 1);
                       Bukkit.broadcastMessage("§6§l"+player.getName() +"§e viens de trouver un objet §7[§d§lEPIQUE§7] §edans une box");
                    }

                    cancel();
                }

                seconds++;
            }
        }.runTaskTimer(Main.getInstance(), 0, 2L);
    }
    public ItemStack getCosmetics(){
        Random rand = new Random();
        int total = rand.nextInt(100);
        if(total >= 0 && total <= 33){
            ItemStack cosmetic01 = new ItemStack(Material.FIREWORK);
            ItemMeta cosmetic01Meta = cosmetic01.getItemMeta();
            cosmetic01Meta.setDisplayName("§7[Commun] Effet de Kill");
            cosmetic01Meta.setLore(Arrays.asList("§7COMMUN", "§7Effet de kill lorsque " ,"§7tu touche un joueur"));
            cosmetic01Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            cosmetic01.setItemMeta(cosmetic01Meta);
            return cosmetic01;
        }
       if(total >= 34 && total <= 57){
           ItemStack cosmetic02 = new ItemStack(Material.SULPHUR);
           ItemMeta cosmetic02Meta = cosmetic02.getItemMeta();
           cosmetic02Meta.setDisplayName("§7[Commun] Effet de tirs");
           cosmetic02Meta.setLore(Arrays.asList("§7COMMUN", "§7Effet  lorsque tu tire"));
           cosmetic02Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
           cosmetic02.setItemMeta(cosmetic02Meta);
           return cosmetic02;
       }


        if(total >= 58 && total <= 67){
            ItemStack cosmetic03 = new ItemStack(Material.NAME_TAG);
            ItemMeta cosmetic03Meta = cosmetic03.getItemMeta();
            cosmetic03Meta.setDisplayName("§7[§9Rare§7] Message de Kills");
            cosmetic03Meta.setLore(Arrays.asList("§9§lRARE", "§7Message  lorsque " ,"§7tu tue quelqu'un"));
            cosmetic03Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            cosmetic03.setItemMeta(cosmetic03Meta);
            return cosmetic03;
        }



        if(total >= 68 && total <= 77){
            ItemStack cosmetic05 = new ItemStack(Material.FIREWORK);
            ItemMeta cosmetic05Meta = cosmetic05.getItemMeta();
            cosmetic05Meta.setDisplayName("§7[§9RARE§7] Effet de Kill");
            cosmetic05Meta.addEnchant(Enchantment.DURABILITY,1,false);
            cosmetic05Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            cosmetic05Meta.setLore(Arrays.asList("§9§lRARE", "§7Effet de kill lorsque " ,"§7tu touche un joueur"));
            cosmetic05.setItemMeta(cosmetic05Meta);
            return cosmetic05;
        }
        if(total >= 78 && total <= 87){
            ItemStack cosmetic06 = new ItemStack(Material.GLOWSTONE_DUST);
            ItemMeta cosmetic06Meta = cosmetic06.getItemMeta();
            cosmetic06Meta.setDisplayName("§7[§9RARE§7] Effet de tirs");
            cosmetic06Meta.setLore(Arrays.asList("§9§lRARE", "§7Effet  lorsque tu tire"));
            cosmetic06Meta.addEnchant(Enchantment.DURABILITY,1,false);
            cosmetic06Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            cosmetic06.setItemMeta(cosmetic06Meta);
            return cosmetic06;
        }

        if(total >= 88 && total <= 94){
            ItemStack cosmetic04 = new ItemStack(Material.STICK);
            ItemMeta cosmetic04Meta = cosmetic04.getItemMeta();
            cosmetic04Meta.setDisplayName("§7[§dEpique§7] Armes");
            cosmetic04Meta.setLore(Arrays.asList("§d§lEPIQUE", "§7Armes  pour tirer"));
            cosmetic04Meta.addEnchant(Enchantment.DURABILITY,1,false);
            cosmetic04Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            cosmetic04Meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            cosmetic04.setItemMeta(cosmetic04Meta);
            return cosmetic04;
        }
        if(total >= 95 && total <= 100){
            ItemStack cosmetic07 = new ItemStack(Material.BLAZE_POWDER);
            ItemMeta cosmetic07Meta = cosmetic07.getItemMeta();
            cosmetic07Meta.setDisplayName("§7[§d§lEPIQUE§7] Effet de tirs");
            cosmetic07Meta.addEnchant(Enchantment.DURABILITY , 1 , false);
            cosmetic07Meta.setLore(Arrays.asList("§d§lEPIQUE", "§7Effet  lorsque tu tire"));
            cosmetic07Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            cosmetic07Meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            cosmetic07.setItemMeta(cosmetic07Meta);
            return cosmetic07;

        }

        return null;
    }
}
