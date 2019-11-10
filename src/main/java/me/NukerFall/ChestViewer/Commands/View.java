package me.NukerFall.ChestViewer.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import me.NukerFall.ChestViewer.Main;
import me.NukerFall.ChestViewer.Utils.Colors;

public class View implements CommandExecutor {
	
	private Main main;
	public View(Main main) {
		this.main = main;
		main.getCommand("view").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			if (sender.hasPermission("chestviewer.view")) {
				Player p = (Player) sender;
				if (p.getTargetBlock(null, 100).getType() == Material.CHEST) {
					Chest c = (Chest) p.getTargetBlock(null, 100).getState();
					if (!main.getConfig().getBoolean("gui")) {
						for (ItemStack i : c.getInventory().getContents()) {
							if (i!=null && !main.getConfig().getStringList("blacklisted-items").contains(i.getType().name())) {
								if (main.getConfig().getBoolean("show-amount")) {
									sender.sendMessage(Colors.clr(main.getConfig().getString("item-type-amount")
									.replaceAll("%item%", i.getType().name().toLowerCase()).replaceAll("%num%", String.valueOf(i.getAmount()))));
								} else {
									sender.sendMessage(Colors.clr(main.getConfig().getString("item-type")
									.replaceAll("%item%", i.getType().name().toLowerCase())));
								}
							}
						}
					} else {
						Inventory inv = Bukkit.createInventory(p, 54, Colors.clr(main.getConfig().getString("chest-name")));
						for (ItemStack i : c.getInventory().getContents()) {
							if (i!=null && !main.getConfig().getStringList("blacklisted-items").contains(i.getType().name())) {
								if (main.getConfig().getBoolean("show-amount")) {
									inv.addItem(i);
								} else {
									inv.addItem(new ItemStack(Material.valueOf(i.getType().name()), 1));
								}
							}
						}
						p.openInventory(inv);
					}
				} else {
					sender.sendMessage(Colors.clr(main.getConfig().getString("chest-warning")));
				}
			} else {
				sender.sendMessage(Colors.clr(main.getConfig().getString("no-perm")));
			}
		} else {
			sender.sendMessage(Colors.clr(main.getConfig().getString("only-players")));
		}
		return false;
	}
}