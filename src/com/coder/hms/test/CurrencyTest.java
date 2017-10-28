package com.coder.hms.test;

import com.coder.hms.utils.GetLiveCurrencyRates;

public class CurrencyTest {

	public static void main(String[] args) throws InterruptedException {
		GetLiveCurrencyRates rates = new GetLiveCurrencyRates();

		if (rates != null) {
			System.out.println(rates.getEURToTRYLiveCurrency());

		}
	}

}
