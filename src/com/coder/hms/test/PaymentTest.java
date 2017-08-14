package com.coder.hms.test;

import com.coder.hms.ui.external.PaymentWindow;
import com.coder.hms.ui.external.PostingWindow;

public class PaymentTest {

	public static void main(String[] args) throws InterruptedException {
		
		
		PostingWindow os = new PostingWindow();
		os.setReadyPaymentWindow("4003");

		PaymentWindow pyw = new PaymentWindow();
		pyw.setReadyPaymentWindow("1004");

		
	}
}
