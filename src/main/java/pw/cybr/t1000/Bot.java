// Main of all mains!

package main.java.pw.cybr.t1000;

import java.util.HashMap;
import java.util.Map;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLogger;
import org.slf4j.impl.SimpleLoggerFactory;

import main.java.pw.cybr.t1000.commands.CommandHandler;

public class Bot {
	
	private static Config config;
	protected static final Map<String, CommandHandler> commands = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		
		config = new Config();
		config.load();
		loadCommands();
		
		// Begin logger init
		System.setProperty(SimpleLogger.SHOW_DATE_TIME_KEY, "true");
		System.setProperty(SimpleLogger.DATE_TIME_FORMAT_KEY, "[HH:mm:ss]");
        System.setProperty(SimpleLogger.SHOW_THREAD_NAME_KEY, "false");
        System.setProperty(SimpleLogger.LEVEL_IN_BRACKETS_KEY, "true");
        System.setProperty(SimpleLogger.SHOW_LOG_NAME_KEY, "true");
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "debug");
        Logger logger = new SimpleLoggerFactory().getLogger(Bot.class.getName());
        System.out.println(logger.isDebugEnabled());
        
        // Begin bot builder/init
        Configuration.Builder builder = new Configuration.Builder();
        builder.setName(config.getBotNickname());
        builder.setRealName(config.getBotRealname());
        builder.setLogin(config.getBotLogin());
        builder.setNickservPassword(config.getBotPassword());
        builder.setAutoNickChange(true);
        builder.addListener(new BotListener());
        builder.setServer(config.getServerHostname(), config.getServerPort(), config.getServerPassword());
        builder.setSocketFactory(new UtilSSLSocketFactory().trustAllCertificates());
        for(String channel : config.getChannels()){
        	builder.addAutoJoinChannel(channel);
        }
        PircBotX bot = new PircBotX(builder.buildConfiguration());
        
        try {
        	logger.debug("Initializing T-1000...");
        	bot.startBot();
        } catch (Exception ex) {
        	logger.error(null, ex);
        	ex.printStackTrace();
        }
	}
	
	 private static void loadCommands() {
		 // TODO: Add some commands
	 }
	 
	 public static Config getConfig() {
		 return config;
	 }
}
