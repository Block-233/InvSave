package me.gerry.invsave.GUI;

import com.mysql.fabric.xmlrpc.base.Array;
import me.gerry.invsave.Commands.Inv;
import me.gerry.invsave.Config;
import me.gerry.invsave.InvManager;
import me.gerry.invsave.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.gerry.invsave.Utils.sendInfoMessageToPlayer;

public class LoadInv implements Listener {
    public static void open(Player p) {
        if (InvManager.getInvList(p) == null) {
            ArrayList<String> el = new ArrayList<>();
            Config.pdata.set("PlayerData." + p.getUniqueId() + ".invlist", el);
            Utils.savePlayerDataFile();
        }
        Inventory gui = Bukkit.createInventory(null, 54, p.getName() + " 保存的物品栏");
        ArrayList<String> l = InvManager.getInvList(p);
        for (int cs = 0; cs < l.size(); cs++) {
            ItemStack i = new ItemStack(Material.CHEST, 1);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a" + l.get(cs)));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add(ChatColor.translateAlternateColorCodes('&', "&6左键 &8>>> &b&l加载&r&6物品栏"));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&6中键 &8>>> &a&l预览&r&6物品栏"));
            lore.add(ChatColor.translateAlternateColorCodes('&', "&6右键 &8>>> &c&l删除&r&6物品栏"));
            lore.add(" ");
            im.setLore(lore);
            i.setItemMeta(im);
            gui.setItem(cs, i);
        }
        p.openInventory(gui);
    }


    @EventHandler
    public void PlayerInvCilckEvt(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            if (e.getView().getTitle().equalsIgnoreCase(p.getName() + " 保存的物品栏")) {
                if (e.getClick() == ClickType.LEFT) {
                    int size = e.getInventory().getSize();
                    for (int cs = 0; cs < size; cs++) {
                        if (e.getRawSlot() == cs) {
                            ArrayList<String> l = InvManager.getInvList(p);
                            InvManager.load(p, InvManager.getInvList(p).get(cs));
                            sendInfoMessageToPlayer(p, "&a已成功加载名为 &b" + l.get(cs) + " &a的物品栏!");
                            e.setCancelled(true);
                        }
                    }
                    e.setCancelled(true);
                } else if (e.getClick() == ClickType.RIGHT) {
                    int size = e.getInventory().getSize();
                    for (int cs = 0; cs < size; cs++) {
                        if (e.getRawSlot() == cs) {
                            ArrayList<String> il = InvManager.getInvList(p);
                            InvManager.remove(p, il.get(cs));
                            sendInfoMessageToPlayer(p, "&a删除成功!");
                            p.closeInventory();
                            open(p);
                            e.setCancelled(true);
                        }
                    }
                    e.setCancelled(true);
                } else if (e.getClick() == ClickType.MIDDLE) {
                    int size = e.getInventory().getSize();
                    for (int cs = 0; cs < size; cs++) {
                        if (e.getRawSlot() == cs) {
                            ArrayList<String> il = InvManager.getInvList(p);
                            Preview.open(p,il.get(cs));
                            e.setCancelled(true);
                        }
                    }
                    e.setCancelled(true);
                }else {
                    e.setCancelled(true);
                }
            }
        }
    }
}
