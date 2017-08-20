/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.start;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.coder.hms.ui.external.LoginWindow;

public class StartApplication {

	public static void main(String[] args) {

		try {

			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new LoginWindow();

			}
		});

	}

}
