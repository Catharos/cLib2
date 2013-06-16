package net.catharos.lib.util.math;

import org.bukkit.World;

/**
 *
 * @version 1.0
 */
public class Dimensions {
	
	/** The world the dimension is placed in */
	private World world;
	
	/** The first 3D Vector */
	private Vector3D a;
	
	/** The second 3D Vector */
	private Vector3D b;
	
	/**
	 * Creates a new 3d boundary box.
	 * 
	 * @param world The world it is placed in
	 * @param a The first point
	 * @param b The second point
	 */
	public Dimensions(World world, Vector3D a, Vector3D b) {
		this.world = world;
		
		this.a = a;
		this.b = b;
	}
	
	public World getWorld() {
		return world;
	}
	
	public Vector3D getA() {
		return a;
	}
	
	public Vector3D getB() {
		return b;
	}
	
	public double getMinX() {
		return a.getX() < b.getX() ? a.getX() : b.getX();
	}
	
	public double getMinY() {
		return a.getY() < b.getY() ? a.getY() : b.getY();
	}
	
	public double getMinZ() {
		return a.getZ() < b.getZ() ? a.getZ() : b.getZ();
	}
	
	public double getMaxX() {
		return a.getX() > b.getX() ? a.getX() : b.getX();
	}
	
	public double getMaxY() {
		return a.getY() > b.getY() ? a.getY() : b.getY();
	}
	
	public double getMaxZ() {
		return a.getZ() > b.getZ() ? a.getZ() : b.getZ();
	}
	
}
