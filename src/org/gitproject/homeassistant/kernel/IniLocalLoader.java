package org.gitproject.homeassistant.kernel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.InputMismatchException;

import application.enums.Theme;

import org.gitproject.homeassistant.network.DHCPManager;
import org.ini4j.Wini;

public class IniLocalLoader {
	private static Wini ini;
	private static Theme theme = Theme.LIGHT;
	private static String dateFormat = "dd/MM/yyyy";
	private static String timeFormat = "hh:mm";
	private static String deviceTimezone = "GMT+1";
	private static boolean useDHCP = true;
	private static String deviceIP = "127.0.0.1";
	
	private static Theme evaluateThemes() throws InputMismatchException
	{
		Theme[] themes = Theme.values();
		boolean hasBeenFound = false;
		int i = -1;
		do {
			i++;
			if(ini.get("localConfig", "theme").toLowerCase().equals(themes[i].toString().toLowerCase()))
			{
				hasBeenFound = true;
			}
		}while(i < themes.length && !hasBeenFound);
		if(!hasBeenFound)
			throw new InputMismatchException();
		
		
		return themes[i];
	}
	
	private static String evaluateDeviceTimezone() throws InputMismatchException
	{
		boolean hasBeenFound = false;
		int i = -1;
		do
		{
			i++;
			if(ini.get("timezoneConfig", "deviceTimezone").toUpperCase().equals("UTC+" + i))
				hasBeenFound = true;
			if(ini.get("timezoneConfig", "deviceTimezone").toUpperCase().equals("UTC-" + i))
			{
				hasBeenFound = true;
				i = -i;
			}
		}while(i < 13 && !hasBeenFound);
		
		if(!hasBeenFound)
			throw new InputMismatchException();
		
		if(i > 0)
			return "UTC+" + i;
		else if(i < 0)
			return "UTC" + i;
		else
			return "UTC+0";
	}
	
	public static void createIni() throws IOException
	{
		try {
			if(Files.notExists(Paths.get(PathFinder.getProjectPath() + "/config")))
				Files.createDirectories(Paths.get(PathFinder.getProjectPath() + "/config"));
			if(Files.notExists(Paths.get(PathFinder.getProjectPath() + "/config/local.ini")))
				Files.createFile(Paths.get(PathFinder.getProjectPath() + "/config/local.ini"));
		}catch(InvalidPathException e)
		{
			if(Files.notExists(Paths.get(PathFinder.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6) + "config")))
				Files.createDirectories(Paths.get(PathFinder.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6) + "config"));
			if(Files.notExists(Paths.get(PathFinder.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6) + "config/local.ini")))
				Files.createFile(Paths.get(PathFinder.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6) + "config/local.ini"));
		}
	}
	
	public static void updateIni() throws IOException
	{
		ini.put("localConfig", "theme", "light");
		ini.put("timezoneConfig", "dateFormat", "dd/MM/yyyy");
		ini.put("timezoneConfig", "timeFormat", "hh:mm");
		ini.put("timezoneConfig", "deviceTimezone", "UTC+1");
		ini.put("networkConfig", "useDHCP", "true");
		ini.put("networkConfig", "deviceIP", DHCPManager.updateIP());
		ini.store();
	}
	
	public static void iniLoad() throws IOException
	{
		try {
			createIni();
			ini = new Wini(new File(PathFinder.getProjectPath().toString() + "/config/local.ini"));
			ini.load();
			updateIniValues();
		}
		catch(NullPointerException e)
		{
			updateIni();
		}
	}
	
	public static int updateIniValues() throws IOException
	{	
		try {
			theme = evaluateThemes();
		}catch(InputMismatchException e)
		{
			System.err.println("The ini file is corrupted.\n"
					+ e.toString() + "\n"
					+ "Reverting to base theme.");
			ini.put("localConfig", "theme", "light");
			ini.store();
			theme = evaluateThemes();
		}
		switch(ini.get("timezoneConfig", "dateFormat").toLowerCase())
		{
		case "dd/mm/yyyy":
		case "mm/dd/yyyy":
		case "yyyy/mm/dd":
			dateFormat = ini.get("timezoneConfig", "dateFormat").toLowerCase();
			break;
		default:
			System.err.println("The ini file is corrupted.\n"
					+ "Reverting to base date format.");
			ini.put("localConfig", "dateFormat", "dd/MM/yyyy");
			ini.store();
			dateFormat = "dd/mm/yyyy";
		}
		switch(ini.get("timezoneConfig", "timeFormat").toLowerCase())
		{
		case "hh:mm":
		case "mm:hh":
		case "hh:mm:ss":
			dateFormat = ini.get("timezoneConfig", "dateFormat").toLowerCase();
			break;
		default:
			System.err.println("The ini file is corrupted.\n"
					+ "Reverting to base time format.");
			ini.put("localConfig", "timeFormat", "hh:mm");
			ini.store();
			dateFormat = "hh:mm";
		}
		
		try {
			deviceTimezone = evaluateDeviceTimezone();
		}catch(InputMismatchException e)
		{
			System.err.println("The ini file is corrupted.\n"
					+ e.toString() + "\n"
					+ "Reverting to base timezone.");
			ini.put("localConfig", "deviceTimezone", "GMT+1");
			ini.store();
			deviceTimezone = "GMT+1";
		}
		switch(ini.get("networkConfig", "useDHCP"))
		{
		case "true":
			useDHCP = true;
			
			break;
		case "false":
			useDHCP = false;
			break;
		default:
			System.err.println("The ini file is corrupted.\n"
					+ "Reverting to base DHCP settings.");
			ini.put("networkConfig", "useDHCP", "true");
			ini.store();
			useDHCP = true;
			DHCPManager.updateIP();
		}
		deviceIP = ini.get("networkConfig", "deviceIP");

		return 0;
	}
	
}
