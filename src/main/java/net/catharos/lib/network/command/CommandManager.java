package net.catharos.lib.network.command;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.catharos.lib.network.command.annotation.CommandHandler;
import net.catharos.lib.plugin.Plugin;
import net.catharos.lib.util.ArrayUtil;

import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

/**
 *
 * @version 1.0
 */
public class CommandManager {

	/** List of all stored commands */
	protected final Set<CommandInformation> commands;

	/** Map of all commands associated to plugins */
	protected final Map<Plugin, Set<CommandInformation>> plugins;
	
	
	public CommandManager() {
		commands = new HashSet<CommandInformation>();
		plugins = new HashMap<Plugin, Set<CommandInformation>>();
	}
	
	
	public <T> T registerCommands(T object) {
		return registerCommands(object, null);
	}
	
	public <T> T registerCommands(T object, Plugin plugin) {
		// TODO: Check for grouping
		// CommandGroup group = object.getClass().getAnnotation(CommandGroup.class);
		
		for(Method method : object.getClass().getMethods()) {
			// Get handler annotation
			CommandHandler handler = method.getAnnotation(CommandHandler.class);
			if(handler == null) continue;
			
			try {
				// Check method parameters
				Class<?>[] params = method.getParameterTypes();
				
				if(params.length < 1 || (params[0] != Command.class)) {
					throw new Exception("Method " + method.getName() + " has to have the command class as first argument!");
				}
				
				// Create command information
				CommandInformation command = new CommandInformation(handler, method, object, null);
				commands.add(command);
				
				// Add command to bukkit, if needed
				// TODO: This needs some more work as well ...
				if(!command.getName().contains(" ")) {
					addBukkitCommand(command, plugin);
				}
				
				// Add to plugin
				if(plugin != null) {
					Set<CommandInformation> set = plugins.get(plugin);
					
					if(set == null) {
						set = new HashSet<CommandInformation>();
						plugins.put(plugin, set);
					}
					
					set.add(command);
				}
				
			} catch(Exception ex) {
				// TODO explain error properly
			}
		}
		
		return object;
	}

	public CommandInformation getCommand(String identifier) {
		for(CommandInformation command : commands) {
			// Check command name
			if(command.getName().equalsIgnoreCase(identifier)) {
				return command;
			}
			
			// Check command aliases
			if(command.getAliases().contains(identifier)) {
				return command;
			}
		}
		
		return null;
	}
	
	public void execute(CommandSender sender, String command) {
		String[] args = parseArguments(command);
		Map<String, String> flagStrings = parseFlags(args);

		String identifier;
		CommandInformation commandInfo;

		for(int left = args.length; left > 0; left--) {
			// Build command string
			identifier = ArrayUtil.implode(Arrays.copyOfRange(args, 0, left));

			commandInfo = getCommand(identifier);

			// Command found
			if(commandInfo != null) {
				boolean showHelp = args[args.length - 1].equals("?");
				
				if(!showHelp) try {
					// Get correct flags
					Map<CommandFlag, String> flags = new HashMap<CommandFlag, String>();
					
					for(String flag : flagStrings.keySet()) {
						if(commandInfo.getFlag(flag) != null) continue;
						
						throw new Exception("Invalid flag: " + flag);
					}
					
					// Execute command
					showHelp = commandInfo.execute(sender, args, flags);
					
				} catch (Exception ex) {
					// TODO Show error message
				}
				
				// Show help message
				if(showHelp) {
					// TODO :D
				}

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
	
	private void addBukkitCommand(CommandInformation command, Plugin plugin) throws Exception {
		// Create bukkit plugin command
		Constructor constructor = PluginCommand.class.getConstructor(String.class, org.bukkit.plugin.Plugin.class);
		constructor.setAccessible(true);
		
		PluginCommand bukkit = (PluginCommand) constructor.newInstance(command.getName(), plugin);
		
		// Set general command information
		bukkit.setDescription(command.getDescription());
		bukkit.setUsage(command.getUsage());
		bukkit.setPermission(command.getPermission());
		
		// Add command aliases
		bukkit.setAliases(command.getAliases());
		
		// Add to bukkit
		// TODO: Find simple command map and register there
	}
	
}
