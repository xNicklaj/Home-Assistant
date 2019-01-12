package it.edu.majoranapa;

public class Main {
	public static void main(String[] args) throws InterruptedException 
	{	
		Console CUI = new Console();
		while(CUI.newCommand() != -2)
			System.out.println("return: " + CUI.getLastReturnValue());
		
		System.exit(0);
	}
	
	public static void console() throws InterruptedException
	{
		MainThread thread = new MainThread();
	}

}

class MainThread implements Runnable {
	public void run()
	{
		Console CUI = new Console();
			try {
				CUI.newCommand();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("return: " + CUI.getLastReturnValue());
	}
}
