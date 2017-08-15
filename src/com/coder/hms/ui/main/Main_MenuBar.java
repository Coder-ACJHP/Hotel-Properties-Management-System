/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.daoImpl.UserDaoImpl;
import com.coder.hms.entities.SessionBean;
import com.coder.hms.entities.User;
import com.coder.hms.ui.external.ChangePasswordWindow;
import com.coder.hms.ui.external.ExchangeWindow;
import com.coder.hms.ui.external.HotelPropertiesWindow;
import com.coder.hms.ui.external.LicenseWindow;
import com.coder.hms.ui.external.LoginWindow;
import com.coder.hms.utils.ApplicaitonThemeChanger;

public class Main_MenuBar {

	private JMenuBar menuBar;
	private JFrame mainFrame;
	private final Runtime run = Runtime.getRuntime();
	private final static Desktop desktop = Desktop.getDesktop();
	private final String command = System.getProperty("os.name");
	public final ApplicaitonThemeChanger themeChanger = new ApplicaitonThemeChanger();
	
	//getter method for getting the modified menubar from another class
	public JMenuBar getMenuBar() {
		return this.menuBar;
	}
	
	public void setJFrame(JFrame frame) {
		this.mainFrame = frame;
	}
	
	public Main_MenuBar() {
		
		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(0, 30));
		menuBar.setFont(new Font("Verdana", Font.BOLD, 15));
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBorder(new LineBorder(new Color(128, 128, 128)));
		menuBar.setAutoscrolls(true);
		
		final JMenu menuItemFrontDesk = new JMenu("Front Desk");
		menuBar.add(menuItemFrontDesk);
		
