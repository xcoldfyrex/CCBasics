package com.cfdigital.cc.ccbasics.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class ServerListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)             
	public void onPlayerLogin(PlayerLoginEvent event){
		// add code to prevent banned users logging in
	}
}