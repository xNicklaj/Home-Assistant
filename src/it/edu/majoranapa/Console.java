package it.edu.majoranapa;

import java.util.Scanner;
import it.edu.majoranapa.timers.*;
import it.edu.majoranapa.io.*;

public class Console {
	private String lastCommand = "";
	private int lastReturnValue = 0;
	private String command = "";
	private Scanner scan;
	TimeBased_Alarms alarm = new TimeBased_Alarms();
	
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
			timer.join();
			return 0;
		}
		
		if(command.contains("stop"))
		{
			timer.join();
			return 0;
		}
	
		return -1;
	}
	
	private int audio()
	{
		if(command.contains("set"))
		{
			switch(getParam("channel="))
			{
			case "alarm":
				return Volume.setAlarmVolumePercentage(Float.parseFloat(getParam("volume=")));
			case "system":
				return Volume.setSystemVolumePercentage(Float.parseFloat(getParam("volume=")));
			case "media":
				return Volume.setMediaVolumePercentage(Float.parseFloat(getParam("volume=")));
			}
		}
		
		return -1;
	}
	
	private int alarm()
	{
		if(command.contains("getinfo"))
		{
			alarm.getSysTimeAndDate();
			return 0;
		}
		
		if(command.contains("showcalendar"))
		{
			alarm.showCalendar();
			return 0;
		}
		
		if(command.contains("gettime"))
		{
			System.out.println(alarm.getTime());
			return 0;
		}
		
		if(command.contains("toggleday"))
		{
			alarm.toggleDay(Integer.parseInt(getParam("day=")));
			return 0;
		}
		
		if(command.contains("settime"))
		{
			return alarm.setTime(this.getParam("time="));
		}
		
		return -1;
	}

	/**
	 * This is a class Constructor.
	 * @param volume
	 * This is the referee to the Volume class containing System and Media volumes.
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
		command = scan.nextLine().toLowerCase();
		
		/*
		 * Commands:
		 * 	start delay=n
		 * 	stop
		 */
		if(command.substring(0, 7).contains("timer"))
			return timer();
		
		if(command.substring(0, 7).contains("alarm"))
			return alarm();
		
		if(command.substring(0, 7).contains("audio"))
			return audio();
		
		if(command.substring(0, 7).contains("exit"))
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
