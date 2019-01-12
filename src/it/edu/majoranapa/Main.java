package it.edu.majoranapa;

public class Main {
	public static void main(String[] args) throws InterruptedException 
	{	
		while(Console.newCommand() != -2)
			System.out.println("return: " + Console.getLastReturnValue());
		
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
			try {
				Console.newCommand();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("return: " + Console.getLastReturnValue());
	}
}
