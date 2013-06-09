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
	private static cLib instance;
	
	@Override
	public void onLoad() {
		cLib.instance = this;
		
		getLogger().info("Commands hooked and loaded!");
	}
	
	@Override
	public void onEnable() {
		
	}
	
}
