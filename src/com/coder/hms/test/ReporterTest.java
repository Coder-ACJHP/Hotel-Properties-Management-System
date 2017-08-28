package com.coder.hms.test;

import java.util.ArrayList;
import java.util.List;

import com.coder.hms.entities.ReportObject;

public class ReporterTest {

	public static void main(String[] args) {
		
		ReportObject reportObject = new ReportObject();
		reportObject.setId(1L);
		reportObject.setAgency("AJAX");
		reportObject.setAgencyRefNo("asd0000");
		reportObject.setBalance("1000");
		reportObject.setCheckinDate("2017-09-8");
		reportObject.setCheckoutDate("2017-09-10");
		reportObject.setCurrency("EURO");
		reportObject.setGroupName("ONUR IŞIK");
		reportObject.setHostType("B.B");
		reportObject.setTheNumber("1001");
		reportObject.setPaymentStatus(true);
		reportObject.setPaymentType("CASH PAYMENT");
		reportObject.setTotalDays(1);
		reportObject.setPrice(300.0);
		reportObject.setType("WEN");
		List<ReportObject> reportDataMap = new ArrayList<ReportObject>();
		reportDataMap.add(reportObject);
		final ReportTest report = new ReportTest("ReservationForm", reportDataMap);
		report.showReport();
		
	}

}
