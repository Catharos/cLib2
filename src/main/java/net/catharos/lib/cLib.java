package net.catharos.lib;

import net.catharos.lib.network.channel.ChannelManager;
import net.catharos.lib.plugin.Plugin;

/**
 * Main library class.
 * 
 * Manages API instances.
 * 
 * @version 1.0
 */
public final class cLib extends Plugin {
	
	/** The cLib plugin instance */
	private static cLib instance;
	
	/** The channel manager instance */
	private ChannelManager channelManager;
	
	@Override
	public void onLoad() {
		cLib.instance = this;
		
		// Instanciate plugin channels
		channelManager = new ChannelManager(this);
		
		getLogger().info("cLib loaded!");
	}
	
	@Override
	public void onEnable() {
		getLogger().info("cLib enabled!");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("cLib disabled!");
	}
	
	/**
	 * Returns the "native" ChannelManager created and used by cLib itself.
	 * Use this if you don't want to manage the channels yourself.
	 * 
	 * <b>If cLib hasn't been loaded yet, this returns null!</b>
	 * 
	 * @return The ChannelManager instance, if any.
	 */
	public static ChannelManager getChannelManager() {
		return instance.channelManager;
	}
	
}
