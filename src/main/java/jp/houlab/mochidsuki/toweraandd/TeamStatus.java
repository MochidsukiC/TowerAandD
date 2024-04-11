package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Collection;

import static jp.houlab.mochidsuki.toweraandd.TowerAandD.plugin;

public class TeamStatus {
    public int coreHealth;
    public boolean generatorHealth;
    public Team team;
    public int side;
    public SiteStatus siteStatus;

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
}
