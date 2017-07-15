package com.coder.hms.userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

public class BlockadeFrame extends JPanel {

	/**
	 * 
	 */
	private JPanel leftSidePanel;
	private static final long serialVersionUID = 1L;
	private JTable table, blokajTable, blokajRoomsTable, blokajCustomerTable;
	private JSplitPane mainVerticalSplitter, leftCenterSplitter, centerRightSplitter;
	private JScrollPane generalScrollPane, blokajScrollPane, roomScrollPane, customerScrollPane;
	
	private final Vector<String> vecColsName = new Vector<String>();
	private DefaultTableModel model;
	
	private final String[] blokajColsName = {"REZERV. NO", "AGENCY REF NO", "GROUP", "AGENCY", "CHECK/IN", "CHECK/OUT", "EARLY PAY"};
	private DefaultTableModel blokajModel = new DefaultTableModel(blokajColsName, 1);
	
	private final String[] blokajRoomsColsName = {"ROOM", "TYPE", "CHECK/IN", "CHECK/OUT", "PERSON"};
	private DefaultTableModel blokajRoomsModel = new DefaultTableModel(blokajRoomsColsName, 1);
	
	private final String[] blokajCustomerColsName = {"FIRSTNAME", "LASTNAME"};
	private DefaultTableModel blokajCustomerModel = new DefaultTableModel(blokajCustomerColsName, 1);
	
	/**
	 * Create the frame.
	 */
	public BlockadeFrame() {
		
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

		this.setAutoscrolls(true);
		this.setMinimumSize(new Dimension(800, 600));
		/*make it default size of frame maximized */
		this.setMaximumSize(new Dimension(1000, 900));
		
		mainVerticalSplitter = new JSplitPane();
		mainVerticalSplitter.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainVerticalSplitter.setOneTouchExpandable(true);
		mainVerticalSplitter.setIgnoreRepaint(true);
		mainVerticalSplitter.setInheritsPopupMenu(true);
		mainVerticalSplitter.setAutoscrolls(true);
		mainVerticalSplitter.setDividerLocation(200);
		mainVerticalSplitter.setAlignmentY(Component.CENTER_ALIGNMENT);
		mainVerticalSplitter.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainVerticalSplitter.setContinuousLayout(true);
		mainVerticalSplitter.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainVerticalSplitter.resetToPreferredSizes();
		setLayout(new BorderLayout(0, 0));
		add(mainVerticalSplitter);
		
		leftSidePanel = new JPanel();
		leftSidePanel.setAutoscrolls(true);
		leftSidePanel.setPreferredSize(new Dimension(10, 300));
		leftSidePanel.setBounds(0, 0, 10, 10);
		leftSidePanel.setLayout(new BorderLayout(0, 0));
		mainVerticalSplitter.setLeftComponent(leftSidePanel);
		
		leftCenterSplitter = new JSplitPane();
		leftCenterSplitter.setIgnoreRepaint(true);
		leftCenterSplitter.setInheritsPopupMenu(true);
		leftCenterSplitter.setOneTouchExpandable(true);
		leftCenterSplitter.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftCenterSplitter.setContinuousLayout(true);
		leftCenterSplitter.setAutoscrolls(true);
		leftCenterSplitter.setDividerLocation(630);
		leftCenterSplitter.resetToPreferredSizes();
		leftSidePanel.add(leftCenterSplitter);
		
		blokajTable = new JTable(blokajModel);
		blokajTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		blokajTable.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		blokajTable.setColumnSelectionAllowed(true);
		blokajTable.setCellSelectionEnabled(true);
		blokajTable.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		
		blokajScrollPane = new JScrollPane();
		blokajScrollPane.setViewportView(blokajTable);
		leftCenterSplitter.setLeftComponent(blokajScrollPane);
		
		centerRightSplitter = new JSplitPane();
		centerRightSplitter.setOneTouchExpandable(true);
		centerRightSplitter.setInheritsPopupMenu(true);
		centerRightSplitter.setIgnoreRepaint(true);
		centerRightSplitter.setContinuousLayout(true);
		centerRightSplitter.setAutoscrolls(true);
		centerRightSplitter.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerRightSplitter.resetToPreferredSizes();
		centerRightSplitter.setDividerLocation(630);
		leftCenterSplitter.setRightComponent(centerRightSplitter);
		
		blokajRoomsTable = new JTable(blokajRoomsModel);
		blokajRoomsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		blokajRoomsTable.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		blokajRoomsTable.setColumnSelectionAllowed(true);
		blokajRoomsTable.setCellSelectionEnabled(true);
		blokajRoomsTable.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		
		roomScrollPane = new JScrollPane();
		roomScrollPane.setViewportView(blokajRoomsTable);
		centerRightSplitter.setLeftComponent(roomScrollPane);
		
		blokajCustomerTable = new JTable(blokajCustomerModel);
		blokajCustomerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		blokajCustomerTable.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		blokajCustomerTable.setColumnSelectionAllowed(true);
		blokajCustomerTable.setCellSelectionEnabled(true);
		blokajCustomerTable.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		
		customerScrollPane = new JScrollPane();
		customerScrollPane.setViewportView(blokajCustomerTable);
		centerRightSplitter.setRightComponent(customerScrollPane);
		
		//populate table headers from this method.
		populateTableHeaders(vecColsName, new Date());
		
		model = new DefaultTableModel(vecColsName, 0);
		table = new JTable(model);
		table.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		
		generalScrollPane = new JScrollPane();
		generalScrollPane.setViewportView(table);
		
		mainVerticalSplitter.setRightComponent(generalScrollPane);
		
		this.setVisible(true);

	}
	
	public void populateTableHeaders(final Vector<String> cols, Date date) {
		String today = "";
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
			
		cols.add("TYPE");
		cols.add("ROOM");
		cols.add(date.toString());
		
		for(int i = 0; i < 7; i++) {
			c.add(Calendar.DATE, 1);
			date = c.getTime();
			today = sdf.format(date);
			cols.add(today);
		}
		
	}
}
