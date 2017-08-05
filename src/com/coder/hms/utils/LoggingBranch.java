package com.coder.hms.utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingBranch {

	private static LoggingBranch instance = null;
	private static Logger logger;
	private static FileHandler fileHandler;
	private static SimpleFormatter simpleFormatter;
	private static Path theDirectory;
	private static Path theFilePath;
	
	 public LoggingBranch() {}
	 
	 public static LoggingBranch getInstance() {
		 if(instance == null) {
			 
			 instance = new LoggingBranch();
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
			
			final String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			
			theFilePath = Paths.get(theDirectory.toString() +File.separator + "Logging_" + today + ".log");
			if(!Files.exists(theFilePath)) {
				Files.createFile(theFilePath);
			}
			
			fileHandler  = new FileHandler(theFilePath.toString());
			logger.addHandler(fileHandler);
			simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			
			
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setInfoMessage(String message) {
		logger.info(message);
	}
	
	public void stopConsoleLogging(boolean status) {
		logger.setUseParentHandlers(status);
	}
	
	public void changeLoggingLevel(Level level) {
		logger.setLevel(level);
	}
}
