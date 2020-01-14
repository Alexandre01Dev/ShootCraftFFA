package be.alexandre01.dreamzon.shootcraftffa.scoreboard;


import be.alexandre01.dreamzon.shootcraftffa.Others.Level;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.UUID;

/*
 * This file is part of SamaGamesAPI.
 *
 * SamaGamesAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SamaGamesAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SamaGamesAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public class PersonalScoreboard {
    private Player player;
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;

    PersonalScoreboard(Player player){
        this.player = player;
        uuid = player.getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "§9§lDream§b§lZon");

        reloadData();
        objectiveSign.addReceiver(player);
    }

    public void reloadData(){}

    public void setLines(){
        objectiveSign.setDisplayName("§9§lShoot§bCraft§9§lFFA");
        objectiveSign.setLine(0, "§4§l      [§c§lBETA 1.0a§4§l]");
        objectiveSign.setLine(1, "§3 ");
        objectiveSign.setLine(2, "§6§l        [§e§lInfos§6§l]");
        objectiveSign.setLine(3, "§e• Joueurs§6: §a" + Bukkit.getOnlinePlayers().size() + "§7/§c20");
        objectiveSign.setLine(4, "§6§l       [§e§lQuêtes§6§l]");
        objectiveSign.setLine(5, "§e• En cours§6: §cAucun");
        objectiveSign.setLine(6, "§6§l        [§e§lStats§6§l]");
        objectiveSign.setLine(7, "§e• Levels§6: §c"+ Level.getLevel(player));
        objectiveSign.setLine(8, "§e• Kills§6: §c"+player.getStatistic(Statistic.PLAYER_KILLS));
        objectiveSign.setLine(9, "§e• Morts§6: §c"+player.getStatistic(Statistic.DEATHS));
        objectiveSign.setLine(10, "§6§l          [§e§lIP§6§l]");


        objectiveSign.updateLines();
    }

    public void onLogout(){
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}