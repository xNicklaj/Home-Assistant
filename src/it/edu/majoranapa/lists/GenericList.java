package it.edu.majoranapa.lists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.edu.majoranapa.io.IniListLoader;

public class GenericList{
	private List<String> list;
	private String name = "";
	private int numberOfSectors = 0;
	private IniListLoader loader;
	
	public GenericList(String name)
	{
		list = new ArrayList<>();
		this.name = name;
		loader = new IniListLoader(name);
	}
	
	public void addSectors(String[] sectors)
	{
		for(int i = 0; i < sectors.length; i++)
			list.add(sectors[i]);
		numberOfSectors += sectors.length;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void storeToIni() throws IOException
	{
		loader.storeToIni(list.toArray(new String[list.size()]));
	}
	
	public void retrieveFromIni()
	{
		addSectors(loader.getListSectors(name));
	}
	
	public String toString()
	{
		String toReturn = name + ":" + "\n";
		for(int i = 0; i < list.size(); i++)
			toReturn.concat(list.get(i) + "\n");
		
		return toReturn;
	}
}
