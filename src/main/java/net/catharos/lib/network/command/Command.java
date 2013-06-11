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
	private final Map<CommandFlag, String> flags;
	
	/**
	 * Creates a new command, ready to be executed.
	 * 
	 * @param info The general command information
	 * @param sender The sender who executes this command
	 * @param flags A map with all command flags (can be empty)
	 */
	protected Command(CommandInformation info, CommandSender sender, Map<CommandFlag, String> flags) {
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
	 * Gets a flag and parses it using the argument parser.
	 * 
	 * @param flag The flag name
	 * @return The parsed flag content object, or null
	 */
	public Object getFlag(String flag) {
		for(Map.Entry<CommandFlag, String> search : flags.entrySet()) {
			if(search.getKey().getFlag().name().equalsIgnoreCase(flag)) {
				return search.getKey().getArgument().parse(search.getValue());
			}
		}
		
		return null;
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
