package me.gerry.invsave;

import me.gerry.invsave.Commands.*;
import me.gerry.invsave.Commands.TabComplete.InvTabComplete;
import me.gerry.invsave.Commands.TabComplete.InvloadTabComplete;
import me.gerry.invsave.Commands.TabComplete.InvremoveTabComplete;
import me.gerry.invsave.GUI.LoadInv;
import me.gerry.invsave.GUI.Preview;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

import static me.gerry.invsave.Utils.*;

public class InvSavePlugin extends JavaPlugin {
    public static Plugin p;
    public void onEnable() {
        p = this;
        try {
            loader();
        }catch (Exception e) {
            sendErrorMessageToConsole("&c插件加载时遇到错误!请确认你下载的文件没有损坏!");
            sendErrorMessageToConsole("&c错误原因: "+ Arrays.toString(e.getStackTrace()).replace("[","").replace("]","").replace(",","\n"));
            getPluginLoader().disablePlugin(this);
        }
    }
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        sendInfoMessageToConsole("&c插件关闭! 作者Gerry");
        //sendInfoMessageToConsole("&a正在保存内存中 &econfig.yml &a的数据到磁盘...");
        //saveConfig();
        //sendInfoMessageToConsole("&a正在保存内存中 &eplayerdata.yml &a的数据到磁盘...");
        //savePlayerDataFile();
    }
    public void loader() {
        //int s = Integer.parseInt(null);
        //getLogger().info("111"+s);
        //startConfirmRunnable();
        Bukkit.getPluginManager().registerEvents(new LoadInv(),this);
        Bukkit.getPluginManager().registerEvents(new Preview(),this);
        Bukkit.getPluginCommand("invopengui").setExecutor(new opengui());
        Bukkit.getPluginCommand("invremove").setExecutor(new Invremove());
        Bukkit.getPluginCommand("invremove").setTabCompleter(new InvremoveTabComplete());
        Bukkit.getPluginCommand("invsave").setExecutor(new Invsave());
        Bukkit.getPluginCommand("invlist").setExecutor(new Invlist());
        Bukkit.getPluginCommand("invload").setExecutor(new Invload());
        Bukkit.getPluginCommand("invload").setTabCompleter(new InvloadTabComplete());
        Bukkit.getPluginCommand("inv").setExecutor(new Inv());
        Bukkit.getPluginCommand("inv").setTabCompleter(new InvTabComplete());
        sendInfoMessageToConsole("&a插件启动! 作者Gerry!");
        sendInfoMessageToConsole("&b检测到版本: &e"+Utils.getServerVersion());
        //String server = getServerVersion().replace(".","_");
        //sendInfoMessageToConsole(server);
        //Double serverver = Double.parseDouble(server.replaceFirst("_","").replace("_","."));
        //sendInfoMessageToConsole(String.valueOf(serverver));
        //if (serverver < 112) {
        //    sendErrorMessageToConsole("&c该插件仅适用于1.12及以上版本!");
        //    sendErrorMessageToConsole("&c服务器将会停止!");
        //    Bukkit.shutdown();
        //}
        sendInfoMessageToConsole("&e正在检测配置文件...");
        if (!Config.pdatac.exists()) {
            sendWarnMessageToConsole("&c未检测到 &6playerdata.yml &c正在创建...");
            saveResource("playerdata.yml",false);
        }else {
            sendInfoMessageToConsole("&a检测到 &eplayerdata.yml &a正在加载...");
        }
    }}
