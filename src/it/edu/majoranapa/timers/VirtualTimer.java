package it.edu.majoranapa.timers;

import java.util.Timer;

public class VirtualTimer extends Thread{
	Timer timer = new Timer();
	Task_PlayAlarm task = new Task_PlayAlarm();
	int delay = 0;
	
	public VirtualTimer(int delay)
	{
		this.delay = delay;
	}
	
	public VirtualTimer()
	{
		super();
	}
	
	public void setDelay(int delay)
	{
		this.delay = delay;
	}
	
	public void playTask()
	{
		timer.schedule(task, delay);
	}
	
	public void run()
	{
		this.playTask();
		return;
	}
	
}
