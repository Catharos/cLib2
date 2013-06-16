package net.catharos.lib.util.math;

import org.bukkit.Location;

/**
 * Represents a vector in a 3D space.
 * 
 * @version 1.0
 */
public class Vector3D {
	
	private double x, y, z;
	
	/**
	 * Creates a new 3d vector from a bukkit location.
	 * 
	 * @param location The bukkit location
	 */
	public Vector3D(Location location) {
		this.x = location.getX();
		this.y = location.getY();
		this.z = location.getZ();
	}
	
	/**
	 * Creates a new 3d vector.
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param z The z coordinate
	 */
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Returns the x coordinate.
	 * 
	 * @return The x coordinate
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Returns the y coordinate.
	 * 
	 * @return The y coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Returns the z coordinate.
	 * 
	 * @return The z coordinate
	 */
	public double getZ() {
		return z;
	}
	
}
