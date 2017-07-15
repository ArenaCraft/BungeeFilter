package it.arenacraft.bungee.filter.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigUtils {

	public static void saveDefaultConfig(Plugin plugin, String configName) {
		File folder = plugin.getDataFolder();
		
		if (!folder.exists()) {
			folder.mkdir();
		}
		
		File file = new File(folder, configName);
		
		if (!file.exists()) {
			try (InputStream stream = plugin.getResourceAsStream(configName)) {
				Files.copy(stream, file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Configuration loadConfig(Plugin plugin, String configName) {
		saveDefaultConfig(plugin, configName);
		
		File configFile = new File(plugin.getDataFolder(), configName);
		
		try {
			return ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
