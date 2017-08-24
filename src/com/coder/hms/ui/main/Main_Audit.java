package com.coder.hms.ui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.coder.hms.beans.LocaleBean;
import com.coder.hms.daoImpl.CustomerDaoImpl;
import com.coder.hms.daoImpl.HotelSystemStatusImpl;
import com.coder.hms.daoImpl.PaymentDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.HotelSystemStatus;
import com.coder.hms.entities.Payment;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.ui.external.DialogFrame;
import com.coder.hms.ui.external.NewReservationWindow;
import com.coder.hms.utils.AuditTableCellRenderer;
import com.coder.hms.utils.BlockadeTableHeaderRenderer;
import com.coder.hms.utils.ResourceControl;

public class Main_Audit extends JPanel implements ActionListener {

	/**
	 * 
	 */
	
	private static long rowId;
	private List<Room> theRoomList;
	private List<Reservation> resList;
	private RoomDaoImpl roomDaoImpl;
	private PaymentDaoImpl paymentDaoImpl;
	private ReservationDaoImpl resDaoImpl;
	private CustomerDaoImpl customerDaoImpl;
	
	private JTable table;
	private Room theRoom;
	private LocalDate today;
	private JLabel lblTitle;
	private JPanel upperPanel, buttonPanel;
	private List<Reservation> foundReservationlist;
	private static final long serialVersionUID = 1L;
	
	private LocaleBean bean;
	private HotelSystemStatus systemStatus;
	private final HotelSystemStatusImpl systemStatusImpl = new HotelSystemStatusImpl();
	
	private JButton btnUpdate, btnCancel, btnAudit, btnShowRes;
	private final String[] columnNames = { "RESERVATION NO", "GROUP NAME", 
						"ROOM NUMBER", "CHECK/IN DATE", "PRICE", "AGENCY" };
	private final AuditTableCellRenderer renderer = new AuditTableCellRenderer();
	private final DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	private final BlockadeTableHeaderRenderer THR = new BlockadeTableHeaderRenderer();

	public Main_Audit() {}
	
