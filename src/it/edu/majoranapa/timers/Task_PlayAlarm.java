package it.edu.majoranapa.timers;

import javax.swing.*;
import java.util.TimerTask;
import java.net.URL;
import javax.sound.sampled.*;

public class Task_PlayAlarm extends TimerTask{
	public void run()
	{
		try {
			this.playSound();
		} catch (Exception e) {
			System.out.println("Could not load audio.");
		}
	}
	
	public void playSound() throws Exception
	{
		URL url = new URL("D:/users/simon/GitHub/Home-Assistant/resources/AlarmTone.wav");
		Clip clip = AudioSystem.getClip();
		AudioInputStream ais = AudioSystem.getAudioInputStream(url);
		clip.open(ais);
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // A GUI element to prevent the Clip's daemon Thread
                // from terminating at the end of the main()
                JOptionPane.showMessageDialog(null, "Close to exit!");
            }
        });
	}
}
