package net.catharos.lib.network.channel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import net.catharos.lib.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * Plugin channel manager.
 * 
 * Manages incoming and outgoing plugin channel messages.
 * 
 * @version 1.0
 */
public class ChannelManager implements PluginMessageListener {
	
	protected final Map<String, PluginChannel> channels;
	protected final Plugin plugin;
	
	public ChannelManager(Plugin plugin) {
		this.plugin = plugin;
		this.channels = new HashMap<String, PluginChannel>();
	}
	
	public Collection<PluginChannel> getRegisteredChannels() {
		return channels.values();
	}
	
	public void registerChannel(PluginChannel channel) throws InvalidChannelException {
		// Validate channel
		channel.validate();
		
		// Set channel manager
		channel.setManager(this);
		
		// Add channel to list
		channels.put(channel.getName(), channel);
		
		// Add channel to bukkit
		Bukkit.getMessenger().registerIncomingPluginChannel(plugin, channel.getName(), this);
		Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, channel.getName());
	}
	
	public void sendMessage(Player player, PluginChannel channel) {
		try {
			// Create packet and output stream
			ByteArrayOutputStream data = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(data);
			
			// Write to stream
			channel.send(player, out);
			
			// Send message
			player.sendPluginMessage(plugin, channel.getName(), data.toByteArray());
			
			// Close stream
			out.close();
			
		} catch (IOException ex) {
			String msg = "Could not send plugin channel message to player " + player.getName();
			Bukkit.getServer().getLogger().log(Level.SEVERE, msg, ex);
		}
	}
	
	public void onPluginMessageReceived(String string, Player player, byte[] bytes) {
		// Iterate through all registered channels
		for(Map.Entry<String, PluginChannel> entry : channels.entrySet()) {
			if(!entry.getKey().equals(string)) continue;
			
			try {
				PluginChannel channel = entry.getValue();
				
				// Create data input stream
				DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
				
				// Channel listens to read input
				channel.listen(player, in);
				
				// Close stream again
				in.close();
				
			} catch (IOException ex) {
				String msg = "Could not read message in channel " + string;
				Bukkit.getServer().getLogger().log(Level.SEVERE, msg, ex);
			}
		}
	}
	
}
