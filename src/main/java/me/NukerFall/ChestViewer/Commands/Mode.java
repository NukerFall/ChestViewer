package me.NukerFall.ChestViewer.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.NukerFall.ChestViewer.Main;
import me.NukerFall.ChestViewer.Utils.Colors;

public class Mode implements CommandExecutor {
	
	private Main main;
	public Mode(Main main) {
		this.main = main;
		main.getCommand("viewmode").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (sender.hasPermission("chestviewer.mode")) {
				main.map.put(p, !main.map.get(p));
				sender.sendMessage(Colors.clr(main.getConfig().getString("mode-changed").replaceAll("%state%", main.map.get(p).toString())));
			} else {
				sender.sendMessage(Colors.clr(main.getConfig().getString("no-perm")));
			}
		} else {
			sender.sendMessage(Colors.clr(main.getConfig().getString("only-players")));
		}
			return false;
	}
}