package net.catharos.lib.network.command;

import java.util.ArrayList;
import java.util.List;
import net.catharos.lib.network.command.annotation.CommandHandler;

/**
 *
 * @version 1.0
 */
public final class CommandInformation {
	
	private final CommandHandler handler;
	
	private final CommandInformation parent;
	
	private List<CommandInformation> children;
	
	
	protected CommandInformation(CommandHandler handler, CommandInformation parent) {
		this.handler = handler;
		this.parent = parent;
		
		children = new ArrayList<CommandInformation>();
	}
	
	
	/* -------- Public functions -------- */
	
	public String getName() {
		return handler.name();
	}
	
	public String getDescription() {
		return handler.description();
	}
	
	public String getUsage() {
		return handler.usage();
	}
	
	public String getPermission() {
		return handler.permission();
	}
	
	public int getRequiredArguments() {
		return handler.minArgs();
	}
	
	public boolean isListed() {
		return handler.listed();
	}
	
	
	/* -------- Protected functions -------- */
	
	protected CommandInformation getParent() {
		return parent;
	}
	
	protected void addChildren(CommandInformation child) {
		children.add(child);
	}
	
}
