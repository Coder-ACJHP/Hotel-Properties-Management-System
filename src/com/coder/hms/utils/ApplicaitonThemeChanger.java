/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class ApplicaitonThemeChanger {

	private JFrame frame;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public ApplicaitonThemeChanger() {
		
	}
	
	public void ChangeTheme(final String themeName) {
		
			try {
				if (themeName.equalsIgnoreCase("mint")) {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);

				} else if (themeName.equalsIgnoreCase("mcwin")) {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);

				} else if (themeName.equalsIgnoreCase("bernstein")) {
					UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);

				} else if (themeName.equalsIgnoreCase("Aero")) {
					UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
				} else if (themeName.equalsIgnoreCase("Nimbus")) {
					for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							SwingUtilities.updateComponentTreeUI(frame);
						}
					}
				}
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			}
		}



}
