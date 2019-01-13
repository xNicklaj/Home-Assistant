package it.edu.majoranapa;

import it.edu.majoranapa.network.SocketIO;

public class Main {
	public static void main(String[] args) throws InterruptedException 
	{
		Thread server = new Thread(new SocketIO(3050));
		server.start(); // Start server listening
		while(Console.getCommand() != -2)
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
		Console.getCommand();

		System.out.println("return: " + Console.getLastReturnValue());
	}
}
