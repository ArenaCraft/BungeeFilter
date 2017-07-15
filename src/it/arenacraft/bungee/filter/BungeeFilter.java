package it.arenacraft.bungee.filter;

import it.arenacraft.bungee.filter.commands.BungeeFilterCommand;
import it.arenacraft.bungee.filter.managers.ChatManager;
import it.arenacraft.bungee.filter.managers.ConfigManager;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeFilter extends Plugin {
	
	@Getter
	private ChatManager chatManager;
	@Getter
	private ConfigManager configManager;
	
	@Override
	public void onEnable() {
		chatManager = new ChatManager(this);
		configManager = new ConfigManager(this);
		
		getProxy().getPluginManager().registerCommand(this, new BungeeFilterCommand(this));
		
		load();
	}
	
	public void load() {
		chatManager.load();
		configManager.load();
	}
		
}
