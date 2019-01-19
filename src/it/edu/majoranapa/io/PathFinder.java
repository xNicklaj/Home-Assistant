package it.edu.majoranapa.io;

import it.edu.majoranapa.Main;

public class PathFinder {
	private static String filePath = "";
	
	/**
	 * This method retrieves the path of a file into the /resources folder.
	 * @param resource
	 * name + extension of the file to retrieve as a String.
	 * @return
	 * This method returns the absolute path of the file.
	 */
	
	public static String getResourcePath(String resource)
	{
		return Main.getProjectPath() + "/resources/" + resource;
	}
	
}
