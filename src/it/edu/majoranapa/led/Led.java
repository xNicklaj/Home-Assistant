package it.edu.majoranapa.led;

/**
 * @author Simone Aronica
 *
 */
public class Led {
	private int int_intensity = 0; 
	private String hex_intensity = "00"; 
	
	/**
	 * @param
	 * This method requires an int parameter containing the integer intensity - 0 to 255 - and
	 * sets the led intensity based on the input parameter.
	 */
	public Led(int intensity) 
	{
		if(intensity > 255 || intensity < 0)
			return;
		this.int_intensity = intensity;	
		this.hex_intensity = Integer.toHexString(intensity);
	}
	
	/**
	 * @param
	 * This method requires an int parameter containing the integer intensity - 0 to 255 - and
	 * sets the led intensity based on the input parameter. 
	 * @return
	 * It returns false if everything worked correctly.
	 */
	public boolean setIntensity(int intensity)	
	{
		this.int_intensity = intensity;
		this.hex_intensity = Integer.toHexString(intensity);
		return false;	
	}

	/**
	 * @param 
	 * This method requires a String parameter containing the hex intensity - 00 to ff - and
	 * sets the led intensity based on the input parameter.
	 * @return
	 * It returns false if everything worked correctly.
	 */
	public boolean setIntensity(String intensity)	
	{
		this.hex_intensity = intensity;
		try {	
			this.int_intensity = Integer.parseInt(intensity, 16);
		}
		catch(Exception exc)
		{
			System.out.println("Value not in hex format.");	
		}
		return false;	
	}
	
	/**
	 * @return
	 * This method returns the led intensity as an int.
	 */
	public int getIntIntensity()
	{
		return this.int_intensity;
	}
	
	/**
	 * @return
	 * This method returns the led Intensity as a hex String.
	 */
	public String getHexIntensity()
	{
		return this.hex_intensity;
	}
}
