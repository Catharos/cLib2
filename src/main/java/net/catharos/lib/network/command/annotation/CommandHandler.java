package net.catharos.lib.network.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a callable commnd.
 * 
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface CommandHandler {
	
	/** The command name (only one word!) */
	public String name();
	
	/** The command description */
	public String description() default "";
	
	/** The command (example) usage */
	public String usage() default "";
	
	/** Needed permission to execute the command */
	public String permission() default "";
	
	/** The command's group */
	public String group() default "";
	
	/** Aliases for this command */
	public String[] aliases() default {};
	
	/** List of all command flags */
	public String[] flags() default {};
	
	/** Number of required arguments */
	public int minArgs() default 0;
	
	/** True if command should be displayed in any help pages / listings */
	public boolean listed() default true;
	
}
