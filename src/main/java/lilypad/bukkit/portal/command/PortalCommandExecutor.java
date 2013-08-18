package lilypad.bukkit.portal.command;

import lilypad.bukkit.portal.command.create.CreateCommand;
import lilypad.bukkit.portal.command.create.CreateListener;
import lilypad.bukkit.portal.command.impl.DeleteCommand;
import lilypad.bukkit.portal.command.impl.ListCommand;
import lilypad.bukkit.portal.gate.GateRegistry;

public class PortalCommandExecutor extends CommandRegistryExecutor {

	public PortalCommandExecutor(GateRegistry gateRegistry, CreateListener createListener) {
		super.submit(new CreateCommand(gateRegistry, createListener));
		super.submit(new DeleteCommand(gateRegistry));
		super.submit(new ListCommand(gateRegistry));
	}
	
	public String getId() {
		return "portal";
	}

}

