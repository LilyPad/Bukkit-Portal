package lilypad.bukkit.portal.command;

import org.bukkit.entity.Player;

public interface Command {

	public void execute(Player player, String[] args) throws CommandPermissionException, CommandSyntaxException;

	public String getId();
	
}
