package net.catharos.lib.event.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * Represents a cancellable event.
 * 
 * @version 1.0
 */
public abstract class CancellableEvent extends Event implements Cancellable {
	
	/** Holds information wether or not the event has been cancelled */
	private boolean cancelled = false;
	
	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
}
