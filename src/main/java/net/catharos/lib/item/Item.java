package net.catharos.lib.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
	
	/** The {@link MaterialData} */
	protected MaterialData material;
	
	/** The item stack size */
	protected int size;
	
	/** The item durability */
	protected short durability;
	
	/** Item display name */
	protected String name;
	
	/** Item description list */
	protected List<String> lore;
	
	/** Item enchantments */
	protected Map<Enchantment, Integer> enchantments;
	
	/**
	 * Creates a new item hased on it's {@link Material}
	 * 
	 * @param material The item's material
	 */
	public Item(Material material) {
		this(new MaterialData(material));
	}
	
	/**
	 * Creates a new item based on an item stack (by cloning).
	 * 
	 * @param item The item reference
	 */
	public Item(ItemStack item) {
		this(item.getData());
	}
	
	/**
	 * Creates a new item based on it's {@link MaterialData}
	 * 
	 * @param data The item's material data
	 */
	public Item(MaterialData data) {
		this.material = data;
		
		size = 1;
		durability = 0;
		
		lore = new ArrayList<String>();
	}
	
	/**
	 * Sets the current item stack size.
	 * 
	 * @param size The new item stack size
	 * @return The item object for method chaining
	 */
	public Item setSize(int size) {
		this.size = size;
		
		return this;
	}
	
	/**
	 * Sets the current item durability.
	 * 
	 * @param durability The new item durability
	 * @return The item object for method chaining
	 */
	public Item setDurability(short durability) {
		this.durability = durability;
		
		return this;
	}
	
	/**
	 * Sets the display name of the item.
	 * 
	 * @param name The displayed name
	 * @return The item object for method chaining
	 */
	public Item setName(String name) {
		this.name = name;
		
		return this;
	}
	
	/**
	 * Returns the item base material.
	 * For special wood blocks the full {@link MaterialData} is needed.
	 * 
	 * @return The item material
	 */
	public Material getMaterial() {
		return material.getItemType();
	}
	
	/**
	 * Returns the current item stack size / amount.
	 * 
	 * @return The item stack size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns the item durability.
	 * The higher the value, the more used the item!
	 * 
	 * @return The item durability
	 */
	public short getDurability() {
		return durability;
	}
	
	/**
	 * Returns the item display name stored inside the meta data.
	 * 
	 * @return The item display name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns a map of all enchantments.
	 * The map key represents the enchantment, the value the needed level.
	 * 
	 * @return A {@link Map} representation of all {@link Enchantment enchantments}
	 */
	public Map<Enchantment, Integer> getEnchantments() {
		return enchantments;
	}
	
	/**
	 * Checks if the item mets the given types.
	 * 
	 * @param types The {@link Type types} to check against.
	 * @return True if the item matches the conditions.
	 */
	public boolean isOf(ItemType... types) {
		boolean match = true;
		
		for(ItemType type : types) {
			match = match && type.matchesType(this);
			
			// No need to check any further
			if(!match) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Returns a newly created bukkit representation of this item.
	 * 
	 * @return The bukkit {@link ItemStack}
	 */
	public ItemStack createItemStack() {
		ItemStack item = material.toItemStack();
		ItemMeta meta = item.getItemMeta();
		
		// Set general item information
		item.setAmount(size);
		item.setDurability(durability);
		item.addEnchantments(enchantments);
		
		// Set item display name
		if(name != null) {
			meta.setDisplayName(name);
		}
		
		// Set item lore
		if(!lore.isEmpty()) {
			meta.setLore(lore);
		}
		
		// Set meta data
		item.setItemMeta(meta);
		
		return item;
	}
	
}
