package it.edu.majoranapa.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.ini4j.Wini;

public class IniListLoader {
	private Wini ini;
	private String listName = "";
	
	public IniListLoader(String listName)
	{
		this.listName = listName;
		try {
			if(Files.notExists(Paths.get(PathFinder.getResourcePath("userdata"))))
				Files.createDirectory(Paths.get(PathFinder.getResourcePath("userdata")));
			if(Files.notExists(Paths.get(PathFinder.getResourcePath("userdata/lists.ini"))))
				Files.createFile(Paths.get(PathFinder.getResourcePath("userdata/lists.ini")));
			ini = new Wini(new File(PathFinder.getResourcePath("userdata/lists.ini")));
		}catch(NullPointerException e)
		{
			System.err.println("Non ci sono liste.");
		} catch (IOException e) {
			System.err.println("Non ci sono liste.");
		}
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