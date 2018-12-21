package it.edu.majoranapa.io;

public class Volume {
	private float systemVolume = 6;
	private float mediaVolume = 6;
	private float alarmVolume = 6;
	
	public int setSystemVolume(float volume)
	{
		if(volume < -80 || volume > 6)
			return -1;
		
		systemVolume = volume;
		return 0;
	}
	
	public int setMediaVolume(float volume)
	{
		if(volume < -80 || volume > 6)
			return -1;
		
		systemVolume = volume;
		return 0;
	}
	
	public int setAlarmVolume(float volume)
	{
		if(volume < -80 || volume > 6)
			return -1;
		
		alarmVolume = volume;
		return 0;
	}

	public float getSystemVolume()
	{
		return systemVolume;
	}
	
	public float getAlarmVolume()
	{
		return alarmVolume;
	}
	
	public float getMediaVolume()
	{
		return mediaVolume;
	}
}
