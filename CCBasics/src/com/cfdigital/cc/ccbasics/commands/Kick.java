package com.cfdigital.cc.ccbasics.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

import com.cfdigital.cc.ccbasics.CCBasics;
import com.cfdigital.cc.ccbasics.misc.Formatter;
import com.cfdigital.cc.ccbasics.misc.Msg;

public class Kick implements CommandExecutor {

	CCBasics plugin;

	public Kick(CCBasics plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		String command = arg1.getName().toLowerCase();
		CommandSender sender = arg0;
		if (sender == null) return false;
		if (arg3.length == 0) {
			sender.sendMessage(Msg.ERR_INSUFFPARAMS.toString());
			return true;
		}
		String playerName = arg3[0].toLowerCase();
		arg3[0] = "";
		String kickMessage = Formatter.joinString(arg3);
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
			if (kickMessage.isEmpty()) kickMessage = "Kicked from server";
			sender.sendMessage(Msg.ERR_NOPLAYER.toString());
		} else {
			player.kickPlayer(kickMessage);
		}
	}
}