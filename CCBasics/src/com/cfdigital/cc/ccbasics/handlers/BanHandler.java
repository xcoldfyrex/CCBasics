package com.cfdigital.cc.ccbasics.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

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

	public static void removeBan (CommandSender sender, OfflinePlayer offlinePlayer) {
		FileConfiguration banFile = openBanFile();
		String playerUUID = offlinePlayer.getUniqueId().toString();
		banFile.set("bans." + playerUUID , null);
		try {
			banFile.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isBanned (OfflinePlayer offlinePlayer) {
		FileConfiguration banFile = openBanFile();
		String playerUUID = offlinePlayer.getUniqueId().toString();
		String banUUID = banFile.getString("bans." + playerUUID);
		if (banUUID == null) return false;
		long banExpires = banFile.getLong("bans." + playerUUID + ".expires");
		if (banExpires == 0) return true;

		return false;
	}

	public static String getBanReason (OfflinePlayer offlinePlayer) {
		FileConfiguration banFile = openBanFile();
		String playerUUID = offlinePlayer.getUniqueId().toString();
		String banUUID = banFile.getString("bans." + playerUUID);
		long banExpires = banFile.getLong("bans." + playerUUID + ".expires");
		String banReason = banFile.getString("bans." + playerUUID + ".reason");
		return banReason;
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
