package jp.houlab.mochidsuki.toweraandd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static jp.houlab.mochidsuki.toweraandd.GameStarter.playerTP;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("tad")){
            switch (strings[0]){
                case "start":{
                    playerTP();
                }

            }
        }
        return false;
    }
}
