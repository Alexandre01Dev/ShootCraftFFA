package be.alexandre01.dreamzon.shootcraftffa.Statistics;

import be.alexandre01.dreamzon.shootcraftffa.Main;
import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class StoreStatistics {

    public void storeStatistics(Player player) throws IOException {
        for (int i = 0; i <= 5; i++) {

            System.out.println();
            Main.getInstance().getStatsConfig().set("statistics."+player.getName()+"."+Statistic.values()[i].toString(), player.getStatistic(Statistic.values()[i]));
            Main.getInstance().getStatsConfig().save(Main.getInstance().getStatsFile());
        }


    }
}
