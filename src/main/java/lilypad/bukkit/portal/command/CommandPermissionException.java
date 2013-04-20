package lilypad.bukkit.portal.command;

public class CommandPermissionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String permission;
	
	public CommandPermissionException(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return this.permission;
	}
	
}
