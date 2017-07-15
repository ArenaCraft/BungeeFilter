package it.arenacraft.bungee.filter.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import it.arenacraft.bungee.filter.BungeeFilter;
import it.arenacraft.bungee.filter.utils.ConfigUtils;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.config.Configuration;

public class ConfigManager {

	private final BungeeFilter plugin;
	
	@Getter
	private Pattern validRegex;
	@Getter
	private TextComponent invalidMessage;
	@Getter
	private boolean commandsAsWhitelist;
	@Getter
	private List<String> commands;
	
	public ConfigManager(BungeeFilter plugin) {
		this.plugin = plugin;
	}
	
	public void load() {
		Configuration configuration = ConfigUtils.loadConfig(plugin, "config.yml");
		
		commands = new ArrayList<>();
		
		if (configuration == null) {
			validRegex = Pattern.compile(".*");
			invalidMessage = textComponentFromString("&cYour message contains characters that are not allowed");
			plugin.getLogger().severe("Unable to load the configuration file");
			return;
		}
		
		validRegex = Pattern.compile(configuration.getString("valid_regex"));
		invalidMessage = textComponentFromString(configuration.getString("invalid_message"));
		commandsAsWhitelist = configuration.getBoolean("commands_as_whitelist");
		commands.addAll(configuration.getStringList("commands"));
	}
	
	private TextComponent textComponentFromString(String string) {
		return new TextComponent(ChatColor.translateAlternateColorCodes('&', string));
	}
	
}
