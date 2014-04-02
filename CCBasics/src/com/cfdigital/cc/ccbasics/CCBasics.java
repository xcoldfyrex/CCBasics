package com.cfdigital.cc.ccbasics;

import org.bukkit.plugin.java.JavaPlugin;

import com.cfdigital.cc.ccbasics.commands.Ban;

public class CCBasics extends JavaPlugin {
	
	
	@Override
	public void onEnable() {
		getCommand("Ban").setExecutor(new Ban());

	}
}