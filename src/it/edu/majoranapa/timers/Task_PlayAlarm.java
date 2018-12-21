package it.edu.majoranapa.timers;

import it.edu.majoranapa.io.*;
import java.util.TimerTask;

public class Task_PlayAlarm extends TimerTask{
	protected PlayAudio session;
	protected String tone = "AlarmTone.wav";
	protected Volume volume;
	protected static PathFinder finder = new PathFinder();
	
	/**
	 * @override of the run() method inherited from TimerTask.
	 * It starts the requested Alarm Tone.
	 */
	public void run()
	{
		session = new PlayAudio(finder.getResourcePath(tone));
		session.setAudioChannel(AudioChannel.ALARM);
		session.setVolumePointer(volume);
		session.startAudio();
		
		return;
	}
	
	/**
	 * Set the pointer to the general volume class.
	 * @param volume
	 */
	public void setVolumePointer(Volume volume)
	{
		this.volume = volume;
	}
	
	/**
	 * Sets the volume of the Alarm.
	 * @param volume
	 */
	public void setVolume(float volume)
	{
		this.volume.setAlarmVolume(volume);
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
