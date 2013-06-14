package net.catharos.lib.util.tree;

/**
 *
 * @version 1.0
 */
public class TreeNode {
		
	/** Holds parent object */
	private TreeNode parent;
	
	/**
	 * Constructor for this node.
	 * 
	 * @param parent The parent node (or null)
	 */
	public TreeNode(TreeNode parent) {
		this.parent = parent;
	}
	
	/**
	 * Gets the parent of this node.
	 * If this is the root node, this will return null.
	 * 
	 * @return The parent node or null
	 */
	public TreeNode getParent() {
		return parent;
	}
	
}
