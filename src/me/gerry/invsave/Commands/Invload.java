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

import static me.gerry.invsave.Utils.*;

public class Invload implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0) {
                if (Config.pdata.contains("PlayerData."+p.getUniqueId()+".invs."+args[0])) {
                    InvManager.load(p, args[0]);
                    sendInfoMessageToPlayer(p, "&a已成功加载名为 &b" + args[0] + " &a的物品栏!");
                    if (Config.pdata.contains("PlayerData."+p.getUniqueId()+".invlist")) {
                        if (!InvManager.getInvList(p).contains(args[0])) {
                            ArrayList<String> li = InvManager.getInvList(p);
                            li.add(args[0]);
                            Config.pdata.set("PlayerData." + p.getUniqueId() + ".invlist", li);
                            Utils.savePlayerDataFile();
                        }
                    }
                }else {
                    sendErrorMessageToPlayer(p,"&c没有这个物品栏!");
                    if (InvManager.getInvList(p).contains(args[0])) {
                        ArrayList<String> lm = InvManager.getInvList(p);
                        lm.remove(args[0]);
                        Config.pdata.set("PlayerData."+p.getUniqueId()+".invlist",lm);
                        Utils.savePlayerDataFile();
                    }
                }

            }else {
                sendErrorMessageToPlayer(p,"&c没有足够的参数!");
            }
            if (args.length > 1) {
                sendInfoMessageToPlayer(p,"&7&o小提示:这个指令不需要这么多参数哦!");
            }
        }else {sendErrorMessageToConsole("&c只有玩家才可以这么做!");}
        return true;
    }
}
