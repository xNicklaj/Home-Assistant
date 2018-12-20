package it.edu.majoranapa.timers;

import it.edu.majoranapa.io.*;
import java.util.TimerTask;

public class Task_PlayAlarm extends TimerTask{
	protected PlayAudio session;
	protected String tone = "AlarmTone.wav";
	Volume volume;
	protected static PathFinder finder = new PathFinder();
	
	/**
	 * @override of the run() method inherited from TimerTask.
	 * It starts the requested Alarm Tone.
	 */
	public void run()
	{
		session.setAudioChannel(AudioChannel.SYSTEM);
		session = new PlayAudio(finder.getResourcePath(tone));
		session.startAudio();
		
		return;
	}
	
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
