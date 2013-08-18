package lilypad.bukkit.portal.command.create;

import java.util.Map;

import lilypad.bukkit.portal.IConfig;
import lilypad.bukkit.portal.gate.GateRegistry;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


import com.google.common.collect.MapMaker;

public class CreateListener implements Listener {

	private Map<Player, CreateSession> createSessions = new MapMaker().weakKeys().makeMap();
	private IConfig config;
	private GateRegistry gateRegistry;
	
	public CreateListener(IConfig config, GateRegistry gateRegistry) {
		this.config = config;
		this.gateRegistry = gateRegistry;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent playerInteractEvent) {
		if(playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK && playerInteractEvent.getAction() != Action.LEFT_CLICK_BLOCK) {
			return;
		}
		Player player = playerInteractEvent.getPlayer();
		CreateSession createSession = this.createSessions.get(player);
		if(createSession == null) {
			return;
		}
		Block block = playerInteractEvent.getClickedBlock();
		if(block == null) {
			return;
		}
		switch(createSession.getState()) {
		case INWARD_CORNER_1:
			createSession.setInwardCorner1(block);
			createSession.setState(CreateSession.State.INWARD_CORNER_2);
			player.sendMessage(this.config.getMessage("create-step-2"));
			break;
		case INWARD_CORNER_2:
			createSession.setInwardCorner2(block);
			createSession.setState(CreateSession.State.OUTWARD);
			player.sendMessage(this.config.getMessage("create-step-3"));
			break;
		case OUTWARD:
			createSession.setOutward(block.getRelative(BlockFace.UP));
			createSession.setOutwardYaw((int) player.getLocation().getYaw());
			this.gateRegistry.register(createSession.createGate());
			this.createSessions.remove(player);
			player.sendMessage(this.config.getMessage("create").replace("{server}", createSession.getDestinationServer()));
			break;
		}
	}
	
	public void submitSession(CreateSession createSession) {
		this.createSessions.put(createSession.getPlayer(), createSession);
	}
	
}
