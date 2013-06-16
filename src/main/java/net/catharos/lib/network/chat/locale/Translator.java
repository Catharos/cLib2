package net.catharos.lib.network.chat.locale;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import net.catharos.lib.plugin.Plugin;
import org.bukkit.command.CommandSender;

/**
 *
 * @version 1.0
 */
public class Translator {
	
	/** The Translator instance (singleton pattern) */
	private static Translator instance;
	
	/** Default translator locale */
	private Locale defaultLocale;
	
	/** Locale to language storage */
	private Map<Locale, Language> languages;
	
	/** Stores the language per player */
	private Map<CommandSender, Locale> players;
	
	
	private Translator() {
		languages = new HashMap<Locale, Language>();
	}
	
	
	/* -------- Static functions -------- */
	
	public static Translator getInstance() {
		if(instance == null) {
			instance = new Translator();
		}
		
		return instance;
	}
	
	public String translate(String key, Object... args) {
		return translate(getInstance().getDefaultLocale(), key, args);
	}
	
	public String translate(Locale locale, String key, Object... args) {
		Language language = getInstance().getLanguage(locale);
		
		if(language != null) {
			MessageFormat format = language.getFormat(key);
			
			if(format != null) {
				return (format.format(args, new StringBuffer(), null)).toString();
			}
		}
		
		return "{" + key + "}";
	}
	
	
	/* -------- Public functions -------- */
	
	public Locale getDefaultLocale() {
		return defaultLocale;
	}
	
	public Language getLanguage(Locale locale) {
		return languages.get(locale);
	}
	
	public void setLanguage(CommandSender sender, Locale locale) {
		players.put(sender, locale);
	}
	
	public Locale getLocale(CommandSender sender) {
		Locale locale = players.get(sender);
		
		if(locale == null) {
			return getDefaultLocale();
		}
		
		return locale;
	}
	
	/**
	 * Attemps to add all language files found within the plugin.jar.
	 * Valid language files end with ".lang.properties" and start with a two letter locale
	 * identifier.
	 * 
	 * @param plugin The plugin to add the translations from
	 * @throws IOException
	 */
	public void addTranslations(Plugin plugin) throws IOException {
		URL jar = plugin.getClass().getProtectionDomain().getCodeSource().getLocation();
		
		ZipInputStream zip = new ZipInputStream(jar.openStream());
		ZipEntry entry;
		
		while( ( entry = zip.getNextEntry() ) != null ) {
			if(!entry.isDirectory() || !entry.getName().endsWith(".lang.properties")) continue;
			
			// Found a language file
			Language lang = getLanguage(new Locale(entry.getName().substring(0, 2)));
			if(lang != null) {
				Properties props = new Properties();
				props.load(plugin.getResource(entry.getName()));
				
				// Add translations
				for(Map.Entry<Object, Object> line : props.entrySet()) {
					lang.addTranslation((String) line.getKey(), (String) line.getValue());
				}
			}
		}
	}
	
}
