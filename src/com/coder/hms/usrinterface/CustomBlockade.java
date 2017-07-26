/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.usrinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.coder.hms.daoImpl.CustomerDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Blockade;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.utils.CustomTableCellRenderer;
import com.coder.hms.utils.CustomTableHeaderRenderer;
import com.coder.hms.utils.CustomTableHeaderRendererBlockaje;

public class CustomBlockade extends JPanel {

	/**
	 * 
	 */
	private List<Long> rezervationId;
	
	private RoomDaoImpl rImpl;
	private List<Room> roomList;
	
	private ReservationDaoImpl resDaoImpl;
	private List<Reservation> resList;
	
	private CustomerDaoImpl cImpl;
	private List<Customer> customerList;
	
	private String today = "";
	private String[] weekDates = new String[7];
	
	private JPanel leftSidePanel;
	private static final long serialVersionUID = 1L;
	private JTable table, blokajTable, blokajRoomsTable, blokajCustomerTable;
	private JSplitPane mainVerticalSplitter, leftCenterSplitter, centerRightSplitter;
	private JScrollPane generalScrollPane, blokajScrollPane, roomScrollPane, customerScrollPane;
	
	private final Vector<String> vecColsName = new Vector<String>();
	private DefaultTableModel model;
	
	private final String[] blokajColsName = {"REZERV. NO", "GROUP", "AGENCY", "CHECK/IN", "CHECK/OUT", "EARLY PAY"};
	private DefaultTableModel blokajModel = new DefaultTableModel(blokajColsName, 0);
	
	private final String[] blokajRoomsColsName = {"ROOM", "TYPE", "PERSON COUNT"};
	private DefaultTableModel blokajRoomsModel = new DefaultTableModel(blokajRoomsColsName, 0);
	
	private final String[] blokajCustomerColsName = {"FIRSTNAME", "LASTNAME"};
	private DefaultTableModel blokajCustomerModel = new DefaultTableModel(blokajCustomerColsName, 0);
	
	private final CustomTableCellRenderer cellRenderer = new CustomTableCellRenderer();
	private final CustomTableHeaderRenderer THR = new CustomTableHeaderRenderer();
	private final CustomTableHeaderRendererBlockaje THRC = new CustomTableHeaderRendererBlockaje();
	/**
	 * Create the frame.
	 */
	public CustomBlockade() {
		
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
		leftCenterSplitter.setDividerLocation(430);
		leftCenterSplitter.resetToPreferredSizes();
		leftSidePanel.add(leftCenterSplitter);
		
		blokajTable = new JTable(blokajModel);
		blokajTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		blokajTable.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		blokajTable.setColumnSelectionAllowed(true);
		blokajTable.setCellSelectionEnabled(true);
		blokajTable.getTableHeader().setDefaultRenderer(THRC);
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
		centerRightSplitter.setDividerLocation(430);
		leftCenterSplitter.setRightComponent(centerRightSplitter);
		
		blokajRoomsTable = new JTable(blokajRoomsModel);
		blokajRoomsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		blokajRoomsTable.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		blokajRoomsTable.setColumnSelectionAllowed(true);
		blokajRoomsTable.setCellSelectionEnabled(true);
		blokajRoomsTable.getTableHeader().setDefaultRenderer(THRC);
		blokajRoomsTable.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		
		roomScrollPane = new JScrollPane();
		roomScrollPane.setViewportView(blokajRoomsTable);
		centerRightSplitter.setLeftComponent(roomScrollPane);
		
		blokajCustomerTable = new JTable(blokajCustomerModel);
		blokajCustomerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		blokajCustomerTable.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		blokajCustomerTable.setColumnSelectionAllowed(true);
		blokajCustomerTable.setCellSelectionEnabled(true);
		blokajCustomerTable.getTableHeader().setDefaultRenderer(THRC);
		blokajCustomerTable.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		
		customerScrollPane = new JScrollPane();
		customerScrollPane.setViewportView(blokajCustomerTable);
		centerRightSplitter.setRightComponent(customerScrollPane);
		
		//populate table headers from this method.
		populateTableHeaders(vecColsName, new Date());
		
		model = new DefaultTableModel(vecColsName, 0);
		
		cellRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		THR.setHorizontalAlignment(SwingConstants.CENTER);
		
		table = new JTable(model);
		table.getTableHeader().setDefaultRenderer(THR);
		table.setDefaultRenderer(Object.class, cellRenderer);
		table.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		
		generalScrollPane = new JScrollPane();
		generalScrollPane.setViewportView(table);
		
		mainVerticalSplitter.setRightComponent(generalScrollPane);
		
		this.setVisible(true);
		
		//invoke this method at last
		getReadyForTables();
		populateBlokajTable(blokajModel);
		populateBlokajCustomerModel(blokajCustomerModel);
		populateBlokajRoomsModel(blokajRoomsModel);
		pupulateBlockadeTable(model);
	}
	
