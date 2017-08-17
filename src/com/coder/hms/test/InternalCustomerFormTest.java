package com.coder.hms.test;

import com.coder.hms.ui.inner.CustomerForm;

public class InternalCustomerFormTest {

	public static void main(String[] args) {
		
		CustomerForm form = new CustomerForm();
		form.setCustomerDetailPanel();
		form.setFirstNameValue("Onur");
		form.setLastNameValue("Işık");
		
	}
}
