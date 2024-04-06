package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import static jp.houlab.mochidsuki.toweraandd.TowerAandD.config;
import static jp.houlab.mochidsuki.toweraandd.V.CoreHealth1;
import static jp.houlab.mochidsuki.toweraandd.V.CoreHealth2;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent event){
        if(event.getBlock().getLocation().equals(new Location(event.getBlock().getWorld(),config.getInt("Core.1.x"), config.getInt("Core.1.y"), config.getInt("Core.1.z")))){
            CoreHealth1 = CoreHealth1 - 1;
        }
        if(event.getBlock().getLocation().equals(new Location(event.getBlock().getWorld(),config.getInt("Core.2.x"), config.getInt("Core.2.y"), config.getInt("Core.2.z")))){
            CoreHealth2 = CoreHealth2 - 1;

        }
    }
}
