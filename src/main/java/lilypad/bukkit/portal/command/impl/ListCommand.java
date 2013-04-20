package lilypad.bukkit.portal.command.impl;

import lilypad.bukkit.portal.command.Command;
import lilypad.bukkit.portal.command.CommandPermissionException;
import lilypad.bukkit.portal.command.CommandSyntaxException;
import lilypad.bukkit.portal.gate.Gate;
import lilypad.bukkit.portal.gate.GateRegistry;
import lilypad.bukkit.portal.util.MessageConstants;
import lilypad.bukkit.portal.util.PermissionConstants;
import lilypad.bukkit.portal.util.StringUtils;

import org.bukkit.entity.Player;


public class ListCommand implements Command {

	private GateRegistry gateRegistry;
	
	public ListCommand(GateRegistry gateRegistry) {
		this.gateRegistry = gateRegistry;
	}
	
	public void execute(Player player, String[] args) throws CommandPermissionException, CommandSyntaxException {
		if(!player.hasPermission(PermissionConstants.PORTAL_LIST)) {
			throw new CommandPermissionException(PermissionConstants.PORTAL_LIST);
		}
		String[] gates = new String[this.gateRegistry.getAll().size()];
		int i = 0;
		for(Gate gate : this.gateRegistry.getAll()) {
			gates[i++] = gate.getDestinationServer();
		}
		player.sendMessage(MessageConstants.format(MessageConstants.GATE_LIST, StringUtils.concat(gates, ", ")));
	}

	public String getId() {
		return "list";
	}

}
