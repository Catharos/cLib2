package net.catharos.lib.event.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * Called whenever an entity has been killed by a player.
 * 
 * @version 1.0
 */
public class EntityKilledEvent extends CancellableEvent {
	private static final HandlerList handlers = new HandlerList();

	/** The "parent" event */
	private final EntityDeathEvent event;
	
	/** The involved killer */
	private final Player killer;
	
	/**
	 * Creates a new event.
	 * 
	 * @param killer The involved killer
	 * @param parent The {@link EntityDeathEvent} triggered this event
	 */
	public EntityKilledEvent(Player killer, EntityDeathEvent parent) {
		this.event = parent;
		this.killer = killer;
	}
	
	/**
	 * Returns the killed entity.
	 * 
	 * @return The killed entity
	 */
	public LivingEntity getEntity() {
		return event.getEntity();
	}
	
	/**
	 * Returns the killer of the killed entity
	 * 
	 * @return The {@link Player} object of the killer
	 */
	public Player getKiller() {
		return killer;
	}
	
	/**
	 * Returns the {@link EntityDeathEvent} that triggered this event.
	 * 
	 * @return The parent event
	 */
	public EntityDeathEvent getDeathEvent() {
		return event;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
}
