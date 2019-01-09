package it.edu.majoranapa;

import it.edu.majoranapa.io.*;

public class Main {
	public static void main(String[] args) throws InterruptedException 
	{	
		Console CUI = new Console();
		while(CUI.newCommand() != -2)
			System.out.println("return: " + CUI.getLastReturnValue());
		
		System.exit(0);;
	}

}
