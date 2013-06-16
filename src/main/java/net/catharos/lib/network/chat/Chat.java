package net.catharos.lib.network.chat;

import java.util.Locale;
import java.util.regex.Pattern;
import net.catharos.lib.network.chat.locale.Translator;
import org.bukkit.ChatColor;
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
		
		reciever.sendMessage(getTranslation(locale, key, args));
	}
	
	public static void info(CommandSender reciever, String key, Object... args) {
		Locale locale = translator.getLocale(reciever);
		String message = getPrefixedMessage(locale, getTranslation(locale, key, args), ChatColor.BLUE, "general.info");
		
		reciever.sendMessage(message);
	}
	
	public static void warn(CommandSender reciever, String key, Object... args) {
		Locale locale = translator.getLocale(reciever);
		String message = getPrefixedMessage(locale, getTranslation(locale, key, args), ChatColor.GOLD, "general.warning");
		
		reciever.sendMessage(message);
	}
	
	public static void error(CommandSender reciever, String key, Object... args) {
		Locale locale = translator.getLocale(reciever);
		String message = getPrefixedMessage(locale, getTranslation(locale, key, args), ChatColor.DARK_RED, "general.error");
		
		reciever.sendMessage(message);
	}
	
	private static String getPrefixedMessage(Locale locale, String msg, ChatColor color, String key) {
		String prefix = color + "[" + getTranslation(locale, key) + "] ";
		
		return prefix + ChatColor.RESET + msg;
	}
	
	private static String getTranslation(Locale locale, String key, Object... args) {
		return translator.translate(locale, key, args);
	}
	
}
