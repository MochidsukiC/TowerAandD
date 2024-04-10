package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static jp.houlab.mochidsuki.toweraandd.V.Round;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("tad")){
            switch (strings[0]){
                case "start":{
                    Round = 1;
                    RoundSystem.startPrepare();
                    break;
                }

            }
        }
        return false;
    }
}
