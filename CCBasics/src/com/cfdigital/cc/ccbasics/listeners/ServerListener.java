package com.cfdigital.cc.ccbasics.listeners;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.cfdigital.cc.ccbasics.CCBasics;
import com.cfdigital.cc.ccbasics.handlers.BanHandler;

public class ServerListener implements Listener {

	CCBasics plugin;

	public ServerListener(CCBasics ccBasics) {
		this.plugin = ccBasics;
	}

	@EventHandler(priority = EventPriority.HIGHEST)             
	public void onPlayerLogin(PlayerLoginEvent event){
		OfflinePlayer offlinePlayer = event.getPlayer();
		if (BanHandler.isBanned(offlinePlayer)) {
			String banMessage;
			banMessage = ChatColor.RED + "" + ChatColor.BOLD + "BANNED" + 
			ChatColor.RESET + "\n" +
			BanHandler.getBanReason(offlinePlayer);
			
			event.disallow(PlayerLoginEvent.Result.KICK_OTHER, banMessage);
		}
	}
}