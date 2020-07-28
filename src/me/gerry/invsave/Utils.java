package me.gerry.invsave;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    public static void sendInfoMessageToConsole(String s) {
        String m = ChatColor.translateAlternateColorCodes('&', "&8[&bInv&3Save&8] &8[&3INFO&8] "+s);
        InvSavePlugin.p.getServer().getConsoleSender().sendMessage(m);
    }
    public static void sendWarnMessageToConsole(String s) {
        String m = ChatColor.translateAlternateColorCodes('&', "&8[&bInv&3Save&8] &8[&eWARN&8] "+s);
        InvSavePlugin.p.getServer().getConsoleSender().sendMessage(m);
    }
    public static void sendErrorMessageToConsole(String s) {
        String m = ChatColor.translateAlternateColorCodes('&', "&8[&bInv&3Save&8] &8[&cERROR&8] "+s);
        InvSavePlugin.p.getServer().getConsoleSender().sendMessage(m);
    }
    public static void reloadPlayerDataFile() {
        try {
            Config.pdata.load(Config.pdatac);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static void savePlayerDataFile() {
        try {
            Config.pdata.save(Config.pdatac);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getServerVersion() {
        return InvSavePlugin.p.getServer().getVersion().substring(InvSavePlugin.p.getServer().getVersion().length()-12).replace(" ","").replace("MC","").replace("(","").replace(")","").replace(":","");
    }
    public static void sendInfoMessageToPlayer(Player p, String msg) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8[&bInv&3Save&8] &8[&3INFO&8] "+msg));
    }
    public static void sendErrorMessageToPlayer(Player p, String msg) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8[&bInv&3Save&8] &8[&cERROR&8] "+msg));
    }
    public static void sendWarnMessageToPlayer(Player p, String msg) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8[&bInv&3Save&8] &8[&eWARN&8] "+msg));
    }
}
