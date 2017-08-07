package com.coder.hms.usrinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Room;
import com.coder.hms.utils.CleaningRoomTableColumnsMaker;
import com.coder.hms.utils.RoomCleaningTableRenderer;
import com.coder.hms.utils.CustomTableHeaderRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main_RoomCleaning extends JPanel {

	/**
	 * 
	 */
	private JTable table;
	private static final long serialVersionUID = 1L;
	private final CustomTableHeaderRenderer headerRenderer;
	private final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
	private CleaningRoomTableColumnsMaker cleaningRoomTableColumn;
	private final String[] columnNames = new String[] { "ROOM NUMBER", "ROOM TYPE", "CLEANING STATUS" };
	private final DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	private final RoomCleaningTableRenderer renderer = new RoomCleaningTableRenderer();

	public Main_RoomCleaning() {

		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

		this.setAutoscrolls(true);
		this.setMinimumSize(new Dimension(800, 600));
		/* make it default size of frame maximized */
		this.setMaximumSize(new Dimension(1000, 900));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setAutoscrolls(true);
		panel.setPreferredSize(new Dimension(10, 45));
		panel.setBackground(Color.decode("#066d95"));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);

		JButton btnCleanSelected = new JButton("Clean Selected");
		btnCleanSelected.addActionListener(cleanSelectedRoom());
		btnCleanSelected.setIcon(
				new ImageIcon(Main_RoomCleaning.class.getResource("/com/coder/hms/icons/cleaning_single.png")));
		btnCleanSelected.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCleanSelected.setBounds(6, 6, 147, 37);
		btnCleanSelected.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(btnCleanSelected);

		JButton btnCleanAll = new JButton("Clean All");
		btnCleanAll.addActionListener(cleanAllListener());
		btnCleanAll.setIcon(new ImageIcon(Main_RoomCleaning.class.
				getResource("/com/coder/hms/icons/cleaning_all.png")));
		btnCleanAll.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCleanAll.setBounds(161, 6, 147, 37);
		btnCleanAll.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(btnCleanAll);
		
		JButton btnPollute = new JButton("Pollute Selected");
		btnPollute.setIcon(new ImageIcon(Main_RoomCleaning.class.getResource("/com/coder/hms/icons/room_dirty.png")));
		btnPollute.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnPollute.setFont(new Font("Arial", Font.BOLD, 13));
		btnPollute.setBounds(316, 6, 147, 37);
		btnPollute.addActionListener(putPolluteListener());
		panel.add(btnPollute);
		
		JButton btnPolluteAll = new JButton("Pollute All");
		btnPolluteAll.setIcon(new ImageIcon(Main_RoomCleaning.class.getResource("/com/coder/hms/icons/rezaerv_report.png")));
		btnPolluteAll.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnPolluteAll.setFont(new Font("Arial", Font.BOLD, 14));
		btnPolluteAll.setBounds(468, 6, 147, 37);
		btnPolluteAll.addActionListener(polluteAllListener());
		panel.add(btnPolluteAll);
		
		JButton btnPutAtDnd = new JButton("Put at DND");
		btnPutAtDnd.addActionListener(putAtDndListener());
		btnPutAtDnd.setIcon(new ImageIcon(Main_RoomCleaning.class.getResource("/com/coder/hms/icons/room_dnd.png")));
		btnPutAtDnd.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnPutAtDnd.setFont(new Font("Arial", Font.BOLD, 14));
		btnPutAtDnd.setBounds(621, 6, 147, 37);
		panel.add(btnPutAtDnd);


		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setAutoscrolls(true);
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setFont(new Font("Verdana", Font.PLAIN, 14));
		/* Before adding model to table we need to populate it */
		populateTableModel(model);

		table.setModel(model);
		headerRenderer = new CustomTableHeaderRenderer();
		headerRenderer.setVerticalAlignment(SwingConstants.CENTER);
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getTableHeader().setDefaultRenderer(headerRenderer);
		cleaningRoomTableColumn = new CleaningRoomTableColumnsMaker();
		cleaningRoomTableColumn.setHeadersWidth(table);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		renderer.setVerticalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, renderer);
		scrollPane.setViewportView(table);
	}



	private void populateTableModel(DefaultTableModel theModel) {

		final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
		final List<Room> roomList = roomDaoImpl.getAllRooms();

		for (Room room : roomList) {
			final Object[] rowData = new Object[] { room.getNumber(), room.getType(), room.getCleaningStatus() };
			model.addRow(rowData);
		}
	}
//--------------------------------------NEW METHOD 26/07/2017	
	private void refreshTable() {
		model.setRowCount(0);
		populateTableModel(model);

	}

	private ActionListener cleanSelectedRoom() {
		final ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				final int rowIndex = table.getSelectedRow();
				final String rowData = table.getValueAt(rowIndex, 0).toString();
				roomDaoImpl.setSingleRoomAsCleanByRoomNumber(rowData);
				refreshTable();
			}
		};
		return listener;
	}

	private ActionListener cleanAllListener() {
		final ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				roomDaoImpl.setAllRoomsAtClean("CLEAN");
				refreshTable();
			}
		};
		return listener;
	}
	
	private ActionListener putPolluteListener() {
		final ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final int rowIndex = table.getSelectedRow();
				final String rowData = table.getValueAt(rowIndex, 0).toString();
				roomDaoImpl.setSingleRoomAsDirtyByRoomNumber(rowData);
				refreshTable();
				
			}
		};
		return listener;
	}
	
	private ActionListener polluteAllListener() {
		final ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				roomDaoImpl.setAllRoomsAtDirty("DIRTY");
				refreshTable();

			}
		};
		return listener;
	}
	
	private ActionListener putAtDndListener() {
		final ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final int rowIndex = table.getSelectedRow();
				final String rowData = table.getValueAt(rowIndex, 0).toString();
				roomDaoImpl.setRoomAtDND(rowData);
				refreshTable();

			}
		};
		return listener;
	}
}
