package it.edu.majoranapa.io;

public class Volume {
	private static float systemVolume = 6;
	private static float mediaVolume = 6;
	private static float alarmVolume = 6;
	
	/**
	 * Use this method to set the System channel volume.
	 * Range -80 to 6.
	 * Unit: dB.
	 * @param volume
	 * @return
	 * Returns 0 if everything worked correctly, -1 if the value is not available.
	 */
	public static int setSystemVolume(float volume)
	{
		if(volume < -80 || volume > 6)
			return -1;
		
		systemVolume = volume;
		return 0;
	}
	
	/**
	 * Use this method to set the Media channel volume.
	 * Range -80 to 6.
	 * Unit: dB.
	 * @param volume
	 * @return
	 * Returns 0 if everything worked correctly, -1 if the value is not available.
	 */
	public static int setMediaVolume(float volume)
	{
		if(volume < -80 || volume > 6)
			return -1;
		
		systemVolume = volume;
		return 0;
	}
	
	/**
	 * Use this method to set the Alarm channel volume.
	 * Range -80 to 6.
	 * Unit: dB.
	 * @param volume
	 * @return
	 * Returns 0 if everything worked correctly, -1 if the value is not available..
	 */
	public static int setAlarmVolume(float volume)
	{
		if(volume < -80 || volume > 6)
			return -1;
		
		alarmVolume = volume;
		return 0;
	}
	
	/**
	 * Use this method to set the System channel volume in percentage.
	 * Range: 0 to 100.
	 * @param volume
	 * @return
	 * Returns 0 if everything worked correctly, -1 if the value is not available.
	 */
	public static int setSystemVolumePercentage(float volume)
	{
		if(volume < 0 || volume > 100)
			return -1;
		
		systemVolume = 86 * volume / 100 - 80;
		return 0;
	}
	
	/**
	 * Use this method to set the Media channel volume in percentage.
	 * Range: 0 to 100.
	 * @param volume
	 * @return
	 * Returns 0 if everything worked correctly, -1 if the value is not available.
	 */
	public static int setMediaVolumePercentage(float volume)
	{
		if(volume < 0 || volume > 100)
			return -1;
		
		mediaVolume = 86 * volume / 100 - 80;
		return 0;
	}
	
	/**
	 * Use this method to set the Alarm channel volume in percentage.
	 * Range: 0 to 100.
	 * @param volume
	 * @return
	 * Returns 0 if everything worked correctly, -1 if the value is not available.
	 */
	public static int setAlarmVolumePercentage(float volume)
	{
		if(volume < 0 || volume > 100)
			return -1;
		
		alarmVolume = 86 * volume / 100 - 80;
		return 0;
	}
	
	public static float getSystemVolume()
	{
		return systemVolume;
	}
	
	public static float getMediaVolume()
	{
		return mediaVolume;
	}

	public static float getAlarmVolume()
	{
		return alarmVolume;
	}
}
