package lilypad.bukkit.portal.command.impl;

import lilypad.bukkit.portal.command.Command;
import lilypad.bukkit.portal.command.CommandPermissionException;
import lilypad.bukkit.portal.command.CommandSyntaxException;
import lilypad.bukkit.portal.gate.Gate;
import lilypad.bukkit.portal.gate.GateRegistry;
import lilypad.bukkit.portal.util.MessageConstants;
import lilypad.bukkit.portal.util.PermissionConstants;

import org.bukkit.entity.Player;


public class DeleteCommand implements Command {

	private GateRegistry gateRegistry;
	
	public DeleteCommand(GateRegistry gateRegistry) {
		this.gateRegistry = gateRegistry;
	}
	
	public void execute(Player player, String[] args) throws CommandPermissionException, CommandSyntaxException {
		if(!player.hasPermission(PermissionConstants.PORTAL_DELETE)) {
			throw new CommandPermissionException(PermissionConstants.PORTAL_DELETE);
		}
		if(args.length < 1) {
			throw new CommandSyntaxException("destinationServer");
		}
		String detinationServer = args[0];
		Gate gate = this.gateRegistry.getByDestinationServer(detinationServer);
		if(gate == null) {
			player.sendMessage(MessageConstants.format(MessageConstants.DELETE_NO_EXIST, detinationServer));
			return;
		}
		this.gateRegistry.unregister(gate);
		player.sendMessage(MessageConstants.format(MessageConstants.DELETE_SUCCESS, detinationServer));
	}

	public String getId() {
		return "delete";
	}

}
