package net.catharos.lib.plugin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.catharos.lib.cLib;
import net.catharos.lib.network.chat.locale.Translator;
import net.catharos.lib.util.ArrayUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Server plugin class.
 * 
 * Use this class as your main plugin class.
 * 
 * @version 1.0
 */
public abstract class Plugin extends JavaPlugin {
	
	/** The translator instance */
	private final Translator translator = Translator.getInstance();
	
	/** Custom plugin log file */
	private PluginLog log;
	
	@Override
	public void onLoad() {
		try {
			// Add translations
			translator.addTranslations(this);
			
		} catch (IOException ex) {
			getLogger().log(Level.WARNING, "Could not add plugin translations", ex);
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Execute command
		cLib.getCommandManager().execute(sender, cmd.getName() + " " + ArrayUtil.implode(args));
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
