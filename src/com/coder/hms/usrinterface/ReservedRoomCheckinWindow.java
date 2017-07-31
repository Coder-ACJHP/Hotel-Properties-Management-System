package com.coder.hms.usrinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import com.coder.hms.utils.ApplicationLogoSetter;

public class ReservedRoomCheckinWindow extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private JSpinner spinner;
	private JPanel upperPanel;
	private String ownRoomNumber;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public Internal_CustomerForm customerFormOne = new Internal_CustomerForm();
	public Internal_CustomerForm customerFormTwo = new Internal_CustomerForm();
	public Internal_CustomerForm customerFormThree = new Internal_CustomerForm();
	private final ApplicationLogoSetter logoSetter = new ApplicationLogoSetter();
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";



	/**
	 * Create the dialog.
	 */
	public ReservedRoomCheckinWindow(String roomNumber) {

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
		roomCheckinBtn.setIcon(new ImageIcon(ReservedRoomCheckinWindow.class
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
		
		//Get all dependencies and make the connection ready.
		 final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
		 final  CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		 final ReservationDaoImpl reservDaoImpl = new ReservationDaoImpl();
		
		/////////////////////////////////////////////////////////////////////////////////////
		// In this odd the reservation is found, that's mean the room already reserved     //
		// let's work on the founded reservation with in below steps :                     //
		// 1- Update the room with all status (price, person count, currency etc. )        //
		// 2- Get all information from 'Customer detail form' and add them to new customer //
		// 3- Save new customers and room to database and show them in room external frame //
		/////////////////////////////////////////////////////////////////////////////////////
		 
		final Room checkingRoom = roomDaoImpl.getRoomByRoomNumber(ownRoomNumber);
		Reservation foundedReserv = reservDaoImpl.getReservationById(checkingRoom.getReservationId());
		
		//Just for to be sure, check the reservation
		if(foundedReserv != null) {
			
			checkingRoom.setUsageStatus("FULL");
			checkingRoom.setCustomerGrupName(foundedReserv.getGroupName());
			
			if((int)spinner.getValue() == 0 || (int)spinner.getValue() == 1) {
				checkingRoom.setPersonCount((int)spinner.getValue());
				
				Customer customer = new Customer();
				customer.setCountry(customerFormOne.getCustomerCountryCmbBoxValue());
				customer.setDateOfBirth(customerFormOne.getDateOfBirthChooserValue());
				customer.setDocument(customerFormOne.getDocumentTypeCmbxValue());
				customer.setDocumentNo(customerFormOne.getDocNoFieldValue());
				customer.setFirstName(customerFormOne.getFirstNameFieldValue());
				customer.setLastName(customerFormOne.getLastNameFieldValue());
				customer.setGender(customerFormOne.getGenderComboxValue());
				customer.setMaritalStatus(customerFormOne.getMarriageComboBoxValue());
				customer.setReservationId(foundedReserv.getId());
				
				customerDaoImpl.save(customer);
			}
			
			else if((int)spinner.getValue() == 2) {
				
				checkingRoom.setPersonCount((int)spinner.getValue());
				
				Customer customerOne = new Customer();
				customerOne.setCountry(customerFormOne.getCustomerCountryCmbBoxValue());
				customerOne.setDateOfBirth(customerFormOne.getDateOfBirthChooserValue());
				customerOne.setDocument(customerFormOne.getDocumentTypeCmbxValue());
				customerOne.setDocumentNo(customerFormOne.getDocNoFieldValue());
				customerOne.setFirstName(customerFormOne.getFirstNameFieldValue());
				customerOne.setLastName(customerFormOne.getLastNameFieldValue());
				customerOne.setGender(customerFormOne.getGenderComboxValue());
				customerOne.setMaritalStatus(customerFormOne.getMarriageComboBoxValue());
				customerOne.setReservationId(foundedReserv.getId());
				
				Customer customerTwo = new Customer();
				customerTwo.setCountry(customerFormTwo.getCustomerCountryCmbBoxValue());
				customerTwo.setDateOfBirth(customerFormTwo.getDateOfBirthChooserValue());
				customerTwo.setDocument(customerFormTwo.getDocumentTypeCmbxValue());
				customerTwo.setDocumentNo(customerFormTwo.getDocNoFieldValue());
				customerTwo.setFirstName(customerFormTwo.getFirstNameFieldValue());
				customerTwo.setLastName(customerFormTwo.getLastNameFieldValue());
				customerTwo.setGender(customerFormTwo.getGenderComboxValue());
				customerTwo.setMaritalStatus(customerFormTwo.getMarriageComboBoxValue());
				customerTwo.setReservationId(foundedReserv.getId());
				
				customerDaoImpl.save(customerOne);
				customerDaoImpl.save(customerTwo);
			}
			
			else if((int)spinner.getValue() == 3) {
				
				checkingRoom.setPersonCount((int)spinner.getValue());
				
				Customer customerOne = new Customer();
				customerOne.setCountry(customerFormOne.getCustomerCountryCmbBoxValue());
				customerOne.setDateOfBirth(customerFormOne.getDateOfBirthChooserValue());
				customerOne.setDocument(customerFormOne.getDocumentTypeCmbxValue());
				customerOne.setDocumentNo(customerFormOne.getDocNoFieldValue());
				customerOne.setFirstName(customerFormOne.getFirstNameFieldValue());
				customerOne.setLastName(customerFormOne.getLastNameFieldValue());
				customerOne.setGender(customerFormOne.getGenderComboxValue());
				customerOne.setMaritalStatus(customerFormOne.getMarriageComboBoxValue());
				customerOne.setReservationId(foundedReserv.getId());
				
				Customer customerTwo = new Customer();
				customerTwo.setCountry(customerFormTwo.getCustomerCountryCmbBoxValue());
				customerTwo.setDateOfBirth(customerFormTwo.getDateOfBirthChooserValue());
				customerTwo.setDocument(customerFormTwo.getDocumentTypeCmbxValue());
				customerTwo.setDocumentNo(customerFormTwo.getDocNoFieldValue());
				customerTwo.setFirstName(customerFormTwo.getFirstNameFieldValue());
				customerTwo.setLastName(customerFormTwo.getLastNameFieldValue());
				customerTwo.setGender(customerFormTwo.getGenderComboxValue());
				customerTwo.setMaritalStatus(customerFormTwo.getMarriageComboBoxValue());
				customerTwo.setReservationId(foundedReserv.getId());
				
				Customer customerThree = new Customer();
				customerThree.setCountry(customerFormThree.getCustomerCountryCmbBoxValue());
				customerThree.setDateOfBirth(customerFormThree.getDateOfBirthChooserValue());
				customerThree.setDocument(customerFormThree.getDocumentTypeCmbxValue());
				customerThree.setDocumentNo(customerFormThree.getDocNoFieldValue());
				customerThree.setFirstName(customerFormThree.getFirstNameFieldValue());
				customerThree.setLastName(customerFormThree.getLastNameFieldValue());
				customerThree.setGender(customerFormThree.getGenderComboxValue());
				customerThree.setMaritalStatus(customerFormThree.getMarriageComboBoxValue());
				customerThree.setReservationId(foundedReserv.getId());
				
				customerDaoImpl.save(customerOne);
				customerDaoImpl.save(customerTwo);
				customerDaoImpl.save(customerThree);
			}
			
			roomDaoImpl.saveRoom(checkingRoom);
			System.out.println("Checked in successfully\nNow loading room frame...");
			
			this.dispose();
			
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					
					new RoomExternalWindow(ownRoomNumber);
					
				}
			});
		}
	}
}
