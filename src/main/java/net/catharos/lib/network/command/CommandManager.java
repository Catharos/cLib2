package net.catharos.lib.network.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.catharos.lib.plugin.Plugin;
import net.catharos.lib.util.ArrayUtil;

import org.bukkit.command.CommandSender;

/**
 *
 * @version 1.0
 */
public class CommandManager {

	/** Map of all stored commands (identifier -> command) */
	protected final Map<String, CommandInformation> commands;

	/** Map of all commands associated to plugins */
	protected final Map<Plugin, List<CommandInformation>> plugins;
	
	
	public CommandManager() {
		commands = new HashMap<String, CommandInformation>();
		plugins = new HashMap<Plugin, List<CommandInformation>>();
	}
	
	
	public void registerCommands(Object object) {
		// TODO :D
	}

	public CommandInformation getCommand(String identifier) {
		return commands.get(identifier);
	}
	
	public void execute(CommandSender sender, String command) {
		String[] args = parseArguments(command);
		Map<String, String> flags = parseFlags(args);

		String identifier;
		CommandInformation commandInfo;

		for(int left = args.length; left > 0; left--) {
			// Build command string
			identifier = ArrayUtil.implode(Arrays.copyOfRange(args, 0, left));

			commandInfo = getCommand(identifier);

			// Execute command, if found
			if(commandInfo != null) {
				commandInfo.execute(sender, args, flags);

				return;
			}
		}

		// TODO Show error message
	}


	/* -------- Private methods -------- */

	private String[] parseArguments(String command) {
		List<String> args = new ArrayList<String>();

		StringBuilder current = new StringBuilder();

		boolean parantheses = false;
		boolean next = false;

		for(int i = 0; i < command.length(); i++) {
			char c = command.charAt(i);

			// Check for single-word arguments
			if(c == ' ' && !parantheses) next = true;

			// Check for paranthesed arguments
			if(c == '"') {
				if(parantheses) next = true;

				parantheses = !parantheses;
			}

			// Save argument, create builder for next
			if(next) {
				String arg = current.toString().trim();

				// We don't need empty arguments!
				if(arg.length() > 0) {
					args.add(arg);
				}

				current = new StringBuilder();
				next = false;

				continue;
			}

			// Append to builder
			current.append(c);
		}

		return args.toArray(new String[args.size()]);
	}

	private Map<String, String> parseFlags(String[] args) {
		Map<String, String> flags = new HashMap<String, String>();

		for(int i = 0; i < args.length; i++) {
			String flag = args[i];

			// All flags start with "--"
			if(flag.startsWith("--") && (i + 1) < args.length) {
				flags.put(flag.substring(2), args[i + 1]);
			}
		}

		return flags;
	}
	
}
