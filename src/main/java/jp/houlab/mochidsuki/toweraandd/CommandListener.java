package jp.houlab.mochidsuki.toweraandd;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.HSVLike;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
                for(Player player:plugin.getServer().getOnlinePlayers()){
                    if(spawnLocation.distance(player.getLocation()) <=3){
                        spawnTextDisplay.get(id).teleport(spawnLocation.add(0,3,0));
                        if(team1.hasEntry(player.getName())){
                            spawnScore.put(Integer.valueOf(strings[0]),spawnScore.get(id) + 1);

                            if(player.isOp()){
                                player.sendMessage(spawnScore.get(id)+"");
                            }
                        } else if (team2.hasEntry(player.getName())) {
                            spawnScore.put(Integer.valueOf(strings[0]),spawnScore.get(id) - 1);
                            if(player.isOp()){
                                player.sendMessage(spawnScore.get(id)+"");
                            }
                        }

                        TextComponent component = Component.text("[");
                        int score = spawnScore.get(id);
                        if(spawnScore.get(id)>0){
                            //1,blue
                            for(int i=0;i<score - 10;i++) {
                                component.append(Component.text("□", TextColor.color(0,0,170)));
                            }
                            for(int i=0;i<score;i++) {
                                component.append(Component.text("■", TextColor.color(0,0,170)));
                            }
                            for(int i=0;i<10;i++) {
                                component.append(Component.text("□", TextColor.color(255,85,85)));
                            }
                        } else if (spawnScore.get(id)<0) {
                            //2,red
                            for(int i=0;i<10;i++) {
                                component.append(Component.text("□", TextColor.color(0,0,170)));
                            }
                            for(int i=0;i<score;i++) {
                                component.append(Component.text("■", TextColor.color(255,85,85)));
                            }
                            for(int i=0;i<score - 10;i++) {
                                component.append(Component.text("□", TextColor.color(255,85,85)));
                            }
                        } else if (spawnScore.get(id)==0) {
                            //0
                        }
                        component.append(Component.text("]"));
                        spawnTextDisplay.get(id).text(component);
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
        return false;
    }
}
