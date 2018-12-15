package it.edu.majoranapa;

import java.util.Scanner;
import it.edu.majoranapa.timers.*;

public class Console {
	private String lastCommand = "";
	private int lastReturnValue = 0;
	private String command = "";
	private Scanner scan;
	
	private String getParam(String param)
	{
		if(command.contains(param))
		{
			try {
				return command.substring(command.indexOf(param) + param.length(), command.indexOf(' ', command.indexOf(param)));
			}
			catch(java.lang.StringIndexOutOfBoundsException e)
			{
				return command.substring(command.indexOf(param) + param.length());
			}
		}
		return "";
	}

	private int timer() throws InterruptedException
	{
		VirtualTimer timer = new VirtualTimer();
		if(command.contains("start"))
		{
			if(!command.contains("delay"))
				return -1;
			
			timer.setDelay(Integer.parseInt(getParam("delay=")));
			timer.start();
			return 0;
		}
		
		if(command.contains("stop"))
		{
			timer.join();
			return 0;
		}
	
		return 0;
	}

	public Console()
	{
		scan = new Scanner(System.in);
	}
	
	public int getLastReturnValue()
	{
		return this.lastReturnValue;
	}
	
	public String getLastCommand()
	{
		return lastCommand; 
	}
	
	public int newCommand() throws InterruptedException
	{
		lastCommand = command;
		command = scan.nextLine();
		
		if(command.contains("timer"))
			return timer();
		
		if(command.contains("exit"))
			return -2;
		
		return 0;
	}
	
	public void close()
	{
		scan.close();
	}
}
