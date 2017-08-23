package com.coder.hms.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LoggingFormatter extends Formatter {

	final DateTimeFormatter dateTimeFormatter = 
			DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
	
	@Override
	public String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();
		String logTime = LocalDateTime.now().format(dateTimeFormatter);
		sb.append(logTime).append("\n");
		sb.append(record.getLevel()).append(":");
		sb.append(record.getMessage()).append("\n");
		return sb.toString();
	}

}
