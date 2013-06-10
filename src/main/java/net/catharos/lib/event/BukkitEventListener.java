package net.catharos.lib.event;

import net.catharos.lib.event.events.EntityKilledEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * Listens for bukkit events and calls custom actions.
 * 
 * @version 1.0
 */
public class BukkitEventListener implements Listener {
	
	@EventHandler
	public void entityDeath(EntityDeathEvent event) {
		Player killer = EventHelper.getKiller(event);
		
		if(killer != null) {
			Events.callEvent(new EntityKilledEvent(killer, event));
		}
	}
	
}
