package it.edu.majoranapa;

import java.util.Scanner;
import it.edu.majoranapa.timers.*;

public class Console {
	private String lastCommand = "";
	private int lastReturnValue = 0;
	private String command = "";
	private Scanner scan;
	
	/**
	 * This method helps by getting a certain passed parameter.
	 * @param param
	 * Parameter to look for.
	 * @return
	 * String containing the value of the parameter.
	 * Example:
	 * In the command "timer start delay=4",
	 * The return string will be "4".
	 */
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

	/**
	 * This method handles timers.
	 * @return
	 * It returns -1 if there has been an error, and 0 if
	 * everything works correctly.
	 * @throws InterruptedException
	 */
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
	
	private int alarm()
	{
		TimeBased_Alarms alarm = new TimeBased_Alarms();
		if(command.contains("getInfo"))
		{
			alarm.printOut();
		}
		
		return 0;
	}

	/**
	 * This is a class Constructor.
	 */
	public Console()
	{
		super();
		scan = new Scanner(System.in);
	}
	
	/**
	 * This method returns an integer containing the return
	 * value of the last command.
	 * @return
	 * Return value of the last command.
	 */
	public int getLastReturnValue()
	{
		return this.lastReturnValue;
	}
	
	/**
	 * This method returns a string containing the last
	 * requested command.
	 * @return
	 * Last requested command.
	 */
	public String getLastCommand()
	{
		return lastCommand; 
	}
	
	/**
	 * This method is the actual console function that allows the user to insert a new command.
	 * @return
	 * This method returns -2 as exit value, 0 as a working value, or
	 * -1 as error value.
	 * @throws InterruptedException
	 * Might throw an exception due to timers threaded nature.
	 * Anyways, it should be checked automatically.
	 */
	public int newCommand() throws InterruptedException
	{
		lastCommand = command;
		command = scan.nextLine();
		
		/*
		 * Commands:
		 * 	start delay=n
		 * 	stop
		 */
		if(command.contains("timer"))
			return timer();
		
		if(command.contains("alarm"))
			return alarm();
		
		if(command.contains("exit"))
			return -2;
		
		return 0;
	}
	
	/**
	 * Use this method to finally close the console.
	 * Note: After being closed you will have to allocate a new console Object.
	 */
	public void close()
	{
		scan.close();
	}
}
