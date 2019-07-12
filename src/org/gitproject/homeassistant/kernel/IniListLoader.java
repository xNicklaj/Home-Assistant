package org.gitproject.homeassistant.kernel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.ini4j.Wini;

public class IniListLoader {
	private Wini ini;
	private String fileName = "";
	private String listName = "";
	
	public IniListLoader(String filename, String listName)
	{
		this.listName = listName;
		this.fileName = filename;
		try {
			if(Files.notExists(Paths.get(PathFinder.getResourcePath("userdata"))))
				Files.createDirectory(Paths.get(PathFinder.getResourcePath("userdata")));
			if(Files.notExists(Paths.get(PathFinder.getResourcePath("userdata/" + fileName + ".ini"))))
				Files.createFile(Paths.get(PathFinder.getResourcePath("userdata/" + fileName + ".ini")));
			ini = new Wini(new File(PathFinder.getResourcePath("userdata/" + fileName + ".ini")));
		}catch(NullPointerException e)
		{
			System.err.println("Non ci sono liste.");
		} catch (IOException e) {
			System.err.println("Non ci sono liste.");
		}
	}
	
	public Wini overrideIniUsage()
	{
		return ini;
	}
	
	public void storeToIni(String[] sectorsList) throws IOException
	{
		for(int i = 0; i < sectorsList.length; i++)
			ini.put(listName, "sector" + (i + 1), sectorsList[i]);
		
		ini.store();
	}
	
	public String[] getListSectors(String listName)
	{
		String[] newArr;
		int sectorsNumber = 0;
		while(true)
		{
			try {
				ini.get(listName, "sector" + (sectorsNumber + 1));
			}catch(NullPointerException e)
			{	
				break;
			}
		}
		newArr = new String[sectorsNumber];
		for(int i = 0; i < sectorsNumber; i++)
			newArr[i] = ini.get(listName, "Sector" + (i + 1));
		
		return newArr;
	}
}
