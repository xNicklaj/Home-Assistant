package it.edu.majoranapa.lists;

public class List {
	private String name = "";
	private String[] sectors;
	private int numberOfSectors = 0;
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void addSectors(String[] sectors)
	{
		String[] newArr = new String[this.sectors.length + sectors.length];
		if(numberOfSectors == 0)
		{
			this.sectors = new String[sectors.length];
		}
		else
		{
			for(int i = 0; i < this.sectors.length; i++)
			{
				newArr[i] = sectors[i];
			}
		}
		for(int i = numberOfSectors; i < numberOfSectors + sectors.length; i++)
		{
			newArr[i] = sectors[i - this.sectors.length]; 
		}
		
		this.sectors = newArr;
	}
	
	public void addSectors(String sectors)
	{
		String[] newArr;
		int sectorsCounter = 0;
		int nextSectorIndex = 0;
		int i = 0;
		for(; i < sectors.length(); i++)
		{
			if(sectors.charAt(i) == '\n' && sectors.charAt(i + 1) == '-' && sectors.charAt(i + 2) == '-')
				sectorsCounter++;
		}
		newArr = new String[sectorsCounter];
		/*
		for(int i; i < sectors.length(); i++)
		{
			if(sectors.charAt(i) == '-' && sectors.charAt(i + 1) == '-')
			*/
		
		sectorsCounter = 0;
		while(i < sectors.length())
		{
			if(sectors.charAt(i) == '-' && sectors.charAt(i + 1) == '-')
			{
					while(sectors.charAt(sectors.indexOf('-', nextSectorIndex) + 1) != '-')
					{
						nextSectorIndex = sectors.charAt(sectors.indexOf('-', nextSectorIndex));
					}
					this.sectors[sectorsCounter] = sectors.substring(i + 2, nextSectorIndex);
					i = nextSectorIndex;
			}
		}
		
		this.sectors = newArr;
		
		/*
		if(numberOfSectors == 0)
		{
			this.sectors = new String[sectors.length];
		}
		else
		{
			for(int i = 0; i < this.sectors.length; i++)
			{
				newArr[i] = sectors[i];
			}
		}
		for(int i = numberOfSectors; i < numberOfSectors + sectors.length; i++)
		{
			newArr[i] = sectors[i - this.sectors.length]; 
		}
		
		this.sectors = newArr;*/
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String toString()
	{
		return name + "\n"
				+ sectors.toString();
	}
}
