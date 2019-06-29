package it.edu.majoranapa;

import java.util.Scanner;
import it.edu.majoranapa.timers.*;
import it.edu.majoranapa.network.*;
import it.edu.majoranapa.customplayer.*;
import it.edu.majoranapa.kernel.*;
import it.edu.majoranapa.lists.Bill;
import it.edu.majoranapa.lists.BillType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;

public class Console{
	private static String lastCommand = "";
	private static int lastReturnValue = 0;
	private static String command = "";
	private static Scanner scan = new Scanner(System.in);
	private static TimeBased_Alarms alarm = new TimeBased_Alarms();
	private static VirtualTimer timer;

	/**
	 * This method handles timers.
	 * @return
	 * It returns -1 if there has been an error, and 0 if
	 * everything works correctly.
	 * @throws InterruptedException
	 */
	private static int timer() throws InterruptedException
	{
		if(command.contains("start"))
		{
			Console.timer = new VirtualTimer();
			if(!command.contains("delay"))
				return -1;

			Console.timer.setDelay(Integer.parseInt(getParam("delay=")));
			Console.timer.run();
			return 0;
		}
		if(command.contains("stop"))
		{
			Console.timer.stopTask();
			Console.timer = null;
			return 0;
		}
		

		return -1;
	}

	private static int audio()
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

	private static int alarm()
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
			return alarm.setTime(getParam("time="));
		}

		return -1;
	}
	
	private static int bill()
	{
		if(command.contains("add"))
		{
			Bill bill = new Bill(getParam("name="), BillType.valueOf(getParam("type=").toUpperCase()), Double.parseDouble(getParam("value=")), Integer.parseInt(getParam("recurrency=")));
			Userdata.bills.addBill(bill);
			return 0;
		}
		else if(command.contains("remove"))
		{
			return Userdata.bills.removeBill(Integer.parseInt(getParam("index=")));
		}
		if(command.contains("show"))
		{
			System.out.println(Userdata.bills.toString());
			return 0;
		}
		
		return -1;
	}

	private static int media()
	{
		Player player;
		String resource = getParam("resource=");
		try
		{
			if(resource != "")
				player = new Player(new Media("file:///" + PathFinder.getResourcePath("MP3/" + resource)));
			player = new Player(new Media("file://" + getParam("file=")));
		}
		catch(MediaException | IllegalArgumentException e)
		{
			System.out.println(resource);
			System.out.println("file://" + PathFinder.getResourcePath(getParam("resource=")));
			System.out.println("file://" + PathFinder.getResourcePath(getParam("file=")));
		}
		player = new Player(null);
		if(command.contains("setvolume"))
		{
			player.setVolumePercentage(Float.parseFloat(getParam("volume=")));
			return 0;
		}

		return -1;
	}
	
	private static int getLastParamFinalIndex()
	{
		int i = command.substring(0, 26).lastIndexOf('=');
		while(command.charAt(i) != ' ')
		{
			i++;
		}
		
		return i + 1;
	}

	private static int network()
	{
		if(getParam("ip=") == "")
			return -1;
		else
			if(getParam("port=") != "")
				SocketIO.sendMessage(getParam("ip="), command.substring(getLastParamFinalIndex()), Integer.parseInt(getParam("port=")));
			else
				SocketIO.sendMessage(getParam("ip="), command.substring(getLastParamFinalIndex()));
		return 0;
	}
	
	/**
	 * This method helps by getting a certain passed parameter.
	 * @param param
	 * Parameter to look for.
	 * @return
	 * String containing the value of the parameter.
	 * Example:
	 * In the command "timer start delay=4",
	 * The return string will be "4"
	 */
	public static String getParam(String param)
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
	 * This is a class Constructor.
	 * @param volume
	 * This is the referee to the Volume class containing System and Media volumes.
	 */
	public Console()
	{
		super();
		scan = new Scanner(System.in);
		System.out.println("Console on");
	}

	/**
	 * This method returns an integer containing the return
	 * value of the last command.
	 * @return
	 * Return value of the last command.
	 */
	public static int getLastReturnValue()
	{
		return lastReturnValue;
	}

	/**
	 * This method returns a string containing the last
	 * requested command.
	 * @return
	 * Last requested command.
	 */
	public static String getLastCommand()
	{
		return lastCommand; 
		//test
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
	
	public static int getCommand() {
		System.out.print(PathFinder.getProjectPath() + ">");
		command = scan.nextLine();
		return newCommand(command);
	}
	
	public static int newCommand(String command)
	{
		try {
			
			/*
			 * Commands:
			 * 	start delay=n
			 * 	stop
			 */
			
			if(command.contains("lastcmd"))
			{
				if(lastCommand == "")
					return lastReturnValue = -1;
				
				Console.command = lastCommand;
			}
			else
				Console.command = command;
			
			if(command.substring(0, 4).contains("exit"))
				System.exit(0);
			
			else if(command.substring(0, 7).contains("timer"))
				return lastReturnValue = timer();

			else if(command.substring(0, 7).contains("alarm"))
				return lastReturnValue = alarm();

			else if(command.substring(0, 7).contains("audio"))
				return lastReturnValue = audio();

			else if(command.substring(0, 7).contains("media"))
				return lastReturnValue = media();
			
			else if(command.substring(0, 5).contains("net"))
				return lastReturnValue = network();
			else if(command.substring(0, 5).contains("bill"))
				return lastReturnValue = bill();

		}
		catch(Exception e)
		{
			return -1;
		}
		finally
		{
			lastCommand = command;
		}

		return 0;
	}

	/**
	 * Use this method to finally close the console.
	 * Note: After being closed you will have to allocate a new console Object.
	 */
	public static void close()
	{
		scan.close();
	}
}
