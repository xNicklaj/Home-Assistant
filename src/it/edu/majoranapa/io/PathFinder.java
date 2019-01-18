package it.edu.majoranapa.io;

import it.edu.majoranapa.Main;

public class PathFinder {
	private static String filePath = "";
	
	/**
	 * This method converts an into to a boolean in a C-like manner.
	 * @param num
	 * Integer number to convert.
	 * @return
	 * The method returns false if the parameter is 0.
	 * Every other number returns true.
	 */
	private static boolean intToBool(int num)
	{
		if(num == 0)
			return false;
		
		return true;
	}
	
	private static void evaluatePath()
	{
		int beginIndex = 0, endIndex = 5;
		while(intToBool(filePath.substring(beginIndex, endIndex).compareTo("file:")))
		{
			if(filePath.substring(beginIndex, endIndex).startsWith("/"))
				return;
			
			System.out.println(filePath.substring(beginIndex, endIndex));
			beginIndex++;
			endIndex++;
		}
		filePath = filePath.substring(endIndex);
	}

	/**
	 * This method evaluates the absolute path of the Home folder of the project.
	 * @return
	 * String containing the path of the Home folder.
	 */
	private static int evaluateProgPath()
	{
		int beginIndex = 0, endIndex = 15;
		evaluatePath();
		while(intToBool(filePath.substring(beginIndex, endIndex).compareTo("Home-Assistant/")))
		{
			beginIndex++;
			endIndex++;
		}
		return endIndex;
	}

	/**
	 * This method sets the path of a custom class, and prepares it
	 * to be used in other methods from this Class.
	 * This is useful if your Main class has been renamed or refactored.
	 * @param filePath
	 * This is the path of the custom class to load.
	 * Example:
	 * new PathFinder(CustomClass.class.getResource(CustomClass.class.getSimpleName() + ".class").toString());
	 */
	
	public static void setMainClassPath()
	{
		filePath = Main.class.getResource(Main.class.getSimpleName() + ".class").toString();;
	}
	
	/**
	 * This method retrieves the path of a file into the /resources folder.
	 * @param resource
	 * name + extension of the file to retrieve as a String.
	 * @return
	 * This method returns the absolute path of the file.
	 */
	public static String getResourcePath(String resource)
	{
		setMainClassPath();
		int endIndex = evaluateProgPath();
		return filePath.substring(1, endIndex) + "resources/" + resource;
	}
	
}
