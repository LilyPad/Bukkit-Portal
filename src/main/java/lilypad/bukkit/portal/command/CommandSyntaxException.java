package lilypad.bukkit.portal.command;

public class CommandSyntaxException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String syntax;
	
	public CommandSyntaxException(String syntax) {
		this.syntax = syntax;
	}
	
	public String getSyntax() {
		return this.syntax;
	}
	
}
