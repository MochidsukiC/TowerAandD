package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

public final class TowerAandD extends JavaPlugin {
    static public Team team1;
    static public Team team2;
    static public FileConfiguration config;
    static public JavaPlugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("TowerAandD Plugin Power on!");
        //config
        saveDefaultConfig();
        config = getConfig();
        //Team
        team1 = this.getServer().getScoreboardManager().getMainScoreboard().registerNewTeam("1");
        team2 = this.getServer().getScoreboardManager().getMainScoreboard().registerNewTeam("2");
        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("TowerAandD Plugin Power off!");
    }
}
