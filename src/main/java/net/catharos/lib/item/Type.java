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

	/** All wooden tools and blocks */
	public static Type WOOD;
	
	/** All stone tools and blocks */
	public static Type STONE;
	
	/** All golden tools and blocks */
	public static Type GOLD;
	
	/** All iron tools and blocks */
	public static Type IRON;
	
	/** All diamond tools and blocks */
	public static Type DIAMOND;
	
	/** All leather tools and leather item */
	public static Type LEATHER;
	
	/** All chained tools */
	public static Type CHAINED;
	
	/** Enchanted item */
	public static Type ENCHANTED;
	
	
	// Initialize all predefined item types
	static {
		TOOL = new Type() {
			@Override
			public boolean matchesType(Item item) {
				switch(item.getMaterial()) {
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
				switch(item.getMaterial()) {
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
				return item.getMaterial().isBlock();
			}
		};
		
		ITEM = new Type() {
			@Override
			public boolean matchesType(Item item) {
				return !item.getMaterial().isBlock();
			}
		};
		
		WOOD = new Type() {
			@Override
			public boolean matchesType(Item item) {
				switch(item.getMaterial()) {
					case WOOD:
					case WOODEN_DOOR:
					case WOOD_AXE:
					case WOOD_BUTTON:
					case WOOD_DOOR:
					case WOOD_DOUBLE_STEP:
					case WOOD_HOE:
					case WOOD_PICKAXE:
					case WOOD_PLATE:
					case WOOD_SPADE:
					case WOOD_STAIRS:
					case WOOD_STEP:
					case WOOD_SWORD:
						return true;
						
					default:
						return false;
				}
			}
		};
		
		STONE = new Type() {
			@Override
			public boolean matchesType(Item item) {
				switch(item.getMaterial()) {
					case STONE:
					case STONE_AXE:
					case STONE_BUTTON:
					case STONE_HOE:
					case STONE_PICKAXE:
					case STONE_PLATE:
					case STONE_SPADE:
					case STONE_SWORD:
						return true;
						
					default:
						return false;
				}
			}
		};
		
		GOLD = new Type() {
			@Override
			public boolean matchesType(Item item) {
				switch(item.getMaterial()) {
					case GOLD_AXE:
					case GOLD_BLOCK:
					case GOLD_BOOTS:
					case GOLD_CHESTPLATE:
					case GOLD_HELMET:
					case GOLD_HOE:
					case GOLD_INGOT:
					case GOLD_LEGGINGS:
					case GOLD_NUGGET:
					case GOLD_PICKAXE:
					case GOLD_PLATE:
					case GOLD_SPADE:
					case GOLD_SWORD:
						return true;
						
					default:
						return false;
				}
			}
		};
		
		GOLD = new Type() {
			@Override
			public boolean matchesType(Item item) {
				switch(item.getMaterial()) {
					case GOLD_AXE:
					case GOLD_BLOCK:
					case GOLD_BOOTS:
					case GOLD_CHESTPLATE:
					case GOLD_HELMET:
					case GOLD_HOE:
					case GOLD_INGOT:
					case GOLD_LEGGINGS:
					case GOLD_NUGGET:
					case GOLD_PICKAXE:
					case GOLD_PLATE:
					case GOLD_SPADE:
					case GOLD_SWORD:
						return true;
						
					default:
						return false;
				}
			}
		};
		
		IRON = new Type() {
			@Override
			public boolean matchesType(Item item) {
				switch(item.getMaterial()) {
					case IRON_AXE:
					case IRON_BLOCK:
					case IRON_BOOTS:
					case IRON_CHESTPLATE:
					case IRON_DOOR:
					case IRON_DOOR_BLOCK:
					case IRON_FENCE:
					case IRON_HELMET:
					case IRON_HOE:
					case IRON_INGOT:
					case IRON_LEGGINGS:
					case IRON_PICKAXE:
					case IRON_PLATE:
					case IRON_SPADE:
					case IRON_SWORD:
						return true;
						
					default:
						return false;
				}
			}
		};
		
		LEATHER = new Type() {
			@Override
			public boolean matchesType(Item item) {
				switch(item.getMaterial()) {
					case LEATHER:
					case LEATHER_BOOTS:
					case LEATHER_CHESTPLATE:
					case LEATHER_HELMET:
					case LEATHER_LEGGINGS:
						return true;
						
					default:
						return false;
				}
			}
		};
		
		CHAINED = new Type() {
			@Override
			public boolean matchesType(Item item) {
				switch(item.getMaterial()) {
					case CHAINMAIL_BOOTS:
					case CHAINMAIL_CHESTPLATE:
					case CHAINMAIL_HELMET:
					case CHAINMAIL_LEGGINGS:
						return true;
						
					default:
						return false;
				}
			}
		};
		
		ENCHANTED = new Type() {
			@Override
			public boolean matchesType(Item item) {
				return !item.getEnchantments().isEmpty();
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
