package it.edu.majoranapa.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.ini4j.Wini;

public class IniLoaderList {
	private Wini ini;
	private String listName = "";
	
	public IniLoaderList()
	{
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
	
	public void storeToIni(String listName, String[] listSectors) throws IOException
	{
		for(int i = 0; i < listSectors.length; i++)
			ini.put(listName, "sector" + (i + 1), listSectors[i]);
		
		ini.store();
	}
	
	public void getListSectors(String listname)
	{
		//TO-DO Add getter
	}
}
