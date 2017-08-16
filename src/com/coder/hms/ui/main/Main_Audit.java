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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.ui.external.NewReservationWindow;
import com.coder.hms.ui.inner.DialogFrame;
import com.coder.hms.utils.AuditTableCellRenderer;
import com.coder.hms.utils.BlockadeTableHeaderRenderer;

public class Main_Audit extends JPanel {

	/**
	 * 
	 */
	private static long rowId;

	private List<Room> theRoomList;
	private List<Reservation> resList;
	private RoomDaoImpl roomDaoImpl;
	private ReservationDaoImpl resDaoImpl;

	private Date today;
	private JTable table;
	private Room theRoom;
	private String newDate;
	private SimpleDateFormat sdf;
	private List<Reservation> foundReservationlist;
	private JPanel upperPanel, buttonPanel;
	private JButton btnUpdate, btnCancel, btnAudit, btnShowRes;
	private static final long serialVersionUID = 1L;
	private final String[] columnNames = { "RESERVATION NO", "GROUP NAME", 
						"ROOM NUMBER", "CHECK/IN DATE", "PRICE", "AGENCY" };
	private final DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	private final BlockadeTableHeaderRenderer THR = new BlockadeTableHeaderRenderer();
	private final AuditTableCellRenderer renderer = new AuditTableCellRenderer();

	public Main_Audit() {
						
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
		btnUpdate.addActionListener(updateReservation());
		btnUpdate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnUpdate.setIcon(new ImageIcon(Main_Audit.class.getResource("/com/coder/hms/icons/cleaning-refresh.png")));
		btnUpdate.setBounds(6, 77, 127, 40);
		buttonPanel.add(btnUpdate);

		btnCancel = new JButton("Cancel res.");
		btnCancel.setToolTipText("<html>Select a reservation from the table with <br>single click and press this button to cancel it.</html>\n");
		btnCancel.setAutoscrolls(true);
		btnCancel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnCancel.addActionListener(cancelReservation());
		btnCancel.setIcon(new ImageIcon(Main_Audit.class.getResource("/com/coder/hms/icons/room_checkout.png")));
		btnCancel.setBounds(6, 169, 127, 40);
		buttonPanel.add(btnCancel);

		btnAudit = new JButton("Audit");
		btnAudit.setToolTipText("<html>Finish your all reservations job and press<br> this button to change system date to new date.</html>");
		btnAudit.setAutoscrolls(true);
		btnAudit.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAudit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAudit.addActionListener(customAuditlistener());
		btnAudit.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
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
		btnShowRes.setToolTipText("<html>Select a reservation from the table with <br>single click and press this button to show it.</html>");
		btnShowRes.addActionListener(showReservation());
		btnShowRes.setIcon(new ImageIcon(Main_Audit.class.getResource("/com/coder/hms/icons/main_new_rez.png")));
		btnShowRes.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnShowRes.setAutoscrolls(true);
		btnShowRes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnShowRes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShowRes.setBounds(6, 123, 127, 40);
		buttonPanel.add(btnShowRes);

		today = new Date();
		sdf = new SimpleDateFormat("dd/MM/YYYY EEEE");
		newDate = sdf.format(today);

		JLabel lblTitle = new JLabel("SYSTEM DAILY AUDIT [" + newDate + "]");
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
	}

	private ActionListener customAuditlistener() {
		final ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final DialogFrame dialog = new DialogFrame();
				dialog.setMessage("Are you sure ?");
				dialog.btnYes.addActionListener(ActionListener ->{
					
					dialog.dispose();
				});
				dialog.btnNo.addActionListener(ActionListener->{
					dialog.dispose();
					return;
				});
				dialog.setVisible(true);
				
			}
		};
		return listener;
	}

	private synchronized void getReadyDependencies() {

		roomDaoImpl = new RoomDaoImpl();
		resDaoImpl = new ReservationDaoImpl();
		resList = resDaoImpl.getAllReservations();

	}

	private void populateMainTable(DefaultTableModel model) {

		// store all reserved Id's in a array to be ready for use in another methods.
		foundReservationlist = new ArrayList<>();
		theRoomList = new ArrayList<>();

		model.setRowCount(0);

		for (int i = 0; i < resList.size(); i++) {
			if (resList.get(i).getCheckinDate().equals(newDate) && resList.get(i).getIsCheckedIn().equals("NO")) {

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

	private ActionListener showReservation() {
		final ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				////////////////////////////////////////////////////////////////////
				// iterate both already populated lists(roomlist & reservationList)//
				// to find reservation and it's room when the reservation found //
				// and the reservation room number equals listed room number //
				// we need to populate reservationPane to show it //
				////////////////////////////////////////////////////////////////////

				System.out.println("SHOW RESERVATION WORKING...");
				System.out.println("SELECTED ROW : 'rowId :'" + rowId);
				
				for (Reservation foundRes : foundReservationlist) {
					for (Room room : theRoomList) {

						if (foundRes.getId() == rowId && foundRes.getTheNumber().equals(room.getNumber())) {

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

							System.out.println("LIST OF RESERVATIONPANE POPULATED SUCCESSFULLY");
							
							/*
							 * if the pane populated that's mean job completed thats why we need to use
							 * break to quit from loop
							 */
							break;
						}
					}
				}

			}
		};
		return listener;
	}
	
	private ActionListener updateReservation() {
		final ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("UPDATE RESERVATION WORKING...");
				System.out.println("SELECTED ROW : 'rowId :'" + rowId);
				
				for (Reservation foundRes : foundReservationlist) {
					if(foundRes.getId() == rowId) {
						
						today = new Date();
						Calendar cl = Calendar.getInstance();
						sdf = new SimpleDateFormat("yyyy-MM-dd");
						cl.setTime(today);
						cl.add(Calendar.DATE, 1);
						newDate = sdf.format(cl.getTime());
											
						foundRes.setCheckinDate(newDate);
						resDaoImpl.saveReservation(foundRes);
					}
				}
				
			}
		};
		return listener;
	}
	
	private ActionListener cancelReservation() {
		final ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				final DialogFrame dialog = new DialogFrame();
				dialog.setMessage("Are you sure to cancel this reservation?");
				dialog.btnYes.addActionListener(ActionListener ->{
					for (Reservation foundRes : foundReservationlist) {
						if(foundRes.getId() == rowId) {
							
							foundRes.setBookStatus("CANCELLED");
							resDaoImpl.saveReservation(foundRes);
							
							System.out.println("RESERVATION UPDATED SUCCESSFULLY.");
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
		};
		return listener;
	}
}
