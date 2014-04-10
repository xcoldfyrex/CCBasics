package com.cfdigital.cc.ccbasics.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.cfdigital.cc.ccbasics.CCBasics;
import com.cfdigital.cc.ccbasics.misc.Formatter;
import com.cfdigital.cc.ccbasics.misc.Msg;
import com.cfdigital.wafflescentials.handlers.BanHandler;

public class Ban implements CommandExecutor {

	CCBasics plugin;

	public Ban(CCBasics plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		CommandSender sender = arg0;
		OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(arg3[0].toLowerCase());
		arg3[0] = "";
		String banReason = Formatter.joinString(arg3);
		if (banReason.trim().length() == 0) banReason = "The banhammer has fallen upon thee.";

		long banExpires = 0;
		if (sender instanceof Player) {
			if (sender.isOp()) {
				BanHandler.addBan(sender, offlinePlayer,banExpires,banReason);
			} else {
				sender.sendMessage(Msg.ERR_INSUFFPERMISSION.toString());
			}
		} else {
			//add ban code
		}
		return false;
	}

}