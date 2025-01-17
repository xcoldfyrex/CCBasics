package com.cfdigital.cc.ccbasics.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

import com.cfdigital.cc.ccbasics.CCBasics;
import com.cfdigital.cc.ccbasics.constants.Msg;
import com.cfdigital.cc.ccbasics.misc.Formatter;

public class Kick implements CommandExecutor {

	CCBasics plugin;

	public Kick(CCBasics plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		String command = arg1.getName().toLowerCase();
		CommandSender sender = arg0;
		if (arg3.length == 0) {
			sender.sendMessage(Msg.ERR_INSUFFPARAMS.toString());
			return true;
		}
		String playerName = arg3[0].toLowerCase();
		arg3[0] = "";
		String kickMessage = Formatter.joinString(arg3);
		if (kickMessage.trim().length() == 0) kickMessage = "Kicked from server";
		Player player = plugin.getPlayer(playerName);
		if (sender instanceof Player) {
			if (sender.isOp()) {
				doAction(sender,player, kickMessage);
				return true;
			} else {
				sender.sendMessage(Msg.ERR_INSUFFPERMISSION.toString());
				return true;
			}
		} else {
			doAction(sender,player, kickMessage);
			return true;	
		}
	}

	private void doAction(CommandSender sender, Player player, String kickMessage) {
		if (player == null) {
			sender.sendMessage(Msg.ERR_NOPLAYER.toString());
		} else {
			player.kickPlayer(kickMessage);
		}
	}
}