package org.gitproject.homeassistant.lists;

import java.io.File;

public class MapValue {
	private Bill Bill;
	private File file;
	
	public MapValue(Bill bill, File file) {
		this.Bill = bill;
		this.file = file;
	}

	public Bill getBill() {
		return Bill;
	}

	public void setBill(Bill bill) {
		Bill = bill;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
