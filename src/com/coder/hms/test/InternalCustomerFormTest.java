package com.coder.hms.test;

import com.coder.hms.ui.inner.Internal_CustomerForm;

public class InternalCustomerFormTest {

	public static void main(String[] args) {
		
		Internal_CustomerForm form = new Internal_CustomerForm();
		form.setCustomerDetailPanel();
		form.setFirstNameValue("Onur");
		form.setLastNameValue("Işık");
		
	}
}
