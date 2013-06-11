package net.catharos.lib.network.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a command flag.
 * 
 * Example: /mycommand --flag "content"
 * Example: /setwarp "name" --location "world,x,y,z"
 * 
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Flag {
	
	/** The flag name */
	public String name();
	
	/** An optional flag description / usage */
	public String description() default "";
	
	/** The flag type, as class (Has to be equal to the argument return type */
	public Class type();
	
}
