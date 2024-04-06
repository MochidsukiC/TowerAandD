package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.entity.Player;


import static jp.houlab.mochidsuki.toweraandd.TowerAandD.*;

public class GameStarter {
    static public void playerTP() {
        for (String entity : team1.getEntries()) {
            Player player = plugin.getServer().getPlayer(entity);
            player.teleport(new Location(plugin.getServer().getWorld(config.getString("World")), config.getInt("Team.spawn.1.x"), config.getInt("Team.spawn.1.y"), config.getInt("Team.spawn.1.z")));
        }
        for (String entity : team2.getEntries()) {
            Player player = plugin.getServer().getPlayer(entity);
            player.teleport(new Location(plugin.getServer().getWorld(config.getString("World")), config.getInt("Team.spawn.2.x"), config.getInt("Team.spawn.2.y"), config.getInt("Team.spawn.2.z")));
        }
    }
}
