package com.cfdigital.cc.ccbasics.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.cfdigital.cc.ccbasics.misc.MessageEnum;

public class Ban implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (!arg1.equals("ban")) { return false;}
		CommandSender sender = arg0;
		if (sender == null) return false;
		if (sender instanceof Player) {
			if (sender.isOp()) {
				// add ban code
			} else {
				sender.sendMessage(MessageEnum.ERR_INSUFFPERMISSION.toString());
			}
		} else {
			//add ban code
		}
		return false;
	}

}