package application.controller;

import javafx.application.Platform;

public class TimerBackgroundRegulatorThread extends Thread{
	private int seconds;
	private double height;
	private boolean mutex = false;
	
	public TimerBackgroundRegulatorThread(int seconds, double height)
	{
		this.seconds = seconds;
		this.height = height;
		ThreadsList.timerThread = this;
	}
	
	public void toggle()
	{
		this.mutex = !mutex;
	}

	public void run()
	{
		do {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getClass().getName());
				e.printStackTrace();
			}
			if(!mutex)
				Platform.runLater(new TimerBackgroundRegulator(seconds, height));
		}while(!mutex);
		ControllerList.timerController.getPrompt_01().setDisable(false);
		ControllerList.timerController.getPrompt_02().setDisable(false);
		ControllerList.timerController.getPrompt_03().setDisable(false);
		ControllerList.timerController.getPrompt_04().setDisable(false);
		ControllerList.timerController.getPrompt_05().setDisable(false);
		ControllerList.timerController.getTimer_button().setId("start-button-stopped");
	}
}
