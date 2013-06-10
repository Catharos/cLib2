package net.catharos.lib.network.channel;

/**
 * Thrown when a {@link PluginChannel} seems invalid.
 * 
 * @version 1.0
 */
public class InvalidChannelException extends Exception {
	
	public InvalidChannelException() {
		super("Channel name exceeds maximum length!");
	}
	
}
