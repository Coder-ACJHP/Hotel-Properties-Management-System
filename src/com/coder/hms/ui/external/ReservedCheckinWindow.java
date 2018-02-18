package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

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

import com.coder.hms.beans.SessionBean;
import com.coder.hms.daoImpl.CustomerDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.ui.inner.CustomerForm;
import com.coder.hms.utils.LoggingEngine;

public class ReservedCheckinWindow extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private JSpinner spinner;
	private JPanel upperPanel;
	private Room ownInjectedRoom;
	private Room prepareRoom;
	private static LoggingEngine loggingEngine;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
	final  CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
	final ReservationDaoImpl reservDaoImpl = new ReservationDaoImpl();
	public CustomerForm customerFormOne = new CustomerForm();
	public CustomerForm customerFormTwo = new CustomerForm();
	public CustomerForm customerFormThree = new CustomerForm();
        public CustomerForm[] formsArray;
	private static SessionBean sessionBean = SessionBean.getSESSION_BEAN();
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";



	/**
	 * Create the dialog.
	 */
	public ReservedCheckinWindow(Room roomNumber) {

		this.ownInjectedRoom = roomNumber;
		
		loggingEngine = LoggingEngine.getInstance();
		loggingEngine.setMessage("User is : " + sessionBean.getNickName());
		
		setMinimumSize(new Dimension(750, 495));
		setPreferredSize(new Dimension(750, 495));
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

		this.setTitle("Coder HPMSA - [Checkin]");
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
		roomCheckinBtn.setIcon(new ImageIcon(ReservedCheckinWindow.class
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

		JLabel roomNumberLbl = new JLabel(ownInjectedRoom.getNumber());
		roomNumberLbl.setForeground(new Color(220, 20, 60));
		roomNumberLbl.setFont(new Font("Verdana", Font.BOLD, 17));
		roomNumberLbl.setBounds(406, 8, 103, 33);
		buttonPanel.add(roomNumberLbl);
		
		contentPanel.add(customerFormOne.setCustomerDetailPanel(), BorderLayout.WEST);
		prepareCustomerForms(ownInjectedRoom.getNumber());

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
                        formsArray = new CustomerForm[]{customerFormOne};
		}
		else if(customersList.size() == 2) {
			customerFormOne.setFirstNameValue(customersList.get(0).getFirstName());
			customerFormOne.setLastNameValue(customersList.get(0).getLastName());
			customerFormOne.setCustomerCountryCmbBoxValue(customersList.get(0).getCountry());
			customerFormTwo.setFirstNameValue(customersList.get(1).getFirstName());
			customerFormTwo.setLastNameValue(customersList.get(1).getLastName());
			customerFormTwo.setCustomerCountryCmbBoxValue(customersList.get(1).getCountry());
                        formsArray = new CustomerForm[]{customerFormOne, customerFormTwo};
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
                        formsArray = new CustomerForm[]{customerFormOne, customerFormTwo, customerFormThree};
		}
	}
	
	private ChangeListener customerCounterListener() {

		final ChangeListener spinnerListener = new ChangeListener() {
			@Override
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
		 
		final Room checkingRoom = roomDaoImpl.getRoomByRoomNumber(ownInjectedRoom.getNumber());
		final Optional<Reservation> foundedReserv = reservDaoImpl.findReservationById(checkingRoom.getReservationId());
		final List<Customer> customerList = customerDaoImpl.getCustomerByReservId(foundedReserv.get().getId());
		//Just for to be sure, check the reservation
		if(foundedReserv != null) {
			
                        int loopCounter = 0;
			checkingRoom.setUsageStatus("FULL");
			checkingRoom.setCustomerGrupName(foundedReserv.get().getGroupName());
			
			if((int)spinner.getValue() == 0 || (int)spinner.getValue() == 1) {
                            loopCounter = 1;
                        }
			
			else if((int)spinner.getValue() == 2) {
                            loopCounter = 2;
			}
			
			else if((int)spinner.getValue() == 3) {
                            loopCounter = 3;
			}
			
                        checkingRoom.setPersonCount(loopCounter);
				
                            for (int i = 0; i < loopCounter; i++) {
                                customerList.get(i).setCountry(formsArray[i].getCustomerCountryCmbBoxValue());
                                customerList.get(i).setDateOfBirth(formsArray[i].getDateOfBirthChooserValue());
                                customerList.get(i).setDocument(formsArray[i].getDocumentTypeCmbxValue());
                                customerList.get(i).setDocumentNo(formsArray[i].getDocNoFieldValue());
                                customerList.get(i).setFirstName(formsArray[i].getFirstNameFieldValue());
                                customerList.get(i).setLastName(formsArray[i].getLastNameFieldValue());
                                customerList.get(i).setGender(formsArray[i].getGenderComboxValue());
                                customerList.get(i).setMaritalStatus(formsArray[i].getMarriageComboBoxValue());
                                customerList.get(i).setReservationId(foundedReserv.get().getId());
                                customerDaoImpl.update(customerList.get(i));
                                
                                loggingEngine.setMessage("Check in for customer(s) : " + customerList.get(i).toString());

                            }
			
			roomDaoImpl.updateRoom(checkingRoom);
			loggingEngine.setMessage("Check in room is : " + checkingRoom.toString());
			
			foundedReserv.get().setIsCheckedIn("YES");
			reservDaoImpl.updateReservation(foundedReserv.get());
			
			loggingEngine.setMessage("Check in reservation is : " + foundedReserv.toString());
			
			this.dispose();
			
			SwingUtilities.invokeLater(() -> {
                            new RoomWindow(ownInjectedRoom.getNumber());
                        });
		}
	}
}
