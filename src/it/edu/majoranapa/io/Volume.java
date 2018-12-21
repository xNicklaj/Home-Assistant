package it.edu.majoranapa.io;

public class Volume {
	private static float systemVolume = 6;
	private static float mediaVolume = 6;
	private static float alarmVolume = 6;
	
	public static int setSystemVolume(float volume)
	{
		if(volume < -80 || volume > 6)
			return -1;
		
		systemVolume = volume;
		return 0;
	}
	
	public static int setMediaVolume(float volume)
	{
		if(volume < -80 || volume > 6)
			return -1;
		
		systemVolume = volume;
		return 0;
	}
	
	public static int setAlarmVolume(float volume)
	{
		if(volume < -80 || volume > 6)
			return -1;
		
		alarmVolume = volume;
		return 0;
	}

	public static float getSystemVolume()
	{
		return systemVolume;
	}
	
	public static float getAlarmVolume()
	{
		return alarmVolume;
	}
	
	public static float getMediaVolume()
	{
		return mediaVolume;
	}
}
