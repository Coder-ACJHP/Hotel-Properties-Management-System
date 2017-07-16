package com.coder.hms.userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.coder.hms.loginface.LoginFrame;
import com.coder.hms.utils.ApplicationLogo;
import com.toedter.calendar.JDateChooser;

public class NewReservationFrame extends JDialog {

	/**
	 * 
	 */
	private int value = 0;
	private double priceValue = 0.0;
	private JFormattedTextField priceField;
	private JButton chancelBtn, SaveBtn, reportBtn;
	private JDateChooser checkinDate, checkoutDate;
	private static final long serialVersionUID = 1L;
	private ApplicationLogo logoSetter = new ApplicationLogo();
	private JLabel agencyLbl, creditTypeLbl, customerCountryLbl;
	private JTextField rezIdField, nameSurnameField, totalDaysField;
	private JSpinner roomCountSpinner, personCountSpinner; 
	private final String[] INFO_TAB_TABLE_HEADERS = {"ROOM", "TYPE", "CHECK/IN", "CHECK/OUT", "CUSTOMER TYPE"};
	private final String[] HOST_TYPES = {"B.B", "F.B", "H.B", "O.B"};
	private final String[] RESERV_STS = {"GUARANTEE", "WAITLIST", "CANCEL"};
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
	private final String[] cmbList = { "TURKISH LIRA", "$ DOLLAR", "€ EURO", "£ POUND"};
	private final String[] CREDIT_TYPES = {"BLACK LIST", "INFINITY CREDIT", "STANDART CUSTOMER CREDIT"};
	private final String[] EARLY_PAYMENT_LIST = {"TITLE", "NAME", "AMOUNT", "CURRENCY", "EXCHANGE", "NOTE"};
	private JComboBox<String> agencyCmbBox, hostCmbBox, creaditTypeCmbBox, rezervStatusCmbBox, customerCountryCmbBox, currencyCmbBox, roomTypeCmbBox;
	private final String[] COUNTRY_LIST = {"Afghanistan", "Albania","Algeria", "American Samoa", "Andorra",
		"Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
		 "Brazil", "Egypt", "Finland", "France", "Germany", "Hong Kong", "India", "Iran", "Iraq", "Ireland", "Israel", "Islands",
		 "Italy", "Jamaica", "Japan", "Republic of Korea", "Kuwait", "Lebanon", "Malaysia", "Mexico", "Nigeria", "Poland",
		 "Portugal", "Puerto Rico,PR", "Qatar", "Romania", "Russian Federation", "Saudi Arabia", "Singapore", "Spain", "Sweden",
		 "Switzerland", "Syrian Arab Republic", "Thailand", "Tunisia", "Turkey", "Turkmenistan", "Ukraine", "United Arab Emirates",
		 "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Virgin Islands U.S.", "Yemen", "Zambia", "Zimbabwe"};
	private JTable table;
	private JTable table_1;
	/**
	 * Create the dialog.
	 */
	public NewReservationFrame() {
		// set upper icon for dialog frame
		logoSetter.setApplicationLogoJDialog(this, LOGOPATH);

		getContentPane().setForeground(new Color(255, 99, 71));
		getContentPane().setFocusCycleRoot(true);
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().setFont(new Font("Verdana", Font.BOLD, 12));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setResizable(false);

		this.setTitle("Coder for HMS - New Reservation");

		/* Set default size of frame */
		this.setSize(780, 550);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.decode("#066d95"));
		getContentPane().setLayout(null);
		
		JLabel rezIdLbl = new JLabel("Reservation Id : ");
		rezIdLbl.setForeground(new Color(255, 255, 255));
		rezIdLbl.setFont(new Font("Verdana", Font.BOLD, 12));
		rezIdLbl.setBounds(10, 11, 132, 23);
		getContentPane().add(rezIdLbl);
		
		rezIdField = new JTextField();
		rezIdField.setEnabled(false);
		rezIdField.setEditable(false);
		rezIdField.setColumns(10);
		rezIdField.setBounds(152, 12, 136, 20);
		getContentPane().add(rezIdField);
		
		JLabel nameSurnameLbl = new JLabel("Name  & Surname : ");
		nameSurnameLbl.setForeground(new Color(255, 255, 255));
		nameSurnameLbl.setFont(new Font("Verdana", Font.BOLD, 12));
		nameSurnameLbl.setBounds(10, 43, 136, 23);
		getContentPane().add(nameSurnameLbl);
		
