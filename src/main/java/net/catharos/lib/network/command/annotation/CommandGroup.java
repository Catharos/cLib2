package net.catharos.lib.network.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A command group 
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface CommandGroup {
	
	/** The group name (only one word!) */
	public String name();
	
	/** The group description */
	public String description() default "";
	
}
