package lilypad.bukkit.portal.user;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRegistry {

	private Map<String, User> users = new ConcurrentHashMap<String, User>();

	public void register(User user) {
		if(user == null) {
			return;
		}
		this.users.put(user.getName(), user);
	}

	public void unregister(User user) {
		this.users.remove(user);
	}

	public void addAll(Collection<User> users) {
		for(User user : users) {
			this.register(user);
		}
	}

	public User getByName(String name) {
		return this.users.get(name);
	}

	public Collection<User> getAll() {
		return this.users.values();
	}

	public boolean hasName(String name) {
		return this.users.containsKey(name);
	}

	public void clear() {
		this.users.clear();
	}

}
