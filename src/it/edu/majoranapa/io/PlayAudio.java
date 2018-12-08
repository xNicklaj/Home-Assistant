package it.edu.majoranapa.io;

import java.io.*;
import javax.sound.sampled.*;


public class PlayAudio {
	String pthFile;
	
	public PlayAudio(String x)
	{
		this.pthFile = x;
	}
	
	public void run()
	{
		try 
		{
			Clip clip = AudioSystem.getClip();
			File f = new File(this.pthFile);

			if(!f.exists())
			{
				System.err.println("Error: file not found!!");
				return;
			}
			AudioInputStream inStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(f)));
	        clip.open(inStream);
	        clip.start(); 
			while(clip.isActive());
		} 
		catch (LineUnavailableException | UnsupportedAudioFileException | IOException e)
		{
			e.printStackTrace();
		}
		finally {
			System.out.println("Fine");
		}
	}
}
