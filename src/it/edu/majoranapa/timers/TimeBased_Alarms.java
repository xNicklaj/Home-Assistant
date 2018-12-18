package it.edu.majoranapa.timers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeBased_Alarms extends Task_PlayAlarm{
	private LocalDate localDate = LocalDate.now();
	private LocalTime localTime = LocalTime.now();
	private Instant instant = Instant.now();
	
	public void printOut()
	{
		System.out.println(localDate.format(DateTimeFormatter.ofPattern("dd-mm-yyyy")) + "\n"
				+ localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond());
	}
	
}
