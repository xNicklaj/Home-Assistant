package it.edu.majoranapa.kernel;

import java.io.*;
import javax.sound.sampled.*;


public class PlayAudio {
	private Clip clip;
	private FloatControl volumeController;
	private File pthFile;
	private AudioChannel channel;
	
	/**
	 * This is the constructor of the class.
	 * @param filePath
	 * This is the path of the file that needs to be played.
	 * It can also be changed from .setPath().
	 */
	public PlayAudio(String filePath)
	{
		this.pthFile = new File(filePath);
	}
	
	/**
	 * This is a class constructor.
	 * @param filePath
	 * This is the path of the file that needs to be played.
	 * It can also be changed from .setPath().
	 * @param channel
	 * This is the channel that the audio session will use.
	 * It can also be changed from .setAudioChannel().
	 */
	public PlayAudio(String filePath, AudioChannel channel)
	{
		this.channel = channel;
		this.pthFile = new File(filePath);
	}
	
	/**
	 * Use this method to set the name of the file to play.
	 * @param path
	 * This is the path of the file that needs to be played.
	 */
	public void setPath(String filePath)
	{
		if(pthFile != null)
			pthFile = null;
		this.pthFile = new File(filePath);
	}

	/**
	 * Use this method to start the audio.
	 */
	public void startAudio()
	{
		try 
		{
			this.clip = AudioSystem.getClip();
			if(!pthFile.isFile())
			{
				System.err.println("Error: file not found!!");
				return;
			}
			AudioInputStream inStream = AudioSystem.getAudioInputStream(pthFile.getAbsoluteFile());
	        clip.open(inStream);
	        this.volumeController = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        this.setLocalAudioVolume(this.getAudioVolume());
	        clip.start(); 
			while(clip.isActive());
		} 
		catch (LineUnavailableException | UnsupportedAudioFileException | IOException e)
		{
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * This method allows to stop the audio.
	 * Note: This method does not free the memory.
	 */
	public void stopAudio()
	{
		if(clip.isActive())
			clip.stop();
	}
	
	/**
	 * This method allows to stop the audio, close the clip
	 * and flush the clip buffer.
	 */
	public void shredAudio()
	{
		if(clip.isActive())
			clip.stop();
		clip.close();
		clip.flush();
		clip = null;
	}
	
	/**
	 * This method allows to choose the channel to use when delivering audio
	 * @param channel
	 * Enum of type AudioChannel containing the info about the audio channel to use.
	 * Can be either SYSTEM, ALARM or MEDIA.
	 */
	public void setAudioChannel(AudioChannel channel)
	{
		this.channel = channel;
	}
	
	/**
	 * This method returns the value of the current AudioChannel.
	 * @return
	 * Enum of type AudioChannel containing the info about the audio channel being used.
	 */
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
	public int setLocalAudioVolume(float volume)
	{
		this.volumeController.setValue(volume);
		
		return 0;
	}

	/**
	 * Use this method to set the volume using percentage (0 to 100).
	 * @param volume
	 * @return
	 * Returns 0 if everything worked
	 * Returns -1 if an incorrect value was used.
	 */
	public int setLocalAudioVolumePercentage(float volume)
	{
		this.volumeController.setValue((86*volume/100)-80);
		
		return 0;
	}

	/**
	 * Use this method to get the current volume.
	 * Range: -80 to +6.
	 * Unit: dB.
	 * @return
	 */
	public float getAudioVolume()
	{
		switch(channel)
		{
		case ALARM:
			return Volume.getAlarmVolume();
		case SYSTEM:
			return Volume.getSystemVolume();
		case MEDIA:
			return Volume.getMediaVolume();
		}
		return 0;
	}
	
	/**
	 * Use this method to get the current volume percentage.
	 * @return
	 * Current volume percentage.
	 */
	public float getAudioVolumePercentage()
	{
		return (this.getAudioVolume() + 80) * 100 / 86;
	}
	
	/**
	 * Use this method to get the current state of the clip
	 * @return
	 * 0 indicates that the clip is playing an audio, 1 indicates
	 * that the clip is ready to play but isn't playing any audio.
	 */
	public int getClipState()
	{
		if(clip != null)
		{
			if(clip.isActive())
				return 0;
			if(clip.isRunning())
				return 1;
		}		
		return -1;
	}
}
