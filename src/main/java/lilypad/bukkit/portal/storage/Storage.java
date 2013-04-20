package lilypad.bukkit.portal.storage;

import lilypad.bukkit.portal.gate.GateRegistry;
import lilypad.bukkit.portal.gate.GateStorage;
import lilypad.bukkit.portal.user.UserRegistry;
import lilypad.bukkit.portal.user.UserStorage;

public abstract class Storage implements GateStorage, UserStorage, Runnable {

	private GateRegistry gateRegistry;
	private UserRegistry userRegistry;

	public void saveAll() {
		this.saveGates();
		this.saveUsers();
	}

	public GateRegistry getGateRegistry() {
		return this.gateRegistry;
	}

	public void setGateRegistry(GateRegistry gateRegistry) {
		this.gateRegistry = gateRegistry;
	}

	public UserRegistry getUserRegistry() {
		return this.userRegistry;
	}

	public void setUserRegistry(UserRegistry userRegistry) {
		this.userRegistry = userRegistry;
	}

	public void run() {
		this.saveAll();
	}

}
