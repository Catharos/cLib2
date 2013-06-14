package net.catharos.lib.network.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import net.catharos.lib.network.command.annotation.CommandHandler;
import net.catharos.lib.util.interfaces.Nameable;
import net.catharos.lib.util.tree.TreeBranch;
import net.catharos.lib.util.tree.TreeNode;

import org.bukkit.command.CommandSender;

/**
 *
 * @version 1.0
 */
public final class CommandInformation extends TreeNode implements Nameable {

	private final CommandHandler handler;

	private final Method method;

	private final Object object;

	private final Argument[] argTypes;

	protected CommandInformation(CommandHandler handler, Method method, Object object, TreeBranch parent) throws Exception {
		super(parent);
		
		// Call objects
		this.handler = handler;
		this.method = method;
		this.object = object;
		
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
		
		// Create alias list
		for(String alias : handler.aliases()) {
			parent.addNode(alias, this);
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
	
	public String[] getAvailableFlags() {
		return handler.flags();
	}
	
	public String[] getAliases() {
		return handler.aliases();
	}
	
	
	/* -------- Protected functions -------- */
	
	protected boolean execute(CommandSender sender, String[] args, Map<String, String> flags) throws Exception {
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
	
}
