package net.catharos.lib.network.chat.locale;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @version 1.0
 */
public class Language {
	
	/** The language locale */
	private final Locale locale;
	
	/** The language key storage */
	private final Map<String, MessageFormat> strings;
	
	protected Language(Locale locale) {
		this.locale = locale;
		this.strings = new HashMap<String, MessageFormat>();
	}
	
	/**
	 * Adds a translation to this language
	 * 
	 * @param key The language key
	 * @param translation The translation
	 */
	public void addTranslation(String key, String translation) {
		strings.put(key, new MessageFormat(translation, locale));
	}
	
	/**
	 * Returns the stored MessageFormat based on the message
	 * key indentifier.
	 * 
	 * @param key The message identifier
	 * @return The stored {@link MessageFormat}
	 */
	public MessageFormat getFormat(String key) {
		return strings.get(key);
	}
	
}
