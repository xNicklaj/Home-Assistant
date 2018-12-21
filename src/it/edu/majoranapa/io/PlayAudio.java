package it.edu.majoranapa.io;

import java.io.*;
import javax.sound.sampled.*;


public class PlayAudio {
	private Clip clip;
	private FloatControl volumeController;
	private String pthFile = "";
	private Volume volume;
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
	        System.out.println(channel.toString());
	        //this.setAudioVolume(this.getAudioVolume()); Istruzione disabilitata per NullPointer //TODO Capire cosa causa il NullPointer
	        clip.start(); 
			while(clip.isActive());
		} 
		catch (LineUnavailableException | UnsupportedAudioFileException | IOException e)
		{
			e.printStackTrace();
		}
		return;
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
			this.volume.setAlarmVolume(volume);
			break;
		case SYSTEM:
			this.volume.setSystemVolume(volume);
			break;
		case MEDIA:
			this.volume.setMediaVolume(volume);
			break;
		}
		this.volumeController.setValue(volume);
		this.setAudioVolume(volume);
	}

	public float getAudioVolume()
	{
		switch(channel)
		{
		case ALARM:
			return volume.getAlarmVolume();
		case SYSTEM:
			return volume.getSystemVolume();
		case MEDIA:
			return volume.getMediaVolume();
		}
		return 0;
	}
}
