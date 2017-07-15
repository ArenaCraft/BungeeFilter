package it.arenacraft.bungee.filter.commands;

import it.arenacraft.bungee.filter.BungeeFilter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class BungeeFilterCommand extends Command {

	private final BungeeFilter plugin;
	
	public BungeeFilterCommand(BungeeFilter plugin) {
		super("bungeefilter", "bungeefilter.admin", "bf", "bfilter");
		
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length <= 0) {
			sender.sendMessage(new TextComponent(ChatColor.GRAY + "Valid arguments: " + ChatColor.GREEN + "reload"));
			return;
		}

		String subCommand = args[0].toLowerCase();

		if (subCommand.equals("reload")) {
			plugin.load();
			sender.sendMessage(new TextComponent(ChatColor.GREEN + "BungeeFilter reloaded"));
			return;
		}
		
		sender.sendMessage(new TextComponent(ChatColor.GRAY + "Invalid argument: " + ChatColor.RED + subCommand));
	}

}