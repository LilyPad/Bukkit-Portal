package lilypad.bukkit.portal.user;

import lilypad.bukkit.portal.IConfig;
import lilypad.bukkit.portal.IRedirector;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class UserRedirectorTask extends BukkitRunnable {

	private IConfig config;
	private IRedirector redirector;
	private Player player;
	private String server;
	private boolean notified;
	
	public UserRedirectorTask(IConfig config, IRedirector redirector, Player player, String server) {
		this.config = config;
		this.player = player;
		this.server = server;
		this.redirector = redirector;
	}
	
	public void run() {
		String user = this.player.getName();
		Server server = this.player.getServer();
		if(server.getPlayerExact(this.player.getName()) == null) {
			super.cancel();
			return;
		}
		if(!this.redirector.redirectResult(user, this.server) && !this.notified) {
			this.player.sendMessage(this.config.getMessage("server-offline-redirect"));
			this.notified = true;
		}
	}
	
}
