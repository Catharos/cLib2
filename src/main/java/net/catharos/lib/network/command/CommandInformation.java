package net.catharos.lib.network.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.catharos.lib.network.command.annotation.CommandHandler;
import net.catharos.lib.network.command.annotation.Flag;

import org.bukkit.command.CommandSender;

/**
 *
 * @version 1.0
 */
public final class CommandInformation {

	private final CommandHandler handler;

	private final Method method;

	private final Object object;

	private final CommandInformation parent;

	private final List<CommandInformation> children;
	
	private final List<String> aliases;

	private final Argument[] argTypes;
	
	private final CommandFlag[] flags;

	protected CommandInformation(CommandHandler handler, Method method, Object object, CommandInformation parent) throws Exception {
		// Call objects
		this.handler = handler;
		this.method = method;
		this.object = object;
		
		// Create alias list
		this.aliases = new ArrayList<String>();
		this.aliases.addAll(Arrays.asList(handler.aliases()));
		
		// Command flags
		this.flags = new CommandFlag[handler.flags().length];
		for(int i = 0; i < flags.length; i++) {
			this.flags[i] = new CommandFlag(handler.flags()[i]);
		}
		
		// Subcommand info
		this.parent = parent;
		this.children = new ArrayList<CommandInformation>();
		
		// Build argument list
		Type[] types = method.getParameterTypes();
		this.argTypes = new Argument[types.length];
		
		for(int i = 0; i < argTypes.length; i++) {
			Type type = types[i];
			Argument arg = Argument.getArgumentByType(type.getClass());

			if(arg == null) {
				throw new Exception("Invalid command argument type: " + type.getClass().getSimpleName());
			}

			argTypes[i] = arg;
		}
	}
	
	
	/* -------- Public functions -------- */
	
	public String getName() {
		return handler.name();
	}
	
	public String getDescription() {
		return handler.description();
	}
	
	public String getUsage() {
		return handler.usage();
	}
	
	public String getPermission() {
		return handler.permission();
	}
	
	public int getRequiredArguments() {
		return handler.minArgs();
	}
	
	public boolean isListed() {
		return handler.listed();
	}

	public CommandInformation getParent() {
		return parent;
	}
	
	public List<String> getAliases() {
		return aliases;
	}
	
	public Flag getFlag(String name) {
		CommandFlag cmdFlag = getCommandFlag(name);
		
		if(cmdFlag != null) {
			return cmdFlag.getFlag();
		}
		
		return null;
	}
	
	
	/* -------- Protected functions -------- */
	
	protected boolean execute(CommandSender sender, String[] args, Map<CommandFlag, String> flags) throws Exception {
		Object[] call = new Object[args.length + 1];
		call[0] = new Command(this, sender, flags);

		// Parse arguments
		for(int i = 0; i < argTypes.length; i++) {
			call[i + 1] = argTypes[i].parse(args[i]);
		}
		
		try {
			// Execute command
			Object obj = method.invoke(object, call);
			
			if(method.getReturnType() == Boolean.class) {
				return Boolean.class.cast(obj);
			}
			
		} catch (IllegalAccessException ex) {
			throw new Exception("Could not access command method!");
			
		} catch (IllegalArgumentException ex) {
			throw new Exception("Invalid command arguments!");
			
		} catch (InvocationTargetException ex) {
			throw new Exception("Could not invoke command method!");
		}
		
		return true;
	}

	protected void addChildren(CommandInformation child) {
		children.add(child);
	}
	
	protected CommandFlag getCommandFlag(String name) {
		for(CommandFlag flag : flags) {
			if(flag.getFlag().name().equalsIgnoreCase(name)) return flag;
		}
		
		return null;
	}
	
}
