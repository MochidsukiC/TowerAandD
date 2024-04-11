package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import static jp.houlab.mochidsuki.toweraandd.TowerAandD.config;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent event){
        if(event.getBlock().getLocation().equals(V.team1.getSiteStatus().getCoreLocation())){
            V.team1.setCoreHealth(V.team1.getCoreHealth() - 1);
        }
        if(event.getBlock().getLocation().equals(V.team2.getSiteStatus().getCoreLocation())){
            V.team2.setCoreHealth(V.team2.getCoreHealth() - 1);

        }
    }
}