		final JMenuItem hoteProps = new JMenuItem("Hotel Properties");
		hoteProps.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/login_hotel.png")));
		hoteProps.addActionListener(ActionListener ->{

			
			SessionBean sessionBean = SessionBean.getSESSION_BEAN();
			
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			User currentUser = userDaoImpl.getUserByName(sessionBean.getNickName());
			
			if(currentUser.getRole().equals("USER")) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(new JFrame(), "Access denied!\nYou don't have permission to access!",
						JOptionPane.MESSAGE_PROPERTY, JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new HotelPropertiesWindow();
						
					}
				});
			}
		});
		menuItemFrontDesk.add(hoteProps);
		
		final JMenuItem restart = new JMenuItem("Restart");
		restart.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/menuBar_restart.png")));
		restart.addActionListener(ActionListener ->{
			mainFrame.dispose();

			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new MainFrame();
					
				}
			});
		});
		menuItemFrontDesk.add(restart);
		
		
		final JMenuItem refresh = new JMenuItem("Refresh");
		refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,
				(InputEvent.SHIFT_MASK) | (Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		refresh.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/cleaning-refresh.png")));
		refresh.addActionListener(ActionListener ->{
			
			mainFrame.revalidate();
			mainFrame.repaint();

		});
		menuItemFrontDesk.add(refresh);
		
		final JMenuItem menuInnerItemExit = new JMenuItem("Exit");
		menuInnerItemExit.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_exit.png")));
		//add shortcut keys
		menuInnerItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,(InputEvent.SHIFT_MASK |
												Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		menuInnerItemExit.setMnemonic(KeyEvent.VK_Q + KeyEvent.VK_CONTROL);
		//add listener for exiting when CTRL+SHIFT+Q pressed
		menuInnerItemExit.addActionListener(ActionEvent -> {

			final int decision = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (decision == JOptionPane.YES_OPTION) {
				new DataSourceFactory().shutDown();
				System.exit(0);
			} else {
				mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}

		});
		menuItemFrontDesk.add(menuInnerItemExit);
		
		
		final JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		//add calculator application section to menubar
		final JMenuItem calculator = new JMenuItem("Calculator");
		calculator.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/menubar_calc.png")));
		calculator.addActionListener(ActionListener ->{

			Thread openLocalApps = new Thread(new Runnable() {
				
				@Override
				public void run() {
					if(command.contains("Windows")) {
						try {
							run.exec("C:/Windows/System32/calc.exe");
							
						} catch (IOException e) {e.printStackTrace();}

					}else {
						
						try {
							run.exec("/usr/bin/open -a Calculator");
							
						} catch (IOException e) {e.printStackTrace();}
					}
					
				}
			});
			
			openLocalApps.start();
		});
		
		mnTools.add(calculator);
		
		//add send mail  section to menubar
		JMenuItem sendMail = new JMenuItem("Send email");
		sendMail.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/manubar_sendMail.png")));
		sendMail.addActionListener(ActionListener ->{
			
			Thread openMailApp = new Thread(new Runnable() {
				
				@Override
				public void run() {
					if(Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.MAIL)) {
						
						try {
							
							URI uriMailTo = new URI("mailto:companyn@example.com?subject=About%20Peyment");
							desktop.mail(uriMailTo);
							 
						} catch (URISyntaxException | IOException e) { e.printStackTrace();}
					}
					
				}
			});
			
			openMailApp.start();
		});
		
		mnTools.add(sendMail);
		
		JMenuItem exchange = new JMenuItem("Exchange");
		exchange.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/menubar_exchange.png")));
		exchange.addActionListener(ActionListener ->{
			new ExchangeWindow();
		});
		mnTools.add(exchange);
		
		/*Add new menu into Tools menu*/	
		JMenu themes = new JMenu("Themes");
		menuBar.add(themes);
		
		/*Add all themes into themes section */		
		JMenuItem defaultTheme = new JMenuItem("Nimbus");
		defaultTheme.addActionListener(ActionListener ->{
			themeChanger.ChangeTheme("Nimbus");
		});
		JMenuItem mnitmAero = new JMenuItem("Aero");
		mnitmAero.addActionListener(ActionListener ->{
			themeChanger.ChangeTheme("Aero");
		});
		JMenuItem mnitmBernstain = new JMenuItem("Bernstein");
		mnitmBernstain.addActionListener(ActionListener ->{
			themeChanger.ChangeTheme("bernstein");
		});
		JMenuItem mnitmMint = new JMenuItem("Mint");
		mnitmMint.addActionListener(ActionListener ->{
			themeChanger.ChangeTheme("Mint");
		});
		JMenuItem mnitmMcwin = new JMenuItem("McWin");
		mnitmMcwin.addActionListener(ActionListener ->{
			themeChanger.ChangeTheme("McWin");
		});
		
		themes.add(defaultTheme);
		themes.add(mnitmAero);
		themes.add(mnitmBernstain);
		themes.add(mnitmMint);
		themes.add(mnitmMcwin);
		
		JMenu usersMenu = new JMenu("Users");
		menuBar.add(usersMenu);
		
		JMenuItem changeUser = new JMenuItem("Change user");
		changeUser.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/menubar_change_user.png")));
		changeUser.addActionListener(ActionListener ->{
		
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					
					mainFrame.dispose();
					new LoginWindow();					
				}
			});
		});
		usersMenu.add(changeUser);
		
		final JMenuItem chngPassword = new JMenuItem("Change password");
		chngPassword.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/menubar_change_pwd.png")));
		chngPassword.addActionListener(ActionListener ->{
		
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new ChangePasswordWindow();
					
				}
			});
		});
		
		usersMenu.add(chngPassword);
		
		JMenu mnAbout = new JMenu("Others");
		menuBar.add(mnAbout);
		
		//add about developer section to menubar
		final JMenuItem aboutDeveloper = new JMenuItem("Developers");
		aboutDeveloper.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/menubar_developer.png")));
		aboutDeveloper.addActionListener(ActionListener ->{
			if(Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {

				try {
					
					URI uri = new URI("https://www.linkedin.com/in/onur-bilal-i%C5%9F%C4%B1k-70663212b/");
					desktop.browse(uri);
					
				} catch (URISyntaxException | IOException e) {e.printStackTrace();} 
				
			}
		});
		mnAbout.add(aboutDeveloper);
		
		//add source code section to menubar
		final JMenuItem sourceCode = new JMenuItem("Source code");
		sourceCode.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/menubar_source_code.png")));
		sourceCode.addActionListener(ActionListener ->{
			if(Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {

				try {
					
					URI uri = new URI("https://github.com/Coder-ACJHP/Hotel-Management-System");
					desktop.browse(uri);
					
				} catch (URISyntaxException | IOException e) {e.printStackTrace();} 
				
			}
		});
		mnAbout.add(sourceCode);
		
		//add feedback section to menubar
		final JMenuItem shareYourOpinion = new JMenuItem("Feedback");
		shareYourOpinion.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/manubar_feedback.png")));
		shareYourOpinion.addActionListener(ActionListener ->{
			
			if(Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.MAIL)) {
				
				try {
					
					URI uriMailTo = new URI("mailto:hexa.octabin@gmail.com?subject=About%20Coder%20Hotel%20Management%20System");
					desktop.mail(uriMailTo);
					 
				} catch (URISyntaxException | IOException e) { e.printStackTrace();}
			}
		});
		
		mnAbout.add(shareYourOpinion);
		
		final JMenuItem license = new JMenuItem("App License");
		license.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/menubar_license.png")));
		license.addActionListener(ActionListener ->{
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new LicenseWindow();	
				}
			});
		});
		
		mnAbout.add(license);
		
	}
	
	
	
}
