package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;


import static jp.houlab.mochidsuki.toweraandd.TowerAandD.*;

public class GameStarter {
    static public void playerTP() {
        for (String entity : team1.getEntries()) {
            Player player = plugin.getServer().getPlayer(entity);
            player.teleport(V.team1.getSiteStatus().getSpawnLocation());
        }
        for (String entity : team2.getEntries()) {
            Player player = plugin.getServer().getPlayer(entity);
            player.teleport(V.team2.getSiteStatus().getSpawnLocation());
        }
    }

    static public void putCore() {
        plugin.getServer().getWorld(config.getString("World")).setType(V.team1.getSiteStatus().getCoreLocation(), Material.EMERALD_BLOCK);
        plugin.getServer().getWorld(config.getString("World")).setType(V.team2.getSiteStatus().getCoreLocation(), Material.EMERALD_BLOCK);
        V.team1.setCoreHealth(config.getInt("Core.Health"));
        V.team2.setCoreHealth(config.getInt("Core.Health"));
    }
}
