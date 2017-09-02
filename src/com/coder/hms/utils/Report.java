package com.coder.hms.utils;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import com.coder.hms.entities.ReportObject;
import com.coder.hms.ui.external.LoginWindow;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class Report extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static LoggingEngine logging;
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";

	public Report() {
		// TODO Auto-generated constructor stub
	}

	public void loadReport(String reportName, ReportObject reportObject) {

		logging = LoggingEngine.getInstance();
		
		try {
			
			final InputStream inputStream = Report.class
					.getResourceAsStream("/com/coder/hms/reports/" + reportName + ".jrxml");
			JasperReport report = JasperCompileManager.compileReport(inputStream);

			HashMap<String, Object> parameters = new HashMap<String, Object>();	
			List<ReportObject> list = new ArrayList<ReportObject>();
			list.add(reportObject);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, beanColDataSource);
			final JRViewer viewer = new JRViewer(jasperPrint);

			setType(Type.POPUP);
			setResizable(false);
			setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
			this.setTitle("Reservation [Report]");
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setAlwaysOnTop(isAlwaysOnTopSupported());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(new BorderLayout());
			this.setIconImage(Toolkit.getDefaultToolkit().
					getImage(LoginWindow.class.getResource(LOGOPATH)));
			this.setResizable(false);
			getContentPane().add(viewer, BorderLayout.CENTER);

		} catch (JRException e) {
			logging.setMessage("JRException report error!");
		}

	}

	public void showReport() {
		this.setVisible(true);
	}
}
