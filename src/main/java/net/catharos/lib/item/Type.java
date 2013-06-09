package net.catharos.lib.item;

/**
 * Represents an item type.
 * 
 * Use with {@link Item#isOf(net.catharos.lib.item.Type[])}.
 * 
 * @version 1.0
 */
public abstract class Type {
	
	/** All crafting tools, except swords. */
	public static Type TOOL;
	
	/** All swords and bows */
	public static Type WEAPON;
	
	/** Item represents a block */
	public static Type BLOCK;
	
	/** Item is not a block */
	public static Type ITEM;
	
	
	// Initialize all predefined item types
	static {
		TOOL = new Type() {
			@Override
			public boolean matchesType(Item item) {
				switch(item.getItemStack().getType()) {
					case WOOD_HOE:
					case WOOD_PICKAXE:
					case WOOD_AXE:
					case STONE_HOE:
					case STONE_PICKAXE:
					case STONE_AXE:
					case IRON_HOE:
					case IRON_PICKAXE:
					case IRON_AXE:
					case GOLD_HOE:
					case GOLD_PICKAXE:
					case GOLD_AXE:
					case DIAMOND_HOE:
					case DIAMOND_PICKAXE:
					case DIAMOND_AXE:
						return true;
						
					default:
						return false;
				}
			}
		};
		
		WEAPON = new Type() {
			@Override
			public boolean matchesType(Item item) {
				switch(item.getItemStack().getType()) {
					case WOOD_SWORD:
					case STONE_SWORD:
					case IRON_SWORD:
					case GOLD_SWORD:
					case DIAMOND_SWORD:
					case BOW:
						return true;
						
					default:
						return false;
				}
			}
		};
		
		BLOCK = new Type() {
			@Override
			public boolean matchesType(Item item) {
				return item.getItemStack().getType().isBlock();
			}
		};
		
		ITEM = new Type() {
			@Override
			public boolean matchesType(Item item) {
				return !item.getItemStack().getType().isBlock();
			}
		};
	}
	
	/**
	 * Checks if the given item matches the type's conditions.
	 * 
	 * @param item The item to check.
	 * @return True on match, false on mis-match.
	 */
	public abstract boolean matchesType(Item item);
	
}
