package lilypad.bukkit.portal.user;

public interface UserStorage {

	public void loadUsers();

	public void saveUsers();

	public UserRegistry getUserRegistry();

	public void setUserRegistry(UserRegistry userRegistry);

}
