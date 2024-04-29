package jp.houlab.mochidsuki.toweraandd;

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
            try {
                BlockCommandSender sender1 = (BlockCommandSender) sender;
                Location spawnLocation = sender1.getBlock().getLocation();
                for(Player player:plugin.getServer().getOnlinePlayers()){
                    if(spawnLocation.distance(player.getLocation()) <=3){
                        if(team1.hasEntry(player.getName())){
                            spawnScore.put(Integer.valueOf(strings[0]),spawnScore.get(Integer.parseInt(strings[0])) + 1);

                            if(player.isOp()){
                                player.sendMessage(spawnScore.get(Integer.parseInt(strings[0]))+"");
                            }
                        } else if (team2.hasEntry(player.getName())) {
                            spawnScore.put(Integer.valueOf(strings[0]),spawnScore.get(Integer.parseInt(strings[0])) - 1);
                            if(player.isOp()){
                                player.sendMessage(spawnScore.get(Integer.parseInt(strings[0]))+"");
                            }
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
        return false;
    }
}
