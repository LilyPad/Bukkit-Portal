package lilypad.bukkit.portal.storage.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import lilypad.bukkit.portal.gate.Gate;
import lilypad.bukkit.portal.gate.GateRegistry;
import lilypad.bukkit.portal.storage.Storage;


public class FileStorage extends Storage {

	private File gateFile;
	
	public FileStorage(File gateFile) throws Exception {
		this.gateFile = gateFile;
		this.gateFile.createNewFile();
	}
	
	public void loadGates() {
		GateRegistry gateRegistry = this.getGateRegistry();
		if(gateRegistry == null) {
			throw new IllegalStateException();
		}
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(this.gateFile));
			List<Gate> result = new ArrayList<Gate>();
			String line;
			while((line = bufferedReader.readLine()) != null) {
				result.add(Gate.fromString(line.trim()));
			}
			gateRegistry.addAll(result);
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch(Exception exception) {
					//ignore
				}
			}
		}
	}

	public void saveGates() {
		GateRegistry gateRegistry = this.getGateRegistry();
		if(gateRegistry == null) {
			throw new IllegalStateException();
		}
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(this.gateFile));
			for(Gate gate : gateRegistry.getAll()) {
				bufferedWriter.append(gate.toString());
				bufferedWriter.newLine();
			}
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			if(bufferedWriter != null) {
				try {
					bufferedWriter.flush();
				} catch(Exception exception) {
					//ignore
				}
				try {
					bufferedWriter.close();
				} catch(Exception exception) {
					//ignore
				}
			}
		}
	}

}
