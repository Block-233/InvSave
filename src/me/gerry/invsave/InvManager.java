package me.gerry.invsave;

import me.gerry.invsave.Commands.Inv;
import me.gerry.invsave.Commands.Invsave;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

import static me.gerry.invsave.Config.*;

public class InvManager {
    public static void save(Player p,String InvName) {
        PlayerInventory inv = p.getInventory();
        for (int cs = 0;cs < 41;cs++) {
            ItemStack itemslot = inv.getItem(cs);
            pdata.set("PlayerData." + p.getUniqueId() + ".invs." + InvName + ".item.slot" + cs, itemslot);
        }
        if (Invsave.saveinvlist) {
            if (pdata.contains("PlayerData." + p.getUniqueId() + ".invlist")) {
                ArrayList<String> l = (ArrayList<String>) pdata.getStringList("PlayerData." + p.getUniqueId() + ".invlist");
                l.add(InvName);
                pdata.set("PlayerData." + p.getUniqueId() + ".invlist", l);
            } else {
                ArrayList<String> l1 = new ArrayList<>();
                l1.add(InvName);
                pdata.set("PlayerData." + p.getUniqueId() + ".invlist", l1);
            }
        }
        Invsave.saveinvlist = true;
        Utils.savePlayerDataFile();

    }
    public static void load(Player p,String InvName) {
        PlayerInventory inv = p.getInventory();
        ItemStack air = new ItemStack(Material.AIR);
        for (int cs = 0;cs < 41;cs++) {
            inv.setItem(cs,air);
        }
        for (int i = 0;i < 41;i++) {
            inv.setItem(i, pdata.getItemStack("PlayerData."+p.getUniqueId()+".invs."+InvName+".item.slot"+i));
        }

    }
    public static ArrayList<String> getInvList(Player p) {
        ArrayList<String> list = (ArrayList<String>) pdata.getList("PlayerData."+p.getUniqueId()+".invlist");
        return list;
    }
    public static void remove(Player p ,String InvName) {
        Config.pdata.set("PlayerData."+p.getUniqueId()+".invs."+InvName,null);
        ArrayList<String> al = InvManager.getInvList(p);
        al.remove(InvName);
        Config.pdata.set("PlayerData."+p.getUniqueId()+".invlist",al);
        Utils.savePlayerDataFile();
    }
}
