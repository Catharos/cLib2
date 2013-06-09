package net.catharos.lib.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;


/**
 * Item builder class.
 * 
 * Setter classes return the item instance itself for method chaining.
 * 
 * @version 1.0
 */
public class Item {
	
	/** Bukkit item stack */
	protected ItemStack stack;
	
	/**
	 * Creates a new item hased on it's {@link Material}
	 * 
	 * @param material The item's material
	 */
	public Item(Material material) {
		this(new MaterialData(material));
	}
	
	/**
	 * Creates a new item based on it's {@link MaterialData}
	 * @param data The item's material data
	 */
	public Item(MaterialData data) {
		this(data.toItemStack());
	}
	
	/**
	 * Creates a new item based on an item stack (by cloning).
	 * 
	 * @param item The item reference
	 */
	public Item(ItemStack item) {
		stack = item.clone();
	}
	
	/**
	 * Sets the display name of the item.
	 * 
	 * @param name The displayed name
	 * @return The item object
	 */
	public Item setName(String name) {
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(name);
		stack.setItemMeta(meta);
		
		return this;
	}
	
	/**
	 * Returns the item display name stored inside the meta data.
	 * @return The item display name
	 */
	public String getName() {
		return stack.getItemMeta().getDisplayName();
	}
	
	/**
	 * Checks if the item mets the given types.
	 * 
	 * @param types The {@link Type types} to check against.
	 * @return True if the item matches the conditions.
	 */
	public boolean isOf(Type... types) {
		boolean outcome = true;
		
		for(Type type : types) {
			outcome = outcome && type.matchesType(this);
		}
		
		return outcome;
	}
	
	/**
	 * Returns the bukkit representation of this item.
	 * @return The bukkit {@link ItemStack}
	 */
	public ItemStack getItemStack() {
		return stack;
	}
	
}
