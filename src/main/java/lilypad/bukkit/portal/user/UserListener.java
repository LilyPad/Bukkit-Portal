package lilypad.bukkit.portal.user;

import lilypad.bukkit.portal.IConnector;
import lilypad.bukkit.portal.IRedirector;
import lilypad.client.connect.api.event.EventListener;
import lilypad.client.connect.api.event.MessageEvent;

import org.bukkit.event.Listener;

public class UserListener implements Listener {

	private IConnector connector;
	private IRedirector redirector;

	public UserListener(IConnector connector, IRedirector redirector) {
		this.connector = connector;
		this.redirector = redirector;
	}


	@EventListener
	public void onMessage(MessageEvent messageEvent) {
		if(!messageEvent.getChannel().equals("lpPortal")) {
			return;
		}
		try {
			String[] message = messageEvent.getMessageAsString().trim().split(" ");
			if(message.length != 2) {
				return;
			}
			if(message[0].equals("REQUEST")) {
				this.redirector.redirect(message[1], this.connector.getConnect().getSettings().getUsername());
			}
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

}
