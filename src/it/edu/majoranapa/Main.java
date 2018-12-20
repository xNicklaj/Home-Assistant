package it.edu.majoranapa;

import it.edu.majoranapa.io.*;

public class Main {
	private static Volume volume;
	private static Console CUI = new Console(volume);
	
	public static void main(String[] args) throws InterruptedException 
	{	
		
		while(CUI.newCommand() != -2)
			System.out.println("return: " + CUI.getLastReturnValue());
		
		System.exit(0);
	}

}
