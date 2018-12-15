package it.edu.majoranapa;

public class Main {
	private static Console CUI = new Console();
	
	public static void main(String[] args) throws InterruptedException 
	{	
		
		while(CUI.newCommand() != -2)
			System.out.println("return: " + CUI.getLastReturnValue());
	}

}
