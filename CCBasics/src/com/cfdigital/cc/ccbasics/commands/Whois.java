package com.cfdigital.cc.ccbasics.commands;

import java.text.SimpleDateFormat;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.cfdigital.cc.ccbasics.CCBasics;
import com.cfdigital.cc.ccbasics.constants.Msg;

public class Whois implements CommandExecutor {

	CCBasics plugin;

	public Whois(CCBasics plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		CommandSender sender = arg0;
		if (arg3.length == 0) {
			sender.sendMessage(Msg.ERR_INSUFFPARAMS.toString());
			return true;
		}
		OfflinePlayer player = plugin.getServer().getOfflinePlayer(arg3[0].toLowerCase());
		if (sender instanceof Player) {
			if (sender.isOp()) {
				doAction(sender, player);
			} else {
				sender.sendMessage(Msg.ERR_INSUFFPERMISSION.toString());
			}
		} else {
			doAction(sender, player);
		}
		return true;
	}

	private void doAction(CommandSender sender, OfflinePlayer offlinePlayer) {
		
		if (!offlinePlayer.hasPlayedBefore()) {
			sender.sendMessage(Msg.ERR_NOPLAYER.toString());
			return;
		}
		
		String message = null;

		SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
		String firstDate = sdf.format(offlinePlayer.getFirstPlayed());
		String lastDate = sdf.format(offlinePlayer.getLastPlayed());
		
		String UUID = offlinePlayer.getUniqueId().toString();
		message = "WHOIS information for " + offlinePlayer.getName() + "\n" +
		"UUID: " + UUID + "\n" +
		"First login: " + firstDate + "\n" +
		"Last login: " + lastDate;
		
		sender.sendMessage(message);
	}

}