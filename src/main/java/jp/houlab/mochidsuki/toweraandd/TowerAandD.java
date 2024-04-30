package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

import static jp.houlab.mochidsuki.toweraandd.V.TimerBossBar;
import static jp.houlab.mochidsuki.toweraandd.V.spawnTextDisplay;

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
        try {
            team1 = this.getServer().getScoreboardManager().getMainScoreboard().registerNewTeam("1");
        }catch (Exception ignored){
            team1 = this.getServer().getScoreboardManager().getMainScoreboard().getTeam("1");
        }
        try {
            team2 = this.getServer().getScoreboardManager().getMainScoreboard().registerNewTeam("2");
        }catch (Exception ignored){
            team2 = this.getServer().getScoreboardManager().getMainScoreboard().getTeam("2");
        }
            plugin = this;



        //Every
        new EveryTicks().runTaskTimer(this,0L,1);
        new EverySeconds().runTaskTimer(this,0L,20);


        //BossBar
        TimerBossBar = this.getServer().createBossBar("開始までお待ちください。。。", BarColor.WHITE, BarStyle.SEGMENTED_10);
        TimerBossBar.setVisible(false);

        //Command
        getCommand("tad").setExecutor(new CommandListener());
        getCommand("spawnCore").setExecutor(new CommandListener());

        //Event
        getServer().getPluginManager().registerEvents(new Listener(), this);

        //spawnTextDisplay
        for(int i = 0;i<config.getInt("SpawnPoint");i++) {
            TextDisplay textDisplay = getServer().getWorld(config.getString("World")).spawn(new Location(this.getServer().getWorld(config.getString("World")), 0, 0, 0), TextDisplay.class);
            textDisplay.text();
            spawnTextDisplay.put(i,textDisplay);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("TowerAandD Plugin Power off!");

        for(int i = 0;i<config.getInt("SpawnPoint");i++) {
            spawnTextDisplay.get(i).remove();
        }

    }
}

class V{
//次に付与される資金の記憶システム
static HashMap<Player,Integer> nextPlayerMoney;

static int Round;
static int RoundTimer;
static BossBar TimerBossBar;
static TeamStatus team1 = new TeamStatus();
static TeamStatus team2 = new TeamStatus();
static SiteStatus siteStatus1 = new SiteStatus();
static SiteStatus siteStatus2 = new SiteStatus();
static HashMap<Integer,Integer> spawnScore = new HashMap<>();
static HashMap<Integer,TextDisplay> spawnTextDisplay = new HashMap<>();

}