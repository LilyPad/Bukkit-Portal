package lilypad.bukkit.portal.command;

import lilypad.bukkit.portal.IConfig;
import lilypad.bukkit.portal.util.StringUtils;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandRegistryExecutor extends CommandRegistry implements CommandExecutor {

	private IConfig config;
	
	public CommandRegistryExecutor(IConfig config) {
		this.config = config;
	}
	
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command __unused, String _unused, String[] args) {
		do {
			if(!(sender instanceof Player)) {
				sender.sendMessage("This command can not be executed from console!");
				break;
			}
			if(args.length == 0) {
				sender.sendMessage(this.config.getMessage("command-no-syntax").replace("{syntax}", "/" + this.getId() + " [command] <sub>"));
				break;
			}
			Player player = (Player) sender;
			String commandLabel = args[0];
			String[] commandArgs = new String[0];
			try {
				commandArgs = StringUtils.shift(args, 1);
			} catch(Exception exception) {
				//ignore
			}
			Command command = this.getById(commandLabel);
			if(command == null) {
				player.sendMessage(this.config.getMessage("command-no-exists").replace("{command}", "/" + this.getId() + " help"));
				break;
			}
			try {
				command.execute(player, commandArgs);
			} catch(CommandSyntaxException commandSyntaxException) {
				player.sendMessage(this.config.getMessage("command-no-syntax").replace("{syntax}", commandSyntaxException.getSyntax()));
			} catch(CommandPermissionException commandPermissionException) {
				player.sendMessage(this.config.getMessage("command-no-permission").replace("{permission}", commandPermissionException.getPermission()));
			}
		} while(false);
		return true;
	}
	
	public abstract String getId();
	
}
