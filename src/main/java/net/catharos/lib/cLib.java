package net.catharos.lib;

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
	
	@Override
	public void onLoad() {
		cLib.instance = this;
		
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
	
}
