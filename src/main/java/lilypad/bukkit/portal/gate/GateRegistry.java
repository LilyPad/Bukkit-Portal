package lilypad.bukkit.portal.gate;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Location;

public class GateRegistry {

	private Map<String, Gate> gates = new ConcurrentHashMap<String, Gate>();

	public void register(Gate gate) {
		if(gate == null) {
			return;
		}
		this.gates.put(gate.getDestinationServer(), gate);
	}

	public void unregister(Gate gate) {
		this.gates.remove(gate.getDestinationServer());
	}

	public void addAll(Collection<Gate> gates) {
		for(Gate gate : gates) {
			this.register(gate);
		}
	}

	public Gate getByDestinationServer(String destinationServer) {
		return this.gates.get(destinationServer);
	}

	public Gate getByLocation(Location location) {
		for(Gate gate : this.getAll()) {
			if(!gate.isInside(location)) {
				continue;
			}
			return gate;
		}
		return null;
	}

	public Collection<Gate> getAll() {
		return this.gates.values();
	}

	public boolean hasByDestinationServer(String destinationServer) {
		return this.gates.containsKey(destinationServer);
	}

	public void clear() {
		this.gates.clear();
	}

}
