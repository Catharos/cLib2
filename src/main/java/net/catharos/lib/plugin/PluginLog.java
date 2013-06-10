package net.catharos.lib.plugin;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Custom plugin logger.
 *
 * Handles the debug output for plugin.
 *
 * @version 1.0
 */
public class PluginLog extends Logger {

	private final static DebugFormatter formatter = new DebugFormatter();
	private FileHandler handler;

	public PluginLog(Plugin plugin) {
		this(plugin.getName(), plugin.getDataFolder() + File.separator + "debug.log");
	}
	
	public PluginLog(String name, String file) {
		super(name, null);

		try {
			handler = new FileHandler(file, true);
			addHandler(handler);
			setLevel(Level.ALL);
			handler.setFormatter(formatter);
		} catch (Exception ex) {
			System.err.println("Error creating logger '" + name + "': ");
			ex.printStackTrace();
		}
	}
	
	public void close() {
		if (this.handler != null) {
			this.handler.close();
		}
	}

	private static class DebugFormatter extends Formatter {

		private final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

		@Override
		public String format(LogRecord rec) {
			Throwable exception = rec.getThrown();

			StringBuilder out = new StringBuilder();
			out.append(this.date.format(Long.valueOf(rec.getMillis())));

			out.append("[").append(rec.getLevel().getName().toUpperCase()).append("] ");
			out.append(rec.getMessage()).append('\r').append('\n');

			if (exception != null) {
				StringWriter writer = new StringWriter();
				exception.printStackTrace(new PrintWriter(writer));

				out.append(writer);
			}

			return out.toString();
		}
	}
	
}
