package net.catharos.lib.network;

import org.bukkit.entity.Player;

/**
 * Represents a client-side command sender.
 * 
 * @version 1.0
 */
public class ClientCommandSender implements CommandSender {

	/** The associated player */
	private final Player player;
	
	/**
	 * Creates a new CommandSender for the specified player
	 * 
	 * @param client The player
	 */
	public ClientCommandSender(Player client) {
		player = client;
	}
	
	public void sendMessage(String message) {
		player.sendMessage(message);
	}

	public boolean hasPermission(String permission) {
		return false;
	}
	
}
