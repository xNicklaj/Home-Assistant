package it.edu.majoranapa;

import java.time.LocalDate;
import java.time.LocalTime;

import application.controller.ThreadsList;

public class SystemInfo extends Thread{
	private static LocalTime systemTime;
	private static LocalDate systemDate;
	
	public SystemInfo() {
		ThreadsList.systemInfo = this;
	}
	
	public void run()
	{
		while(true)
		{
			systemTime = LocalTime.now();
			systemDate = LocalDate.now();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getClass().getName());
				e.printStackTrace();
			}
		}
	}

	public static LocalTime getSystemTime() {
		return systemTime;
	}

	public static LocalDate getSystemDate() {
		return systemDate;
	}
	
	
}
