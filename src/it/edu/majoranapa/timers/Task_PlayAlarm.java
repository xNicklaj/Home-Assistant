package it.edu.majoranapa.timers;

import it.edu.majoranapa.io.*;
import java.util.TimerTask;

public class Task_PlayAlarm extends TimerTask{
	protected PlayAudio session;
	protected String tone = "AlarmTone.wav";
	protected static PathFinder finder = new PathFinder();
	protected final AudioChannel channel = AudioChannel.ALARM;
	
	/**
	 * @override of the run() method inherited from TimerTask.
	 * It starts the requested Alarm Tone.
	 */
	public void run()
	{
		session = new PlayAudio(PathFinder.getResourcePath(tone));
		session.setAudioChannel(channel);
		session.startAudio();
		
		return;
	}
	
	/**
	 * Sets the volume of the Alarm.
	 * @param volume
	 */
	public void setVolume(float volume)
	{
		Volume.setAlarmVolume(volume);
	}
	
	/**
	 * Use this method to get the current state of the session
	 * @return
	 * 0 indicates that the session is playing an audio, 1 indicates
	 * that the session is ready to play but isn't playing any audio.
	 */
	public int getState()
	{
		if(session != null)
			return session.getClipState();
		else 
			return -1;
	}
	/**
	 * Use this method to kill the session
	 */
	public void killSession()
	{
		session.stopAudio();
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
