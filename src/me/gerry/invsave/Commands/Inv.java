package me.gerry.invsave.Commands;

import me.gerry.invsave.Config;
import me.gerry.invsave.InvSavePlugin;
import me.gerry.invsave.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Inv implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                File pf = new File(InvSavePlugin.p.getDataFolder(), "playerdata.yml");
                if (pf.exists()) {
                    try {
                        Config.pdata.load(Config.pdatac);
                    } catch (IOException | InvalidConfigurationException e) {
                        e.printStackTrace();
                    }
                } else {
                    InvSavePlugin.p.saveResource("playerdata.yml", false);
                    try {
                        Config.pdata.load(Config.pdatac);
                    } catch (IOException | InvalidConfigurationException e) {
                        e.printStackTrace();
                    }
                }
                if (sender instanceof ConsoleCommandSender) {
                    Utils.sendInfoMessageToConsole("&a重载配置文件!");
                } else if (sender instanceof Player) {
                    Utils.sendInfoMessageToPlayer(((Player) sender).getPlayer(), "&a重载配置文件!");
                }
            } else if (args[0].equalsIgnoreCase("help")) {
                if (sender instanceof ConsoleCommandSender) {
                    ArrayList<String> helpm = new ArrayList<>();
                    helpm.add("&a/inv <reload;help> - 插件主命令 权限:invsave.maincmd");
                    helpm.add("&a/invsave <InvName> - 保存一个你的物品栏 权限:invsave.save");
                    helpm.add("&a/invlist - 列出你保存的物品栏 权限:invsave.list");
                    helpm.add("&a/invremove <InvName> - 移除一个你的物品栏 权限:invsave.remove");
                    helpm.add("&a/invload <InvName> - 加载一个你的物品栏 权限:invsave.load");
                    helpm.add("&a/invopengui - 打开菜单 权限:invsave.opengui");
                    int size = helpm.size();
                    for (int cs = 0; cs < size; cs++) {
                        Utils.sendInfoMessageToConsole(helpm.get(cs));
                    }
                } else if (sender instanceof Player) {
                    ArrayList<String> helpm = new ArrayList<>();
                    helpm.add("&a/inv <reload;help> - 插件主命令 权限:invsave.maincmd");
                    helpm.add("&a/invsave <InvName> - 保存一个你的物品栏 权限:invsave.save");
                    helpm.add("&a/invlist - 列出你保存的物品栏 权限:invsave.list");
                    helpm.add("&a/invremove <InvName> - 移除一个你的物品栏 权限:invsave.remove");
                    helpm.add("&a/invload <InvName> - 加载一个你的物品栏 权限:invsave.load");
                    helpm.add("&a/invopengui - 打开菜单 权限:invsave.opengui");
                    int size = helpm.size();
                    for (int cs = 0; cs < size; cs++) {
                        Utils.sendInfoMessageToPlayer(((Player) sender).getPlayer(), helpm.get(cs));
                    }
                }
            }else {
                if (sender instanceof ConsoleCommandSender) {
                    Utils.sendErrorMessageToConsole("&c没有这个参数!");
                }else if (sender instanceof Player) {
                    Utils.sendErrorMessageToPlayer(((Player) sender).getPlayer(),"&c没有这个参数!");
                }
            }
        }else {
            if (sender instanceof ConsoleCommandSender) {
                Utils.sendErrorMessageToConsole("&c你没有输入参数!");
            }else if (sender instanceof Player) {
                Utils.sendErrorMessageToPlayer(((Player) sender).getPlayer(),"&c你没有输入参数!");
            }
        }
        return true;
    }
}
