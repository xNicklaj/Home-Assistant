package it.edu.majoranapa.timers;

public class Main {
	private static VirtualTimer timer = new VirtualTimer();
	
	public static void main(String[] args) 
	{	
		timer.playTask(10);
	}

}
