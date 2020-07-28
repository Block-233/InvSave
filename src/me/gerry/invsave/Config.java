package me.gerry.invsave;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    public static File pdatac=new File(InvSavePlugin.p.getDataFolder(),"playerdata.yml");
    public static FileConfiguration pdata= YamlConfiguration.loadConfiguration(pdatac);
    public static File cachec=new File(InvSavePlugin.p.getDataFolder(),"cache.yml");
    public static FileConfiguration cachedata= YamlConfiguration.loadConfiguration(cachec);
}
