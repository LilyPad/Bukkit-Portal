package lilypad.bukkit.portal.gate;

public interface GateStore {

	public void loadGates();
	
	public void saveGates();
	
	public GateRegistry getGateRegistry();
	
	public void setGateRegistry(GateRegistry gateRegistry);

}
