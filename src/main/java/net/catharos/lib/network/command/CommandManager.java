package net.catharos.lib.network.command;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.catharos.lib.network.CommandSender;
import net.catharos.lib.network.ServerCommandSender;
import net.catharos.lib.plugin.Plugin;

/**
 *
 * @version 1.0
 */
public class CommandManager {
	
	protected final Map<CommandInformation, Method> commands;
	
	protected final Map<Method, Object> methods;
	
	protected final Map<Plugin, List<CommandInformation>> plugins;
	
	protected final ServerCommandSender serverCommandSender;
	
	
	public CommandManager() {
		commands = new HashMap<CommandInformation, Method>();
		methods = new HashMap<Method, Object>();
		plugins = new HashMap<Plugin, List<CommandInformation>>();
		
		serverCommandSender = new ServerCommandSender();
	}
	
	
	public void registerCommands(Object object) {
		// TODO :D
	}
	
	public void execute(CommandSender sender, String command) {
		List<String> args = new ArrayList<String>();
		Map<String, String> flags = new HashMap<String, String>();
		
		for(int i = 0; i < command.length(); i++) {
			char c = command.charAt(i);
			
			// TODO Iterate and get args and flags
		}
		
		// TODO Get correct command + execute
	}
	
	public ServerCommandSender getServerCommandSender() {
		return serverCommandSender;
	}
	
}
