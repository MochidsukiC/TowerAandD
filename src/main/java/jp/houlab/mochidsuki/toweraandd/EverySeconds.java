package jp.houlab.mochidsuki.toweraandd;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.title.Title;
import org.bukkit.Sound;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;

import static jp.houlab.mochidsuki.toweraandd.RoundSystem.startRound;
import static jp.houlab.mochidsuki.toweraandd.TowerAandD.config;
import static jp.houlab.mochidsuki.toweraandd.TowerAandD.plugin;
import static jp.houlab.mochidsuki.toweraandd.V.*;
import static jp.houlab.mochidsuki.toweraandd.V.TimerBossBar;

public class EverySeconds extends BukkitRunnable {
    @Override
    public void run() {
        RoundTimer--; //タイマー減算


        switch (Round) {
            case 1:
            case 4:
            case 7: {
                TimerBossBar.setTitle("準備時間 - " + (RoundTimer - RoundTimer % 60) / 60 + ":" + RoundTimer % 60);
                TimerBossBar.setProgress(RoundTimer / config.getDouble("PrepareTime"));


                if (RoundTimer <= 5 && RoundTimer > 0) {
                    for (Player player : plugin.getServer().getOnlinePlayers()) {
                        TextComponent textComponent = Component.text(String.valueOf(RoundTimer));
                        player.showTitle(Title.title(textComponent, Component.text("")));
                        player.playSound(player.getLocation(),Sound.ENTITY_ARROW_HIT_PLAYER,100,0);
                    }
                }
                if (RoundTimer == 0) {
                    TimerBossBar.setVisible(false);
                    startRound();
                }
                break;
            }
        }
    }
}
