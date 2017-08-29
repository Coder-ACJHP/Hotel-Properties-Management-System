package com.coder.hms.test;

import java.util.Optional;

import com.coder.hms.utils.GetLiveCurrencyRates;

public class CurrencyTest {

	public static void main(String[] args) throws InterruptedException {
		GetLiveCurrencyRates rates = new GetLiveCurrencyRates();
		System.out.println(rates.getUSDToTRYLiveCurrency().substring(rates.
				getUSDToTRYLiveCurrency().length() -5, rates.getUSDToTRYLiveCurrency().length()));
			String noteTextArea = null;
			System.out.println(Optional.ofNullable(noteTextArea).orElse(" "));

			
	}

}
