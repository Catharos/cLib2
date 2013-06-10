package net.catharos.lib.plugin;

import java.util.logging.Logger;
import net.catharos.lib.cLib;
import net.catharos.lib.network.ClientCommandSender;
import net.catharos.lib.network.CommandSender;
import net.catharos.lib.util.Arrays;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
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
	
	@Override
	public boolean onCommand(org.bukkit.command.CommandSender cs, Command cmd, String label, String[] args) {
		CommandSender sender;
		
		// Get correct command sender
		if(cs instanceof Player) {
			sender = new ClientCommandSender((Player) cs);
		} else {
			sender = cLib.getCommandManager().getServerCommandSender();
		}
		
		// Execute command
		cLib.getCommandManager().execute(sender, cmd.getName() + " " + Arrays.implode(args));
		return true;
	}
	
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
