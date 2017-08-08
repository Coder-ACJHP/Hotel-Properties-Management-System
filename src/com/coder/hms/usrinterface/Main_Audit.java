package com.coder.hms.usrinterface;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.coder.hms.utils.CustomDateFactory;

public class Main_Audit extends JPanel {

	/**
	 * 
	 */
	private CustomDateFactory cdf = new CustomDateFactory(); 
	private static final long serialVersionUID = 1L;

	public Main_Audit() {
		
		cdf.getValidDateUntilAudit(-1);
		
		JOptionPane.showMessageDialog(null, cdf.getDate());
	}
}
