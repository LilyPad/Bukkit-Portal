package lilypad.bukkit.portal.gate;

public interface GateStorage {

	public void loadGates();
	
	public void saveGates();
	
	public GateRegistry getGateRegistry();
	
	public void setGateRegistry(GateRegistry gateRegistry);

}
