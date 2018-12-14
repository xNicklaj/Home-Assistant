package it.edu.majoranapa;

import it.edu.majoranapa.timers.*;

public class Main {
	private static VirtualTimer timer = new VirtualTimer();
	
	public static void main(String[] args) 
	{	
		timer.setDelay(5000);
		timer.start();
		return;
	}

}
