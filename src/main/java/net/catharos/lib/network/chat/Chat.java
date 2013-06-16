package net.catharos.lib.network.chat;

import java.util.Locale;
import java.util.regex.Pattern;
import net.catharos.lib.network.chat.locale.Translator;
import org.bukkit.command.CommandSender;

/**
 *
 * @version 1.0
 */
public class Chat {
	private final static Pattern COLOR_PATTERN = Pattern.compile( "&([0-9a-fk-or])" );
	
	private final static Translator translator = Translator.getInstance();
	
	public static String colorize( String msg ) {
		if (msg == null) return null;

		return COLOR_PATTERN.matcher( msg ).replaceAll( "\u00a7$1" );
	}
	
	public static void send(CommandSender reciever, String key, Object... args) {
		Locale locale = translator.getLocale(reciever);
		String message = translator.translate(locale, key, args);
		
		reciever.sendMessage(message);
	}
	
}
