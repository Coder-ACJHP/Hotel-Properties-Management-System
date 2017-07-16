/**
 * 
 */
/**
 * @author MacbookPro
 *
 */
package com.coder.hms.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.utils.ApplicationLogo;
import com.coder.hms.utils.GetLiveCurrencyRates;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomMenuBar customMenuBar;
	private final HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
	private final ApplicationLogo logoSetter = new ApplicationLogo();
	private final GetLiveCurrencyRates currency = new GetLiveCurrencyRates();
	
	/*get the external toolbar and initialize it*/
	private final CustomUpperToolbar customToolbar;
	private final BottomToolbar customBottomToolbar;
	
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
	
	// Set basic properties for main frame.
	public MainFrame() {

		// get operation system name to add icon (if windows to taskbar else for dock)
		logoSetter.setApplicationLogoJFrame(this, LOGOPATH);
		
		this.setTitle("Coder for HMS (BETA 1)");
		this.getContentPane().setBackground(Color.decode("#066d95"));
		

		this.setMinimumSize(new Dimension(800, 600));
		/*make it default size of frame maximized */
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		/*set exiting from the application when clicking on X button*/
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				final int decision =JOptionPane.showConfirmDialog(null, "Are you sure to exit?", 
						"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(decision == JOptionPane.YES_OPTION) {
					new DataSourceFactory().close();
					System.exit(0);
				}
				else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
				
				super.windowClosing(e);
			}
		});
		
		/*get the external menubar and initialize it*/
		customMenuBar = new CustomMenuBar();
		customMenuBar.setJFrame(this);
		customMenuBar.themeChanger.setFrame(this);
		/*add it to our frame*/
		this.setJMenuBar(customMenuBar.getMenuBar());
		
		customToolbar = new CustomUpperToolbar(this);
		customBottomToolbar = new BottomToolbar();
		customBottomToolbar.setUserLabelText("Onur");
		customBottomToolbar.setUsdLabelText(currency.getUSDToTRYLiveCurrency());
		customBottomToolbar.setEuroLabelText(currency.getEURToTRYLiveCurrency());
		customBottomToolbar.setPoundLabelText(currency.getGBPToTRYLiveCurrency());
		customBottomToolbar.setDateLabelText("");
		customBottomToolbar.sethotelNameLabelText(hotelDaoImpl.getHotel().getName());
		/*add it to our frame*/
		getContentPane().add(customToolbar.getJPanel(), BorderLayout.NORTH);
		
		getContentPane().add(customBottomToolbar.getToolBar(), BorderLayout.SOUTH);

		
		this.setVisible(true);

	}
	

}