	public void initializeAuditPane() {
		
		bean = LocaleBean.getInstance();
		setLayout(new BorderLayout(0, 0));

		upperPanel = new JPanel();
		upperPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		upperPanel.setPreferredSize(new Dimension(10, 48));
		upperPanel.setBackground(Color.decode("#066d95"));
		upperPanel.setAutoscrolls(true);
		add(upperPanel, BorderLayout.NORTH);
		upperPanel.setLayout(new BorderLayout(0, 0));

		buttonPanel = new JPanel();
		buttonPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonPanel.setBackground(new Color(173, 216, 230));
		buttonPanel.setAutoscrolls(true);
		buttonPanel.setPreferredSize(new Dimension(140, 48));
		add(buttonPanel, BorderLayout.WEST);
		buttonPanel.setLayout(null);

		btnUpdate = new JButton("Update res.");
		btnUpdate.setToolTipText("<html>Select a reservation from the table with <br>single click and press this button to update it.</html>");
		btnUpdate.setAutoscrolls(true);
		btnUpdate.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.addActionListener(this);
		btnUpdate.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnUpdate.setIcon(new ImageIcon(Main_Audit.class.getResource("/com/coder/hms/icons/cleaning-refresh.png")));
		btnUpdate.setBounds(6, 77, 127, 40);
		buttonPanel.add(btnUpdate);

		btnCancel = new JButton("Cancel res.");
		btnCancel.setToolTipText("<html>Select a reservation from the table with "
				+ "<br>single click and press this button to cancel it.</html>\n");
		btnCancel.setAutoscrolls(true);
		btnCancel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnCancel.addActionListener(this);
		btnCancel.setIcon(new ImageIcon(Main_Audit.class.getResource("/com/coder/hms/icons/room_checkout.png")));
		btnCancel.setBounds(6, 169, 127, 40);
		buttonPanel.add(btnCancel);

		btnAudit = new JButton("Audit");
		btnAudit.setToolTipText("<html>Finish your all reservations job and press"
				+ "<br> this button to change system date to new date.</html>");
		btnAudit.setAutoscrolls(true);
		btnAudit.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAudit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAudit.addActionListener(this);
		btnAudit.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnAudit.setIcon(new ImageIcon(Main_Audit.class.getResource("/com/coder/hms/icons/main_audit.png")));
		btnAudit.setBounds(6, 11, 127, 40);
		buttonPanel.add(btnAudit);

		final JSeparator sep = new JSeparator();
		sep.setPreferredSize(new Dimension(0, 13));
		sep.setForeground(UIManager.getColor("CheckBoxMenuItem.disabledForeground"));
		sep.setAutoscrolls(true);
		sep.setAlignmentX(Component.CENTER_ALIGNMENT);
		sep.setBounds(6, 57, 127, 8);
		buttonPanel.add(sep);

		btnShowRes = new JButton("Show res.");
		btnShowRes.setToolTipText("<html>Select a reservation from the table with <br>"
				+ "single click and press this button to show it.</html>");
		btnShowRes.addActionListener(this);
		btnShowRes.setIcon(new ImageIcon(Main_Audit.class.getResource("/com/coder/hms/icons/main_new_rez.png")));
		btnShowRes.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnShowRes.setAutoscrolls(true);
		btnShowRes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnShowRes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShowRes.setBounds(6, 123, 127, 40);
		buttonPanel.add(btnShowRes);
		
		systemStatus = systemStatusImpl.getSystemStatus();

		lblTitle = new JLabel("SYSTEM DAILY AUDIT [" + systemStatus.getDateTime() + " " 
								+ systemStatus.getDateTime().getDayOfWeek().name() + "]");
		lblTitle.setForeground(UIManager.getColor("Button.highlight"));
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setAutoscrolls(true);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 25));
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(lblTitle, BorderLayout.CENTER);

		final JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable(model);
		table.setRowHeight(20);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setDefaultRenderer(Integer.class, renderer);
		table.getTableHeader().setDefaultRenderer(THR);
		table.addMouseListener(customMouseListener());
		scrollPane.setViewportView(table);

		getReadyDependencies();
		populateMainTable(model);
		changeLanguage(bean.getLocale());
		
	}
	
	private void changeLanguage(Locale locale) {

		final ResourceBundle bundle = ResourceBundle
				.getBundle("com/coder/hms/languages/LocalizationBundle", locale, new ResourceControl());
		this.btnAudit.setText(bundle.getString("Audit"));
		this.btnShowRes.setText(bundle.getString("ShowRes"));
		this.btnUpdate.setText(bundle.getString("UpdateRes"));
		this.btnCancel.setText(bundle.getString("CancelRes"));
		this.revalidate();
		this.repaint();
	}

	private synchronized void getReadyDependencies() {

		roomDaoImpl = new RoomDaoImpl();
		resDaoImpl = new ReservationDaoImpl();
		customerDaoImpl = new CustomerDaoImpl();
		resList = resDaoImpl.getAllReservations();

	}

	private void populateMainTable(DefaultTableModel model) {

		// store all reserved Id's in a array to be ready for use in another methods.
		foundReservationlist = new ArrayList<>();
		theRoomList = new ArrayList<>();

		model.setRowCount(0);
		
		Date checkinDate;
		LocalDate localDate;
		final Date currentDate = Date.from(systemStatus.getDateTime().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		for (int i = 0; i < resList.size(); i++) {
			//convert reservations date to compare between check in date and current date.
			localDate = LocalDate.parse(resList.get(i).getCheckinDate()); 
			checkinDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
			if (checkinDate.equals(currentDate) && resList.get(i).getIsCheckedIn().equals("NO")) {

				theRoom = roomDaoImpl.getRoomByReservId(resList.get(i).getId());

				model.addRow(new Object[] { resList.get(i).getId(), resList.get(i).getGroupName(), theRoom.getNumber(),
						resList.get(i).getCheckinDate(), theRoom.getPrice(), resList.get(i).getAgency() });

				// Storing Id's here
				foundReservationlist.add(resList.get(i));
				theRoomList.add(theRoom);
			}
		}
	}

	private MouseListener customMouseListener() {
		final MouseAdapter listener = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				final int rowIndex = table.getSelectedRow();
				rowId = Long.valueOf(table.getValueAt(rowIndex, 0).toString());
				System.out.println("SELECTED ROW : 'rowId :'" + rowId);
				super.mousePressed(e);
			}

		};
		return listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnAudit) {
			
				
			final DialogFrame dialog = new DialogFrame();
			dialog.setMessage("Are you sure ?");
			dialog.btnYes.addActionListener(ActionListener ->{
				
				if(model.getRowCount() == 0) {
					if(systemStatus.getIsAuditted() == false) {
						final LocalDate localDate = LocalDate.now();
						systemStatus.setDateTime(localDate);
						systemStatus.setIsAuditted(true);
						systemStatusImpl.updateSystemStatus(systemStatus);
						dialog.dispose();
					}else {
						JOptionPane.showMessageDialog(dialog, 
								"Your system already 'Auditted' to night!", JOptionPane.MESSAGE_PROPERTY,
								JOptionPane.WARNING_MESSAGE);
						dialog.dispose();
					}
				
				}else {
					JOptionPane.showMessageDialog(dialog, 
							"You have to cancel or update all reservations\nthis list must be empty!", 
							JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
					dialog.dispose();
				}
			});
			dialog.btnNo.addActionListener(ActionListener->{
				dialog.dispose();
				return;
			});
			dialog.setVisible(true);
		}
		
		else if(e.getSource() == btnShowRes) {
			
			String customerCountry = "";
			String customerName = "";
			String customerSurName = "";
			
			Payment payment = null;
			
			for (Reservation foundRes : foundReservationlist) {
				for (Room room : theRoomList) {

					if (foundRes.getId() == rowId && foundRes.getTheNumber().equals(room.getNumber())) {
						
						List<Customer> customerList = customerDaoImpl.getCustomerByReservId(foundRes.getId());
						
						for(Customer cst: customerList) {
							customerCountry = cst.getCountry();
							customerName = cst.getFirstName();
							customerSurName = cst.getLastName();
						}
						
						final NewReservationWindow nex = new NewReservationWindow();
						
						nex.setRezIdField(foundRes.getId());
						nex.setNameSurnameField(foundRes.getGroupName());
						nex.setCheckinDate(foundRes.getCheckinDate());
						nex.setCheckoutDate(foundRes.getCheckoutDate());
						nex.setTotalDaysField(foundRes.getTotalDays());
						nex.setReservNote(foundRes.getNote());
						nex.setAgency(foundRes.getAgency());
						nex.setHostType(foundRes.getHostType());
						nex.setCreditType(foundRes.getCreditType());
						nex.setReservStatus(foundRes.getBookStatus());
						nex.setRoomNumber(room.getNumber());
						nex.setRoomType(room.getType());
						nex.setPersonCountSpinner(room.getPersonCount());
						nex.setPriceOfRoom(room.getPrice());
						nex.setCurrency(room.getCurrency());
						nex.setAgencyRefNo(foundRes.getAgencyRefNo());
						nex.setReferanceNo(foundRes.getReferanceNo());
						nex.setCustomerCountry(customerCountry);
						
						nex.setRoomCountTableRows(new Object[]{room.getNumber(), room.getType(),
								room.getPersonCount(), room.getPrice(), room.getCurrency()});
						
						nex.setRoomInfoTableRows(new Object[]{room.getNumber(), room.getType(),
								customerName, customerSurName});
						
						if(foundRes.getPaymentStatus()) {
							
							payment = paymentDaoImpl.getEarlyPaymentByRoomNumber(room.getNumber());
							nex.setEarlyPaymetTableRows(new Object[]{payment.getTitle(), payment.getPaymentType(),
									payment.getPrice(), payment.getCurrency(), payment.getExplanation()});
						}
						nex.setVisible(true);
						
						if(foundRes.getPaymentStatus()) {
							JOptionPane.showMessageDialog(this, "Early payment " + payment.getPrice() + payment.getCurrency(),
									JOptionPane.MESSAGE_PROPERTY, JOptionPane.INFORMATION_MESSAGE);
						}
						/*
						 * if the pane populated that's mean job completed thats why we need to use
						 * break to quit from loop
						 */
						break;
					}
				}
			}
		}
		
		else if(e.getSource() == btnCancel) {
			
			final DialogFrame dialog = new DialogFrame();
			dialog.setMessage("Are you sure to cancel this reservation?");
			dialog.btnYes.addActionListener(ActionListener ->{
				for (Reservation foundRes : foundReservationlist) {
					if(foundRes.getId() == rowId) {
						
						foundRes.setBookStatus("CANCELLED");
						resDaoImpl.saveReservation(foundRes);
						
						System.out.println("RESERVATION CANCELLED SUCCESSFULLY.");
					}
				}
				dialog.dispose();
			});
			dialog.btnNo.addActionListener(ActionListener->{
				dialog.dispose();
				return;
			});
			dialog.setVisible(true);
		}
		
		else if(e.getSource() == btnUpdate) {
			
			System.out.println("UPDATE RESERVATION WORKING...");
			System.out.println("SELECTED ROW : 'rowId :'" + rowId);
			
			for (Reservation foundRes : foundReservationlist) {
				if(foundRes.getId() == rowId) {
					
					today = systemStatus.getDateTime();
					today = today.plusDays(1);											
					foundRes.setCheckinDate(today.toString());
					foundRes.setTotalDays(foundRes.getTotalDays()+1);
					resDaoImpl.saveReservation(foundRes);
				}
			}
		}
		
	}
}
