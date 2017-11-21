package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.apple.eawt.Application;
import com.coder.hms.beans.SessionBean;
import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Room;
import com.coder.hms.ui.extras.BlockadeTableHeaderRenderer;
import com.coder.hms.utils.LoggingEngine;

public class RoomsPropertiesWindow {

	/**
	 * 
	 */
	private JLabel lblTitle;
	private JTable roomsTable;
	private JPanel contentPane;
	private final JFrame jFrame;
	private JButton btnCancel, btnSave;
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
	private JScrollPane tableHolder;
	private static LoggingEngine loggingEngine;
	final InformationFrame INFORMATION_FRAME = new InformationFrame();
	private static SessionBean sessionBean = SessionBean.getSESSION_BEAN();
	private final String[] roomsColsName = {"ID", "NUMBER", "TYPE", "PRICE", "CURRENCY"};
	private DefaultTableModel roomsModel = new DefaultTableModel(roomsColsName, 0);
	private final BlockadeTableHeaderRenderer tableHeaderRenderer = new BlockadeTableHeaderRenderer();
	private JLabel infoLbl;
	private JLabel lblEditTableCells;

	/**
	 * Create the frame.
	 */
	public RoomsPropertiesWindow() {
		
		loggingEngine = LoggingEngine.getInstance();
		loggingEngine.setMessage("User is : " + sessionBean.getNickName());
		
		jFrame = new JFrame();
		jFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		jFrame.setTitle("Coder HPMSA - [Change Room Properties]");
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jFrame.setBounds(100, 100, 801, 522);
		jFrame.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(null);
		jFrame.setContentPane(contentPane);
		jFrame.getContentPane().setBackground(Color.decode("#066d95"));
		jFrame.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jFrame.getContentPane().setFont(new Font("Monospaced", Font.BOLD, 15));
		contentPane.setLayout(new BorderLayout(0, 0));

		String opSystem = System.getProperty("os.name").toLowerCase();

		if (opSystem.contains("windows") || opSystem.contains("nux")) {

			jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWindow.class.getResource(LOGOPATH)));
		} else {
			Application.getApplication().setDockIconImage(new ImageIcon(getClass().getResource(LOGOPATH)).getImage());
		}

		JPanel upperPanel = new JPanel();
		upperPanel = new JPanel();
		upperPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		upperPanel.setPreferredSize(new Dimension(10, 50));
		upperPanel.setBackground(new Color(173, 216, 230));
		upperPanel.setAutoscrolls(true);
		jFrame.getContentPane().add(upperPanel, BorderLayout.NORTH);
		upperPanel.setLayout(new BorderLayout(0, 0));

		lblTitle = new JLabel("ROOMS PROPERTIES");
		lblTitle.setForeground(new Color(70, 130, 180));
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setAutoscrolls(true);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 25));
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(lblTitle, BorderLayout.CENTER);

		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonsPanel.setPreferredSize(new Dimension(600, 52));
		buttonsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buttonsPanel.setBounds(700, 161, 277, 49);
		buttonsPanel.setForeground(new Color(95, 158, 160));
		buttonsPanel.setBackground(Color.decode("#066d95"));
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		final JPanel infoPanel = new JPanel();
		infoPanel.setAutoscrolls(true);
		infoPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		infoPanel.setPreferredSize(new Dimension(600, 52));
		infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.setBounds(700, 161, 277, 49);
		infoPanel.setForeground(new Color(95, 158, 160));
		infoPanel.setBackground(Color.decode("#066d95"));
		infoPanel.setLayout(null);
		
		infoLbl = new JLabel("INFO : ");
		infoLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		infoLbl.setHorizontalAlignment(SwingConstants.LEFT);
		infoLbl.setBounds(6, 16, 51, 18);
		infoLbl.setForeground(new Color(255, 255, 0));
		infoLbl.setFont(new Font("Verdana", Font.PLAIN, 14));
		infoPanel.add(infoLbl);
		
		btnCancel = new JButton("CANCEL");
		btnCancel.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/coder/hms/icons/login_clear.png")));
		btnCancel.setForeground(new Color(220, 20, 60));
		btnCancel.setOpaque(true);
		btnCancel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancel.setPreferredSize(new Dimension(135, 40));
		btnCancel.setFont(new Font("Verdana", Font.BOLD, 15));
		mouseListenerForButtons(btnCancel);
		actionListenerForButtons(btnCancel);
		buttonsPanel.add(btnCancel);

		btnSave = new JButton("SAVE CHANGES");
		btnSave.setSelectedIcon(null);
		btnSave.setIcon(new ImageIcon(RoomsPropertiesWindow.class.getResource("/com/coder/hms/icons/reserv_save.png")));
		btnSave.setForeground(new Color(0, 191, 255));
		btnSave.setOpaque(true);
		btnSave.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSave.setMnemonic(KeyEvent.VK_ENTER);
		btnSave.setPreferredSize(new Dimension(165, 40));
		btnSave.setFont(new Font("Verdana", Font.BOLD, 15));
		mouseListenerForButtons(btnSave);
		actionListenerForButtons(btnSave);
		buttonsPanel.add(btnSave);
		
		final JPanel panelsHolder = new JPanel();
		panelsHolder.setAutoscrolls(true);
		panelsHolder.setBorder(null);
		panelsHolder.setPreferredSize(new Dimension(400, 52));
		panelsHolder.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelsHolder.setBounds(700, 161, 277, 49);
		panelsHolder.setForeground(new Color(95, 158, 160));
		panelsHolder.setBackground(Color.decode("#066d95"));
		panelsHolder.setLayout(new BorderLayout());
		panelsHolder.add(infoPanel, BorderLayout.WEST);
		
		lblEditTableCells = new JLabel("Edit table cells as you want but don't forget saving before exit.");
		lblEditTableCells.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblEditTableCells.setBounds(66, 18, 508, 16);
		infoPanel.add(lblEditTableCells);
		panelsHolder.add(buttonsPanel, BorderLayout.EAST);
		
		jFrame.getContentPane().add(panelsHolder, BorderLayout.SOUTH);
		
		tableHolder = new JScrollPane();
		tableHolder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableHolder.setAutoscrolls(true);
		tableHolder.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(tableHolder, BorderLayout.CENTER);
		
		roomsTable = new JTable(roomsModel);
		roomsTable.setFont(new Font("Dialog", Font.PLAIN, 14));
		roomsTable.setRowSelectionAllowed(true);
		roomsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		roomsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomsTable.getTableHeader().setDefaultRenderer(tableHeaderRenderer);
		roomsTable.setRowHeight(22);
		roomsTable.setBackground(Color.WHITE);
		roomsTable.setGridColor(Color.GRAY.darker());
		roomsTable.setSelectionBackground(Color.decode("#D3D3D3"));
		
		tableHolder.setViewportView(roomsTable);
		
		populateMainTable(roomsModel);
		
		jFrame.setVisible(true);
	}

	public void populateMainTable(DefaultTableModel model) {
		//In all update this will repopulate the table from scratch
		model.setRowCount(0);
		
		//Get all rooms in list
		final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
		final List<Room> roomList = roomDaoImpl.getAllRooms();
		
		for(Room theRoom: roomList) {
			model.addRow(new Object[]{theRoom.getTheRoomId(), theRoom.getNumber(), 
							theRoom.getType(), theRoom.getPrice(), theRoom.getCurrency()});
		}
		
		roomsTable.revalidate();
		roomsTable.repaint();
	}
	
	public void mouseListenerForButtons(final JButton jButton) {

		// change opaque property for making button color changeable
		jButton.setOpaque(true);
		// add listener for buttons when mouse hover, active.
		final MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jButton.setBackground(Color.decode("#8be9fd"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jButton.setBackground(jFrame.getBackground());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				jButton.setBackground(Color.decode("#0fd0f9"));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				jButton.setBackground(Color.decode("#0fd0f9"));
			}
		};
		// set the button mouse listener this listener
		jButton.addMouseListener(ma);
	}

	public void actionListenerForButtons(final JButton jButton) {

		final ActionListener myListner = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == btnSave) {

					saveChanges();
					loggingEngine.setMessage("All rooms details has been changed.");

				} else if (e.getSource() == btnCancel) {
					
					loggingEngine.setMessage("Rooms details change window closing...");
					jFrame.dispose();
				}

			}
		};

		jButton.addActionListener(myListner);
	}
	
	private void saveChanges() {
		
		Room theRoom;
		final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
		
		try {
			
			for(int index=0; index < roomsTable.getRowCount(); index++) {
				
				String selectedRoom = roomsModel.getValueAt(index, 1).toString();
				theRoom = roomDaoImpl.getRoomByRoomNumber(selectedRoom);
				
				theRoom.setNumber(selectedRoom);
				theRoom.setType(roomsModel.getValueAt(index, 2).toString());
				theRoom.setPrice(Double.parseDouble(roomsModel.getValueAt(index, 3).toString()));
				theRoom.setCurrency(roomsModel.getValueAt(index, 4).toString());
				
				roomDaoImpl.updateRoom(theRoom);
			}
			
			INFORMATION_FRAME.setMessage("All changes saved successfully.");
			INFORMATION_FRAME.setVisible(true);
			
		} catch (RuntimeException e) {
			
			INFORMATION_FRAME.setMessage("Encountered a problem!Cannot save changes right now.");
			INFORMATION_FRAME.setVisible(true);
			loggingEngine.setMessage("When saving all rooms details encountered a problem! Rooling back.");
			new DataSourceFactory().getTransaction().rollback();
		}
	}
}
