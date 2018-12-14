package it.edu.majoranapa.io;

public class PathFinder {
	private String filePath = "";
	
	public PathFinder(String filePath)
	{
		this.filePath = filePath;
	}
	
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
	
	private boolean intToBool(int num)
	{
		if(num == 0)
			return false;
		
		return true;
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
	
	public String getResourcePath(String resource)
	{
		int endIndex = evaluateProgPath();
		return filePath.substring(0, endIndex) + "resources/" + resource;
	}
	
}
