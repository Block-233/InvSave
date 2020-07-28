package me.gerry.invsave.Commands.TabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class InvTabComplete implements TabCompleter {
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> tm = new ArrayList<>();
        tm.add("reload");
        tm.add("help");
        return tm;
    }
}
