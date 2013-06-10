package net.catharos.lib.network.command;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents available Arguments
 * 
 * @version 1.0
 */
public abstract class Argument<T> {
	
	/** Full number, no comma values */
	public final static Argument INTEGER;
	
	/** Big number, including comma values */
	public final static Argument DECIMAL;
	
	/** Color code */
	public final static Argument COLOR;
	
	
	static {
		// Create registry
		registered = new ArrayList<Argument>();

		// Create basic argument types
		INTEGER = new Argument<Integer>(Integer.class) {
			public Integer parse(String argument) {
				return Integer.parseInt(argument, 0);
			}
		}.register();
	
		DECIMAL = new Argument<Double>(Double.class) {
			public Double parse(String argument) {
				try {
					double number = Double.parseDouble(argument);
					return number;

				} catch(NumberFormatException ignore) {
					return 0D;
				}
			}
		}.register();
	
		COLOR = new Argument<ChatColor>(ChatColor.class) {
			public ChatColor parse(String argument) {
				return ChatColor.valueOf(argument.toUpperCase());
			}
		}.register();
	}

	/** Holds all available argument types */
	protected static List<Argument> registered;

	/** Holds the class type information */
	protected Class<T> clazz;


	public Argument(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Registers the argument to be available for commands.
	 */
	public Argument register() {
		registered.add(this);

		return this;
	}

	/**
	 * Returns the returned type of this argument when using {@link #parse(String)}.
	 *
	 * @return The class of the object being returned on argument parsing
	 */
	public Class<T> getType() {
		return clazz;
	}
	
	/**
	 * Parses the given argument.
	 * 
	 * @param argument The raw argument String
	 * @return The parsed object
	 */
	public abstract T parse(String argument);
	
}
