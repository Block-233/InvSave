package me.gerry.invsave.Commands.TabComplete;

import com.mysql.fabric.xmlrpc.base.Array;
import me.gerry.invsave.InvManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InvloadTabComplete implements TabCompleter {
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            return InvManager.getInvList(p);
        }else {
            //List<String> emptyl = new ArrayList<>();
            return null;
        }
    }
}
