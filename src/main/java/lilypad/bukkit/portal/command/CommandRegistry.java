package lilypad.bukkit.portal.command;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

	private Map<String, Command> commands = new HashMap<String, Command>();
	
	public void submit(Command command) {
		if(command == null) {
			return;
		}
		this.commands.put(command.getId(), command);
	}
	
	public Command getById(String id) {
		return this.commands.get(id.toLowerCase());
	}
	
}
