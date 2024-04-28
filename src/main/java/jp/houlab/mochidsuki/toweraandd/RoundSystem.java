package jp.houlab.mochidsuki.toweraandd;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

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
        team1.getBossbar().setVisible(false);
        team2.getBossbar().setVisible(false);

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
        TimerBossBar.setVisible(false);
        team1.getBossbar().setVisible(true);
        team2.getBossbar().setVisible(true);




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

    static public void endRound(TeamStatus loseTeam){
        TeamStatus winTeam;
        if(loseTeam.getTeam() == TowerAandD.team1){
            winTeam = team2;
        } else {
            winTeam =team1;
        }

        for (Player player : winTeam.getPlayers()){
            TextComponent textComponent = Component.text("ラウンド勝利!!").color(TextColor.color(255,254,0));
            player.showTitle(Title.title(textComponent,Component.text("")));
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE,100,1);
        }
        winTeam.setPoint(winTeam.getPoint()+1);
        for (Player player : loseTeam.getPlayers()){
            TextComponent textComponent = Component.text("ラウンド敗北!!").color(TextColor.color(255,254,0));
            player.showTitle(Title.title(textComponent,Component.text("")));
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH,100,1);
        }
    }

}
