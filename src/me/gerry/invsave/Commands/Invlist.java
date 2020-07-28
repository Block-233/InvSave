package me.gerry.invsave.Commands;

import me.gerry.invsave.Config;
import me.gerry.invsave.InvManager;
import me.gerry.invsave.InvSavePlugin;
import me.gerry.invsave.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.rmi.CORBA.Util;

public class Invlist implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Config.pdata.contains("PlayerData."+p.getUniqueId()+".invlist")) {
                if (InvManager.getInvList(p).size() > 0) {
                    int s = InvManager.getInvList(p).size();
                    Utils.sendInfoMessageToPlayer(p, "&e以下是你保存的物品栏:");
                    for (int cs = 0; cs < s; cs++) {
                        Utils.sendInfoMessageToPlayer(p, "&a" + InvManager.getInvList(p).get(cs));
                    }
                }else {
                    Utils.sendErrorMessageToPlayer(p,"&c你还没有保存物品栏!");
                }
            }else {
                Utils.sendErrorMessageToPlayer(p,"&c你还没有保存物品栏!");
            }
        }else {
            Utils.sendErrorMessageToConsole("&c只有玩家才可以这么做!");
        }
        return true;
    }
        
}
