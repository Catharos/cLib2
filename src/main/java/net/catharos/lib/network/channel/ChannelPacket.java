package net.catharos.lib.network.channel;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Represents a channel packet.
 * 
 * Used with {@link PluginChannel PluginChannels}.
 * 
 * @version 1.0
 */
public interface ChannelPacket {
	
	/**
	 * Reads the data from the input message.
	 * 
	 * @param message The ingoing message (byte array).
	 * @throws IOException
	 */
	public void read(DataInput message) throws IOException;
	
	/**
	 * Writes data to the output message.
	 * 
	 * @param message The outgoing message (byte array).
	 * @throws IOException
	 */
	public void write(DataOutput message) throws IOException;
	
}
