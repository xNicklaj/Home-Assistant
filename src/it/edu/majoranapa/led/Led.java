package it.edu.majoranapa.led;

public class Led {
	/**
	 * 	The led class lets the user create a bind to a single led.
	 * 	attributes:
	 * 		- int_intensity: int
	 * 		- hex_intensity: int
	 * 	methods:
	 * 		+ Led(intensity: int)
	 * 		+ setIntensity(intensity: int): boolean
	 * 		+ setIntensity(intensity: String): boolean
	 * 		+ getIntIntensity(): int
	 * 		+ getHexIntensity(): String
	 */
	private int int_intensity = 0; 
	private String hex_intensity = "00"; 
	
	public Led(int intensity) 
	{
		/**
		 * This method requires an int parameter containing the integer intensity - 0 to 255 - and
		 * sets the led intensity based on the input parameter.
		 */
		if(intensity > 255 || intensity < 0)
			return;
		this.int_intensity = intensity;	
		this.hex_intensity = Integer.toHexString(intensity);
	}
	
	public boolean setIntensity(int intensity)	
	{
		/**
		 * This method requires an int parameter containing the integer intensity - 0 to 255 - and
		 * sets the led intensity based on the input parameter. It returns false if everything worked correctly.
		 */
		this.int_intensity = intensity;
		this.hex_intensity = Integer.toHexString(intensity);
		return false;	
	}

	public boolean setIntensity(String intensity)	
	{
		/**
		 * This method requires a String parameter containing the hex intensity - 00 to ff - and
		 * sets the led intensity based on the input parameter. It returns false if everything worked correctly.
		 */
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
	
	public int getIntIntensity()
	{
		/**
		 * This method returns the led intensity as an int.
		 */
		return this.int_intensity;
	}
	
	public String getHexIntensity()
	{
		/**
		 * 	This method returns the led Intensity as a hex String.
		 */
		return this.hex_intensity;
	}
}
