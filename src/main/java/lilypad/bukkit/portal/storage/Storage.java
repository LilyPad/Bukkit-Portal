package lilypad.bukkit.portal.storage;

import lilypad.bukkit.portal.gate.GateRegistry;
import lilypad.bukkit.portal.gate.GateStorage;

public abstract class Storage implements GateStorage, Runnable {

	private GateRegistry gateRegistry;

	public void saveAll() {
		this.saveGates();
	}

	public GateRegistry getGateRegistry() {
		return this.gateRegistry;
	}

	public void setGateRegistry(GateRegistry gateRegistry) {
		this.gateRegistry = gateRegistry;
	}

	public void run() {
		this.saveAll();
	}

}