	public synchronized void getReadyForTables() {
		rImpl = new RoomDaoImpl();		
		roomList = rImpl.getAllRooms();
		
		resDaoImpl = new ReservationDaoImpl();
		resList = resDaoImpl.getAllReservations();
		
		cImpl = new CustomerDaoImpl();
		customerList = cImpl.getAllCustomers();
	}
	
	public void populateTableHeaders(final Vector<String> cols, Date date) {

		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
			
		cols.add("ROOM");
		cols.add("TYPE");
		cols.add("STATUS");
		
		for(int i = 0; i < 7; i++) {
			c.add(Calendar.DATE, 1);
			date = c.getTime();
			today = sdf.format(date);
			cols.add(today);
			//this date array for tables
			weekDates[i] = today;
		}
		
	}
	
	public void pupulateBlockadeTable(DefaultTableModel model) {
		
		Blockade blockade = null;
		for(int i=0; i < roomList.size(); i++) {
			
			blockade = new Blockade();
			blockade.setNumber(roomList.get(i).getNumber());
			blockade.setType(roomList.get(i).getType());
			blockade.setStatus(roomList.get(i).getUsageStatus());
			model.addRow(new Object[]{blockade.getNumber(), blockade.getType(), blockade.getStatus()});
			for (int j = 0; j < resList.size(); j++) {
				if(blockade.getNumber().equals(resList.get(j).getTheNumber()))
					for(int x=0; x < weekDates.length; x++) {
						if(resList.get(j).getCheckinDate().equals(weekDates[x])) {
							//add 3 to weekDates[x] because weekDates start from -3 in table;
							model.setValueAt(resList.get(j).getGroupName(), i, x + 3);
						}
					}
			}
			
			
			
		}
		
	}
	
	public void populateBlokajTable(DefaultTableModel blokajModel) {
		
		final Date date = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		final String today = sdf.format(date);
		
		final Reservation reservation = new Reservation();
		
		rezervationId = new ArrayList<>();
		
		for(int i=0; i < resList.size(); i++) {
			if(resList.get(i).getCheckinDate().equals(today)) {
				reservation.setId(resList.get(i).getId());
				reservation.setGroupName(resList.get(i).getGroupName());
				reservation.setAgency(resList.get(i).getAgency());
				reservation.setPaymentStatus(resList.get(i).getPaymentStatus());
				reservation.setCheckinDate(resList.get(i).getCheckinDate());
				reservation.setCheckoutDate(resList.get(i).getCheckoutDate());

				blokajModel.addRow(new Object[]{reservation.getId(), reservation.getGroupName(),
						reservation.getAgency(), reservation.getCheckinDate(), reservation.getCheckoutDate(), 
						reservation.getPaymentStatus()});
				rezervationId.add(resList.get(i).getId());
			}
			
		}
	}
	
	public void populateBlokajRoomsModel(DefaultTableModel blokajRoomsModel) {
		for(int index=0; index < roomList.size(); index++) {
			for(long id: rezervationId) {
				if(roomList.get(index).getReservationId() == id) {
					blokajRoomsModel.addRow(new Object[]{roomList.get(index).getNumber(),
							roomList.get(index).getType(), roomList.get(index).getPersonCount()});
				}
			}
		}
	}
	
	public void populateBlokajCustomerModel(DefaultTableModel blokajCustomerModel) {
		for(int k=0; k < customerList.size(); k++) {
			for(long id : rezervationId) {
				if(customerList.get(k).getReservationId() == id) {
					blokajCustomerModel.addRow(new Object[]{customerList.get(k).getFirstName(), customerList.get(k).getLastName()});
				}
			}
		}
	}
}
