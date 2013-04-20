package lilypad.bukkit.portal.user;

public class User {

	private String name;
	private String server;
	private String fromServer;
	
	public User(String name, String server) {
		this.name = name;
		this.server = server;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getServer() {
		return this.server;
	}
	
	public void setServer(String server) {
		this.server = server;
	}
	
	public String getFromServer() {
		return this.fromServer;
	}
	
	public void setFromServer(String fromServer) {
		this.fromServer = fromServer;
	}
	
	public static User fromString(String string) {
		String[] stringSplit = string.split("\\:");
		if(stringSplit.length != 2) {
			return null;
		}
		return new User(stringSplit[0], stringSplit[1]);
	}
	
	public static String toString(User user) {
		return user.name + ":" + user.server;
	}
	
	@Override
	public String toString() {
		return toString(this);
	}
	
}
