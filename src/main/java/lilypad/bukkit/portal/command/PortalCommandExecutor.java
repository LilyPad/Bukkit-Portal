package lilypad.bukkit.portal.command;

import lilypad.bukkit.portal.IConfig;
import lilypad.bukkit.portal.command.create.CreateCommand;
import lilypad.bukkit.portal.command.create.CreateListener;
import lilypad.bukkit.portal.command.impl.DeleteCommand;
import lilypad.bukkit.portal.command.impl.ListCommand;
import lilypad.bukkit.portal.gate.GateRegistry;

public class PortalCommandExecutor extends CommandRegistryExecutor {
	
	public PortalCommandExecutor(IConfig config, GateRegistry gateRegistry, CreateListener createListener) {
		super(config);
		super.submit(new CreateCommand(config, gateRegistry, createListener));
		super.submit(new DeleteCommand(config, gateRegistry));
		super.submit(new ListCommand(config, gateRegistry));
	}
	
	public String getId() {
		return "portal";
	}

}

