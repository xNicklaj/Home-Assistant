package it.edu.majoranapa.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathFinder {
	private static Path projectPath = Paths.get(new File("").getAbsolutePath());
	
	public static Path getProjectPath()
	{
		return projectPath;
	}
	
	public static String getResourcePath(String resource)
	{
		return getProjectPath() + "/resources/" + resource;
	}
	
}
