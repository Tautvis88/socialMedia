package com.socialMedia.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatUtil {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static String format(LocalDateTime dateTime) {
		return dateTime == null ? null : dateTime.format(formatter);
	}
}
