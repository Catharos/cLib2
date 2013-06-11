package net.catharos.lib.network.command;

import java.util.Map;

import org.bukkit.command.CommandSender;

/**
 * A temporarly created command information object.
 * 
 * Allows getting the command information, flags and its sender.
 * 
 * @version 1.0
 */
public final class Command {
	
	/** Holds all information about the command */
	private final CommandInformation info;
	
	/** The command sender */
	private final CommandSender sender;
	
	/** A key-value map holding all command flags */
	private final Map<String, String> flags;
	
	/**
	 * Creates a new command, ready to be executed.
	 * 
	 * @param info The general command information
	 * @param sender The sender who executes this command
	 * @param flags A map with all command flags (can be empty)
	 */
	protected Command(CommandInformation info, CommandSender sender, Map<String, String> flags) {
		this.info = info;
		this.sender = sender;
		this.flags = flags;
	}
	
	/**
	 * Returns the sender who executed this command.
	 * 
	 * @return The {@link CommandSender}
	 */
	public CommandSender getSender() {
		return sender;
	}
	
	/**
	 * Gets the raw flag content.
	 * 
	 * @param flag The flag name
	 * @return The raw flag content string
	 */
	public String getFlag(String flag) {
		return flags.get(flag.toLowerCase());
	}
	
	/**
	 * Gets a flag and parses it using the argument parser.
	 * 
	 * @param flag The flag name
	 * @param type The argument type used for parsing
	 * @return The parsed flag
	 */
	public <T> T getFlag(String flag, Argument<T> type) {
		return type.parse(getFlag(flag));
	}
	
	/**
	 * Returns the command information holding all the details.
	 * 
	 * @return The {@link CommandInformation} for this command
	 */
	public CommandInformation getInformation() {
		return info;
	}
	
}
