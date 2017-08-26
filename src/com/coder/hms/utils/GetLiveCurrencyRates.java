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
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GetLiveCurrencyRates {

	private static LoggingEngine loggingEngine;
	
	public GetLiveCurrencyRates() {
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
		
		Currency theCurrency = null;
		try {
			final URL queryUrl = new URL("http://api.fixer.io/latest?base="+currency);
			final BufferedReader in = new BufferedReader(new InputStreamReader(queryUrl.openStream()));
			
			String line = "";
			theCurrency = null;
			while((line = in.readLine()) != null) {

				theCurrency = new Gson().fromJson(line, Currency.class);
			}
		} catch (JsonSyntaxException e) {
			loggingEngine.setMessage("JsonSyntaxException : " + e.getMessage());
		} catch (MalformedURLException e) {
			loggingEngine.setMessage("MalformedURLException : " + e.getMessage());
		} catch (IOException e) {
			loggingEngine.setMessage("IOException : " + e.getMessage());
		}
		
		String trimmedRate = "";
		String rate = theCurrency.getRates().toString();
		if(theCurrency.getRates().toString().indexOf("TRY") != -1) {
			trimmedRate = rate.substring(rate.indexOf("TRY"), rate.indexOf("TRY") + 9);
		}
		
		return theCurrency.getBase() +"/"+ trimmedRate;
	}
	
}
