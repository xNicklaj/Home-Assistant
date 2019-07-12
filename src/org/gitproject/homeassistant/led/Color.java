package org.gitproject.homeassistant.led;

/**
 * @author Simone Aronica
 *
 */
public class Color {
	private String colorName;
	private String hexValue;
	
	/**
	 * @param colorName
	 * 		This parameter contains the name of the color to bind.
	 * @param hexValue
	 * 		This parameter contains the hex value of the color to bind.
	 */
	public Color(String colorName, String hexValue)
	{
		this.colorName = colorName.toLowerCase();
		this.hexValue = hexValue.toLowerCase();
	}
	
	/**
	 * @return
	 * 		This method returns the name of the color to bind.
	 */
	public String getColorName() {
		return this.colorName;
	}
	
	/**
	 * @return
	 * 		This method returns the hex value of the color to bind.
	 */
	public String getHexValue() {
		return this.hexValue;
	}
}
