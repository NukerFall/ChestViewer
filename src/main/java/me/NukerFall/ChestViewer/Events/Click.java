package me.NukerFall.ChestViewer.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import me.NukerFall.ChestViewer.Main;
import me.NukerFall.ChestViewer.Utils.Colors;

public class Click implements Listener {
	
	private Main main;
	public Click(Main main) {
		this.main = main;
		Bukkit.getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Chest c = null;
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.CHEST) {
			c = (Chest) e.getClickedBlock().getState();
		}
			if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getPlayer().getTargetBlock(null, 100).getType() == Material.CHEST) {
			c = (Chest) e.getPlayer().getTargetBlock(null, 100).getState();
		}
				if (e.getPlayer().hasPermission("chestviewer.view")) {
					if (main.isWork(e.getPlayer())) {
						if ((main.getConfig().getBoolean("item-event")
							&& e.getPlayer().getInventory().getItemInMainHand().getType() == Material.valueOf(main.getConfig().getString("item-material")))
							|| !main.getConfig().getBoolean("item-event")) {
							Player p = e.getPlayer();
							if (!main.getConfig().getBoolean("gui")) {
								for (ItemStack i : c.getInventory().getContents()) {
									if (i!=null && !main.getConfig().getStringList("blacklisted-items").contains(i.getType().name())) {
										if (main.getConfig().getBoolean("show-amount")==true) {
											p.sendMessage(Colors.clr(main.getConfig().getString("item-type-amount")
											.replaceAll("%item%", i.getType().name().toLowerCase()).replaceAll("%num%", String.valueOf(i.getAmount()))));
										} else {
											p.sendMessage(Colors.clr(main.getConfig().getString("item-type")
											.replaceAll("%item%", i.getType().name().toLowerCase())));
										}
									}
								}
							} else {
								Inventory inv = Bukkit.createInventory(e.getPlayer(), 54, Colors.clr(main.getConfig().getString("chest-name")));
								for (ItemStack i : c.getInventory().getContents()) {
									if (i!=null && !main.getConfig().getStringList("blacklisted-items").contains(i.getType().name())) {
										inv.addItem(i);
									}
								}
								p.openInventory(inv);
							}
						}  else {
							e.getPlayer().sendMessage(Colors.clr(main.getConfig().getString("no-item")));
						}
					}
				} else {
					e.getPlayer().sendMessage(Colors.clr(main.getConfig().getString("no-perm")));
				}
	}
}