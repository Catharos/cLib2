package net.catharos.lib.network.command;

import java.util.Map;
import net.catharos.lib.network.CommandSender;
import net.catharos.lib.network.command.argument.Argument;

/**
 * A temporarly created command information object.
 * 
 * Allows getting flags and it's sender.
 * 
 * @version 1.0
 */
public final class Command {
	
	private final CommandInformation info;
	
	private final CommandSender sender;
	
	private final Map<String, String> flags;
	
	
	public Command(CommandInformation info, CommandSender sender, Map<String, String> flags) {
		this.info = info;
		this.sender = sender;
		this.flags = flags;
	}
	
	public CommandSender getSender() {
		return sender;
	}
	
	public String getFlag(String flag) {
		return flags.get(flag);
	}
	
	public <T> T getFlagAs(String flag, Argument<T> type) {
		return type.parse(getFlag(flag));
	}
	
	public CommandInformation getInformation() {
		return info;
	}
	
}
