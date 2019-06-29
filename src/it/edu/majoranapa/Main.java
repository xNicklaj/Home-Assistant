package it.edu.majoranapa;

import java.io.IOException;
import java.net.URISyntaxException;

import it.edu.majoranapa.kernel.IniLocalLoader;
import it.edu.majoranapa.network.DHCPManager;
import it.edu.majoranapa.network.SocketIO;

public class Main {
	public static void main(String[] args) throws InterruptedException, URISyntaxException 
	{
		Thread server = new Thread(new SocketIO(3050));
		server.start(); // Start server listening
		Userdata.load();
		try {
			IniLocalLoader.iniLoad();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
