package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.potion.PotionEffectType;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent event){
        int x = event.getBlock().getLocation().getBlockX();
        int y = event.getBlock().getLocation().getBlockY();
        int z = event.getBlock().getLocation().getBlockZ();
        if(event.getBlock().getType().equals(Material.EMERALD_BLOCK)) {
            if (x == V.team1.getSiteStatus().getCoreLocation().getBlockX() && y == V.team1.getSiteStatus().getCoreLocation().getBlockY() && z == V.team1.getSiteStatus().getCoreLocation().getBlockZ()) {
                V.team1.setCoreHealth(V.team1.getCoreHealth() - 1);
                event.setCancelled(true);
            }
            if (x == V.team2.getSiteStatus().getCoreLocation().getBlockX() && y == V.team2.getSiteStatus().getCoreLocation().getBlockY() && z == V.team2.getSiteStatus().getCoreLocation().getBlockZ()) {
                V.team2.setCoreHealth(V.team2.getCoreHealth() - 1);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Player damager = null;

        if (event.getDamager().getType().equals(EntityType.PLAYER)) {
            if (((Player) event.getDamager()).hasPotionEffect(PotionEffectType.UNLUCK)) {
                event.setCancelled(true);
                return;
            }
            try {
                ((Player) event.getEntity()).removePotionEffect(PotionEffectType.INVISIBILITY);
            } catch (Exception ignored) {
            }
        }

        if (event.getEntity().getType().equals(EntityType.PLAYER)) {


            Player player = (Player) event.getEntity();
            if (event.getDamager().getType() == EntityType.PLAYER || event.getDamager().getType() == EntityType.ARROW) {
                if (event.getDamager().getType() == EntityType.PLAYER) {
                    damager = (Player) event.getDamager();
                } else {
                    damager = (Player) ((Arrow) event.getDamager()).getShooter();
                }
            }


            //シールド
            double damage = event.getFinalDamage();
            if (player.getInventory().getItem(22) != null) {
                if (player.getInventory().getItem(22).getType() == Material.LEATHER_CHESTPLATE || player.getInventory().getItem(22).getType() == Material.CHAINMAIL_CHESTPLATE || player.getInventory().getItem(22).getType() == Material.IRON_CHESTPLATE || player.getInventory().getItem(22).getType() == Material.GOLDEN_CHESTPLATE || player.getInventory().getItem(22).getType() == Material.DIAMOND_CHESTPLATE || player.getInventory().getItem(22).getType() == Material.NETHERITE_CHESTPLATE) {
                    ShieldUtil shieldUtil = new ShieldUtil(player.getInventory().getItem(22));
                    if (shieldUtil.getShieldNow() > 0) {
                        int shieldNow;


                        shieldNow = shieldUtil.getShieldNow();
                        if (shieldNow > 0) {
                            damage = (int) (event.getFinalDamage() - shieldNow);
                            shieldNow = (int) (shieldNow - event.getFinalDamage());
                            if (shieldNow <= 0) {
                                shieldNow = 0;
                                if (event.getDamager().getType() == EntityType.PLAYER) {
                                    damager.playSound(damager.getLocation(), Sound.BLOCK_GLASS_BREAK, 100, 0);
                                }
                            }
                        }
                        if (damage <= 0) {
                            damage = 0;
                        }
                        event.setDamage(damage);
                        double da = (shieldUtil.getShieldMax() - shieldNow) / shieldUtil.getShieldMax() * shieldUtil.getShieldMaxDurability();
                        Damageable damageable = (Damageable) player.getInventory().getItem(22).getItemMeta();
                        damageable.setDamage((int) da);
                        player.getInventory().getItem(22).setItemMeta(damageable);

                    }
                }
            }
        }
    }
}
