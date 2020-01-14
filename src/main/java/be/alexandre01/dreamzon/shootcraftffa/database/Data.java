package be.alexandre01.dreamzon.shootcraftffa.database;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Data {
    public HashMap<Player,Integer> coins = new HashMap<Player,Integer>();
    public HashMap<Player,Integer> totalexp = new HashMap<Player,Integer>();
    public HashMap<Player,String> actualcosmetic = new HashMap<Player,String>();
    public void setCoins(HashMap<Player, Integer> coins) {
        this.coins = coins;
    }

    public HashMap<Player, Integer> getTotalexp() {
        return totalexp;
    }

    public HashMap<Player, String> getActualcosmetic() {
        return actualcosmetic;
    }

    public void setTotalexp(HashMap<Player, Integer> totalexp) {
        this.totalexp = totalexp;
    }

    public void setActualcosmetic(HashMap<Player, String> actualcosmetic) {
        this.actualcosmetic = actualcosmetic;
    }

    public HashMap<Player, Integer> getCoins() {
        return coins;
    }

    public void actualcosmetic(Player player, String valueOf) {
    }
}
