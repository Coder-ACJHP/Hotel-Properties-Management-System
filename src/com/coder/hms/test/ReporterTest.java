package com.coder.hms.test;

import com.coder.hms.entities.ReportObject;
import com.coder.hms.utils.Report;

public class ReporterTest {

	public static void main(String[] args) {
		
		final ReportObject reportBean = new ReportObject(1, "Onur", 
				"TAYFUN ARSLANHAN 1 TRIPLE", "2017-08-29", "2017-08-31",
				"WEB", "0001-XX", 2, "DOUBLE", "B.B", "5004", "DOLLAR",
				300.0, true, "DOLLAR", "250.0","CASH PAYMENT");
		final  Report report = new Report();
		report.loadReport("ReservationForm", reportBean);
		report.showReport();
		
	}

}
