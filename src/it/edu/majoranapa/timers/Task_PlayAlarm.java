package it.edu.majoranapa.timers;

import it.edu.majoranapa.Main;
import it.edu.majoranapa.io.*;
import java.util.TimerTask;

public class Task_PlayAlarm extends TimerTask{
	private PlayAudio session;
	private String tone = "AlarmTone.wav";
	private static PathFinder finder = new PathFinder(Main.class.getResource(Main.class.getSimpleName() + ".class").toString());
	
	public void run()
	{
		session = new PlayAudio(finder.getResourcePath(tone));
		session.startAudio();
		
		return;
	}
	
	public void setTone(String tone)
	{
		this.tone = tone;
	}
}
