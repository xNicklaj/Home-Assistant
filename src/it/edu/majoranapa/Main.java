package it.edu.majoranapa;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import it.edu.majoranapa.io.IniLocalLoader;
import it.edu.majoranapa.network.SocketIO;
import java.nio.file.Path;

public class Main {
	private static Path projectPath = Paths.get(new File("").getAbsolutePath());
	public static void main(String[] args) throws InterruptedException, URISyntaxException 
	{
		System.out.println(Main.getProjectPath());
		Thread server = new Thread(new SocketIO(3050));
		server.start(); // Start server listening
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
	public static Path getProjectPath()
	{
		return projectPath;
	}

}


class MainThread implements Runnable {
	public void run()
	{
		Console.getCommand();

		System.out.println("return: " + Console.getLastReturnValue());
	}
}
