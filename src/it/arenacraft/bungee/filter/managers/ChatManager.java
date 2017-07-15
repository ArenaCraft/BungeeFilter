package it.arenacraft.bungee.filter.managers;

import it.arenacraft.bungee.filter.BungeeFilter;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ChatManager implements Listener {

	private final BungeeFilter plugin;

	public ChatManager(BungeeFilter plugin) {
		this.plugin = plugin;

		plugin.getProxy().getPluginManager().registerListener(plugin, this);
	}

	public void load() {
		//	
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onChat(ChatEvent event) {
		if (event.isCancelled()) {
			return;
		}

		if (!(event.getSender() instanceof ProxiedPlayer)) {
			return;
		}

		ProxiedPlayer player = (ProxiedPlayer) event.getSender();

		if (player.hasPermission("bungeefilter.bypass")) {
			return;
		}

		ConfigManager config = plugin.getConfigManager();
		String message = event.getMessage().toLowerCase();

		boolean whitelist = config.isCommandsAsWhitelist();
		
		if (message.startsWith("/")) {
			boolean commandMatches = false;
			
			for (String command : config.getCommands()) {
				boolean configCommandMatches = message.startsWith("/" + command + " ");

				if (configCommandMatches) {
					commandMatches = true;
					break;
				}
			}
			
			// Exit if the command matches and whitelist == true,
			// Exit if the command doesn't match and whitelist == false
			if (whitelist == commandMatches) {
				return;
			}
		}

		if (!config.getValidRegex().matcher(message).matches()) {
			event.setCancelled(true);
			player.sendMessage(config.getInvalidMessage());
		}
	}

}
