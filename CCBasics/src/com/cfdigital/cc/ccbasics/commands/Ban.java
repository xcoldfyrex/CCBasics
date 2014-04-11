package com.cfdigital.cc.ccbasics.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.cfdigital.cc.ccbasics.CCBasics;
import com.cfdigital.cc.ccbasics.constants.Msg;
import com.cfdigital.cc.ccbasics.handlers.BanHandler;
import com.cfdigital.cc.ccbasics.misc.Formatter;

public class Ban implements CommandExecutor {

	CCBasics plugin;

	public Ban(CCBasics plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		CommandSender sender = arg0;
		OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(arg3[0].toLowerCase());
		if (arg3.length == 0) {
			sender.sendMessage(Msg.ERR_INSUFFPARAMS.toString());
			return true;
		}
		arg3[0] = "";
		String banReason = Formatter.joinString(arg3);
		if (banReason.trim().length() == 0) banReason = "The ban hammer has fallen upon thee.";

		long banExpires = 0;
		if (sender instanceof Player) {
			if (sender.isOp()) {
				doAction(sender, offlinePlayer,banExpires,banReason);
			} else {
				sender.sendMessage(Msg.ERR_INSUFFPERMISSION.toString());
			}
		} else {
			doAction(sender, offlinePlayer,banExpires,banReason);
		}
		return true;
	}

	private void doAction(CommandSender sender, OfflinePlayer offlinePlayer, long banExpires, String banReason) {
		
		if (!offlinePlayer.hasPlayedBefore()) {
			sender.sendMessage(Msg.ERR_NOPLAYER.toString());
			return;
		}
		
		BanHandler.addBan(sender, offlinePlayer,banExpires,banReason);
		if (offlinePlayer.isOnline()) {
			Player player = (Player) offlinePlayer;
			player.kickPlayer(banReason);
		}
	}
}