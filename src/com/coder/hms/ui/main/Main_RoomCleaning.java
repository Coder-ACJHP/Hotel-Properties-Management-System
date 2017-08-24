package com.coder.hms.ui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.coder.hms.beans.LocaleBean;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Room;
import com.coder.hms.ui.external.DialogFrame;
import com.coder.hms.utils.CleaningRoomTableColumnsMaker;
import com.coder.hms.utils.CustomTableHeaderRenderer;
import com.coder.hms.utils.ResourceControl;
import com.coder.hms.utils.RoomCleaningTableRenderer;

public class Main_RoomCleaning extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private JTable table;
	private LocaleBean bean;
	private JLabel lblSearch;
	private ResourceBundle bundle;
	private JTextField searchField;
	private JPanel upperPanel, buttonsPanel, panel;
	private static final long serialVersionUID = 1L;
	private final CustomTableHeaderRenderer headerRenderer;
	private TableRowSorter<DefaultTableModel> tableRowShorter;
	private final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
	private CleaningRoomTableColumnsMaker cleaningRoomTableColumn;
	private final RoomCleaningTableRenderer renderer = new RoomCleaningTableRenderer();
	private final String[] columnNames = new String[] { "ROOM NUMBER", "ROOM TYPE", "CLEANING STATUS" };
	private final DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	private JButton btnCleanSelected, btnCleanAll, btnPollute, btnPolluteAll, btnSetAsDnd;
	
	public Main_RoomCleaning() {

		bean = LocaleBean.getInstance();
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

		this.setAutoscrolls(true);
		this.setMinimumSize(new Dimension(800, 600));
		/* make it default size of frame maximized */
		this.setMaximumSize(new Dimension(1000, 900));
		setLayout(new BorderLayout(0, 0));

		upperPanel = new JPanel();
		upperPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		upperPanel.setLayout(new BorderLayout());
		upperPanel.setBackground(Color.decode("#066d95"));
		add(upperPanel, BorderLayout.NORTH);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setAutoscrolls(true);
		buttonsPanel.setPreferredSize(new Dimension(850, 48));
		buttonsPanel.setBackground(Color.decode("#066d95"));
		buttonsPanel.setLayout(null);
		upperPanel.add(buttonsPanel, BorderLayout.WEST);

		btnCleanSelected = new JButton("Clean Selected");
		btnCleanSelected.setPreferredSize(new Dimension(135, 35));
		btnCleanSelected.setAutoscrolls(true);
		btnCleanSelected.addActionListener(this);
		btnCleanSelected.setIcon(
				new ImageIcon(Main_RoomCleaning.class.getResource("/com/coder/hms/icons/cleaning_single.png")));
		btnCleanSelected.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCleanSelected.setBounds(12, 2, 146, 44);
		btnCleanSelected.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnCleanSelected.setFont(new Font("Dialog", Font.BOLD, 13));
		buttonsPanel.add(btnCleanSelected);

		btnCleanAll = new JButton("Clean All");
		btnCleanAll.setAutoscrolls(true);
		btnCleanAll.addActionListener(this);
		btnCleanAll.setIcon(new ImageIcon(Main_RoomCleaning.class.
				getResource("/com/coder/hms/icons/cleaning_all.png")));
		btnCleanAll.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnCleanAll.setBounds(172, 2, 146, 44);
		btnCleanAll.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnCleanAll.setFont(new Font("Dialog", Font.BOLD, 13));
		buttonsPanel.add(btnCleanAll);
		
		btnPollute = new JButton("Pollute Selected");
		btnPollute.setAutoscrolls(true);
		btnPollute.setIcon(new ImageIcon(Main_RoomCleaning.class.getResource("/com/coder/hms/icons/room_dirty.png")));
		btnPollute.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnPollute.setFont(new Font("Dialog", Font.BOLD, 13));
		btnPollute.setBounds(332, 2, 146, 44);
		btnPollute.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPollute.addActionListener(this);
		buttonsPanel.add(btnPollute);
		
		btnPolluteAll = new JButton("Pollute All");
		btnPolluteAll.setAutoscrolls(true);
		btnPolluteAll.setIcon(new ImageIcon(Main_RoomCleaning.class.getResource("/com/coder/hms/icons/rezaerv_report.png")));
		btnPolluteAll.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnPolluteAll.setFont(new Font("Dialog", Font.BOLD, 13));
		btnPolluteAll.setBounds(492, 2, 146, 44);
		btnPolluteAll.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPolluteAll.addActionListener(this);
		buttonsPanel.add(btnPolluteAll);
		
		btnSetAsDnd = new JButton("Set As DND");
		btnSetAsDnd.setAutoscrolls(true);
		btnSetAsDnd.addActionListener(this);
		btnSetAsDnd.setIcon(new ImageIcon(Main_RoomCleaning.class.getResource("/com/coder/hms/icons/room_dnd.png")));
		btnSetAsDnd.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSetAsDnd.setFont(new Font("Dialog", Font.BOLD, 13));
		btnSetAsDnd.setBounds(653, 2, 146, 44);
		btnSetAsDnd.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonsPanel.add(btnSetAsDnd);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(250, 10));
		upperPanel.add(panel, BorderLayout.EAST);
		panel.setLayout(null);
		
		lblSearch = new JLabel("Search : ");
		lblSearch.setForeground(new Color(255, 255, 0));
		lblSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSearch.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblSearch.setBounds(32, 12, 46, 23);
		panel.add(lblSearch);
		
		searchField = new JTextField();
		searchField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchField.setBounds(79, 10, 165, 26);
		searchField.setColumns(10);
		searchField.addKeyListener(customKeyListener());
		panel.add(searchField);


		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setAutoscrolls(true);
		add(scrollPane, BorderLayout.CENTER);
		
		/* Before adding model to table we need to populate it */
		populateTableModel(model);
		tableRowShorter = new TableRowSorter<DefaultTableModel>(model);

		table = new JTable();
		table.setModel(model);
		table.setRowHeight(18);
		table.setRowSelectionAllowed(true);
		table.setRowSorter(tableRowShorter);
		table.setColumnSelectionAllowed(false);
		table.setFont(new Font("Verdana", Font.PLAIN, 14));

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
		
		changeLanguage(bean.getLocale());
	}

	private void changeLanguage(Locale locale) {

		bundle = ResourceBundle.getBundle("com/coder/hms/languages/LocalizationBundle", locale, new ResourceControl());
		
		this.btnCleanSelected.setText(bundle.getString("CleanSelected"));
		this.btnCleanAll.setText(bundle.getString("CleanAll"));
		this.btnPollute.setText(bundle.getString("PolluteSelected"));
		this.btnPolluteAll.setText(bundle.getString("PolluteAll"));
		this.btnSetAsDnd.setText(bundle.getString("SetAsDnd"));
		this.lblSearch.setText(bundle.getString("Search"));
		
	}

	private void populateTableModel(DefaultTableModel theModel) {

		final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
		final List<Room> roomList = roomDaoImpl.getAllRooms();

		for (Room room : roomList) {
			Object[] rowData = { room.getNumber(), room.getType(), room.getCleaningStatus() };
			model.addRow(rowData);
		}
	}
