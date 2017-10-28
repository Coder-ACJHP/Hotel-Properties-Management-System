/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.coder.hms.entities.Currency;
import com.coder.hms.ui.external.InformationFrame;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GetLiveCurrencyRates {

	private InformationFrame infoFrame;
	private static LoggingEngine loggingEngine;
	
	public GetLiveCurrencyRates() {
		infoFrame = new InformationFrame();
		loggingEngine = LoggingEngine.getInstance();
	}
	
	public String getUSDToTRYLiveCurrency() {

		String UsdRate = getGsonParser("USD");
		return UsdRate;
	}
	
	public String getEURToTRYLiveCurrency() {

		String EurRate = getGsonParser("EUR");
		return EurRate;
	}
	
	public String getGBPToTRYLiveCurrency() {

		String GbpRate = getGsonParser("GBP");
		return GbpRate;
	}
	
	public String getGsonParser(String currency) {
		
		String trimmedRate = "";
		Currency theCurrency = null;
		
		try {
			final URL queryUrl = new URL("http://api.fixer.io/latest?base="+currency);
			final BufferedReader in = new BufferedReader(new InputStreamReader(queryUrl.openStream()));
			
			String line = "";
			theCurrency = null;
			while((line = in.readLine()) != null) {

				theCurrency = new Gson().fromJson(line, Currency.class);
			}
			
			String rate = theCurrency.getRates().toString();
			if(theCurrency.getRates().toString().indexOf("TRY") != -1) {
				trimmedRate = rate.substring(rate.indexOf("TRY"), rate.indexOf("TRY") + 9);
			}
			return theCurrency.getBase() +"/"+ trimmedRate;
			
		} catch (JsonSyntaxException e) {
			loggingEngine.setMessage("Error (JsonSyntaxException) : " + e.getMessage());
			infoFrame.setMessage(e.getMessage());
			infoFrame.setVisible(true);
		} catch (MalformedURLException e) {
			loggingEngine.setMessage("Error (MalformedURLException) : " + e.getMessage());
			infoFrame.setMessage(e.getMessage());
			infoFrame.setVisible(true);
		} catch (IOException e) {
			loggingEngine.setMessage("Yahoo currency api error code : " + e.getMessage());
			infoFrame.setMessage("Cannot fetch currency! Please check your internet connection.\nError code : " + e.getMessage());
			infoFrame.setVisible(true);
		}

		return "OUT OF SERVICE";
	}
	
}
