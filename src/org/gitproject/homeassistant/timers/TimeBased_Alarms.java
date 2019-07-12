package org.gitproject.homeassistant.timers;

import java.time.LocalDateTime;

public class TimeBased_Alarms extends VirtualTimer{
	private LocalDateTime sysTime = LocalDateTime.now();
	private boolean[] calendar = new boolean[7];
	private String time;
	
	public void getSysTimeAndDate()
	{
		System.out.println(sysTime.getDayOfWeek().toString() + " " + sysTime.getDayOfMonth() + "/" + sysTime.getMonthValue() + "/" + sysTime.getYear() + "\n"
				+ sysTime.getHour() + ":" + sysTime.getMinute() + ":" + sysTime.getSecond());
	}
	
	public String getTime()
	{
		return time;
	}
	
	public void showCalendar()
	{
		System.out.println("Calendario attuale: \n"
				+ "| Lun | Mar | Mer | Gio | Ven | Sab | Dom |");
		for(int i = 0; i < 7; i++)
		{
			System.out.print("   ");
			if(calendar[i] == true)
				System.out.print("V");
			else
				System.out.print("X");
			System.out.print("  ");
		}
		System.out.println();
	}
	
	/**
	 * Use this to toggle the alarm on a certain day of the week.
	 * @param day
	 */
	public void toggleDay(int day)
	{
		calendar[day - 1] = !calendar[day - 1];
	}
	
	
	public int setTime(String time)
	{
		if(time.charAt(2) == ':' && time.charAt(5) == ':')
		{
			this.time = time;
			return 0;
		}
		else
			return -1;
	}
	
}
