package net.catharos.lib.network;

/**
 *
 * @version 1.0
 */
public interface CommandSender {
	
	public void sendMessage(final String message);
	
	public boolean hasPermission(final String permission);
	
}
