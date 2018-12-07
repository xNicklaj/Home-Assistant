package it.edu.majoranapa.timers;

import java.util.Timer;

public class VirtualTimer {
	Timer timer = new Timer();
	Task_PlayAlarm task = new Task_PlayAlarm();
	
	public VirtualTimer(int delay)
	{
		timer.schedule(task, delay);
	}
	
	public VirtualTimer()
	{
		super();
	}
	
	public void playTask(int delay)
	{
		timer.schedule(task, delay);
	}
	
}
