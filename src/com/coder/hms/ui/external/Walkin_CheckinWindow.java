package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.coder.hms.beans.SessionBean;
import com.coder.hms.daoImpl.CustomerDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.ui.inner.CustomerForm;
import com.coder.hms.utils.LoggingEngine;
import com.toedter.calendar.JDateChooser;

public class Walkin_CheckinWindow extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private int value = 0;
	private Date startDate;
	private Date endDate;
	private JPanel upperPanel;
        private Room injectedOwnRoom;
	private NumberFormat formatter;
	private double priceValue = 0.0;
	private JSpinner personCountSpinner;
	private JFormattedTextField priceField;
	private static LoggingEngine loggingEngine;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField groupNameField, totalDaysField;
	private JDateChooser checkinDateChooser, checkoutDateChooser;
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public final CustomerForm customerFormOne = new CustomerForm();
	public final CustomerForm customerFormTwo = new CustomerForm();
	public final CustomerForm customerFormThree = new CustomerForm();
        public CustomerForm[] formsArray;
	private static SessionBean sessionBean = SessionBean.getSESSION_BEAN();
	private JComboBox<String> agencyCmbBox, customerCnrtyCmbBox, creditTypeCmbBox, hostTypeCmbBox, currencyCmbBox;
	private final String[] AGENCY_LIST = {"WALKIN"};
	private final String[] HOST_TYPES = {"B.B", "F.B", "H.B", "O.B"};
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
	private final String[] CURRENCY = { "TURKISH LIRA", "DOLLAR", "EURO", "POUND"};
	private final String[] CREDIT_TYPES = {"BLACK LIST", "INFINITY CREDIT", "STANDART CUSTOMER CREDIT"};
	private final String[] COUNTRY_LIST = {"Afghanistan", "Albania","Algeria", "American Samoa", "Andorra",
			"Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
			 "Brazil", "Egypt", "Finland", "France", "Germany", "Hong Kong", "India", "Iran", "Iraq", "Ireland", "Israel", "Islands",
			 "Italy", "Jamaica", "Japan", "Republic of Korea", "Kuwait", "Lebanon", "Malaysia", "Mexico", "Nigeria", "Poland",
			 "Portugal", "Puerto Rico,PR", "Qatar", "Romania", "Russian Federation", "Saudi Arabia", "Singapore", "Spain", "Sweden",
			 "Switzerland", "Syrian Arab Republic", "Thailand", "Tunisia", "Turkey", "Turkmenistan", "Ukraine", "United Arab Emirates",
			 "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Virgin Islands U.S.", "Yemen", "Zambia", "Zimbabwe"};



	/**
	 * Create the dialog.
	 */
	public Walkin_CheckinWindow(Room injectedRoom) {
		
                this.injectedOwnRoom =  injectedRoom;
                
		loggingEngine = LoggingEngine.getInstance();
		loggingEngine.setMessage("User is : " + sessionBean.getNickName());
		
		setMinimumSize(new Dimension(750, 600));
		setPreferredSize(new Dimension(750, 600));
		setLocationRelativeTo(null);

		this.setIconImage(Toolkit.getDefaultToolkit().
				getImage(getClass().getResource(LOGOPATH)));

		getContentPane().setForeground(new Color(255, 99, 71));
		getContentPane().setFocusCycleRoot(true);
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().setFont(new Font("Verdana", Font.BOLD, 12));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);

		this.setTitle("Coder HPMSA - [Walkin Checkin]");
		contentPanel.setAutoscrolls(true);
		contentPanel.setPreferredSize(new Dimension(10, 415));

		contentPanel.setBackground(Color.decode("#066d95"));
		contentPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		upperPanel = new JPanel();
		upperPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		upperPanel.setBackground(new Color(135, 206, 235));
		upperPanel.setPreferredSize(new Dimension(10, 150));
		contentPanel.add(upperPanel, BorderLayout.NORTH);
		upperPanel.setLayout(null);
		
		agencyCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(AGENCY_LIST));
		agencyCmbBox.setBounds(511, 11, 209, 20);
		upperPanel.add(agencyCmbBox);

		JLabel changePersonLbl = new JLabel("Change person count : ");
		changePersonLbl.setForeground(Color.BLACK);
		changePersonLbl.setBounds(357, 115, 152, 18);
		changePersonLbl.setFont(new Font("Arial", Font.BOLD, 13));
		upperPanel.add(changePersonLbl);

		personCountSpinner = new JSpinner();
		personCountSpinner.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		personCountSpinner.setBounds(512, 115, 40, 20);
		personCountSpinner.setPreferredSize(new Dimension(40, 20));
		personCountSpinner.setMinimumSize(new Dimension(35, 20));
		personCountSpinner.addChangeListener(customerCounterListener());
		upperPanel.add(personCountSpinner);
		
		final JLabel groupNameLbl = new JLabel("Group Name  :");
		groupNameLbl.setFont(new Font("Arial", Font.BOLD, 13));
		groupNameLbl.setForeground(Color.BLACK);
		groupNameLbl.setBounds(10, 11, 110, 20);
		upperPanel.add(groupNameLbl);
		
		groupNameField = new JTextField();
		groupNameField.setBounds(131, 11, 189, 20);
		upperPanel.add(groupNameField);
		groupNameField.setColumns(10);
		
		final JLabel lblCheckinDate = new JLabel("Checkin date : ");
		lblCheckinDate.setFont(new Font("Arial", Font.BOLD, 13));
		lblCheckinDate.setForeground(Color.BLACK);
		lblCheckinDate.setBounds(10, 37, 110, 20);
		upperPanel.add(lblCheckinDate);
		
		checkinDateChooser = new JDateChooser();
		checkinDateChooser.setCalendar(Calendar.getInstance(Locale.getDefault()));
		checkinDateChooser.setDateFormatString("dd/MM/yyyy");
		checkinDateChooser.setBounds(131, 36, 189, 20);
		checkinDateChooser.addPropertyChangeListener(chechkDates());
		upperPanel.add(checkinDateChooser);
		
		checkoutDateChooser = new JDateChooser();
		checkoutDateChooser.setDateFormatString("dd/MM/yyyy");
		checkoutDateChooser.setBounds(132, 63, 188, 20);
		checkoutDateChooser.addPropertyChangeListener(chechkDates());
		upperPanel.add(checkoutDateChooser);
		
		final JLabel lblCheckoutDate = new JLabel("Checkout date : ");
		lblCheckoutDate.setFont(new Font("Arial", Font.BOLD, 13));
		lblCheckoutDate.setForeground(Color.BLACK);
		lblCheckoutDate.setBounds(10, 63, 110, 20);
		upperPanel.add(lblCheckoutDate);
		
		final JLabel totalDaysLbl = new JLabel("Total days : ");
		totalDaysLbl.setFont(new Font("Arial", Font.BOLD, 13));
		totalDaysLbl.setForeground(Color.BLACK);
		totalDaysLbl.setBounds(10, 88, 110, 20);
		upperPanel.add(totalDaysLbl);
		
		totalDaysField = new JTextField();
		totalDaysField.setColumns(10);
		totalDaysField.setEnabled(true);
		totalDaysField.setEditable(false);
		totalDaysField.setBounds(131, 88, 96, 20);
		totalDaysField.setText(value + "");
		upperPanel.add(totalDaysField);
		
		final JLabel customerCountryLbl = new JLabel("Customer Country : ");
		customerCountryLbl.setForeground(Color.BLACK);
		customerCountryLbl.setFont(new Font("Arial", Font.BOLD, 13));
		customerCountryLbl.setBounds(356, 88, 139, 20);
		upperPanel.add(customerCountryLbl);
		
		customerCnrtyCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(COUNTRY_LIST));
		customerCnrtyCmbBox.setSelectedIndex(0);
		customerCnrtyCmbBox.setBounds(511, 88, 209, 20);
		upperPanel.add(customerCnrtyCmbBox);
		
		final JLabel creditTypeLbl = new JLabel("Credit type : ");
		creditTypeLbl.setForeground(Color.BLACK);
		creditTypeLbl.setFont(new Font("Arial", Font.BOLD, 13));
		creditTypeLbl.setBounds(356, 63, 139, 20);
		upperPanel.add(creditTypeLbl);
		
		final JLabel agencyLbl = new JLabel("Agency : ");
		agencyLbl.setForeground(Color.BLACK);
		agencyLbl.setFont(new Font("Arial", Font.BOLD, 13));
		agencyLbl.setBounds(356, 11, 139, 20);
		upperPanel.add(agencyLbl);
		
		creditTypeCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(CREDIT_TYPES));
		creditTypeCmbBox.setSelectedIndex(2);
		creditTypeCmbBox.setBounds(511, 63, 209, 20);
		upperPanel.add(creditTypeCmbBox);
		
		final JLabel hostTypeLbl = new JLabel("Host type : ");
		hostTypeLbl.setForeground(Color.BLACK);
		hostTypeLbl.setFont(new Font("Arial", Font.BOLD, 13));
		hostTypeLbl.setBounds(356, 37, 139, 20);
		upperPanel.add(hostTypeLbl);
		
		hostTypeCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(HOST_TYPES));
		hostTypeCmbBox.setSelectedIndex(0);
		hostTypeCmbBox.setBounds(511, 37, 209, 20);
		upperPanel.add(hostTypeCmbBox);
		
		formatter = NumberFormat.getInstance();
		formatter.setMinimumFractionDigits(2);
		
		priceField = new JFormattedTextField(formatter);
		priceField.setValue(new Double(priceValue));
		priceField.setColumns(10);
		priceField.setBounds(132, 119, 73, 20);
		upperPanel.add(priceField);
		
		currencyCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(CURRENCY));
		currencyCmbBox.setBounds(206, 119, 116, 20);
		currencyCmbBox.addItemListener(currencyActionListener());
		upperPanel.add(currencyCmbBox);
		
		JLabel lblPrice = new JLabel("Price : ");
		lblPrice.setFont(new Font("Arial", Font.BOLD, 13));
		lblPrice.setBounds(10, 120, 110, 14);
		upperPanel.add(lblPrice);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		buttonPanel.setPreferredSize(new Dimension(10, 50));
		buttonPanel.setLayout(null);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);

		JButton roomCheckinBtn = new JButton("Room checkin");
		roomCheckinBtn.addActionListener(this);
		roomCheckinBtn.setIcon(new ImageIcon(Walkin_CheckinWindow.class.
						getResource("/com/coder/hms/icons/extra_checkin.png")));
		roomCheckinBtn.setBounds(7, 4, 144, 42);
		buttonPanel.add(roomCheckinBtn);

		final JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(158, 6, 10, 36);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setFocusable(true);
		separator.setForeground(Color.DARK_GRAY);
		separator.setAutoscrolls(true);
		separator.setPreferredSize(new Dimension(10, 20));
		buttonPanel.add(separator);

		JLabel lblRoom = new JLabel("ROOM : ");
		lblRoom.setFont(new Font("Verdana", Font.BOLD, 15));
		lblRoom.setBounds(330, 8, 68, 33);
		buttonPanel.add(lblRoom);

		JLabel roomNumberLbl = new JLabel(injectedOwnRoom.getNumber());
		roomNumberLbl.setForeground(new Color(220, 20, 60));
		roomNumberLbl.setFont(new Font("Verdana", Font.BOLD, 17));
		roomNumberLbl.setBounds(406, 8, 103, 33);
		buttonPanel.add(roomNumberLbl);
		
                formsArray = new CustomerForm[]{customerFormOne};
		contentPanel.add(customerFormOne.setCustomerDetailPanel(), BorderLayout.WEST);

	}

	private ChangeListener customerCounterListener() {

		final ChangeListener spinnerListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				switch ((int) personCountSpinner.getValue()) {
				case 1:
					contentPanel.removeAll();
					contentPanel.add(upperPanel, BorderLayout.NORTH);
					contentPanel.add(customerFormOne.setCustomerDetailPanel(), BorderLayout.WEST);
                                        formsArray = new CustomerForm[]{customerFormOne};
					contentPanel.revalidate();
					contentPanel.repaint();
				case 2:
					contentPanel.removeAll();
					contentPanel.add(upperPanel, BorderLayout.NORTH);
					contentPanel.add(customerFormOne.setCustomerDetailPanel(), BorderLayout.WEST);
					contentPanel.add(customerFormTwo.setCustomerDetailPanel(), BorderLayout.EAST);
                                        formsArray = new CustomerForm[]{customerFormOne, customerFormTwo};
					contentPanel.revalidate();
					contentPanel.repaint();
					break;
				case 3:
					contentPanel.removeAll();
					contentPanel.add(upperPanel, BorderLayout.NORTH);
					contentPanel.add(customerFormOne.setCustomerDetailPanel(), BorderLayout.WEST);
					contentPanel.add(customerFormTwo.setCustomerDetailPanel(), BorderLayout.EAST);
					contentPanel.add(customerFormThree.setCustomerDetailPanel(), BorderLayout.CENTER);
                                        formsArray = new CustomerForm[]{customerFormOne, customerFormTwo, customerFormThree};
					contentPanel.revalidate();
					contentPanel.repaint();
					break;
				default:
					break;
				}
			}
		};
		return spinnerListener;
	}

	
	private PropertyChangeListener chechkDates() {
		final PropertyChangeListener listener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				
				// here leave ancestors empty because we need dates not ancestor
				if (evt.getPropertyName().equals("ancestor")) {
					return;
				}
				
				boolean showed = false;
				startDate = checkinDateChooser.getDate();
				endDate = checkoutDateChooser.getDate();

				if (startDate != null && endDate != null) {
					// add to calendar to be able get day of date
					// and compare
					Calendar cs = Calendar.getInstance();
					cs.setTime(startDate);
					Calendar ce = Calendar.getInstance();
					ce.setTime(endDate);

					// compare if start date greater than end date
					if (cs.after(ce) && !showed) {
						JOptionPane.showMessageDialog(null, "Start date is after end date!",
								JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
						showed = true;
					}
					// or both is same date
					else if (cs.get(Calendar.DAY_OF_YEAR) == ce.get(Calendar.DAY_OF_YEAR) && !showed) {
						JOptionPane.showMessageDialog(null,
								"Start date equals end date!\nPlease be sure you're choose right date.",
								JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
						showed = true;
					}
					// other odds
					else {
						value = (int) ((startDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24));
						totalDaysField.setText(Math.abs(value) + "");
						totalDaysField.revalidate();
						totalDaysField.repaint();
					}

				}
			}
		};
		return listener;
	}
	
	public ItemListener currencyActionListener() {
		ItemListener itemListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) {

				final String choosed = currencyCmbBox.getSelectedItem().toString();
				NumberFormatter nf = null;
				DefaultFormatterFactory dfc = null;
				priceField.removeAll();
				
					switch (choosed) {
					case "TURKISH LIRA":
						
						formatter.setCurrency(Currency.getInstance(Locale.getDefault()));
						nf = new NumberFormatter(formatter);
						dfc = new DefaultFormatterFactory(nf);
						priceField.setFormatterFactory(dfc);
						priceField.revalidate();
						priceField.repaint();
						break;
					case "DOLLAR":
						formatter.setCurrency(Currency.getInstance(Locale.US));
						nf = new NumberFormatter(formatter);
						dfc = new DefaultFormatterFactory(nf);
						priceField.setFormatterFactory(dfc);
						priceField.revalidate();
						priceField.repaint();
						break;
					case "EURO":
						formatter.setCurrency(Currency.getInstance(Locale.FRANCE));
						nf = new NumberFormatter(formatter);
						dfc = new DefaultFormatterFactory(nf);
						priceField.setFormatterFactory(dfc);
						priceField.revalidate();
						priceField.repaint();
						break;
					case "POUND":
						formatter.setCurrency(Currency.getInstance(Locale.UK));
						nf = new NumberFormatter(formatter);
						dfc = new DefaultFormatterFactory(nf);
						priceField.setFormatterFactory(dfc);
						priceField.revalidate();
						priceField.repaint();
						break;
					default:
						break;
					}
					repaint();
			}

		};
		return itemListener;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		//Get all dependencies and make the connection ready.
		 final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
		 final CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		 final ReservationDaoImpl reservDaoImpl = new ReservationDaoImpl();
		
		/////////////////////////////////////////////////////////////////////////////////////
		// Here there is no reservation and we gonna create new Reservation from scratch   //
		// let's work on the new reservation with in below steps :                         //
		// 1- Update the room with all status (price, person count, currency etc. )        //
		// 2- Get all information from 'Customer detail form' and add them to new customer //
		// 3- Save new customers and room to database and show them in room external frame //
		/////////////////////////////////////////////////////////////////////////////////////
		
		
		//1- Find the room number who that clicked for walkin checkin.
		final Room checkingRoom = roomDaoImpl.getRoomByRoomNumber(injectedOwnRoom.getNumber());
		
		//2- Create new reservation for walkin and populate it from from fields.	
		final Reservation newReservation = new Reservation();
		newReservation.setAgency(agencyCmbBox.getSelectedItem().toString());
		newReservation.setCheckinDate(sdf.format(checkinDateChooser.getDate()));
		newReservation.setCheckoutDate(sdf.format(checkoutDateChooser.getDate()));
		newReservation.setRentedRoomNum(checkingRoom.getNumber());
		newReservation.setCreditType(creditTypeCmbBox.getSelectedItem().toString());
		newReservation.setGroupName(groupNameField.getText().trim());
		newReservation.setHostType(hostTypeCmbBox.getSelectedItem().toString());
		newReservation.setTotalDays(Integer.parseInt(totalDaysField.getText()));
		newReservation.setBookStatus("GUARANTEE");
		newReservation.setIsCheckedIn("YES");
		
		
		loggingEngine.setMessage("New walkin reservation detail : " + newReservation.toString());
				
		//3- Get last saved reservation, because we need it Id for customers.
		final Reservation lastReservation = reservDaoImpl.getLastReservation();
		
		//4- Create new room(it will update) and fill it with customers and reservation infos.
                int loopCounter = 0;
		checkingRoom.setNumber(injectedOwnRoom.getNumber());
                newReservation.setRentedRoomNum(injectedOwnRoom.getNumber());
                
		final String val = priceField.getValue().toString();
		priceValue = Double.valueOf(val);
		checkingRoom.setPrice(priceValue);
                newReservation.setRentedRoomPrice(val);
                
		checkingRoom.setCurrency(currencyCmbBox.getSelectedItem().toString());
                newReservation.setRentedRoomCurrency(currencyCmbBox.getSelectedItem().toString());
                
		checkingRoom.setCustomerGrupName(groupNameField.getText().trim());
		checkingRoom.setReservationId(lastReservation.getId());
		checkingRoom.setUsageStatus("FULL");
		
		final double lastPrice = checkingRoom.getPrice() * newReservation.getTotalDays();
		checkingRoom.setTotalPrice(lastPrice + "");
		checkingRoom.setRemainingDebt(lastPrice);
                
                newReservation.setRentedRoomType(checkingRoom.getType());
                		
                        if((int)personCountSpinner.getValue() == 0 || (int)personCountSpinner.getValue() == 1) {
                            loopCounter = 1;
                        }
			
			else if((int)personCountSpinner.getValue() == 2) {
                            loopCounter = 2;
			}
			
			else if((int)personCountSpinner.getValue() == 3) {
                            loopCounter = 3;
			}
			
                        Customer theCustomer = null;
                        checkingRoom.setPersonCount(loopCounter);
                        newReservation.setPersonCount(String.valueOf(loopCounter));
				
                            for (int i = 0; i < loopCounter; i++) {
                                
                                theCustomer = new Customer();
                                theCustomer.setCountry(formsArray[i].getCustomerCountryCmbBoxValue());
                                theCustomer.setDateOfBirth(formsArray[i].getDateOfBirthChooserValue());
                                theCustomer.setDocument(formsArray[i].getDocumentTypeCmbxValue());
                                theCustomer.setDocumentNo(formsArray[i].getDocNoFieldValue());
                                theCustomer.setFirstName(formsArray[i].getFirstNameFieldValue());
                                theCustomer.setLastName(formsArray[i].getLastNameFieldValue());
                                theCustomer.setGender(formsArray[i].getGenderComboxValue());
                                theCustomer.setMaritalStatus(formsArray[i].getMarriageComboBoxValue());
                                theCustomer.setReservationId(lastReservation.getId());
                                customerDaoImpl.save(theCustomer);
                                
                                loggingEngine.setMessage("Check in for customer(s) : " + theCustomer.toString());

                            }
                            
		 
                        reservDaoImpl.saveReservation(newReservation);    
			//All thing id OK and all fields populated, just save it.
			roomDaoImpl.updateRoom(checkingRoom);
			loggingEngine.setMessage("Check in for room : " + checkingRoom.toString());
			
			this.dispose();
			
			SwingUtilities.invokeLater(() -> {
                            new RoomWindow(injectedOwnRoom.getNumber());
                        });
			
	}
}
