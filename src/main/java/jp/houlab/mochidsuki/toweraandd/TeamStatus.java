package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Collection;

import static jp.houlab.mochidsuki.toweraandd.TowerAandD.*;

public class TeamStatus {
    public int coreHealth;
    public boolean generatorHealth;
    public Team team;
    public int side;
    public SiteStatus siteStatus;
    public BossBar coreHealthBar;

    {
        coreHealthBar = plugin.getServer().createBossBar("コア残り体力 - ", BarColor.RED, BarStyle.SEGMENTED_12);
    }

    //coreHealth
    public void setCoreHealth(int coreHealthInt){
        coreHealth = coreHealthInt;
    }
    public int getCoreHealth(){
        return coreHealth;
    }

    //GeneratorHealth
    public void setGeneratorHealth(boolean generatorHealthBoolean){
        generatorHealth=generatorHealthBoolean;
    }
    public boolean isGeneratorHealth(){
        return generatorHealth;
    }

    //Team
    public void setTeam(Team teamClass){
        team=teamClass;
    }
    public Team getTeam(){
        return team;
    }

    //Side
    public void setSide(int sideInt){
        side=sideInt;
    }
    public int getSide(){
        return side;
    }

    //Players
    public Collection<Player> getPlayers(){
        Collection<Player> players = new ArrayList<>();
        for(String playerName : team.getEntries()){
            players.add(plugin.getServer().getPlayer(playerName));
        }
        return players;
    }

    //SiteStatus
    public void setSiteStatus(SiteStatus siteStatus1){
        siteStatus=siteStatus1;
    }
    public SiteStatus getSiteStatus(){
        return siteStatus;
    }

    //Bossbar
    public BossBar getBossbar(){
        return coreHealthBar;
    }
    public void refreshBossbar(){
        coreHealthBar.setTitle("コア残り体力 - " + coreHealth/config.getInt("Core.Health")*100+"%");
        coreHealthBar.setProgress(coreHealth/config.getDouble("Core.Health"));
        if(team == team1) {
            coreHealthBar.setColor(BarColor.RED);
        } else if (team == team2) {
            coreHealthBar.setColor(BarColor.BLUE);
        }
    }
}
