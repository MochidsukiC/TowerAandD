package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class V{
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
static HashMap<Integer, ArmorStand> spawnTextDisplay = new HashMap<>();
static HashMap<Integer, Location> spawnLocation = new HashMap<>();
static HashMap<Player,Boolean> canUseSpawner = new HashMap<>();
}
