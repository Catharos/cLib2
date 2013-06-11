package net.catharos.lib.network.command;

import net.catharos.lib.network.command.annotation.Flag;

/**
 * Package-Protected command flag helper class.
 * 
 * Spares us all that checking and parsing when executing a command each time
 * again.
 * 
 * @version 1.0
 */
class CommandFlag {
	
	/** The flag itself */
	private final Flag flag;
	
	/** The argument type */
	private final Argument argument;
	
	
	protected CommandFlag(Flag flag) throws Exception {
		this.flag = flag;
		
		// Get correct argument
		this.argument = Argument.getArgumentByType(flag.type());
		
		// Throw error if flag holds invalid argument type
		if(argument == null) {
			throw new Exception("Invalid flag type (Must be a registered argument)");
		}
	}
	
	protected Flag getFlag() {
		return flag;
	}
	
	protected Argument getArgument() {
		return argument;
	}
	
}
