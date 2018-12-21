package it.edu.majoranapa.timers;

import java.util.Timer;
import it.edu.majoranapa.io.*;

public class VirtualTimer extends Thread{
	private Timer timer = new Timer();
	private Task_PlayAlarm task = new Task_PlayAlarm();
	private int delay = 0;
	
	/**
	 * This is a class constructor.
	 * @param delay
	 * This parameter allows to set the delay of the timer of
	 */
	public VirtualTimer(int delay)
	{
		this.delay = delay;
	}
	
	/**
	 * This is a class constructor.
	 * It allows to generate the timer without specifying the delay.
	 */
	public VirtualTimer()
	{
		super();
	}

	public void setVolume(float volume)
	{
		task.setVolume(volume);
	}
	
	/**
	 * This method allows to set the delay of the timer.
	 * @param delay
	 * Delay in seconds.
	 */
	public void setDelay(int delay)
	{
		this.delay = delay*1000;
	}

	/**
	 * This method allows to schedule the timer.
	 */
	public void playTask()
	{
		timer.schedule(task, delay);
	}

	/**
	 * @override of the run() method from Threads.
	 * It allows to schedule the timer through playTask().
	 */
	public void run()
	{
		this.playTask();
		return;
	}
}
