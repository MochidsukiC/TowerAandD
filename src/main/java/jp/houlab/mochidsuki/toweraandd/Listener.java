package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import static jp.houlab.mochidsuki.toweraandd.TowerAandD.config;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent event){
        int x = event.getBlock().getLocation().getBlockX();
        int y = event.getBlock().getLocation().getBlockY();
        int z = event.getBlock().getLocation().getBlockZ();
        if(x == V.team1.getSiteStatus().getCoreLocation().getBlockX() && y == V.team1.getSiteStatus().getCoreLocation().getBlockY() && z == V.team1.getSiteStatus().getCoreLocation().getBlockZ()){
            V.team1.setCoreHealth(V.team1.getCoreHealth() - 1);
            event.setCancelled(true);
        }
        if(x == V.team2.getSiteStatus().getCoreLocation().getBlockX() && y == V.team2.getSiteStatus().getCoreLocation().getBlockY() && z == V.team2.getSiteStatus().getCoreLocation().getBlockZ()){
            V.team2.setCoreHealth(V.team2.getCoreHealth() - 1);
            event.setCancelled(true);
        }
    }
}
