package com.coder.hms.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FirstRunChecker {

	
	 public static boolean checkIsFirstRun() {
		  
		  final String content = "First run counter is 0X1";
		  final String path = System.getProperty("user.dir")+ File.separator + "Logging Store/Coder_HPMSA.sc";
		  File theFile = new File(path);
		  
		  if(!theFile.exists()) {
			  try {
				final FileWriter fileWriter = new FileWriter(theFile);
				  fileWriter.write(content);
				  fileWriter.flush();
				  fileWriter.close();
			} catch (IOException e) { e.printStackTrace(); }
			  
			  if(theFile.canWrite()) {
				  theFile.setWritable(false);
				  theFile.setReadOnly();
			  }
			  return true;
		  }
		  
		  return false;
	  }
}
