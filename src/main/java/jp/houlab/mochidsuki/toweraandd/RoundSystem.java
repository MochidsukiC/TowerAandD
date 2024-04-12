package jp.houlab.mochidsuki.toweraandd;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static jp.houlab.mochidsuki.toweraandd.GameStarter.playerTP;
import static jp.houlab.mochidsuki.toweraandd.GameStarter.putCore;
import static jp.houlab.mochidsuki.toweraandd.MoneySystem.giveInitial;
import static jp.houlab.mochidsuki.toweraandd.TowerAandD.config;
import static jp.houlab.mochidsuki.toweraandd.TowerAandD.plugin;
import static jp.houlab.mochidsuki.toweraandd.V.*;

public class RoundSystem {
    static public void startPrepare() {
        BorderManager.close();
        playerTP();
        giveInitial();
        putCore();


        TimerBossBar.setVisible(true);
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            TimerBossBar.addPlayer(player);
        }
        RoundTimer = config.getInt("PrepareTime");
        switch (Round){
            case 1:
            case 4:
            case 7:
                break;

        }

    }

    static public void startRound(){
        for (Player player : plugin.getServer().getOnlinePlayers()){
            TextComponent textComponent = Component.text("START!!").color(TextColor.color(255,254,0));
            player.showTitle(Title.title(textComponent,Component.text("")));
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT,100,1);
        }
        BorderManager.open();


        //debug zone
        for(OfflinePlayer offlinePlayer : plugin.getServer().getOperators()){
            if(offlinePlayer.isOnline()) {
                Player player = plugin.getServer().getPlayer(offlinePlayer.getName());
                player.sendMessage("=debug info===================");
                player.sendMessage("team1");
                player.sendMessage(team1.getPlayers() + "");
                player.sendMessage(team1.getTeam() + "");
                player.sendMessage(team1.getSide() + "");
                player.sendMessage("SiteStatus");
                player.sendMessage(team1.getSiteStatus().getTeamId() + "");
                player.sendMessage(team1.getSiteStatus().getCoreLocation() + "");
                player.sendMessage(team1.getSiteStatus().getGeneratorLocation() + "");
                player.sendMessage(Arrays.toString(team1.getSiteStatus().getSpawn()));

                player.sendMessage("team2");
                player.sendMessage(team2.getPlayers() + "");
                player.sendMessage(team2.getTeam() + "");
                player.sendMessage(team2.getSide() + "");
                player.sendMessage("SiteStatus");
                player.sendMessage(team2.getSiteStatus().getTeamId() + "");
                player.sendMessage(team2.getSiteStatus().getCoreLocation() + "");
                player.sendMessage(team2.getSiteStatus().getGeneratorLocation() + "");
                player.sendMessage(Arrays.toString(team2.getSiteStatus().getSpawn()));
            }
        }
    }
}
