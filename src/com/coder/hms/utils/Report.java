package com.coder.hms.utils;

import java.awt.BorderLayout;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import com.coder.hms.entities.ReportObject;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class Report {

	private JFrame frame;
	private LoggingEngine logging;

	public Report() {
		// TODO Auto-generated constructor stub
	}

	public Report(String reportName, List<ReportObject> list) {

		logging = LoggingEngine.getInstance();
		
		try {
			
			final InputStream inputStream = Report.class
					.getResourceAsStream("/com/coder/hms/reports/" + reportName + ".jrxml");
			JasperReport report = JasperCompileManager.compileReport(inputStream);

			HashMap<String, Object> parameters = new HashMap<String, Object>();	
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, beanColDataSource);
			final JRViewer viewer = new JRViewer(jasperPrint);

			frame = new JFrame();
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(viewer, BorderLayout.CENTER);

		} catch (JRException e) {
			logging.setMessage("JRException report error!");
		}

	}

	public void showReport() {
		frame.setVisible(true);
	}
}
