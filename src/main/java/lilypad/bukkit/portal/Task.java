package lilypad.bukkit.portal;

import org.bukkit.Bukkit;

public abstract class Task implements Runnable {

	private int taskId;

	public int getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public void cancelTask() {
		Bukkit.getServer().getScheduler().cancelTask(this.taskId);
	}
	
}
