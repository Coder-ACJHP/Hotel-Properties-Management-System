package com.coder.hms.utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingEngine {

	private static LoggingEngine instance = null;
	private static Logger logger;
	private static FileHandler fileHandler;
	private static LoggingFormatter formatter;
	private static Path theDirectory;
	private static Path theFilePath;
	
	 public LoggingEngine() {}
	 
	 public static LoggingEngine getInstance() {
		 if(instance == null) {
			 
			 instance = new LoggingEngine();
		 }
		 return instance;
	 }
	
	 public void setReady(String className) {
		
		 final String path = System.getProperty("user.dir");
		 logger = Logger.getLogger(className);
		 
		try {
			
			theDirectory = Paths.get(path + File.separator + "Logging Store/");
			if(!Files.exists(theDirectory)) {
				Files.createDirectories(theDirectory);
			}
			
			final String today = LocalDate.now().toString();
			
			theFilePath = Paths.get(theDirectory.toString() +File.separator + "Logging_" + today + ".log");
			if(!Files.exists(theFilePath)) {
				Files.createFile(theFilePath);
			}
			
			fileHandler  = new FileHandler(theFilePath.toString(), true);
			logger.addHandler(fileHandler);
			formatter = new LoggingFormatter();
			fileHandler.setFormatter(formatter);
			
			
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMessage(String message) {
		logger.info(message);
	}
	
	public void setConsoleLogging(boolean status) {
		logger.setUseParentHandlers(status);
	}
	
	public void changeLoggingLevel(Level level) {
		logger.setLevel(level);
	}
}
