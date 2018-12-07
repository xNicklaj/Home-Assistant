package it.edu.majoranapa;

import it.edu.majoranapa.timers.VirtualTimer;
import it.edu.majoranapa.timers.*;

public class Main {
	private static VirtualTimer timer = new VirtualTimer();
	
	public static void main(String[] args) 
	{	
		timer.playTask(10);
	}

}
