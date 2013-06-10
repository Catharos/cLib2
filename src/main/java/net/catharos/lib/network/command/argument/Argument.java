package net.catharos.lib.network.command.argument;

import org.bukkit.ChatColor;

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
		INTEGER = new Argument<Integer>() {
			public Integer parse(String argument) {
				return Integer.parseInt(argument, 0);
			}
		};
	
		DECIMAL = new Argument<Double>() {
			public Double parse(String argument) {
				try {
					double number = Double.parseDouble(argument);
					return number;

				} catch(NumberFormatException ignore) {
					return 0D;
				}
			}
		};
	
		COLOR = new Argument<ChatColor>() {
			public ChatColor parse(String argument) {
				return ChatColor.valueOf(argument.toUpperCase());
			}
		};
	}
	
	/**
	 * Parses the given argument.
	 * 
	 * @param argument The raw argument String
	 * @return The parsed object
	 */
	public abstract T parse(String argument);
	
}
