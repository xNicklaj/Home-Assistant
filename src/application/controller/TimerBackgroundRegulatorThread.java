package application.controller;

import javafx.application.Platform;

public class TimerBackgroundRegulatorThread extends Thread{
	private int seconds;
	private double height;
	private boolean mutex = false;
	public static int spentTicks = 0;
	
	public TimerBackgroundRegulatorThread(int seconds, double height)
	{
		this.seconds = seconds;
		this.height = height;
		ThreadsList.timerThread = this;
		this.setName("timer-background-regulator");
	}
	
	public void toggle()
	{
		this.mutex = !mutex;
	}

	public void run()
	{
		do {
			TimerBackgroundRegulator reg = new TimerBackgroundRegulator((seconds - 0.5)*16.666666666667, height);
			try {
				Thread.sleep(Double.valueOf(16.666666666667).longValue());
			} catch (InterruptedException e) {
				System.out.println(e.getClass().getName());
				e.printStackTrace();
			}
			if(!mutex)
				Platform.runLater(reg);
		}while(!mutex);
		ControllerList.timerController.getPrompt_01().setDisable(false);
		ControllerList.timerController.getPrompt_02().setDisable(false);
		ControllerList.timerController.getPrompt_03().setDisable(false);
		ControllerList.timerController.getPrompt_04().setDisable(false);
		ControllerList.timerController.getPrompt_05().setDisable(false);
		ControllerList.timerController.getTimer_button().setId("start-button-stopped");
	}
}
