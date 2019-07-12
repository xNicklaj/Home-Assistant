package application.controller;

import java.time.format.DateTimeFormatter;

import org.gitproject.homeassistant.SystemInfo;

public class AppBarUpdater implements Runnable{
	private DateTimeFormatter formatter;
	
	public AppBarUpdater() {
		formatter = DateTimeFormatter.ofPattern("HH:mm");
	}
	
	
	public void updateSystemTime()
	{
		ControllerList.mainController.setSystemTime(formatter.format(SystemInfo.getSystemTime()));
	}


	@Override
	public void run() {
		this.updateSystemTime();
	}
}
