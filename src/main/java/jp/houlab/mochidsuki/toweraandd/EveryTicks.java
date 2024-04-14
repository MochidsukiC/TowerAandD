package jp.houlab.mochidsuki.toweraandd;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.Optional;

import static jp.houlab.mochidsuki.toweraandd.TowerAandD.plugin;

public class EveryTicks extends BukkitRunnable {
    @Override
    public void run() {

        for(Player player : plugin.getServer().getOnlinePlayers()) {

            try {
                Optional<ItemStack> headItem = Optional.ofNullable(player.getInventory().getItem(21));
                Optional<ItemStack> bootsItem = Optional.ofNullable(player.getInventory().getItem(23));
                ChatColor colorH = ChatColor.RESET;
                ChatColor colorB = ChatColor.RESET;

                switch (headItem.orElse(new ItemStack(Material.LEATHER_HELMET)).getType()) {
                    case CHAINMAIL_HELMET:
                        colorH = ChatColor.GRAY;
                        break;
                    case IRON_HELMET:
                        colorH = ChatColor.DARK_GRAY;
                        break;
                    case GOLDEN_HELMET:
                        colorH = ChatColor.YELLOW;
                        break;
                    case DIAMOND_HELMET:
                        colorH = ChatColor.AQUA;
                        break;
                    case NETHERITE_HELMET:
                        colorH = ChatColor.DARK_RED;
                        break;
                    default:
                }

                switch (bootsItem.orElse(new ItemStack(Material.LEATHER_BOOTS)).getType()) {
                    case CHAINMAIL_BOOTS:
                        colorB = ChatColor.GRAY;
                        break;
                    case IRON_BOOTS:
                        colorB = ChatColor.DARK_GRAY;
                        break;
                    case GOLDEN_BOOTS:
                        colorB = ChatColor.YELLOW;
                        break;
                    case DIAMOND_BOOTS:
                        colorB = ChatColor.AQUA;
                        break;
                    case NETHERITE_BOOTS:
                        colorB = ChatColor.DARK_RED;
                        break;
                    default:
                }


                TextComponent component = new net.md_5.bungee.api.chat.TextComponent();
                String shield;
                String half;
                String openings;

                ShieldUtil shieldUtil = new ShieldUtil(player.getInventory().getItem(22));
                shield = String.join("", Collections.nCopies((int) (shieldUtil.getShieldNow() / 2), "■"));

                if (!(shieldUtil.getShieldNow() % 2 == 0)) {
                    half = "□";
                } else {
                    half = "";
                }
                openings = String.join("", Collections.nCopies((int) ((shieldUtil.getShieldMax() - shieldUtil.getShieldNow()) / 2), "-"));
                component.setText(colorH + "[" + shieldUtil.getShieldColor() + shield + half + openings + colorB + "]");
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