//--------------------------------------NEW METHOD 26/07/2017	
	private void refreshTable() {
		model.setRowCount(0);
		populateTableModel(model);

	}
	
	private KeyListener customKeyListener() {
		final KeyAdapter adapter = new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				String modifiedQuery = "(?i)" + searchField.getText();
				tableRowShorter.setRowFilter(RowFilter.regexFilter(modifiedQuery));
				
				super.keyTyped(e);
			}
			
		};
		return adapter;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		
		if(e.getSource() == btnCleanSelected) {
			
			int rowIndex = table.getSelectedRow();
			
			if(rowIndex < 0) {
				JOptionPane.showMessageDialog(null, "You have to select a row at first!", 
								JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				
				String rowData = table.getValueAt(rowIndex, 0).toString();
				roomDaoImpl.setSingleRoomAsCleanByRoomNumber(rowData);
				refreshTable();
			}
		}
		
		else if(e.getSource() == btnCleanAll){
			
			final DialogFrame diFrame = new DialogFrame();
			diFrame.setMessage("Are you sure change all room status as clean?");
			diFrame.btnYes.addActionListener(ActionListener ->{
				roomDaoImpl.setAllRoomsAtClean("CLEAN");
				refreshTable();
				diFrame.dispose();
			});
			diFrame.btnNo.addActionListener(ActionListener->{
				diFrame.dispose();
				return;
			});
			diFrame.setVisible(true);
		}
		
		else if(e.getSource() == btnPollute) {
			
			int rowIndex = table.getSelectedRow();
			
			if(rowIndex < 0) {
				JOptionPane.showMessageDialog(null, "You have to select a row at first!", 
								JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				return;
			}else {
					
				String rowData = table.getValueAt(rowIndex, 0).toString();
				roomDaoImpl.setSingleRoomAsDirtyByRoomNumber(rowData);
				refreshTable();
			}
		}
		
		else if(e.getSource() == btnPolluteAll) {
			
			final DialogFrame dialog = new DialogFrame();
			dialog.setMessage("Are you sure change all room status as dirty?");
			dialog.btnYes.addActionListener(ActionListener ->{
				roomDaoImpl.setAllRoomsAtDirty("DIRTY");
				refreshTable();
				dialog.dispose();
			});
			dialog.btnNo.addActionListener(ActionListener->{
				dialog.dispose();
				return;
			});
			dialog.setVisible(true);
		}
		
		else if(e.getSource() == btnSetAsDnd) {
			
			int rowIndex = table.getSelectedRow();
			
			if(rowIndex < 0) {
				JOptionPane.showMessageDialog(null, "You have to select a row at first!", 
								JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				String rowData = table.getValueAt(rowIndex, 0).toString();
				roomDaoImpl.setSingleRoomAsDNDByRoomNumber(rowData);
				refreshTable();
			}
		}
		
	}
	
}
