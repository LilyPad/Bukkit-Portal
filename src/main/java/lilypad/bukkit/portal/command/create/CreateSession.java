package lilypad.bukkit.portal.command.create;

import lilypad.bukkit.portal.gate.Gate;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;


public class CreateSession {

	public enum State {
		INWARD_CORNER_1,
		INWARD_CORNER_2,
		OUTWARD;
	}

	private State state;
	private Block inwardCorner1;
	private Block inwardCorner2;
	private Block outward;
	private int outwardYaw;

	private Player player;
	private String destinationServer;

	public CreateSession(Player player, String destinationServer) {
		this.state = State.INWARD_CORNER_1;
		this.player = player;
		this.destinationServer = destinationServer;
	}

	public Gate createGate() {
		int inwardMinX;
		if(this.inwardCorner1.getX() < this.inwardCorner2.getX()) {
			inwardMinX = this.inwardCorner1.getX();
		} else {
			inwardMinX = this.inwardCorner2.getX();
		}
		int inwardMaxX;
		if(this.inwardCorner1.getX() > this.inwardCorner2.getX()) {
			inwardMaxX = this.inwardCorner1.getX();
		} else {
			inwardMaxX = this.inwardCorner2.getX();
		}
		int inwardMinY;
		if(this.inwardCorner1.getY() < this.inwardCorner2.getY()) {
			inwardMinY = this.inwardCorner1.getY();
		} else {
			inwardMinY = this.inwardCorner2.getY();
		}
		int inwardMaxY;
		if(this.inwardCorner1.getY() > this.inwardCorner2.getY()) {
			inwardMaxY = this.inwardCorner1.getY();
		} else {
			inwardMaxY = this.inwardCorner2.getY();
		}
		int inwardMinZ;
		if(this.inwardCorner1.getZ() < this.inwardCorner2.getZ()) {
			inwardMinZ = this.inwardCorner1.getZ();
		} else {
			inwardMinZ = this.inwardCorner2.getZ();
		}
		int inwardMaxZ;
		if(this.inwardCorner1.getZ() > this.inwardCorner2.getZ()) {
			inwardMaxZ = this.inwardCorner1.getZ();
		} else {
			inwardMaxZ = this.inwardCorner2.getZ();
		}
		String inwardWorld = this.inwardCorner1.getWorld().getName();
		int outwardX = this.outward.getX();
		int outwardY = this.outward.getY();
		int outwardZ = this.outward.getZ();
		int outwardYaw = this.outwardYaw;
		String outwardWorld = this.outward.getWorld().getName();
		return new Gate(this.destinationServer, inwardMinX, inwardMaxX, inwardMinY, inwardMaxY, inwardMinZ, inwardMaxZ, inwardWorld, outwardX, outwardY, outwardZ, outwardYaw, outwardWorld);
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setInwardCorner1(Block block) {
		this.inwardCorner1 = block;
	}

	public void setInwardCorner2(Block block) {
		this.inwardCorner2 = block;
	}

	public void setOutward(Block block) {
		this.outward = block;
	}
	
	public void setOutwardYaw(int yaw) {
		this.outwardYaw = yaw;
	}

	public Player getPlayer() {
		return this.player;
	}
	
	public String getDestinationServer() {
		return this.destinationServer;
	}

}
