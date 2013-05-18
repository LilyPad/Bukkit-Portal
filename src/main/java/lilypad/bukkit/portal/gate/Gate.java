package lilypad.bukkit.portal.gate;

import org.bukkit.Location;

public class Gate {

	private String destinationServer;
	private int inwardMinX;
	private int inwardMaxX;
	private int inwardMinY;
	private int inwardMaxY;
	private int inwardMinZ;
	private int inwardMaxZ;
	private String inwardWorld;
	private int outwardX;
	private int outwardY;
	private int outwardZ;
	private int outwardYaw;
	private String outwardWorld;

	public Gate(String destinationServer, int inwardMinX, int inwardMaxX, int inwardMinY, int inwardMaxY, int inwardMinZ, int inwardMaxZ, String inwardWorld, int outwardX, int outwardY, int outwardZ, int outwardYaw, String outwardWorld) {
		this.destinationServer = destinationServer;
		this.inwardMinX = inwardMinX;
		this.inwardMaxX = inwardMaxX;
		this.inwardMinY = inwardMinY;
		this.inwardMaxY = inwardMaxY;
		this.inwardMinZ = inwardMinZ;
		this.inwardMaxZ = inwardMaxZ;
		this.inwardWorld = inwardWorld;
		this.outwardX = outwardX;
		this.outwardY = outwardY;
		this.outwardZ = outwardZ;
		this.outwardYaw = outwardYaw;
		this.outwardWorld = outwardWorld;
	}
	
	public boolean isInside(Location location) {
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		String world = location.getWorld().getName();
		if(x < this.inwardMinX || x > this.inwardMaxX) {
			return false;
		}
		if(y < this.inwardMinY || y > inwardMaxY) {
			return false;
		}
		if(z < this.inwardMinZ || z > this.inwardMaxZ) {
			return false;
		}
		if(!this.inwardWorld.equals(world)) {
			return false;
		}
		return true;
	}
	
	public String getDestinationServer() {
		return this.destinationServer;
	}
	
	public double getOutwardX() {
		return this.outwardX + 0.5;
	}

	public int getOutwardY() {
		return this.outwardY;
	}

	public double getOutwardZ() {
		return this.outwardZ + 0.5;
	}

	public int getOutwardYaw() {
		return this.outwardYaw;
	}

	public String getOutwardWorld() {
		return this.outwardWorld;
	}
	
	public static Gate fromString(String string) {
		String[] stringSplit = string.split("\\:");
		if(stringSplit.length != 13) {
			return null;
		}
		return new Gate(stringSplit[0], Integer.parseInt(stringSplit[1]), Integer.parseInt(stringSplit[2]), Integer.parseInt(stringSplit[3]), Integer.parseInt(stringSplit[4]), Integer.parseInt(stringSplit[5]), Integer.parseInt(stringSplit[6]), stringSplit[7], Integer.parseInt(stringSplit[8]), Integer.parseInt(stringSplit[9]), Integer.parseInt(stringSplit[10]), Integer.parseInt(stringSplit[11]), stringSplit[12]);
	}
	
	public static String toString(Gate gate) {
		return gate.destinationServer + ":" + gate.inwardMinX + ":" + gate.inwardMaxX + ":" + gate.inwardMinY + ":" + gate.inwardMaxY + ":" + gate.inwardMinZ + ":" + gate.inwardMaxZ + ":" + gate.inwardWorld + ":" + gate.outwardX + ":" + gate.outwardY + ":" + gate.outwardZ + ":" + gate.outwardYaw + ":" + gate.outwardWorld;
	}
	
	@Override
	public String toString() {
		return toString(this);
	}
	
}