		nameSurnameField = new JTextField();
		nameSurnameField.setBounds(152, 43, 209, 20);
		getContentPane().add(nameSurnameField);
		nameSurnameField.setColumns(10);
		
		JLabel checkinLbl = new JLabel("Checkin : ");
		checkinLbl.setForeground(new Color(255, 255, 255));
		checkinLbl.setFont(new Font("Verdana", Font.BOLD, 12));
		checkinLbl.setBounds(10, 75, 90, 23);
		getContentPane().add(checkinLbl);
		
		checkinDate = new JDateChooser();
		checkinDate.setCalendar(Calendar.getInstance());
		checkinDate.setDateFormatString("dd/MM/yyyy");
		checkinDate.setBounds(152, 75, 209, 20);
		checkinDate.addPropertyChangeListener(chechkDates());
		getContentPane().add(checkinDate);
		
		JLabel lblCheckout = new JLabel("Checkout : ");
		lblCheckout.setForeground(new Color(255, 255, 255));
		lblCheckout.setFont(new Font("Verdana", Font.BOLD, 12));
		lblCheckout.setBounds(10, 106, 90, 23);
		getContentPane().add(lblCheckout);
		
		checkoutDate = new JDateChooser();
		checkoutDate.setDateFormatString("dd/MM/yyyy");
		checkoutDate.setBounds(152, 106, 209, 20);
		checkoutDate.addPropertyChangeListener(chechkDates());
		getContentPane().add(checkoutDate);
		
		JLabel totalDaysLbl = new JLabel("Total days : ");
		totalDaysLbl.setForeground(new Color(255, 255, 255));
		totalDaysLbl.setFont(new Font("Verdana", Font.BOLD, 12));
		totalDaysLbl.setBounds(10, 136, 90, 23);
		getContentPane().add(totalDaysLbl);
		
		totalDaysField = new JTextField();
		totalDaysField.setEnabled(true);
		totalDaysField.setEditable(false);
		totalDaysField.setText(value + "");
		totalDaysField.setBounds(152, 137, 136, 20);
		getContentPane().add(totalDaysField);
		
		agencyLbl = new JLabel("Agency : ");
		agencyLbl.setForeground(new Color(255, 255, 255));
		agencyLbl.setFont(new Font("Verdana", Font.BOLD, 12));
		agencyLbl.setBounds(384, 11, 90, 23);
		getContentPane().add(agencyLbl);
		
		agencyCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>());
		agencyCmbBox.setBounds(539, 11, 209, 20);
		getContentPane().add(agencyCmbBox);
		
		JLabel hostTypeLbl = new JLabel("Host type : ");
		hostTypeLbl.setForeground(new Color(255, 255, 255));
		hostTypeLbl.setFont(new Font("Verdana", Font.BOLD, 12));
		hostTypeLbl.setBounds(384, 44, 90, 23);
		getContentPane().add(hostTypeLbl);
		
		hostCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(HOST_TYPES));
		hostCmbBox.setSelectedIndex(0);
		hostCmbBox.setBounds(539, 42, 209, 20);
		getContentPane().add(hostCmbBox);
		
		creditTypeLbl = new JLabel("Credit type : ");
		creditTypeLbl.setForeground(new Color(255, 255, 255));
		creditTypeLbl.setFont(new Font("Verdana", Font.BOLD, 12));
		creditTypeLbl.setBounds(384, 73, 90, 23);
		getContentPane().add(creditTypeLbl);
		
		creaditTypeCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(CREDIT_TYPES));
		creaditTypeCmbBox.setSelectedIndex(2);
		creaditTypeCmbBox.setBounds(539, 73, 209, 20);
		getContentPane().add(creaditTypeCmbBox);
		
		JLabel reservationNoteLbl = new JLabel("Reservation Note : ");
		reservationNoteLbl.setForeground(new Color(255, 255, 255));
		reservationNoteLbl.setFont(new Font("Verdana", Font.BOLD, 12));
		reservationNoteLbl.setBounds(10, 191, 151, 23);
		getContentPane().add(reservationNoteLbl);
		
		JTextArea noteTextArea = new JTextArea();
		noteTextArea.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		noteTextArea.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		noteTextArea.setBackground(SystemColor.info);
		noteTextArea.setLineWrap(true);
		noteTextArea.setBounds(162, 170, 491, 64);
		getContentPane().add(noteTextArea);
		
		JLabel reservationStatusLbl = new JLabel("Reservation Status : ");
		reservationStatusLbl.setForeground(new Color(255, 255, 255));
		reservationStatusLbl.setFont(new Font("Verdana", Font.BOLD, 12));
		reservationStatusLbl.setBounds(384, 106, 146, 23);
		getContentPane().add(reservationStatusLbl);
		
		rezervStatusCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(RESERV_STS));
		rezervStatusCmbBox.setSelectedIndex(0);
		rezervStatusCmbBox.setBounds(540, 107, 209, 20);
		getContentPane().add(rezervStatusCmbBox);
		
		customerCountryLbl = new JLabel("Customer Country : ");
		customerCountryLbl.setForeground(new Color(255, 255, 255));
		customerCountryLbl.setFont(new Font("Verdana", Font.BOLD, 12));
		customerCountryLbl.setBounds(384, 136, 146, 23);
		getContentPane().add(customerCountryLbl);
		
		customerCountryCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(COUNTRY_LIST));
		customerCountryCmbBox.setBounds(539, 137, 209, 20);
		customerCountryCmbBox.setSelectedIndex(0);
		getContentPane().add(customerCountryCmbBox);
		
		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(341, 463, 423, 49);
		buttonsPanel.setForeground(new Color(95, 158, 160));
		buttonsPanel.setBackground(Color.decode("#066d95"));
		getContentPane().add(buttonsPanel);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		chancelBtn = new JButton("CANCEL");
		chancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		chancelBtn.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/coder/hms/icons/login_clear.png")));
		chancelBtn.setForeground(new Color(220, 20, 60));
		chancelBtn.setOpaque(true);
		chancelBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		chancelBtn.setPreferredSize(new Dimension(110, 40));
		chancelBtn.setFont(new Font("Verdana", Font.BOLD, 15));
		buttonsPanel.add(chancelBtn);

		
		reportBtn = new JButton("REPORT");
		reportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		reportBtn.setIcon(new ImageIcon(NewReservationFrame.class.getResource("/com/coder/hms/icons/rezaerv_report.png")));
		reportBtn.setForeground(new Color(0, 128, 128));
		reportBtn.setOpaque(true);
		reportBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		reportBtn.setPreferredSize(new Dimension(110, 40));
		reportBtn.setFont(new Font("Verdana", Font.BOLD, 15));
		buttonsPanel.add(reportBtn);
		
		SaveBtn = new JButton("SAVE");
		SaveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		SaveBtn.setMaximumSize(new Dimension(120, 23));
		SaveBtn.setMinimumSize(new Dimension(120, 23));
		SaveBtn.setToolTipText("Press ALT + ENTER keys for shortcut");
		SaveBtn.setSelectedIcon(null);
		SaveBtn.setIcon(new ImageIcon(NewReservationFrame.class.getResource("/com/coder/hms/icons/reserv_save.png")));
		SaveBtn.setForeground(new Color(0, 191, 255));
		SaveBtn.setOpaque(true);
		SaveBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		SaveBtn.setMnemonic(KeyEvent.VK_ENTER);
		SaveBtn.setPreferredSize(new Dimension(130, 40));
		SaveBtn.setFont(new Font("Verdana", Font.BOLD, 15));
		buttonsPanel.add(SaveBtn);
		
		final JPanel panel = new JPanel();
		panel.setBounds(10, 245, 754, 205);
		getContentPane().add(panel);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.activeCaption);
		panel.setAutoscrolls(true);
		panel.setLayout(new BorderLayout(0, 0));
		final JPanel roomPanel = new JPanel();
		final JPanel infoPanel = new JPanel();
		final JPanel earlyPanel = new JPanel();
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("RoomFrame Type", roomPanel);
		
		final JPanel roomTypePanel = new JPanel();
		roomTypePanel.setAutoscrolls(true);
		roomTypePanel.setPreferredSize(new Dimension(745, 65));
		roomTypePanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		roomTypePanel.setBackground(SystemColor.activeCaption);
		roomPanel.add(roomTypePanel);
		roomTypePanel.setLayout(null);
		
		final JLabel lblNewLabel = new JLabel("RoomFrame Type");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(56, 12, 86, 14);
		roomTypePanel.add(lblNewLabel);
		
		final JLabel lblRoomCount = new JLabel("RoomFrame Count");
		lblRoomCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomCount.setBounds(221, 12, 76, 14);
		roomTypePanel.add(lblRoomCount);
		
		final JLabel lblPersonCount = new JLabel("Person Count");
		lblPersonCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonCount.setBounds(345, 12, 76, 14);
		roomTypePanel.add(lblPersonCount);
		
		final JLabel lblCurrency = new JLabel("Currency");
		lblCurrency.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrency.setBounds(594, 12, 86, 14);
		roomTypePanel.add(lblCurrency);
		
		roomTypeCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>());
		roomTypeCmbBox.setBounds(31, 36, 140, 20);
		roomTypePanel.add(roomTypeCmbBox);
		
		roomCountSpinner = new JSpinner();
		roomCountSpinner.setBounds(244, 36, 40, 20);
		roomTypePanel.add(roomCountSpinner);
		
		personCountSpinner = new JSpinner();
		personCountSpinner.setBounds(363, 36, 40, 20);
		roomTypePanel.add(personCountSpinner);
		
		currencyCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(cmbList));
		currencyCmbBox.setBounds(571, 36, 140, 20);
		roomTypePanel.add(currencyCmbBox);
		
		final JLabel lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(465, 12, 46, 14);
		roomTypePanel.add(lblPrice);
		
		final NumberFormat formatter = NumberFormat.getCurrencyInstance();
		formatter.setCurrency(Currency.getInstance(Locale.getDefault()));
		priceField = new JFormattedTextField(formatter);
		priceField.setValue(new Double(priceValue));
		priceField.setBounds(460, 36, 57, 20);
		roomTypePanel.add(priceField);
		
		tabbedPane.addTab("Informations", infoPanel);
		infoPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		infoPanel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(new DefaultTableModel(INFO_TAB_TABLE_HEADERS, 0));
		scrollPane.setViewportView(table);
		
		tabbedPane.addTab("Early Payment", earlyPanel);
		earlyPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("Add new Payment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread paymentFrame = new Thread(new Runnable() {
					
					@Override
					public void run() {
						new PaymentFrame();
						
					}
				}); 
				paymentFrame.start();
			}
		});
		btnNewButton.setIcon(new ImageIcon(NewReservationFrame.class.getResource("/com/coder/hms/icons/newReserv_payment.png")));
		btnNewButton.setSelectedIcon(new ImageIcon(NewReservationFrame.class.getResource("/com/coder/hms/icons/newReserv_payment.png")));
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.setBounds(10, 11, 150, 35);
		earlyPanel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 57, 723, 103);
		earlyPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		table_1 = new JTable(new DefaultTableModel(EARLY_PAYMENT_LIST, 0));
		scrollPane_1.setViewportView(table_1);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		this.setVisible(true);
		
	}
	
	private PropertyChangeListener chechkDates() {
		final PropertyChangeListener listener = new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				
				//here leave ancestors empty because we need dates not ancestor
				if(evt.getPropertyName().equals("ancestor")) {
					return;
				}
				//main reason
				else {
					Thread thread = new Thread(new Runnable() {
						
						@Override
						public void run() {
							boolean showed = false;
							final Date startDate = checkinDate.getDate();
							final Date endDate = checkoutDate.getDate();
							
						if(startDate != null && endDate != null) {	
							//add to calendar to be able get day of date and compare
							Calendar cs = Calendar.getInstance();
							cs.setTime(startDate);
							Calendar ce = Calendar.getInstance();
							ce.setTime(endDate);
							
								//compare if start date greater than end date
								if(cs.after(ce) && !showed) {
									JOptionPane.showMessageDialog(null, "Start date is after end date!", 
													JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
									showed = true;
								}
								//or both is same date
								else if(cs.get(Calendar.DAY_OF_YEAR) == ce.get(Calendar.DAY_OF_YEAR) && !showed) {
									JOptionPane.showMessageDialog(null, "Start date equals end date!\nPlease be sure you're choose right date.", 
											JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
									showed = true;
								}
								//other odds
								else {
									value = (int)( (startDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24) );
									totalDaysField.setText(Math.abs(value) + "");
								}
						}
							
						}
					});	
					thread.start();
				}
			}
		};
		return listener;
	}
}
