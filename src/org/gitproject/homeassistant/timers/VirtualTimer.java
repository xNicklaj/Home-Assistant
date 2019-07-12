package org.gitproject.homeassistant.timers;

import java.util.Timer;

public class VirtualTimer implements Runnable{
	private Timer timer = new Timer();
	private Task_PlayAlarm task = new Task_PlayAlarm();
	private int delay = 0;
	
	/**
	 * This is a class constructor.
	 * @param delay
	 * This parameter allows to set the delay of the timer.
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

	/**
	 * @override of the run() method from Threads.
	 * It allows to schedule the timer through playTask().
	 */
	public void run()
	{
		this.playTask();
		return;
	}

	/**
	 * This method allows to set the volume of the timer.
	 * @param volume
	 * Float of range -80 to 6.
	 */
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
	 * This method allows to cancel the timer.
	 */
	public void stopTask()
	{
		if(task.getState() == 0)
			task.killSession();
		task.cancel();
		timer.cancel();
		task = null;
		timer = null;
	}
}
