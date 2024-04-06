package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static jp.houlab.mochidsuki.toweraandd.TowerAandD.plugin;
import static jp.houlab.mochidsuki.toweraandd.V.nextPlayerMoney;

public class MoneySystem {
    static public void giveInitial(){
        for(Player player : plugin.getServer().getOnlinePlayers()){
            player.getInventory().setItem(6,new ItemStack(Material.EMERALD,10));
        }
    }
    static public void giveMoney(Player player){
        player.getInventory().addItem(new ItemStack(Material.EMERALD,nextPlayerMoney.get(player)));
    }
}
