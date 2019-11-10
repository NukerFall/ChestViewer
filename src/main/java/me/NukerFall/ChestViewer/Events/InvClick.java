package me.NukerFall.ChestViewer.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.NukerFall.ChestViewer.Main;

public class InvClick implements Listener {
	
	@SuppressWarnings("unused")
	private Main main;
	public InvClick(Main main) {
		this.main = main;
		Bukkit.getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if (e.getClickedInventory().getHolder() == e.getWhoClicked() && e.getClickedInventory().getSize() == 54 && e.getClickedInventory().getViewers().size() == 1) {
			e.setCancelled(true);
		}
	}

}
