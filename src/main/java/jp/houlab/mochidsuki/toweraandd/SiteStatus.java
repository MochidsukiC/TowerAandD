package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.scoreboard.Team;

import static jp.houlab.mochidsuki.toweraandd.TowerAandD.config;
import static jp.houlab.mochidsuki.toweraandd.TowerAandD.plugin;

public class SiteStatus {
    public Team team;
    public int teamId;
    public Location coreLocation;
    public Location generatorLocation;
    public boolean[] spawn;

    {
        initialization();
    }

    //初期化
    public void initialization(){
        loadCoreLocation();
        loadGeneratorLocation();
        spawn = new boolean[5];
    }

    //Team
    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    //TeamId
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
    public int getTeamId() {
        return teamId;
    }

    //CoreLocation
    public void loadCoreLocation() {
        this.coreLocation = new Location(plugin.getServer().getWorld(config.getString("World")), config.getInt("Core."+ teamId +".x"), config.getInt("Core."+ teamId +".y"), config.getInt("Core."+ teamId +".z"));
    }
    public Location getCoreLocation() {
        return coreLocation;
    }

    //GeneratorLocation

    public void loadGeneratorLocation() {
        this.generatorLocation = new Location(plugin.getServer().getWorld(config.getString("World")), config.getInt("Generator."+ teamId +".x"), config.getInt("Generator."+ teamId +".y"), config.getInt("Generator."+ teamId +".z"));
    }

    public Location getGeneratorLocation() {
        return generatorLocation;
    }
    //Spawn
    public void setSpawn(int id,boolean spawn1){
        spawn[id] = spawn1;
    }
    public boolean[] getSpawn(){
        return spawn;
    }
}
