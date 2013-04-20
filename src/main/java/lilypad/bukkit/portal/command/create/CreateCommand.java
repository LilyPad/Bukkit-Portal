package lilypad.bukkit.portal.command.create;

import lilypad.bukkit.portal.command.Command;
import lilypad.bukkit.portal.command.CommandPermissionException;
import lilypad.bukkit.portal.command.CommandSyntaxException;
import lilypad.bukkit.portal.gate.GateRegistry;
import lilypad.bukkit.portal.util.MessageConstants;
import lilypad.bukkit.portal.util.PermissionConstants;

import org.bukkit.entity.Player;


public class CreateCommand implements Command {

	private GateRegistry gateRegistry;
	private CreateListener createListener;
	
	public CreateCommand(GateRegistry gateRegistry, CreateListener createListener) {
		this.gateRegistry = gateRegistry;
		this.createListener = createListener;
	}
	
	public void execute(Player player, String[] args) throws CommandPermissionException, CommandSyntaxException {
		if(!player.hasPermission(PermissionConstants.PORTAL_CREATE)) {
			throw new CommandPermissionException(PermissionConstants.PORTAL_CREATE);
		}
		if(args.length < 1) {
			throw new CommandSyntaxException("destinationServer");
		}
		String destinationServer = args[0];
		if(this.gateRegistry.hasByDestinationServer(destinationServer)) {
			player.sendMessage(MessageConstants.format(MessageConstants.CREATE_ALREADY_EXISTS, destinationServer));
			return;
		}
		this.createListener.submitSession(new CreateSession(player, destinationServer));
		player.sendMessage(MessageConstants.format(MessageConstants.CREATE_STEP_1));
	}

	public String getId() {
		return "create";
	}

}
