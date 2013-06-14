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
import net.catharos.lib.util.tree.TreeBranch;
import net.catharos.lib.util.tree.TreeNode;

import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

/**
 *
 * @version 1.0
 */
public class CommandManager extends TreeBranch {

	/** Map of all commands associated to plugins */
	protected final Map<Plugin, Set<CommandInformation>> plugins;
	
	
	public CommandManager() {
		super("CommandManager", null); // The manager doesn't have a parent
		
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
				String[] split = command.getName().split(" ");
				TreeBranch branch = createBranch(Arrays.copyOfRange(split, 0, split.length - 2));
				branch.addNode(split[split.length - 1], command);
				
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
		TreeNode node = getNode(identifier.toLowerCase().split(" "));
		
		if(node instanceof CommandInformation) {
			return (CommandInformation) node;
		}
		
		return null;
	}
	
	public void execute(CommandSender sender, String command) {
		String[] args = parseArguments(command);
		Map<String, String> flags = parseFlags(args);

		CommandInformation commandInfo = null;
		TreeBranch node = this;
		
		for(String arg : args) {
			TreeNode n = node.getNode(arg);
			if((n != null) && (n instanceof CommandInformation)) {
				commandInfo = (CommandInformation) n;
				break;
			}
			
			node = (TreeBranch) n;
		}

		// When command could be found, try to execute
		if(commandInfo != null) {
			boolean showHelp = args[args.length - 1].equals("?");

			if(!showHelp) try {
				// Throw error when flag does not exist
				for(String flag : flags.keySet()) {
					if(Arrays.binarySearch(commandInfo.getAvailableFlags(), flag) < 0) {
						throw new Exception("Invalid flag: " + flag);
					}
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
				flags.put(flag.substring(2).toLowerCase(), args[i + 1]);
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
		// bukkit.setAliases(Arrays.asList(command.getAliases()));
		
		// Add to bukkit
		// TODO: Find simple command map and register there
	}
	
}
