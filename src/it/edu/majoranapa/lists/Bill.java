package it.edu.majoranapa.lists;

import org.ini4j.Wini;

import it.edu.majoranapa.io.IniListLoader;

public class Bill {
	private String name = "";
	private BillType type;
	private IniListLoader loader;
	
	public Bill(String name, BillType type){
		this.name = name;
		this.type = type;
		if(this.name != "")
			loader = new IniListLoader("bills", this.name);
		else
			loader = new IniListLoader("bills", type.toString().toLowerCase());
	}
	
	public void storeToFile()
	{
		Wini ini = loader.overrideIniUsage();
		String key = type.toString().toLowerCase();
		ini.put(key, "name", name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BillType getType() {
		return type;
	}
	public void setType(BillType type) {
		this.type = type;
	}
	
	
}
