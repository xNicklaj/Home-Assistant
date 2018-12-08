package it.edu.majoranapa.timers;

import it.edu.majoranapa.io.PlayAudio;
import java.util.TimerTask;

public class Task_PlayAlarm extends TimerTask{
	PlayAudio session;
	
	public void run()
	{
		session = new PlayAudio("D:/users/simon/GitHub/Home-Assistant/resources/AlarmTone.wav");
	}
}
