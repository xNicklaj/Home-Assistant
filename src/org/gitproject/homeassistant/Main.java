package org.gitproject.homeassistant;

import java.io.IOException;
import java.net.URISyntaxException;

import org.gitproject.homeassistant.kernel.IniLocalLoader;
import org.gitproject.homeassistant.network.DHCPManager;
import org.gitproject.homeassistant.network.SocketIO;

public class Main {
	public static void main(String[] args) throws InterruptedException, URISyntaxException 
	{
		Thread server = new Thread(new SocketIO(3050));
		server.start(); // Start server listening
		SystemInfo info = new SystemInfo();
		info.start();
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
