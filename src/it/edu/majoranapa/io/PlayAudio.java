package it.edu.majoranapa.io;

import java.io.*;
import javax.sound.sampled.*;


public class PlayAudio {
	private Clip clip;
	private FloatControl volumeController;
	private Volume volume;
	private String pthFile = "";
	private AudioChannel channel;
	
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
	
	public void setVolumePointer(Volume volume)
	{
		this.volume = volume;
	}
	
	public void setAudioChannel(AudioChannel channel)
	{
		this.channel = channel;
	}
	
	public AudioChannel getAudioChannel()
	{
		return this.channel;
	}
	
	private void setVolume(float volume)
	{
		this.volume = volume;
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
	 * Use this method to set the volume of the clip.
	 * @param volume
	 * Volume in db
	 * Range: -80 to 6
	 */
	public void setAudioVolume(float volume)
	{
		switch(channel)
		{
		case ALARM:
			
			break;
		case SYSTEM:
			
			break;
		case MEDIA:
			
			break;
		}
		this.volumeController.setValue(volume);
		setVolume(volume);
	}
	
	/**
	 * Use this method to start the audio.
	 */
	public void startAudio()
	{
		try 
		{
			this.clip = AudioSystem.getClip();
			File f = new File(this.pthFile);
			if(!f.isFile())
			{
				System.err.println("Error: file not found!!");
				return;
			}
			AudioInputStream inStream = AudioSystem.getAudioInputStream(new File(pthFile).getAbsoluteFile());
	        clip.open(inStream);
	        this.volumeController = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        
	        this.setAudioVolume(this.volume);
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
