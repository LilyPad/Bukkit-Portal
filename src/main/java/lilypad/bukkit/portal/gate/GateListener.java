package lilypad.bukkit.portal.gate;

import java.util.Map;

import lilypad.bukkit.portal.IConfig;
import lilypad.bukkit.portal.IRedirector;
import lilypad.bukkit.portal.util.PermissionConstants;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.google.common.collect.MapMaker;

public class GateListener implements Listener {
	
	private IConfig config;
	private GateRegistry gateRegistry;
	private IRedirector redirector;
	
	private Map<Player, Long> playersToLogins = new MapMaker().weakKeys().makeMap();
	
	public GateListener(IConfig config, GateRegistry gateRegistry, IRedirector redirector) {
		this.config = config;
		this.gateRegistry = gateRegistry;
		this.redirector = redirector;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
		this.playersToLogins.put(playerJoinEvent.getPlayer(), System.currentTimeMillis());
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
		Player player = playerMoveEvent.getPlayer();
		Location to = playerMoveEvent.getTo();
		Location from = playerMoveEvent.getFrom();
		Gate gate = this.gateRegistry.getByLocation(to);
		if(gate == null) {
			return;
		}
		if(gate.isInside(from)) {
			return;
		}
		if(this.playersToLogins.containsKey(player) && System.currentTimeMillis() - this.playersToLogins.get(player) < 2500L) {
			return;
		}
		if(!player.hasPermission(PermissionConstants.PORTAL_USE)) {
			player.sendMessage(this.config.getMessage("gate-no-permission").replace("{permission}", PermissionConstants.PORTAL_USE));
			return;
		}
		this.redirector.redirect(player, gate.getDestinationServer());
	}
	
}
