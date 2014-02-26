package main.java.pw.cybr.t1000;

import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

public class BotListener extends ListenerAdapter {
	
	private Logger logger = new SimpleLoggerFactory().getLogger(this.getClass().getName());
	
	@Override
	public void onMessage(MessageEvent event) {
		String message = event.getMessage();
		if (message.startsWith(Bot.getConfig().getTrigger())) {
			String[] messageArr = event.getMessage().substring(1).split(" ");
			String command = messageArr[0].toLowerCase();
			executeCommand(event, command);
		}
	}
	
	private void executeCommand(GenericMessageEvent event, String command) {
		if (Bot.commands.containsKey(command)) {
			Bot.commands.get(command).run(event);
		}
	}
	
	private boolean hasPermissions(User user) {
		// TODO: Permissions w/ MySQL.
		// For now just using hostmasks
		
		return user.getHostmask().endsWith("to.us");
	}
	
	@Override
	public void onPrivateMessage(PrivateMessageEvent event) {
		if (hasPermissions(event.getUser())) {
			String command = event.getMessage().split(" ")[0];
			executeCommand(event, command);
		}
	}
}
