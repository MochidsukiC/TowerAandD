package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;


import static jp.houlab.mochidsuki.toweraandd.TowerAandD.*;
import static jp.houlab.mochidsuki.toweraandd.V.CoreHealth1;
import static jp.houlab.mochidsuki.toweraandd.V.CoreHealth2;

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

    static public void putCore() {
        plugin.getServer().getWorld(config.getString("World")).setType(new Location(plugin.getServer().getWorld(config.getString("World")), config.getInt("Core.1.x"), config.getInt("Core.1.y"), config.getInt("Core.1.z")), Material.BEACON);
        plugin.getServer().getWorld(config.getString("World")).setType(new Location(plugin.getServer().getWorld(config.getString("World")), config.getInt("Core.2.x"), config.getInt("Core.2.y"), config.getInt("Core.2.z")), Material.BEACON);
        CoreHealth1 = config.getInt("Core.Health");
        CoreHealth2 = config.getInt("Core.Health");
    }
}
