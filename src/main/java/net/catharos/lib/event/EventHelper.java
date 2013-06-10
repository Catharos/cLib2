package net.catharos.lib.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Tameable;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * Helps with general event activities.
 * 
 * @version 1.0
 */
public class EventHelper {
	
	/**
	 * Returns true if the entity died by a projectile shot.
	 * 
	 * @param event The {@link EntityDeathEvent} to check against
	 * @return True if the entity was shot by a projectile, otherwise false
	 */
	public static boolean wasShotByProjectile(EntityDeathEvent event) {
		// If event is null, do nothing
		if(event == null) {
			return false;
		}
		
		// Get cause
		EntityDamageEvent cause = event.getEntity().getLastDamageCause();
		if (cause instanceof EntityDamageByEntityEvent) {
			Entity damager = ((EntityDamageByEntityEvent) cause).getDamager();
			
			// Check for projectile
			if (damager instanceof Projectile) {
				Projectile projectile = (Projectile) damager;
				
				if (projectile.getShooter() instanceof Player) {
					return true;
				}
			}
		}
			
		return false;
	}
	
	/**
	 * Gets the killer (A player) from a death event
	 * 
	 * @param event The {@link EntityDeathEvent} to check
	 * @return The killer, or null if no killer could be found
	 */
	public static Player getKiller(EntityDeathEvent event) {
		// If event is null, return null
		if(event == null) {
			return null;
		}
		
		// Get cause
		EntityDamageEvent cause = event.getEntity().getLastDamageCause();
		if(cause instanceof EntityDamageByEntityEvent) {
			Entity attacker = ((EntityDamageByEntityEvent) cause).getDamager();
			
			// Killer was a player
			if (attacker instanceof Player) {
				return (Player) attacker;
			}
			
			// Killer was an animal (e.g. wolf), get tamer
			if(attacker instanceof Tameable) {
				Tameable tameable = (Tameable) attacker;
				
				if (tameable.isTamed() && tameable.getOwner() instanceof Player) {
					return (Player) tameable.getOwner();
				}
			}
			
			// Killer was projectile, get shooter
			if(attacker instanceof Projectile) {
				Projectile projectile = (Projectile) attacker;
				
				if(projectile.getShooter() instanceof Player) {
					return (Player) projectile.getShooter();
				}
			}
		}
		
		return null;
	}
	
}
