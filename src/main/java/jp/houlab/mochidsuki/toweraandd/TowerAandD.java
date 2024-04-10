package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

import static jp.houlab.mochidsuki.toweraandd.V.TimerBossBar;

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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("TowerAandD Plugin Power off!");
    }
}

class V{
//次に付与される資金の記憶システム
static HashMap<Player,Integer> nextPlayerMoney;

static int CoreHealth1;
static int CoreHealth2;
static int Round;
static int RoundTimer;
static BossBar TimerBossBar;
}