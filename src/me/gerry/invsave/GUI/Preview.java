package me.gerry.invsave.GUI;

import com.mysql.fabric.xmlrpc.base.Array;
import me.gerry.invsave.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Preview implements Listener {
    public static void open(Player p,String InvName) {
        Inventory gui = Bukkit.createInventory(null, 54, "预览物品");
        for (int cs = 0;cs < 41;cs++) {
            ItemStack item = Config.pdata.getItemStack("PlayerData."+p.getUniqueId()+".invs."+InvName+".item.slot"+cs);
            gui.setItem(cs, item);
        }
        ItemStack back = new ItemStack(Material.OAK_BUTTON);
        ItemMeta backim = back.getItemMeta();
        backim.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&a返回"));
        ArrayList<String> l = new ArrayList<>();
        l.add(ChatColor.translateAlternateColorCodes('&',"&7点击返回"));
        backim.setLore(l);
        back.setItemMeta(backim);
        gui.setItem(53,back);
        p.openInventory(gui);
    }
    @EventHandler
    public void PlayerCilckPreviewGUIEvt(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            if (e.getView().getTitle().equals("预览物品")) {
                if (e.getRawSlot()==53) {
                    e.setCancelled(true);
                    LoadInv.open(p);
                }else {
                    e.setCancelled(true);
                }
            }
        }
    }

}
