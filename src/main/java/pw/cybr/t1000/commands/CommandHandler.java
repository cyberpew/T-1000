package main.java.pw.cybr.t1000.commands;

import org.pircbotx.hooks.types.GenericMessageEvent;

public abstract class CommandHandler {
	
	private final String name;
	private final String help;
	protected String command;
	protected String args;
	
	public CommandHandler(String name) {
		this.name = name;
		this.help = "";
	}
	
	public CommandHandler(String name, String help) {
		this.name = name;
		this.help = help;
	}
	
	public abstract void run(GenericMessageEvent event);
	
	public String getName() {
		return name;
	}
	
	public String getHelp() {
		return help;
	}

}
