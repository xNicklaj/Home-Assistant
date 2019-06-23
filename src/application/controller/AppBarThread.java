package application.controller;

import javafx.application.Platform;

public class AppBarThread extends Thread{
	private AppBarUpdater updater;
	public AppBarThread() {
		ThreadsList.appBarThread = this;
		this.setName("appbar-manager");
		this.updater = new AppBarUpdater();
	}
	
	@Override
	public void run() {
		while(true)
		{
			Platform.runLater(updater);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getClass().getName());
				e.printStackTrace();
			}
		}
	}
	
}
