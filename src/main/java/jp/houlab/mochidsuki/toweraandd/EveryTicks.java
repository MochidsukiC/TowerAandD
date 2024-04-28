package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import static jp.houlab.mochidsuki.toweraandd.TowerAandD.*;

public class EveryTicks extends BukkitRunnable {
    @Override
    public void run() {
        V.team1.refreshBossbar();
        V.team2.refreshBossbar();
    }



    //Original Event

}
