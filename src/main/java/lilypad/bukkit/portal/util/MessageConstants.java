package lilypad.bukkit.portal.util;

import org.bukkit.ChatColor;

public class MessageConstants {

	public static final String PREFIX = "&f[&7Portal&f] ";
	
	public static final String SERVER_OFFLINE = PREFIX + "This server is currently &4offline&f. Try again later!";
	public static final String[] SERVER_OFFLINE_REDIRECT = new String[] {
		PREFIX + "Your last server is currently &4offline&f. If you join",
		PREFIX + "another server, you will lose your last position.",
		PREFIX + "&2You will be redirected when it becomes available."
	};
	
	public static final String COMMAND_NO_SYNTAX = PREFIX + "&cYou do not have the required syntax &f(&4%s&f)";
	public static final String COMMAND_NO_PERMISSION = PREFIX + "&cYou do not have the required permissions &f(&4%s&f)";
	public static final String COMMAND_NO = PREFIX + "&cThe specified command does not exist &f(&4%s&f)";
	
	public static final String CREATE_ALREADY_EXISTS =  PREFIX + "&cThere is already a gate leading to the specified server &f(&4%s&f)";
	public static final String CREATE_STEP_1 =  PREFIX + "Right click on one of the &6corner blocks &fof the gate";
	public static final String CREATE_STEP_2 =  PREFIX + "Right click on the &6opposite corner &fblock of the gate";
	public static final String CREATE_STEP_3 =  PREFIX + "Right click on the &6outward block &fof the gate (&6be wary of the direction you face&f)";
	public static final String CREATE = PREFIX + "You have successfully created a portal going to &6%s";
	
	public static final String DELETE_NO_EXIST = PREFIX + "&cThere is no gate leading to the specified server &f(&4%s&f)";
	public static final String DELETE_SUCCESS = PREFIX + "The gate leading to &6%s &fhas been deleted";
	
	public static final String GATE_LIST = PREFIX + "&6Gates: &f%s";
	
	public static final String[] format(String[] messages, Object... args) {
		return ChatColor.translateAlternateColorCodes('&', StringUtils.concat(messages, "\n")).split("\n");
	}
	
	public static final String format(String message, Object... args) {
		return ChatColor.translateAlternateColorCodes('&', String.format(message, args));
	}
	
}
