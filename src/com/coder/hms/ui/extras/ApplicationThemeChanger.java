/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.extras;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class ApplicationThemeChanger {

	private JFrame frame;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public ApplicationThemeChanger() {
		
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
                                        
				} else if(themeName.equalsIgnoreCase("Noire")) {
                                        UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                                        SwingUtilities.updateComponentTreeUI(frame);
                                        
                                } else if(themeName.equalsIgnoreCase("Acryl")) {
                                        UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                                        SwingUtilities.updateComponentTreeUI(frame);
                                        
                                } else if(themeName.equalsIgnoreCase("Luna")) {
                                        UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
                                        SwingUtilities.updateComponentTreeUI(frame);
                                        
                                } else if (themeName.equalsIgnoreCase("Texture")) {
                                        UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
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
