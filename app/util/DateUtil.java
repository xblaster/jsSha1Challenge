package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date getOnlyDatePart(Date d) {
		// Get Calendar object set to the date and time of the given Date object
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Put it back in the Date object
		return cal.getTime();
	}
	
	public static Date getTommorow(Date d) {
		// Get Calendar object set to the date and time of the given Date object
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		cal.add(Calendar.DAY_OF_MONTH, 1);
		
		// Put it back in the Date object
		return cal.getTime();
	}
}
