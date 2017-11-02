package com.coder.hms.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindPathTest {

	public static void main(String[] args) {
		
		String command = "cmd C:\\Program Files\\MySQL\\MySQL\\bin>mysql -V";
		
		try {
			final Process process = Runtime.getRuntime().exec(command);

			final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line = reader.readLine();
			System.out.println(line);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
