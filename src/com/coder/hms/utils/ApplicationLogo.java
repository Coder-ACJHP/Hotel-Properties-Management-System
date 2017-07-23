/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.utils;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.apple.eawt.Application;
import com.coder.hms.usrinterface.LoginFrame;


public class ApplicationLogo {
	
	public ApplicationLogo() {
		// TODO Auto-generated constructor stub
	}
	
	public void setApplicationLogoJFrame(final JFrame frame, String thePath) {
		
		String opSystem = System.getProperty("os.name").toLowerCase();

		if (opSystem.contains("windows") || opSystem.contains("nux")) {
			
			frame.setIconImage(Toolkit.getDefaultToolkit().
					getImage(LoginFrame.class.getResource(thePath)));
		}else {
			Application.getApplication().setDockIconImage(new ImageIcon(getClass().
													getResource(thePath)).getImage());
		}
	}
	
	public void setApplicationLogoJDialog(final JDialog dialog, String thePath) {
		
		String opSystem = System.getProperty("os.name").toLowerCase();

		if (opSystem.contains("windows") || opSystem.contains("nux")) {
			
			dialog.setIconImage(Toolkit.getDefaultToolkit().
					getImage(LoginFrame.class.getResource(thePath)));
		}else {
			Application.getApplication().setDockIconImage(new ImageIcon(getClass().
													getResource(thePath)).getImage());
		}
	}
}
