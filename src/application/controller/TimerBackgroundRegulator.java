package application.controller;

public class TimerBackgroundRegulator implements Runnable{
	private double ratio;
	private double ticks;
	private double height;

	public TimerBackgroundRegulator(double ticks, double height)
	{
		this.ticks = ticks;
		this.height = height;
		this.ratio = height / ticks;
	}

	@Override
	public void run() {
		ControllerList.timerController.getBackground_fill_state().setPrefHeight(ControllerList.timerController.getBackground_fill_state().getPrefHeight() - this.ratio/3.5);
		if(TimerBackgroundRegulatorThread.spentTicks == 60)
		{
			if(Integer.parseInt(ControllerList.timerController.getTimer_sec().getText()) > 0)
				ControllerList.timerController.getTimer_sec().setText("" + (Integer.parseInt(ControllerList.timerController.getTimer_sec().getText()) - 1));
			else if(Integer.parseInt(ControllerList.timerController.getTimer_sec().getText()) == 0 && Integer.parseInt(ControllerList.timerController.getTimer_min().getText()) > 0)
			{
				ControllerList.timerController.getTimer_sec().setText("" + 59);
				ControllerList.timerController.getTimer_min().setText("0" + (Integer.parseInt(ControllerList.timerController.getTimer_min().getText()) - 1));				
			}
			else if(Integer.parseInt(ControllerList.timerController.getTimer_sec().getText()) == 1 && Integer.parseInt(ControllerList.timerController.getTimer_min().getText()) == 0)
			{
				ControllerList.timerController.getTimer_sec().setText("" + 0);
				ThreadsList.timerThread.toggle();
			}

			if(ControllerList.timerController.getTimer_sec().getText().length() < 2)
			{
				ControllerList.timerController.getTimer_sec().setText("0" + ControllerList.timerController.getTimer_sec().getText());
			}
			if(ControllerList.timerController.getTimer_min().getText().length() < 2)
			{
				ControllerList.timerController.getTimer_min().setText("0" + ControllerList.timerController.getTimer_min().getText());
			}
			TimerBackgroundRegulatorThread.spentTicks = 0;
		}
		TimerBackgroundRegulatorThread.spentTicks++;
	}


}
