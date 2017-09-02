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
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import com.coder.hms.beans.LocaleBean;
import com.coder.hms.beans.SessionBean;
import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.daoImpl.UserDaoImpl;
import com.coder.hms.entities.User;
import com.coder.hms.ui.external.ChangePasswordWindow;
import com.coder.hms.ui.external.ExchangeWindow;
import com.coder.hms.ui.external.HotelPropertiesWindow;
import com.coder.hms.ui.external.LicenseWindow;
import com.coder.hms.ui.external.LoginWindow;
import com.coder.hms.ui.external.ReadLogsWindow;
import com.coder.hms.utils.ApplicaitonThemeChanger;
import com.coder.hms.utils.ResourceControl;

public class Main_MenuBar {

	private JMenuBar menuBar;
	private JFrame mainFrame;
	private String exitMessage = "";
	private String titleMessage = "";
	private final Runtime run = Runtime.getRuntime();
	private final LocaleBean bean = LocaleBean.getInstance();
	private final static Desktop desktop = Desktop.getDesktop();
	private final String command = System.getProperty("os.name");
	public final ApplicaitonThemeChanger themeChanger = new ApplicaitonThemeChanger();
	private JMenu frontDesk, mnTools, themes, usersMenu, mnAbout;
	private JMenuItem hoteProps, restart, menuInnerItemExit, calculator, sendMail, exchange, systemLogs,
	defaultTheme, mnitmAero, mnitmBernstain, mnitmMint, mnitmMcwin, changeUser, chngPassword, aboutDeveloper,
	sourceCode, shareYourOpinion, license;
	
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
		menuBar.setFont(new Font("Dialog", Font.BOLD, 14));
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBorder(new LineBorder(new Color(128, 128, 128)));
		menuBar.setAutoscrolls(true);
		
		frontDesk = new JMenu("Front Desk");
		frontDesk.setFont(new Font("Dialog", Font.BOLD, 13));
		menuBar.add(frontDesk);
		
		hoteProps = new JMenuItem("Hotel Properties");
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
		frontDesk.add(hoteProps);
		
		restart = new JMenuItem("Restart");
		restart.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/menuBar_restart.png")));
		restart.addActionListener(ActionListener ->{
			
//			ScheduledExecutorService schedulerExecutor = Executors.newScheduledThreadPool(2);
//			Callable<Process> callable = new Callable<Process>() {
//
//			    @Override
//			    public Process call() throws Exception {
//			        Process p = Runtime.getRuntime().exec("cmd /c start /b java -jar D:\\MovieLibrary.jar");
//			        return p;
//			    }
//			};
//			FutureTask<Process> futureTask = new FutureTask<Process>(callable);
//			schedulerExecutor.submit(futureTask);           
//
//			System.exit(0);
		});
		frontDesk.add(restart);
		
