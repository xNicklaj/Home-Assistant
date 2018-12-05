package it.edu.majoranapa.led;

public class Color {
	private String colorName;
	private String hexValue;
	
	public Color(String colorName, String hexValue)
	{
		this.colorName = colorName.toLowerCase();
		this.hexValue = hexValue.toLowerCase();
	}
	
	public String getColorName() {
		return colorName;
	}
	public String getHexValue() {
		return hexValue;
	}
}
