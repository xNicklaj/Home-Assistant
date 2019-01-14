package it.edu.majoranapa.io;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAndTime {
	private LocalDate localDate;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public LocalDate updateDate()
	{
		return localDate = LocalDate.now();
	}
	
	public String DateFormatter()
	{
		return formatter.format(updateDate());
	}
}
