package lilypad.bukkit.portal.user;

import lilypad.bukkit.portal.IRedirector;
import lilypad.bukkit.portal.Task;
import lilypad.bukkit.portal.util.MessageConstants;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class UserRedirectorTask extends Task {

	private String username;
	private String server;
	private IRedirector redirector;
	private boolean notified;
	
	public UserRedirectorTask(String username, String server, IRedirector redirector) {
		this.username = username;
		this.server = server;
		this.redirector = redirector;
	}
	
	public void run() {
		Player player = Bukkit.getServer().getPlayerExact(this.username);
		if(player == null) {
			this.cancelTask();
			return;
		}
		if(!this.redirector.redirectResult(this.username, this.server) && !this.notified) {
			for(String message : MessageConstants.format(MessageConstants.SERVER_OFFLINE_REDIRECT)) {
				player.sendMessage(message);
			}
			this.notified = true;
		}
	}
	
}
