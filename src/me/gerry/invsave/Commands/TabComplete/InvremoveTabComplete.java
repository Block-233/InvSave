package me.gerry.invsave.Commands.TabComplete;

import me.gerry.invsave.InvManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class InvremoveTabComplete implements TabCompleter {
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            return InvManager.getInvList(((Player) sender).getPlayer());
        }
        return null;
    }
}
