package com.coder.hms.test;

import com.coder.hms.usrinterface.PaymentExternalWindow;
import com.coder.hms.usrinterface.PostingExternalWindow;

public class PaymentTest {

	public static void main(String[] args) {
		
		
		new PostingExternalWindow("4003");

		PaymentExternalWindow pyw = new PaymentExternalWindow();
		pyw.setReadyPaymentWindow("1004");
	}
}
