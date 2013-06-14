package net.catharos.lib.util.tree;

import net.catharos.lib.util.interfaces.Nameable;

/**
 *
 * @author spaceemotion
 */
public class SimpleTreeNode extends TreeNode implements Nameable{

	public String name;
	
	public SimpleTreeNode(String name, TreeNode parent) {
		super(parent);
		
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
