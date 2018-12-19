package it.edu.majoranapa.io;

import java.io.*;
import javax.sound.sampled.*;


public class PlayAudio {
	private String pthFile = "";
	
	/**
	 * This is the constructor of the class.
	 * @param fileName
	 * This is the path of the file that needs to be played.
	 * It can also be changed from .setPath().
	 */
	public PlayAudio(String filePath)
	{
		this.pthFile = filePath;
	}
	
	/**
	 * Use this method to set the name of the file to play.
	 * @param path
	 * This is the path of the file that needs to be played.
	 */
	public void setPath(String filePath)
	{
		this.pthFile = filePath;
	}
	
	/**
	 * Use this method to start the audio.
	 */
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
}
