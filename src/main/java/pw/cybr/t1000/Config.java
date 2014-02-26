package main.java.pw.cybr.t1000;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

import sun.applet.Main;

public class Config {
	
	private String nickname;
	private String realname;
	private String login;
	private String identpass;
	private String server;
	private int port;
	private boolean debug;
	private String trigger;
	private List<String> channels;
	private String serverpass;
	private Logger logger = new SimpleLoggerFactory().getLogger(this.getClass().getName());
	
	public void load() throws IOException {
		Properties properties = new Properties();
		File config = new File("bot.conf");
		
		if (!config.exists()) {
			// notify if the config is not found
			logger.error("Bot config not found, creating bot.conf with default values...");
			
			//get the default config from the jar and create if it isn't created yet
			BufferedReader reader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/bot.conf")));
			config.createNewFile();
			
			// Write out the default config
			PrintWriter writer = new PrintWriter(new FileWriter(config));
			String line;
			while ((line = reader.readLine()) != null) {
				writer.println(line);
			}
			writer.flush();
			writer.close();
		}
		//begin loading the config
		properties.load(new FileInputStream("bot.conf"));
		
		this.setBotNickname(properties.getProperty("nickname"));
		this.setBotLogin(properties.getProperty("login"));
		this.setBotRealname(properties.getProperty("realname"));
		this.setBotPassword(properties.getProperty("identpass"));
		this.setDebug(Boolean.parseBoolean(properties.getProperty("debug")));
		this.setTrigger(properties.getProperty("trigger"));
		this.setChannels(Arrays.asList(properties.getProperty("channels").split(" ")));
		this.setServerHostname(properties.getProperty("server"));
		this.setServerPort(Integer.parseInt(properties.getProperty("port")));
		this.setServerPassword(properties.getProperty("serverpass"));
	}
	
	public List<String> getChannels() {
		return channels;
	}
	public void setChannels(List<String> channels) {
		this.channels = channels;
	}
	
	public String getBotNickname() {
		return nickname;
	}
	public void setBotNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getBotRealname() {
		return realname;
	}
	public void setBotRealname(String realname) {
		this.realname = realname;
	}
	
	public String getBotLogin() {
		return login;
	}
	public void setBotLogin(String login) {
		this.login = login;
	}
	
	public String getBotPassword() {
		return identpass;
	}
	public void setBotPassword(String identpass) {
		this.identpass = identpass;
	}
	
	public String getServerHostname() {
		return server;
	}
	public void setServerHostname(String server) {
		this.server = server;
	}
	
	public int getServerPort() {
		return port;
	}
	public void setServerPort(int port) {
		this.port = port;
	}
	
	public boolean getDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public String getTrigger() {
		return trigger;
	}
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	
	public String getServerPassword() {
		return serverpass;
	}
	public void setServerPassword(String serverpass) {
		this.serverpass = serverpass;
	}
}
