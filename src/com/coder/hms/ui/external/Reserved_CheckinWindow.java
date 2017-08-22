package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.coder.hms.daoImpl.CustomerDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.ui.inner.CustomerForm;
import com.coder.hms.utils.ApplicationLogoSetter;

public class Reserved_CheckinWindow extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private JSpinner spinner;
	private JPanel upperPanel;
	private String ownRoomNumber;
	private Room prepareRoom;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
	final  CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
	final ReservationDaoImpl reservDaoImpl = new ReservationDaoImpl();
	public CustomerForm customerFormOne = new CustomerForm();
	public CustomerForm customerFormTwo = new CustomerForm();
	public CustomerForm customerFormThree = new CustomerForm();
	private final ApplicationLogoSetter logoSetter = new ApplicationLogoSetter();
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";



	/**
	 * Create the dialog.
	 */
	public Reserved_CheckinWindow(String roomNumber) {

		this.ownRoomNumber = roomNumber;
		
		setMinimumSize(new Dimension(750, 495));
		setPreferredSize(new Dimension(750, 495));
		setLocationRelativeTo(null);

		logoSetter.setApplicationLogoJDialog(this, LOGOPATH);

		getContentPane().setForeground(new Color(255, 99, 71));
		getContentPane().setFocusCycleRoot(true);
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().setFont(new Font("Verdana", Font.BOLD, 12));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);

		this.setTitle("Coder for HMS - [Checkin]");
		contentPanel.setAutoscrolls(true);
		contentPanel.setPreferredSize(new Dimension(10, 415));

		contentPanel.setBackground(Color.decode("#066d95"));
		contentPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(contentPanel, BorderLayout.SOUTH);

		upperPanel = new JPanel();
		upperPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		upperPanel.setBackground(new Color(135, 206, 235));
		upperPanel.setPreferredSize(new Dimension(10, 35));
		contentPanel.add(upperPanel, BorderLayout.NORTH);

		JLabel lblChangeRoomPerson = new JLabel("Change person count : ");
		lblChangeRoomPerson.setFont(new Font("Arial", Font.PLAIN, 15));
		upperPanel.add(lblChangeRoomPerson);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		spinner.setPreferredSize(new Dimension(40, 20));
		spinner.setMinimumSize(new Dimension(35, 20));
		spinner.addChangeListener(customerCounterListener());
		upperPanel.add(spinner);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		buttonPanel.setPreferredSize(new Dimension(10, 50));
		buttonPanel.setLayout(null);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);

		JButton roomCheckinBtn = new JButton("Room checkin");
		roomCheckinBtn.addActionListener(this);
		roomCheckinBtn.setIcon(new ImageIcon(Reserved_CheckinWindow.class
						.getResource("/com/coder/hms/icons/extra_checkin.png")));
		roomCheckinBtn.setBounds(7, 4, 130, 42);
		buttonPanel.add(roomCheckinBtn);

		final JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(149, 6, 10, 36);
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

		JLabel roomNumberLbl = new JLabel(ownRoomNumber);
		roomNumberLbl.setForeground(new Color(220, 20, 60));
		roomNumberLbl.setFont(new Font("Verdana", Font.BOLD, 17));
		roomNumberLbl.setBounds(406, 8, 103, 33);
		buttonPanel.add(roomNumberLbl);
		
		contentPanel.add(customerFormOne.setCustomerDetailPanel(), BorderLayout.WEST);
		prepareCustomerForms(ownRoomNumber);

	}

	private void prepareCustomerForms(String roomNumber) {
		prepareRoom = roomDaoImpl.getRoomByRoomNumber(roomNumber);
		List<Customer> customersList = customerDaoImpl.getCustomerByReservId(prepareRoom.getReservationId());
		
		spinner.setValue(prepareRoom.getPersonCount());
		spinner.revalidate();
		spinner.repaint();
				
		if(customersList.size() == 1) {
			customerFormOne.setFirstNameValue(customersList.get(0).getFirstName());
			customerFormOne.setLastNameValue(customersList.get(0).getLastName());
			customerFormOne.setCustomerCountryCmbBoxValue(customersList.get(0).getCountry());
		}
		else if(customersList.size() == 2) {
			customerFormOne.setFirstNameValue(customersList.get(0).getFirstName());
			customerFormOne.setLastNameValue(customersList.get(0).getLastName());
			customerFormOne.setCustomerCountryCmbBoxValue(customersList.get(0).getCountry());
			customerFormTwo.setFirstNameValue(customersList.get(1).getFirstName());
			customerFormTwo.setLastNameValue(customersList.get(1).getLastName());
			customerFormTwo.setCustomerCountryCmbBoxValue(customersList.get(1).getCountry());
		}
		else {
			customerFormOne.setFirstNameValue(customersList.get(0).getFirstName());
			customerFormOne.setLastNameValue(customersList.get(0).getLastName());
			customerFormOne.setCustomerCountryCmbBoxValue(customersList.get(0).getCountry());
			customerFormTwo.setFirstNameValue(customersList.get(1).getFirstName());
			customerFormTwo.setLastNameValue(customersList.get(1).getLastName());
			customerFormTwo.setCustomerCountryCmbBoxValue(customersList.get(1).getCountry());
			customerFormThree.setFirstNameValue(customersList.get(2).getFirstName());
			customerFormThree.setLastNameValue(customersList.get(2).getLastName());
			customerFormThree.setCustomerCountryCmbBoxValue(customersList.get(2).getCountry());
		}
	}
	
	private ChangeListener customerCounterListener() {

		final ChangeListener spinnerListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				switch ((int) spinner.getValue()) {
				case 1:
					contentPanel.removeAll();
					contentPanel.add(upperPanel, BorderLayout.NORTH);
					contentPanel.add(customerFormOne.setCustomerDetailPanel(), BorderLayout.WEST);
					contentPanel.revalidate();
					contentPanel.repaint();
					break;
				case 2:
					contentPanel.removeAll();
					contentPanel.add(upperPanel, BorderLayout.NORTH);
					contentPanel.add(customerFormOne.setCustomerDetailPanel(), BorderLayout.WEST);
					contentPanel.add(customerFormTwo.setCustomerDetailPanel(), BorderLayout.EAST);
					contentPanel.revalidate();
					contentPanel.repaint();
					break;
				case 3:
					contentPanel.removeAll();
					contentPanel.add(upperPanel, BorderLayout.NORTH);
					contentPanel.add(customerFormOne.setCustomerDetailPanel(), BorderLayout.WEST);
					contentPanel.add(customerFormTwo.setCustomerDetailPanel(), BorderLayout.EAST);
					contentPanel.add(customerFormThree.setCustomerDetailPanel(), BorderLayout.CENTER);
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

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		/////////////////////////////////////////////////////////////////////////////////////
		// In this odd the reservation is found, that's mean the room already reserved     //
		// let's work on the founded reservation with in below steps :                     //
		// 1- Update the room with all status (price, person count, currency etc. )        //
		// 2- Get all information from 'Customer detail form' and add them to new customer //
		// 3- Save new customers and room to database and show them in room external frame //
		/////////////////////////////////////////////////////////////////////////////////////
		 
		final Room checkingRoom = roomDaoImpl.getRoomByRoomNumber(ownRoomNumber);
		final Reservation foundedReserv = reservDaoImpl.findReservationById(checkingRoom.getReservationId());
		final List<Customer> customerList = customerDaoImpl.getCustomerByReservId(foundedReserv.getId());
		//Just for to be sure, check the reservation
		if(foundedReserv != null) {
			
			checkingRoom.setUsageStatus("FULL");
			checkingRoom.setCustomerGrupName(foundedReserv.getGroupName());
			
			if((int)spinner.getValue() == 0 || (int)spinner.getValue() == 1) {
				checkingRoom.setPersonCount((int)spinner.getValue());
				
				customerList.get(0).setCountry(customerFormOne.getCustomerCountryCmbBoxValue());
				customerList.get(0).setDateOfBirth(customerFormOne.getDateOfBirthChooserValue());
				customerList.get(0).setDocument(customerFormOne.getDocumentTypeCmbxValue());
				customerList.get(0).setDocumentNo(customerFormOne.getDocNoFieldValue());
				customerList.get(0).setFirstName(customerFormOne.getFirstNameFieldValue());
				customerList.get(0).setLastName(customerFormOne.getLastNameFieldValue());
				customerList.get(0).setGender(customerFormOne.getGenderComboxValue());
				customerList.get(0).setMaritalStatus(customerFormOne.getMarriageComboBoxValue());
				customerList.get(0).setReservationId(foundedReserv.getId());
				
				customerDaoImpl.save(customerList.get(0));
			}
			
			else if((int)spinner.getValue() == 2) {
				
				checkingRoom.setPersonCount((int)spinner.getValue());
				
				customerList.get(0).setCountry(customerFormOne.getCustomerCountryCmbBoxValue());
				customerList.get(0).setDateOfBirth(customerFormOne.getDateOfBirthChooserValue());
				customerList.get(0).setDocument(customerFormOne.getDocumentTypeCmbxValue());
				customerList.get(0).setDocumentNo(customerFormOne.getDocNoFieldValue());
				customerList.get(0).setFirstName(customerFormOne.getFirstNameFieldValue());
				customerList.get(0).setLastName(customerFormOne.getLastNameFieldValue());
				customerList.get(0).setGender(customerFormOne.getGenderComboxValue());
				customerList.get(0).setMaritalStatus(customerFormOne.getMarriageComboBoxValue());
				customerList.get(0).setReservationId(foundedReserv.getId());
				
				customerList.get(1).setCountry(customerFormTwo.getCustomerCountryCmbBoxValue());
				customerList.get(1).setDateOfBirth(customerFormTwo.getDateOfBirthChooserValue());
				customerList.get(1).setDocument(customerFormTwo.getDocumentTypeCmbxValue());
				customerList.get(1).setDocumentNo(customerFormTwo.getDocNoFieldValue());
				customerList.get(1).setFirstName(customerFormTwo.getFirstNameFieldValue());
				customerList.get(1).setLastName(customerFormTwo.getLastNameFieldValue());
				customerList.get(1).setGender(customerFormTwo.getGenderComboxValue());
				customerList.get(1).setMaritalStatus(customerFormTwo.getMarriageComboBoxValue());
				customerList.get(1).setReservationId(foundedReserv.getId());
				
				customerDaoImpl.save(customerList.get(0));
				customerDaoImpl.save(customerList.get(1));
			}
			
			else if((int)spinner.getValue() == 3) {
				
				checkingRoom.setPersonCount((int)spinner.getValue());
				
				customerList.get(0).setCountry(customerFormOne.getCustomerCountryCmbBoxValue());
				customerList.get(0).setDateOfBirth(customerFormOne.getDateOfBirthChooserValue());
				customerList.get(0).setDocument(customerFormOne.getDocumentTypeCmbxValue());
				customerList.get(0).setDocumentNo(customerFormOne.getDocNoFieldValue());
				customerList.get(0).setFirstName(customerFormOne.getFirstNameFieldValue());
				customerList.get(0).setLastName(customerFormOne.getLastNameFieldValue());
				customerList.get(0).setGender(customerFormOne.getGenderComboxValue());
				customerList.get(0).setMaritalStatus(customerFormOne.getMarriageComboBoxValue());
				customerList.get(0).setReservationId(foundedReserv.getId());
				
				customerList.get(1).setCountry(customerFormTwo.getCustomerCountryCmbBoxValue());
				customerList.get(1).setDateOfBirth(customerFormTwo.getDateOfBirthChooserValue());
				customerList.get(1).setDocument(customerFormTwo.getDocumentTypeCmbxValue());
				customerList.get(1).setDocumentNo(customerFormTwo.getDocNoFieldValue());
				customerList.get(1).setFirstName(customerFormTwo.getFirstNameFieldValue());
				customerList.get(1).setLastName(customerFormTwo.getLastNameFieldValue());
				customerList.get(1).setGender(customerFormTwo.getGenderComboxValue());
				customerList.get(1).setMaritalStatus(customerFormTwo.getMarriageComboBoxValue());
				customerList.get(1).setReservationId(foundedReserv.getId());
				
				customerList.get(2).setCountry(customerFormThree.getCustomerCountryCmbBoxValue());
				customerList.get(2).setDateOfBirth(customerFormThree.getDateOfBirthChooserValue());
				customerList.get(2).setDocument(customerFormThree.getDocumentTypeCmbxValue());
				customerList.get(2).setDocumentNo(customerFormThree.getDocNoFieldValue());
				customerList.get(2).setFirstName(customerFormThree.getFirstNameFieldValue());
				customerList.get(2).setLastName(customerFormThree.getLastNameFieldValue());
				customerList.get(2).setGender(customerFormThree.getGenderComboxValue());
				customerList.get(2).setMaritalStatus(customerFormThree.getMarriageComboBoxValue());
				customerList.get(2).setReservationId(foundedReserv.getId());
				
				customerDaoImpl.save(customerList.get(0));
				customerDaoImpl.save(customerList.get(1));
				customerDaoImpl.save(customerList.get(2));
			}
			
			
			roomDaoImpl.saveRoom(checkingRoom);
			foundedReserv.setIsCheckedIn("YES");
			reservDaoImpl.saveReservation(foundedReserv);
			this.dispose();
			
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					
					new RoomWindow(ownRoomNumber);
					
				}
			});
		}
	}
}
