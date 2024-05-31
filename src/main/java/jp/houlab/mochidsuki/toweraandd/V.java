package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class V{
//次に付与される資金の記憶システム
public static HashMap<Player,Integer> nextPlayerMoney;

public static int Round;
public static int RoundTimer;
public static BossBar TimerBossBar;
public static TeamStatus team1 = new TeamStatus();
public static TeamStatus team2 = new TeamStatus();
public static SiteStatus siteStatus1 = new SiteStatus();
public static SiteStatus siteStatus2 = new SiteStatus();
public static HashMap<Integer,Integer> spawnScore = new HashMap<>();
public static HashMap<Integer, ArmorStand> spawnTextDisplay = new HashMap<>();
public static HashMap<Integer, Location> spawnLocation = new HashMap<>();
public static HashMap<Player,Boolean> canUseSpawner = new HashMap<>();
}
