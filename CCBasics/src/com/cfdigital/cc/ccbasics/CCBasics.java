package com.cfdigital.cc.ccbasics;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.cfdigital.cc.ccbasics.commands.Ban;
import com.cfdigital.cc.ccbasics.commands.Kick;
import com.cfdigital.cc.ccbasics.commands.Unban;
import com.cfdigital.cc.ccbasics.commands.Whois;
import com.cfdigital.cc.ccbasics.listeners.ServerListener;

public class CCBasics extends JavaPlugin {
	
	public static CCBasics plugin;
	private final ServerListener serverListener = new ServerListener(this);

	
	public CCBasics() {
		plugin = this;
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(serverListener, this);
		
		getCommand("ban").setExecutor(new Ban(this));
		getCommand("kick").setExecutor(new Kick(this));
		getCommand("unban").setExecutor(new Unban(this));
		getCommand("whois").setExecutor(new Whois(this));
	}
	
	public UUID getPlayerUUID(String playerName) {
		UUID targetPlayerUUID = getServer().getPlayer(playerName).getUniqueId();
		if (targetPlayerUUID != null)
			return targetPlayerUUID;
		return null;
	}
	
	public Player getPlayer(String playerName) {
		Player targetPlayer = getServer().getPlayer(playerName.toLowerCase());
		if (targetPlayer != null)
			return targetPlayer;
		return null;
	}

	public String getPlayerName(String playerName) {
		String targetPlayer = getServer().getPlayer(playerName).getName();
		if (targetPlayer != null)
			return targetPlayer;
		return null;
	}
}