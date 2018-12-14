package it.edu.majoranapa.io;

import java.io.*;
import javax.sound.sampled.*;


public class PlayAudio {
	private String pthFile;
	
	public PlayAudio(String x)
	{
		this.pthFile = x;
	}
	
	public void setPath(String path)
	{
		this.pthFile = path;
	}
	
	public void startAudio()
	{
		try 
		{
			Clip clip = AudioSystem.getClip();
			File f = new File(this.pthFile);

			if(!f.isFile())
			{
				System.err.println("Error: file not found!!");
				return;
			}
			AudioInputStream inStream = AudioSystem.getAudioInputStream(new File(pthFile).getAbsoluteFile());
	        clip.open(inStream);
	        clip.start(); 
			while(clip.isActive());
		} 
		catch (LineUnavailableException | UnsupportedAudioFileException | IOException e)
		{
			e.printStackTrace();
		}
		return;
	}
	
	public void run()
	{
		this.startAudio();
		return;
	}
}
