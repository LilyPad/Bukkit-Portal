package lilypad.bukkit.portal.command.impl;

import java.util.Collection;

import lilypad.bukkit.portal.IConfig;
import lilypad.bukkit.portal.command.Command;
import lilypad.bukkit.portal.command.CommandPermissionException;
import lilypad.bukkit.portal.command.CommandSyntaxException;
import lilypad.bukkit.portal.gate.Gate;
import lilypad.bukkit.portal.gate.GateRegistry;
import lilypad.bukkit.portal.util.PermissionConstants;
import lilypad.bukkit.portal.util.StringUtils;

import org.bukkit.entity.Player;

public class ListCommand implements Command {

	private IConfig config;
	private GateRegistry gateRegistry;
	
	public ListCommand(IConfig config, GateRegistry gateRegistry) {
		this.config = config;
		this.gateRegistry = gateRegistry;
	}
	
	public void execute(Player player, String[] args) throws CommandPermissionException, CommandSyntaxException {
		if(!player.hasPermission(PermissionConstants.PORTAL_LIST)) {
			throw new CommandPermissionException(PermissionConstants.PORTAL_LIST);
		}
		Collection<Gate> gates = this.gateRegistry.getAll();
		String[] gateStrings = new String[gates.size()];
		int i = 0;
		for(Gate gate : gates) {
			gateStrings[i++] = this.config.getMessage("gate").replace("{gate}", gate.getDestinationServer());
		}
		player.sendMessage(this.config.getMessage("gate-list").replace("{gates}", StringUtils.concat(gateStrings, ", ")));
	}

	public String getId() {
		return "list";
	}

}
