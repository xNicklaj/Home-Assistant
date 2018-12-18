package it.edu.majoranapa.timers;

import it.edu.majoranapa.io.*;
import java.util.TimerTask;

public class Task_PlayAlarm extends TimerTask{
	protected PlayAudio session;
	protected String tone = "AlarmTone.wav";
	protected static PathFinder finder = new PathFinder();
	
	/**
	 * @override of the run() method inherited from TimerTask.
	 * It starts the requested Alarm Tone.
	 */
	public void run()
	{
		session = new PlayAudio(finder.getResourcePath(tone));
		session.startAudio();
		
		return;
	}
	
	/**
	 * This method allows to choose the alarm tone file name.
	 * @param tone
	 * Alarm Tone File name as a String.
	 */
	public void setTone(String tone)
	{
		this.tone = tone;
	}
}
