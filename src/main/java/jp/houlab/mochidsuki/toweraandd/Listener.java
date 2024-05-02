package jp.houlab.mochidsuki.toweraandd;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;
import org.codehaus.plexus.util.cli.shell.CmdShell;

import java.util.Iterator;

import static jp.houlab.mochidsuki.toweraandd.TowerAandD.config;
import static jp.houlab.mochidsuki.toweraandd.TowerAandD.team1;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent event){
        boolean isEventCancel = false;
        int x = event.getBlock().getLocation().getBlockX();
        int y = event.getBlock().getLocation().getBlockY();
        int z = event.getBlock().getLocation().getBlockZ();
        if(event.getBlock().getType().equals(Material.EMERALD_BLOCK)) {
            isEventCancel = coreBreak(V.team1,x,y,z);
            if(isEventCancel == false) {
                isEventCancel = coreBreak(V.team2, x, y, z);
            }
        }
        event.setCancelled(isEventCancel);
    }



    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event){
        Component textComponent = Component.text("[スポーン地点を選択]")
                        .appendNewline().append(Component.text("[3]")).append(Component.text("   ")).append(Component.text("[4]"))
                        .appendNewline().append(Component.text("   \\   /"))
                        .appendNewline().append(Component.text("   ")).append(Component.text("[2]"))
                        .appendNewline().append(Component.text("   /  \\"))
                        .appendNewline().append(Component.text("[0]")).append(Component.text("   ")).append(Component.text("[1]"));



        event.getPlayer().sendMessage(textComponent);
    }


    public boolean coreBreak(TeamStatus team,int x,int y,int z){
        if (x == team.getSiteStatus().getCoreLocation().getBlockX() && y == team.getSiteStatus().getCoreLocation().getBlockY() && z == team.getSiteStatus().getCoreLocation().getBlockZ()) {
            team.setCoreHealth(team.getCoreHealth() - 1);
            if(team.getCoreHealth() <= 0){
                RoundSystem.endRound(team);
                return false;
            }

            return true;
        }
        return false;
    }



}
