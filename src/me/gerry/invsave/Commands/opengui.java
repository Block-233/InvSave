package me.gerry.invsave.Commands;

import me.gerry.invsave.GUI.LoadInv;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class opengui implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        LoadInv.open(p);
        return true;
    }
}
