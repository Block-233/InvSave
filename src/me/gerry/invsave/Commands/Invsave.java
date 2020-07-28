package me.gerry.invsave.Commands;

import me.gerry.invsave.Config;
import me.gerry.invsave.InvManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static me.gerry.invsave.Utils.*;

public class Invsave implements CommandExecutor {
    public static boolean saveinvlist = true;
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length < 1) {
                sendErrorMessageToPlayer(p,"&c没有足够的参数!");
            }else {
                if (Config.pdata.contains("PlayerData."+p.getUniqueId()+".invs."+args[0])) {
                    sendWarnMessageToPlayer(p,"&c已经存在同名的物品栏了 已将其覆盖!");
                    saveinvlist = false;
                }
                    InvManager.save(p, args[0]);
                    sendInfoMessageToPlayer(p, "&a已保存名为 &e" + args[0] + " &a的物品栏! 使用 &b/invload " + args[0] + " &a可以加载该物品栏!");
            }
        }else {
            sendErrorMessageToConsole("&c只有玩家才可以这么做!");
        }
        return true;
    }
}
