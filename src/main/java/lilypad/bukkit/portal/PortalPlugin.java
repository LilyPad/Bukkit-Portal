package lilypad.bukkit.portal;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.Files;

import lilypad.bukkit.portal.command.PortalCommandExecutor;
import lilypad.bukkit.portal.command.create.CreateListener;
import lilypad.bukkit.portal.gate.GateListener;
import lilypad.bukkit.portal.gate.GateRegistry;
import lilypad.bukkit.portal.storage.Storage;
import lilypad.bukkit.portal.storage.impl.FileStorage;
import lilypad.client.connect.api.Connect;
import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.RedirectRequest;
import lilypad.client.connect.api.result.FutureResultListener;
import lilypad.client.connect.api.result.StatusCode;
import lilypad.client.connect.api.result.impl.RedirectResult;

public class PortalPlugin extends JavaPlugin implements IRedirector, IConfig {

	private GateRegistry gateRegistry;
	private GateListener gateListener;
	private CreateListener createListener;
	private Storage storage;

	@Override
	public void onLoad() {
		super.getConfig().options().copyDefaults(true);
		super.saveConfig();
		super.reloadConfig();
	}

	@Override
	public void onEnable() {
		// convert old store_gate.dat -> gates.dat
		File oldGatesFile = new File(super.getDataFolder(), "store_gate.dat");
		File gatesFile = new File(super.getDataFolder(), "gates.dat");
		if(oldGatesFile.exists()) {
			if(gatesFile.exists()) {
				oldGatesFile.delete();
			} else {
				try {
					Files.move(oldGatesFile, gatesFile);
				} catch(Exception exception) {
					exception.printStackTrace(); // what do?
				}
			}
		}
		
		try {
			this.gateRegistry = new GateRegistry();
			this.gateListener = new GateListener(this, this.gateRegistry, this);
			this.createListener = new CreateListener(this, this.gateRegistry);
			this.storage = new FileStorage(gatesFile);
			this.storage.setGateRegistry(this.gateRegistry);
			this.storage.loadGates();
			super.getServer().getPluginCommand("portal").setExecutor(new PortalCommandExecutor(this, this.gateRegistry, this.createListener));
			super.getServer().getScheduler().runTaskTimer(this, this.storage, 1200L, 1200L);
			super.getServer().getPluginManager().registerEvents(this.gateListener, this);
			super.getServer().getPluginManager().registerEvents(this.createListener, this);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		try {
			if(this.storage != null) {
				this.storage.saveAll();
			}
			if(this.gateRegistry != null) {
				this.gateRegistry.clear();
			}
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			this.gateRegistry = null;
			this.gateListener = null;
			this.createListener = null;
			this.storage = null;
		}
	}
	
	public void redirect(final Player player, String server) {
		try {
			this.getConnect().request(new RedirectRequest(server, player.getName())).registerListener(new FutureResultListener<RedirectResult>() {
				public void onResult(RedirectResult redirectResult) {
					if(redirectResult.getStatusCode() == StatusCode.SUCCESS) {
						return;
					}
					player.sendMessage(PortalPlugin.this.getMessage("server-offline"));
				}
			});
		} catch(RequestException exception) {
			// ignore
		}
	}
	
	public Connect getConnect() {
		return super.getServer().getServicesManager().getRegistration(Connect.class).getProvider();
	}

	public String getMessage(String string) {
		return ChatColor.translateAlternateColorCodes('&', super.getConfig().getString("messages." + string).replace("&n", "\n"));
	}

}
