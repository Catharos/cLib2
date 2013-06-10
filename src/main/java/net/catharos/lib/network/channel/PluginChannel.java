package net.catharos.lib.network.channel;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Represents a plugin channel.
 * 
 * Helps with client to server communication (and vise-versa).
 * 
 * @version 1.0
 */
public abstract class PluginChannel<T extends ChannelPacket> {
	
	/** The maximum channel name size */
	public final static int MAX_NAME_SIZE = 16;
	
	/** The associated channel manager */
	private ChannelManager manager;
	
	/**
	 * Sets the {@link ChannelManager} for this channel.
	 * 
	 * @param manager The new channel manager.
	 */
	public void setManager(ChannelManager manager) {
		this.manager = manager;
	}
	
	/**
	 * Returns the associated {@link ChannelManager}.
	 * 
	 * @return The channel manager.
	 */
	public ChannelManager getManager() {
		return this.manager;
	}
	
	/**
	 * Validates this plugin channel.
	 * An exception will be thrown when the channel is invalid.
	 * 
	 * @throws InvalidChannelException
	 */
	public void validate() throws InvalidChannelException {
		// Check channel name length
		if(getName().length() > MAX_NAME_SIZE) {
			throw new InvalidChannelException();
		}
	}
	
	/**
	 * Sends a message to the reciever's client.
	 * 
	 * @param reciever The recieving player
	 */
	public void sendMessage(Player reciever) {
		// Check if manager is present
		if(manager == null) {
			String msg = "No channel manager set for channel '" + getName() + "'!";
			Bukkit.getServer().getLogger().warning(msg);
			return;
		}
		
		// Send message
		manager.sendMessage(reciever, this);
	}
	
	/**
	 * Returns the name of this channel.<br />
	 * <b>Remember: The maximum number of characters for a name is 16!</b>
	 * 
	 * @return The channel name
	 */
	public abstract String getName();
	
	/**
	 * Creates a new packet for the specific player.
	 * 
	 * @return The newly created packet.
	 */
	public abstract T createPacket(Player player);
	
	/**
	 * Used whenever this channel is being used by recieving the
	 * dedicated {@link ChannelPacket}.
	 * 
	 * @param incoming The recieved packet
	 */
	public abstract void listen(Player sender, T packet);
	
}
