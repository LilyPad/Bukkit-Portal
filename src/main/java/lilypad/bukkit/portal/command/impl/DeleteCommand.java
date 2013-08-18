package lilypad.bukkit.portal.command.impl;

import lilypad.bukkit.portal.IConfig;
import lilypad.bukkit.portal.command.Command;
import lilypad.bukkit.portal.command.CommandPermissionException;
import lilypad.bukkit.portal.command.CommandSyntaxException;
import lilypad.bukkit.portal.gate.Gate;
import lilypad.bukkit.portal.gate.GateRegistry;
import lilypad.bukkit.portal.util.PermissionConstants;

import org.bukkit.entity.Player;

public class DeleteCommand implements Command {

	private IConfig config;
	private GateRegistry gateRegistry;
	
	public DeleteCommand(IConfig config, GateRegistry gateRegistry) {
		this.config = config;
		this.gateRegistry = gateRegistry;
	}
	
	public void execute(Player player, String[] args) throws CommandPermissionException, CommandSyntaxException {
		if(!player.hasPermission(PermissionConstants.PORTAL_DELETE)) {
			throw new CommandPermissionException(PermissionConstants.PORTAL_DELETE);
		}
		if(args.length < 1) {
			throw new CommandSyntaxException("destinationServer");
		}
		String destinationServer = args[0];
		Gate gate = this.gateRegistry.getByDestinationServer(destinationServer);
		if(gate == null) {
			player.sendMessage(this.config.getMessage("delete-no-exists").replace("{server}", destinationServer));
			return;
		}
		this.gateRegistry.unregister(gate);
		player.sendMessage(this.config.getMessage("delete").replace("{server}", destinationServer));
	}

	public String getId() {
		return "delete";
	}

}
