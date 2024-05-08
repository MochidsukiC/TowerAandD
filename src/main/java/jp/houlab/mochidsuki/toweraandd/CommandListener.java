package jp.houlab.mochidsuki.toweraandd;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.util.HSVLike;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.util.permissions.CommandPermissions;
import org.jetbrains.annotations.NotNull;

import javax.naming.Name;

import static jp.houlab.mochidsuki.toweraandd.TowerAandD.*;
import static jp.houlab.mochidsuki.toweraandd.TowerAandD.team1;
import static jp.houlab.mochidsuki.toweraandd.TowerAandD.team2;
import static jp.houlab.mochidsuki.toweraandd.V.*;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("tad")){
            switch (strings[0]){
                case "start":{
                    Round = 0;
                    boolean[] spawn = new boolean[5];

                    V.team1.setTeam(team1);
                    V.team1.setSide(0);
                    siteStatus1.setTeam(team1);
                    siteStatus1.setTeamId(1);
                    siteStatus1.initialization();
                    V.team1.setSiteStatus(siteStatus1);
                    V.team1.setGeneratorHealth(true);

                    V.team2.setTeam(team2);
                    V.team2.setSide(0);
                    siteStatus2.setTeam(team2);
                    siteStatus2.setTeamId(2);
                    siteStatus2.initialization();
                    V.team2.setSiteStatus(siteStatus2);
                    V.team2.setGeneratorHealth(true);
                    RoundSystem.startPrepare();
                    break;
                }
                case "reload":{
                    break;
                }
                case "Round":{
                    sender.sendMessage(Round+"");
                }


            }
        }
        if(s.equalsIgnoreCase("spawnCore")){
            int id = Integer.parseInt(strings[0]);
            try {
                BlockCommandSender sender1 = (BlockCommandSender) sender;
                Location spawnLocation = sender1.getBlock().getLocation();

                spawnTextDisplay.get(id).teleport(spawnLocation.add(0,3,0));
                V.spawnLocation.put(id,spawnLocation);

                for(Player player:plugin.getServer().getOnlinePlayers()){
                    if(spawnLocation.distance(player.getLocation()) <=3){
                        int spawnScoreMaxK = config.getInt("SpawnScoreMaxK");
                        double scale = spawnScoreMaxK/10;
                        TextColor color = NamedTextColor.WHITE;

                        if(team1.hasEntry(player.getName())){
                            if(spawnScore.get(id) <= spawnScoreMaxK) {
                                spawnScore.put(id, spawnScore.get(id) + 1);
                                if(spawnScore.get(id) > 100){
                                    V.team1.getSiteStatus().setSpawn(id,true);
                                    color = NamedTextColor.BLUE;
                                }
                                if(spawnScore.get(id) < 100){
                                    V.team2.getSiteStatus().setSpawn(id,false);
                                    color = NamedTextColor.WHITE;
                                }
                            }

                            if(player.isOp()){
                                player.sendMessage(spawnScore.get(id)+"");
                            }
                        } else if (team2.hasEntry(player.getName())) {
                            if(spawnScore.get(id) >= spawnScoreMaxK *-1) {
                                spawnScore.put(id, spawnScore.get(id) - 1);
                                if(spawnScore.get(id) < -100){
                                    V.team2.getSiteStatus().setSpawn(id,true);
                                    color = NamedTextColor.RED;
                                }
                                if(spawnScore.get(id) > -100){
                                    V.team1.getSiteStatus().setSpawn(id,false);
                                    color = NamedTextColor.WHITE;
                                }
                            }
                            if(player.isOp()){
                                player.sendMessage(spawnScore.get(id)+"");
                            }
                        }
                        int score = (int) (spawnScore.get(id)/scale);
                        if(score == 0 || spawnScore.get(id)%scale == 0) {
                            TextComponent component = Component.text("[").color(color);
                            if (spawnScore.get(id) > 0) {
                                //1,blue
                                for (int i = 0; i < 10 - score; i++) {
                                    component = component.append(Component.text("□", TextColor.color(0, 0, 170)));

                                }
                                for (int i = 0; i < score; i++) {
                                    component = component.append(Component.text("■", TextColor.color(0, 0, 170)));
                                }
                                for (int i = 0; i < 10; i++) {
                                    component = component.append(Component.text("□", TextColor.color(255, 85, 85)));
                                }
                            } else if (spawnScore.get(id) < 0) {
                                //2,red
                                for (int i = 0; i < 10; i++) {
                                    component = component.append(Component.text("□", TextColor.color(0, 0, 170)));
                                }
                                for (int i = 0; i < score*-1; i++) {
                                    component = component.append(Component.text("■", TextColor.color(255, 85, 85)));
                                }
                                for (int i = 0; i < 10 - score*-1; i++) {
                                    component = component.append(Component.text("□", TextColor.color(255, 85, 85)));
                                }
                            } else if (spawnScore.get(id) == 0) {
                                //0
                                    component = component.append(Component.text("□□□□□□□□□□□□□□□□□□□□"));
                            }
                            component = component.append(Component.text("]")).color(color);

                            spawnTextDisplay.get(id).customName(component);
                        }
                    }


                    }
            }catch (Exception e){
                e.printStackTrace();
            }

            /*
            try {
                Entity entity = (Entity) sender;
                if(entity.getServer().getScoreboardManager().getMainScoreboard().getTeam(entity.getScoreboardEntryName()) == team1) {
                    spawnScore.put(Integer.valueOf(strings[0]), spawnScore.get(strings[0]) + 1);
                }else {
                    spawnScore.put(Integer.valueOf(strings[0]), spawnScore.get(strings[0]) - 1);
                }

            }catch (Exception ignored){}

             */
        }

        if(s.equalsIgnoreCase("rsp")){
            try {
                Player player = (Player) sender;
                if(canUseSpawner.get(player)) {
                    if (player.getScoreboard().getEntryTeam(player.getName()).getName() == siteStatus1.getTeam().getName()) {
                        switch (strings[0]) {
                            case "0":
                                if (spawnScore.get(0) > 100) {
                                    //true
                                    player.teleport(spawnLocation.get(0));
                                    player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントにテレポートしました!").color(NamedTextColor.YELLOW)));
                                    canUseSpawner.put(player,false);
                                    return true;
                                }
                                break;
                            case "1":
                                if (spawnScore.get(1) > 100) {
                                    //true
                                    player.teleport(spawnLocation.get(1));
                                    player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントにテレポートしました!").color(NamedTextColor.YELLOW)));
                                    canUseSpawner.put(player,false);
                                    return true;
                                }
                                break;
                            case "2":
                                if ((spawnScore.get(0) > 100 || spawnScore.get(1) > 100) && spawnScore.get(2) > 100) {
                                    //true
                                    player.teleport(spawnLocation.get(2));
                                    player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントにテレポートしました!").color(NamedTextColor.YELLOW)));
                                    canUseSpawner.put(player,false);
                                    return true;
                                }
                                break;
                            case "3":
                                if (((spawnScore.get(0) > 100 || spawnScore.get(1) > 100) && spawnScore.get(2) > 100) && spawnScore.get(3) > 100) {
                                    //true
                                    player.teleport(spawnLocation.get(3));
                                    player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントにテレポートしました!").color(NamedTextColor.YELLOW)));
                                    canUseSpawner.put(player,false);
                                    return true;
                                }
                                break;
                            case "4":
                                if (((spawnScore.get(0) > 100 || spawnScore.get(1) > 100) && spawnScore.get(2) > 100) && spawnScore.get(4) > 100) {
                                    //true
                                    player.teleport(spawnLocation.get(4));
                                    player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントにテレポートしました!").color(NamedTextColor.YELLOW)));
                                    canUseSpawner.put(player,false);
                                    return true;
                                }
                                break;

                        }
                        player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントが支配できていないか、接続できません。").color(NamedTextColor.RED)));
                        canUseSpawner.put(player,false);


                    }


                    if (player.getScoreboard().getEntryTeam(player.getName()).getName() == siteStatus2.getTeam().getName()) {
                        switch (strings[0]) {
                            case "4":
                                if (spawnScore.get(4) < -100) {
                                    //true
                                    player.teleport(spawnLocation.get(4));
                                    player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントにテレポートしました!").color(NamedTextColor.YELLOW)));
                                    canUseSpawner.put(player,false);
                                    return true;
                                }
                                break;
                            case "3":
                                if (spawnScore.get(3) < -100) {
                                    //true
                                    player.teleport(spawnLocation.get(3));
                                    player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントにテレポートしました!").color(NamedTextColor.YELLOW)));
                                    canUseSpawner.put(player,false);
                                    return true;
                                }
                                break;
                            case "2":
                                if ((spawnScore.get(4) < -100 || spawnScore.get(3) < -100) && spawnScore.get(2) < -100) {
                                    //true
                                    player.teleport(spawnLocation.get(2));
                                    player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントにテレポートしました!").color(NamedTextColor.YELLOW)));
                                    canUseSpawner.put(player,false);
                                    return true;
                                }
                                break;
                            case "1":
                                if (((spawnScore.get(4) < -100 || spawnScore.get(3) < -100) && spawnScore.get(2) < -100) && spawnScore.get(1) < -100) {
                                    //true
                                    player.teleport(spawnLocation.get(3));
                                    player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントにテレポートしました!").color(NamedTextColor.YELLOW)));
                                    canUseSpawner.put(player,false);
                                    return true;
                                }
                                break;
                            case "0":
                                if (((spawnScore.get(4) < -100 || spawnScore.get(3) < -100) && spawnScore.get(2) < -100) && spawnScore.get(0) < -100) {
                                    //true
                                    player.teleport(spawnLocation.get(4));
                                    player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントにテレポートしました!").color(NamedTextColor.YELLOW)));
                                    canUseSpawner.put(player,false);
                                    return true;
                                }
                                break;

                        }
                        player.showTitle(Title.title(Component.text(""), Component.text("スポーンポイントが支配できていないか、接続できません。").color(NamedTextColor.RED)));
                    }

                }

            }catch (Exception e){e.printStackTrace();}

        }
        return true;
    }
}
