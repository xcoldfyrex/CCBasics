package com.cfdigital.wafflescentials.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.cfdigital.cc.ccbasics.CCBasics;

public class BanHandler {

	private static FileConfiguration config;
	private static File configFile = new File(CCBasics.plugin.getDataFolder(), "bans.yml");

	public static void addBan (CommandSender sender, OfflinePlayer offlinePlayer, Long banExpires, String banReason) {
		FileConfiguration banFile = openBanFile();
		String playerUUID = offlinePlayer.getUniqueId().toString();
		banFile.set("bans." + playerUUID + ".banned", System.currentTimeMillis() /1000L);
		banFile.set("bans." + playerUUID + ".issuer", sender.getName());
		banFile.set("bans." + playerUUID + ".reason", banReason);
		banFile.set("bans." + playerUUID + ".expires", banExpires);

		try {
			banFile.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void removeBan (OfflinePlayer offlinePlayer) {

	}

	public static boolean isBanned (OfflinePlayer offlinePlayer) {
		return false;
	}

	private static FileConfiguration openBanFile() {
		config = CCBasics.plugin.getConfig();

		if (!CCBasics.plugin.getDataFolder().exists()) {
			CCBasics.plugin.getDataFolder().mkdirs();
		}

		try {
			config.load(configFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
		return config;
	}
}
