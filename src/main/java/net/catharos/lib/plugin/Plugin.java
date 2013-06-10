package net.catharos.lib.plugin;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Server plugin class.
 * 
 * Use this class as your main plugin class.
 * 
 * @version 1.0
 */
public abstract class Plugin extends JavaPlugin {
	
	/** Custom plugin log file */
	private PluginLog log;
	
	/**
	 * Creates a custom logger for the current plugin.
	 * Allows better plugin debugging.
	 * 
	 * @return The plugin's {@link Logger}
	 */
	public Logger getLog() {
		if(log == null) {
			log = new PluginLog(this);
		}
		
		return log;
	}
	
}
