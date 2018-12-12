package it.edu.majoranapa;

import it.edu.majoranapa.io.*;
import it.edu.majoranapa.timers.*;

public class Main {
	private static VirtualTimer timer = new VirtualTimer();
	private static PlayAudio session = new PlayAudio("../resources/AlarmTone.wav");
	
	public static void main(String[] args) 
	{	
		session.startAudio();
		timer.setDelay(30);
		timer.start();
		return;
	}

}
