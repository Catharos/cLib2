package net.catharos.lib.event;

import net.catharos.lib.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

/**
 * Event helper class.
 * 
 * Helps with often used event functions (call, register).
 * 
 * @version 1.0
 */
public class Events {
	
	/**
	 * Calls a bukkit event and returns it afterwards.
	 * 
	 * @param event The event to call
	 * @return The called event
	 */
	public static <T extends Event> T callEvent(T event) {
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		return event;
	}
	
	/**
	 * Registers an event listener.
	 * 
	 * @param listener The event listener class
	 * @param plugin The plugin the events being registered to
	 */
	public static void registerListener(Listener listener, Plugin plugin) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
	}
	
}
