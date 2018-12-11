package it.edu.majoranapa.timers;

import it.edu.majoranapa.io.PlayAudio;
import java.util.TimerTask;

public class Task_PlayAlarm extends TimerTask{
	PlayAudio session;
	
	public void run()
	{
		session = new PlayAudio("../resources/AlarmTone.wav");
	}
}
