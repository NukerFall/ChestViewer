package me.NukerFall.ChestViewer.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import me.NukerFall.ChestViewer.Main;

public class Joiner implements Listener {
	
	private Main main;
	public Joiner(Main main) {
		this.main = main;
		Bukkit.getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (!main.map.containsKey(e.getPlayer())) {
			main.map.put(e.getPlayer(), false);
		}
	}

}
