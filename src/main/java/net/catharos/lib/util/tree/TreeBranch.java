package net.catharos.lib.util.tree;

import java.util.HashMap;
import java.util.Map;
import net.catharos.lib.util.interfaces.Nameable;

/**
 * Represents a branch class that can be nested by creating new branch-nodes.
 * 
 * @version 1.0
 */
public class TreeBranch extends TreeNode implements Nameable {
	
	/** Holds list of all branching nodes */
	private Map<String, TreeNode> nodes;
	
	/** The branch name */
	private String name;
	
	/**
	 * Constructor for this group (node).
	 * 
	 * @param name The current branch name
	 * @param parent The parent node (or null)
	 */
	public TreeBranch(String name, TreeNode parent) {
		super(parent);
		
		this.nodes = new HashMap<String, TreeNode>();
		
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public TreeBranch createBranch(String... path) {
		TreeBranch parent = this;
		
		for(String key : path) {
			TreeNode node = parent.getNode(key);
			
			if(node != null) {
				
			} else {
				TreeBranch branch = new TreeBranch(key, parent);
				parent = (TreeBranch) parent.addNode(key, branch);
			}
		}
		
		return parent;
	}
	
	/**
	 * Adds a new node to this branch.
	 * 
	 * @param name The node name
	 * @param node The node object
	 * @return The node, can be used for method chaining
	 */
	public TreeNode addNode(String name, TreeNode node) {
		nodes.put(name, node);
		
		return node;
	}
	
	/**
	 * Gets a node by its path or name.
	 * 
	 * @param path The node path or name
	 * @return The node object
	 */
	public TreeNode getNode(String... path) {
		TreeBranch node = this;
		
		for(String string : path) {
			TreeNode n = node.getNode(string);
			if((n == null) || (n instanceof TreeNode)) {
				return n;
			}
			
			node = (TreeBranch) n;
		}
		
		return node;
	}
	
	/**
	 * Returns a map of all stored children node objects.
	 * Can be empty.
	 * 
	 * @return A map of all tree nodes
	 */
	public Map<String, TreeNode> getNodes() {
		return nodes;
	}
	
}