		menuInnerItemExit = new JMenuItem("Exit");
		menuInnerItemExit.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/main_exit.png")));
		//add shortcut keys
		menuInnerItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,(InputEvent.SHIFT_MASK |
												Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		menuInnerItemExit.setMnemonic(KeyEvent.VK_Q + KeyEvent.VK_CONTROL);
		//add listener for exiting when CTRL+SHIFT+Q pressed
		menuInnerItemExit.addActionListener(ActionEvent -> {

			final int decision =JOptionPane.showConfirmDialog(null, exitMessage, 
					titleMessage, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(decision == JOptionPane.YES_OPTION) {
				new DataSourceFactory().shutDown();
				System.exit(0);
			}
			else {
				mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			}

		});
		frontDesk.add(menuInnerItemExit);
		
		
		mnTools = new JMenu("Tools");
		mnTools.setFont(new Font("Dialog", Font.BOLD, 13));
		menuBar.add(mnTools);
		
		//add calculator application section to menubar
		calculator = new JMenuItem("Calculator");
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
		sendMail = new JMenuItem("Send email");
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
		
		exchange = new JMenuItem("Exchange");
		exchange.setIcon(new ImageIcon(getClass().getResource("/com/coder/hms/icons/menubar_exchange.png")));
		exchange.addActionListener(ActionListener ->{
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new ExchangeWindow();
				}
				
			});
		});
		mnTools.add(exchange);
		
		systemLogs = new JMenuItem("System logs");
		systemLogs.setIcon(new ImageIcon(Main_MenuBar.class.getResource("/com/coder/hms/icons/logging.png")));
		systemLogs.addActionListener(ActionListener ->{
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new ReadLogsWindow();
				}
				
			});
		});
		mnTools.add(systemLogs);
		
		/*Add new menu into Tools menu*/	
		themes = new JMenu("Themes");
		themes.setFont(new Font("Dialog", Font.BOLD, 13));
		menuBar.add(themes);
		
		/*Add all themes into themes section */		
		defaultTheme = new JMenuItem("Nimbus");
		defaultTheme.addActionListener(ActionListener ->{
			themeChanger.ChangeTheme("Nimbus");
		});
		mnitmAero = new JMenuItem("Aero");
		mnitmAero.addActionListener(ActionListener ->{
			themeChanger.ChangeTheme("Aero");
		});
		mnitmBernstain = new JMenuItem("Bernstein");
		mnitmBernstain.addActionListener(ActionListener ->{
			themeChanger.ChangeTheme("bernstein");
		});
		mnitmMint = new JMenuItem("Mint");
		mnitmMint.addActionListener(ActionListener ->{
			themeChanger.ChangeTheme("Mint");
		});
		mnitmMcwin = new JMenuItem("McWin");
		mnitmMcwin.addActionListener(ActionListener ->{
			themeChanger.ChangeTheme("McWin");
		});
		
		themes.add(defaultTheme);
		themes.add(mnitmAero);
		themes.add(mnitmBernstain);
		themes.add(mnitmMint);
		themes.add(mnitmMcwin);
		
		usersMenu = new JMenu("Users");
		usersMenu.setFont(new Font("Dialog", Font.BOLD, 13));
		menuBar.add(usersMenu);
		
		changeUser = new JMenuItem("Change user");
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
		
		chngPassword = new JMenuItem("Change password");
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
				
		mnAbout = new JMenu("Others");
		mnAbout.setFont(new Font("Dialog", Font.BOLD, 13));
		menuBar.add(mnAbout);
		
		//add about developer section to menubar
		aboutDeveloper = new JMenuItem("About developer");
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
		sourceCode = new JMenuItem("Source code");
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
		shareYourOpinion = new JMenuItem("Feedback");
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
		
		license = new JMenuItem("App License");
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
		
		changeLanguage(bean.getLocale());
	}
	
	private void changeLanguage(Locale locale) {

		final ResourceBundle bundle = ResourceBundle
				.getBundle("com/coder/hms/languages/LocalizationBundle", locale, new ResourceControl());

		frontDesk.setText(bundle.getString("FrontDesk"));
		hoteProps.setText(bundle.getString("HotelProperties"));
		restart.setText(bundle.getString("Restart"));
		menuInnerItemExit.setText(bundle.getString("Exit"));
		mnTools.setText(bundle.getString("Tools"));
		calculator.setText(bundle.getString("Calculator"));
		sendMail.setText(bundle.getString("SendEmail"));
		exchange.setText(bundle.getString("Exchange"));
		themes.setText(bundle.getString("Themes"));
		usersMenu.setText(bundle.getString("Users"));
		changeUser.setText(bundle.getString("ChangeUser"));
		chngPassword.setText(bundle.getString("ChangePwd"));
		mnAbout.setText(bundle.getString("Others"));
		aboutDeveloper.setText(bundle.getString("AboutDev"));
		sourceCode.setText(bundle.getString("SourceCode"));
		shareYourOpinion.setText(bundle.getString("Feedback"));
		license.setText(bundle.getString("License"));
		exitMessage = bundle.getString("ExitMessage");
		titleMessage = bundle.getString("Confirmation");
		
		menuBar.revalidate();
		menuBar.repaint();
	}
	
}
