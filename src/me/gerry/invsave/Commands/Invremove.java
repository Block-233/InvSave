package me.gerry.invsave.Commands;

import com.mysql.fabric.xmlrpc.base.Array;
import me.gerry.invsave.Config;
import me.gerry.invsave.InvManager;
import me.gerry.invsave.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Invremove implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args[0].length() > 0) {
                if (Config.pdata.contains("PlayerData."+p.getUniqueId()+".invs."+args[0])) {
                    InvManager.remove(p,args[0]);
                    Utils.sendInfoMessageToPlayer(p,"&a删除成功!");
                }else {
                    Utils.sendErrorMessageToPlayer(p,"&c没有这个物品栏!");
                }
            }else {
                Utils.sendErrorMessageToPlayer(p,"&c没有足够的参数!");
            }
        }else {
            Utils.sendErrorMessageToConsole("&c只有玩家才可以这么做!");
        }
        return true;
    }
}
