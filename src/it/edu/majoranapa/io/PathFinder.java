package it.edu.majoranapa.io;

import it.edu.majoranapa.Main;

public class PathFinder {
	private String filePath = "";
	
	/**
	 * This method converts an into to a boolean in a C-like manner.
	 * @param num
	 * Integer number to convert.
	 * @return
	 * The method returns false if the parameter is 0.
	 * Every other number returns true.
	 */
	private boolean intToBool(int num)
	{
		if(num == 0)
			return false;
		
		return true;
	}
	
	public void nulla()
	{
		
	}

	private void evaluatePath()
	{
		int beginIndex = 0, endIndex = 5;
		while(intToBool(filePath.substring(beginIndex, endIndex).compareTo("file:")))
		{
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
	private int evaluateProgPath()
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
	 * This is a class constructor.
	 * It sets the path of the Main class, and prepares it
	 * to be used in other methods from this Class.
	 */
	public PathFinder()
	{
		this.filePath = Main.class.getResource(Main.class.getSimpleName() + ".class").toString();
	}

	/**
	 * This is a class constructor.
	 * It sets the path of a custom class, and prepares it
	 * to be used in other methods from this Class.
	 * This is useful if your Main class has been renamed or refactored.
	 * @param filePath
	 * This is the path of the custom class to load.
	 * Example:
	 * new PathFinder(CustomClass.class.getResource(CustomClass.class.getSimpleName() + ".class").toString());
	 */
	public PathFinder(String filePath)
	{
		this.filePath = filePath;
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
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
	
	/**
	 * This method retrieves the path of a file into the /resources folder.
	 * @param resource
	 * name + extension of the file to retrieve as a String.
	 * @return
	 * This method returns the absolute path of the file.
	 */
	public String getResourcePath(String resource)
	{
		int endIndex = evaluateProgPath();
		return filePath.substring(0, endIndex) + "resources/" + resource;
	}
	
}
