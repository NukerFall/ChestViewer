package me.NukerFall.ChestViewer;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.NukerFall.ChestViewer.Commands.Mode;
import me.NukerFall.ChestViewer.Commands.View;
import me.NukerFall.ChestViewer.Events.Click;
import me.NukerFall.ChestViewer.Events.InvClick;
import me.NukerFall.ChestViewer.Events.Joiner;

public class Main extends JavaPlugin {
	
	public HashMap<Player, Boolean> map = new HashMap<Player, Boolean>();
	
	public void onEnable() {
		send("[ChestViewer] &aPlugin Enabled!");
		saveDefaultConfig();
		new View(this);
		new Click(this);
		new Joiner(this);
		new Mode(this);
		new InvClick(this);
	}
	
	public void onDisable() {
		send("[ChestViewer] &cPlugin Disabled!");
		saveConfig();
	}
	
	public void send(String s) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
	}

	public boolean isWork(Player p) {
		return map.get(p);
	}

}
