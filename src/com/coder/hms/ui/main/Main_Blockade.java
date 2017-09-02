/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import com.coder.hms.beans.Blockade;
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
import com.coder.hms.ui.external.InformationFrame;
import com.coder.hms.ui.external.UpdateReservationWindow;
import com.coder.hms.utils.BlockadeTableCellRenderer;
import com.coder.hms.utils.BlockadeTableHeaderRenderer;
import com.coder.hms.utils.CustomTableHeaderRenderer;
import com.coder.hms.utils.LoggingEngine;
import com.coder.hms.utils.ResourceControl;
import com.toedter.calendar.JDateChooser;

public class Main_Blockade extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private JPanel panel;
	private LocaleBean bean;
	private JLabel lblSearch;
	private ResourceBundle bundle;
	private JTextField searchField;
	private String reservIdFromRow;
	private static LoggingEngine loggingEngine;
	
	private List<Long> rezervationIdList;
	private RoomDaoImpl rImpl;
	private List<Room> roomList;
	private ReservationDaoImpl resDaoImpl;
	private List<Reservation> resList;
	private CustomerDaoImpl cImpl;
	private List<Customer> customerList;
	private PaymentDaoImpl paymentDaoImpl;
	
	private String today = "";
	private final Calendar masterDate = Calendar.getInstance();
	private String[] weekDates = new String[10];
	
	private JDateChooser dateChooser;
	private JPanel leftSidePanel, buttonPanel;
	private JButton previousBtn, nextBtn, btnShowRes;
	private static final long serialVersionUID = 1L;
	private TableRowSorter<DefaultTableModel> tableRowShorter;
	
	private final HotelSystemStatus systemStatus;
	private final HotelSystemStatusImpl statusImpl = new HotelSystemStatusImpl();
	
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private JTable table, blokajTable, blokajRoomsTable, blokajCustomerTable;
	private JSplitPane mainVerticalSplitter, leftCenterSplitter, centerRightSplitter;
	private JScrollPane generalScrollPane, blokajScrollPane, roomScrollPane, customerScrollPane;
	
	private final String[] bottomTableHeader = new String[10];
	private DefaultTableModel model = new DefaultTableModel(bottomTableHeader, 0);
	
	private final String[] blokajColsName = {"REZERV. NO", "GROUP", "AGENCY", "CHECK/IN", "CHECK/OUT", "EARLY PAY"};
	private DefaultTableModel blokajModel = new DefaultTableModel(blokajColsName, 0);
	
	private final String[] blokajRoomsColsName = {"ROOM", "TYPE", "PERSON COUNT"};
	private DefaultTableModel blokajRoomsModel = new DefaultTableModel(blokajRoomsColsName, 0);
	
	private final String[] blokajCustomerColsName = {"FIRSTNAME", "LASTNAME"};
	private DefaultTableModel blokajCustomerModel = new DefaultTableModel(blokajCustomerColsName, 0);
	
	private final CustomTableHeaderRenderer THR = new CustomTableHeaderRenderer();
	private final BlockadeTableHeaderRenderer THRC = new BlockadeTableHeaderRenderer();
	private final BlockadeTableCellRenderer cellRenderer = new BlockadeTableCellRenderer();
	/**
	 * Create the frame.
	 */
	public Main_Blockade() {
			
		loggingEngine = LoggingEngine.getInstance();
		
		bean = LocaleBean.getInstance();
		
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

		this.setAutoscrolls(true);
		this.setMinimumSize(new Dimension(800, 600));
		/*make it default size of frame maximized */
		this.setMaximumSize(new Dimension(1000, 900));
		this.setLayout(new BorderLayout());
		
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
		add(mainVerticalSplitter, BorderLayout.CENTER);
		
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
		blokajTable.setRowSelectionAllowed(true);
		blokajTable.getTableHeader().setDefaultRenderer(THRC);
		blokajTable.addMouseListener(blokajMouseListener());
		blokajTable.setRowHeight(20);
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
		blokajRoomsTable.setColumnSelectionAllowed(false);
		blokajRoomsTable.setCellSelectionEnabled(false);
		blokajRoomsTable.setRowSelectionAllowed(true);
		blokajRoomsTable.getTableHeader().setDefaultRenderer(THRC);
		blokajRoomsTable.setRowHeight(20);
		blokajRoomsTable.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		
		roomScrollPane = new JScrollPane();
		roomScrollPane.setViewportView(blokajRoomsTable);
		centerRightSplitter.setLeftComponent(roomScrollPane);
		
		blokajCustomerTable = new JTable(blokajCustomerModel);
		blokajCustomerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		blokajCustomerTable.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		blokajCustomerTable.setCellSelectionEnabled(false);
		blokajCustomerTable.setColumnSelectionAllowed(false);
		blokajCustomerTable.getTableHeader().setDefaultRenderer(THRC);
		blokajCustomerTable.setRowHeight(20);
		blokajCustomerTable.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		
		customerScrollPane = new JScrollPane();
		customerScrollPane.setViewportView(blokajCustomerTable);
		centerRightSplitter.setRightComponent(customerScrollPane);
		
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		THR.setHorizontalAlignment(SwingConstants.CENTER);
		tableRowShorter = new TableRowSorter<DefaultTableModel>(model);
		
		table = new JTable(model);
		table.getTableHeader().setDefaultRenderer(THR);
		table.setDefaultRenderer(Object.class, cellRenderer);
		table.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setRowHeight(20);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setRowSorter(tableRowShorter);
		table.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		
		//populate table headers from this method.
		populateTableHeaders();

		generalScrollPane = new JScrollPane();
		generalScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		generalScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		generalScrollPane.setViewportView(table);
		
		mainVerticalSplitter.setRightComponent(generalScrollPane);
		
		final JPanel upperPanel = new JPanel();
		upperPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		upperPanel.setAutoscrolls(true);
		upperPanel.setPreferredSize(new Dimension(300, 45));
		upperPanel.setBackground(Color.decode("#066d95"));
		upperPanel.setLayout(new BorderLayout());
		add(upperPanel, BorderLayout.NORTH);
		
		//make a panel to add date chooser and buttons with null layout.
		buttonPanel = new JPanel();
		buttonPanel.setBorder(null);
		buttonPanel.setAutoscrolls(true);
		buttonPanel.setPreferredSize(new Dimension(440, 40));
		buttonPanel.setBackground(Color.decode("#066d95"));
		buttonPanel.setLayout(null);
		upperPanel.add(buttonPanel, BorderLayout.WEST);
		
		systemStatus = statusImpl.getSystemStatus();
		final Date convertedDate = Date.from(systemStatus.getDateTime().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		dateChooser = new JDateChooser();
		dateChooser.setDate(convertedDate);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(55, 6, 164, 26);
		dateChooser.addPropertyChangeListener(customPropListener());
		buttonPanel.add(dateChooser);
		
		masterDate.setTime(convertedDate);
		
		previousBtn = new JButton("");
		previousBtn.setMaximumSize(new Dimension(400, 29));
		previousBtn.setMinimumSize(new Dimension(400, 29));
		previousBtn.setAutoscrolls(true);
		previousBtn.addActionListener(this);
		previousBtn.setIcon(new ImageIcon(Main_Blockade.class.getResource("/com/coder/hms/icons/blockade_previous.png")));
		previousBtn.setBounds(6, 6, 49, 26);
		buttonPanel.add(previousBtn);
		
		nextBtn = new JButton("");
		nextBtn.addActionListener(this);
		nextBtn.setIcon(new ImageIcon(Main_Blockade.class.getResource("/com/coder/hms/icons/blockade_next.png.png")));
		nextBtn.setBounds(219, 6, 49, 26);
		buttonPanel.add(nextBtn);
		
		btnShowRes = new JButton("Show reservation");
		btnShowRes.setToolTipText("<html>Select a reservation from the table with <br>"
				+ "single click and press this button to show it.</html>");
		btnShowRes.addActionListener(this);
		btnShowRes.setIcon(new ImageIcon(Main_Audit.class.getResource("/com/coder/hms/icons/main_new_rez.png")));
		btnShowRes.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnShowRes.setAutoscrolls(true);
		btnShowRes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnShowRes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShowRes.setBounds(280, 1, 154, 37);
		buttonPanel.add(btnShowRes);
		
		//add this label to upperPanel(main) to be centered.
		final JLabel lblBlockade = new JLabel("BLOCKADE");
		lblBlockade.setAutoscrolls(true);
		lblBlockade.setMinimumSize(new Dimension(70, 16));
		lblBlockade.setPreferredSize(new Dimension(70, 16));
		lblBlockade.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBlockade.setLabelFor(buttonPanel);
		lblBlockade.setLocation(571, 6);
		lblBlockade.setSize(159, 30);
		lblBlockade.setForeground(UIManager.getColor("Button.highlight"));
		lblBlockade.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBlockade.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlockade.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 25));
		upperPanel.add(lblBlockade, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setAutoscrolls(true);
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(300, 40));
		upperPanel.add(panel, BorderLayout.EAST);
		panel.setLayout(null);
		
		lblSearch = new JLabel("Search : ");
		lblSearch.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearch.setForeground(new Color(255, 255, 51));
		lblSearch.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblSearch.setBounds(6, 6, 93, 22);
		panel.add(lblSearch);
		
		searchField = new JTextField();
		searchField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchField.setSelectionColor(new Color(102, 153, 255));
		searchField.setPreferredSize(new Dimension(13, 26));
		searchField.setIgnoreRepaint(true);
		searchField.setBounds(111, 5, 183, 26);
		searchField.setColumns(10);
		searchField.addKeyListener(customKeyListener());
		panel.add(searchField);
		
		this.setVisible(true);
		
		//invoke this method at last
		getReadyForTables();
		populateBlokajTable(blokajModel);
		populateMainTable(model);
		
		changeLanguage(bean.getLocale());
	}

	private void changeLanguage(Locale locale) {

		bundle = ResourceBundle.getBundle("com/coder/hms/languages/LocalizationBundle", locale, new ResourceControl());
		this.btnShowRes.setText(bundle.getString("ShowRes"));
		this.lblSearch.setText(bundle.getString("Search"));
		
	}
	
	// before creating GUI make ready all dependencies
	public synchronized void getReadyForTables() {
		rImpl = new RoomDaoImpl();		
		roomList = rImpl.getAllRooms();
		
		resDaoImpl = new ReservationDaoImpl();
		resList = resDaoImpl.getAllReservations();
		
		cImpl = new CustomerDaoImpl();
		customerList = cImpl.getAllCustomers();
		
		paymentDaoImpl = new PaymentDaoImpl();
	}
	
	public void populateTableHeaders() {
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final Calendar c = Calendar.getInstance();
		c.setTime(masterDate.getTime());
			
		JTableHeader tableHeader = table.getTableHeader();
		TableColumnModel tableColumnModel = tableHeader.getColumnModel();
		TableColumn tableColumn;
		
		tableColumn = tableColumnModel.getColumn(0);
		tableColumn.setHeaderValue("ROOM");
		tableColumn = tableColumnModel.getColumn(1);
		tableColumn.setHeaderValue("TYPE");
		tableColumn = tableColumnModel.getColumn(2);
		tableColumn.setHeaderValue("STATUS");
		
		//start the date from minus 1 to get today date.
		c.add(Calendar.DATE, -1);
		
		//start the loop from 3 because first 3 columns already 
		//populated up and the loop on 10 to get one week 
		for(int i = 3; i < 10; i++) {
			c.add(Calendar.DATE, 1);
			today = sdf.format(c.getTime());
			tableColumn = tableColumnModel.getColumn(i);
			tableColumn.setHeaderValue(today);

			//store dates in special array to use it in bottom
			weekDates[i] = today;
		}
		
		tableHeader.revalidate();
		tableHeader.repaint();
		
	}

	//Bottom main tabel that include all reservations
	public void populateMainTable(DefaultTableModel model) {
		
		model.setRowCount(0);
		/*Simple object POJO class (entity)*/
		Blockade blockade = null;
		
		for(int colindex=0; colindex < roomList.size(); colindex++) {
			/////////////////////////////////////////////////////////////////
			//here we created new 'Blockade' object special for this table //
			//and we will use this object to move datas as desire          //
			blockade = new Blockade();                                     //
			blockade.setNumber(roomList.get(colindex).getNumber());        //
			blockade.setType(roomList.get(colindex).getType());            //
			blockade.setStatus(roomList.get(colindex).getCleaningStatus());//
			/////////////////////////////////////////////////////////////////
			
			//populate first three columns with following informations
			model.addRow(new Object[]{blockade.getNumber(), blockade.getType(), blockade.getStatus()});
			
			////////////////////////////////////////////////////////////////////
			//in this part of code we gonna use special date array 'weekDates'// 
			//that initialized up and populated table header as date, so after//
			//checking the date we have to get that reservation is equals with//
			//header date and finally populate the table.                     //
			for (int listIndex = 0; listIndex < resList.size(); listIndex++) {
				
				if(blockade.getNumber().equals(resList.get(listIndex).getTheNumber()))
					
					for(int rowIndex=0; rowIndex < weekDates.length; rowIndex++) {
						
						if(resList.get(listIndex).getCheckinDate().equals(weekDates[rowIndex])) {
							
							//populating table and sorting as dates
							model.setValueAt(resList.get(listIndex).getGroupName(), colindex, rowIndex);
						}
					}
			}			
		}	
	}
	
	//Upper first at left tabel including blockade reservations
	public void populateBlokajTable(DefaultTableModel blokajModel) {
				
		final String workingDate = sdf.format(dateChooser.getDate());
		
		final Reservation reservation = new Reservation();
		rezervationIdList = new ArrayList<>();
		
		blokajModel.setRowCount(0);
		
		for(int i=0; i < resList.size(); i++) {
			if(resList.get(i).getCheckinDate().equals(workingDate) && resList.get(i).getIsCheckedIn().equals("NO")) {
				reservation.setId(resList.get(i).getId());
				reservation.setGroupName(resList.get(i).getGroupName());
				reservation.setAgency(resList.get(i).getAgency());
				reservation.setPaymentStatus(resList.get(i).getPaymentStatus());
				reservation.setCheckinDate(resList.get(i).getCheckinDate());
				reservation.setCheckoutDate(resList.get(i).getCheckoutDate());

				blokajModel.addRow(new Object[]{reservation.getId(), reservation.getGroupName(),
						reservation.getAgency(), reservation.getCheckinDate(), reservation.getCheckoutDate(), 
						reservation.getPaymentStatus()});
				rezervationIdList.add(resList.get(i).getId());
			}
			
		}
	}

	public void populateBlokajRoomsModel(DefaultTableModel blokajRoomsModel, String reservId) {
		if(blokajModel.getRowCount() == 0) {
			
			blokajRoomsModel.setRowCount(0);
		}else {
		
			for (int index = 0; index < roomList.size(); index++) {
				if (roomList.get(index).getReservationId() == Long.parseLong(reservId)) {
					blokajRoomsModel.addRow(new Object[] { roomList.get(index).getNumber(), roomList.get(index).getType(),
							roomList.get(index).getPersonCount() });
					break;
				}
			}
		}
	}

	public void populateBlokajCustomerModel(DefaultTableModel blokajCustomerModel, String reservId) {
		if(blokajRoomsModel.getRowCount() == 0) {
			
			blokajCustomerModel.setRowCount(0);
		}else {
			for (int k = 0; k < customerList.size(); k++) {
				if (customerList.get(k).getReservationId() == Long.parseLong(reservId)) {
					blokajCustomerModel.addRow(new Object[] { 
							customerList.get(k).getFirstName(), customerList.get(k).getLastName()});
				}
			}
		}
	}

	private MouseListener blokajMouseListener() {
		final MouseAdapter adapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    int  selectedIndex = blokajTable.getSelectedRow();

			    if(selectedIndex < 0) {
			    	blokajRoomsTable.revalidate();
			    	blokajRoomsTable.repaint();
			    	blokajCustomerTable.revalidate();
			    	blokajCustomerTable.repaint();
			    }
			    
				reservIdFromRow = blokajTable.getValueAt(selectedIndex, 0).toString();
				blokajRoomsModel.setRowCount(0);
				populateBlokajRoomsModel(blokajRoomsModel, reservIdFromRow);
				blokajCustomerModel.setRowCount(0);
				populateBlokajCustomerModel(blokajCustomerModel, reservIdFromRow);
				super.mousePressed(e);
			}
		};
		return adapter;
	}

	//this listener for changing table headers when date chosen from two rows.
	@Override
	public void actionPerformed(ActionEvent e) {

		masterDate.setTime(dateChooser.getDate());

		if (e.getSource() == nextBtn) {
			masterDate.add(Calendar.DATE, 1);

		} else if (e.getSource() == previousBtn) {
			masterDate.add(Calendar.DATE, -1);

		} else if (e.getSource() == btnShowRes) {

			String customerCountry = "";

			Payment payment = null;
			Room room = null;

			Optional<String> resIdOptional = Optional.ofNullable(reservIdFromRow);
			Reservation foundRes = resDaoImpl.findReservationById(Long.valueOf(resIdOptional.get()));

			loggingEngine.setMessage("[Blockade window] Required reservation found : " + foundRes.toString());
			final UpdateReservationWindow nex = new UpdateReservationWindow();
			
			for (Room searchedRoom : roomList)
				if (searchedRoom.getReservationId() == foundRes.getId())
					room = searchedRoom;

			for (Customer cst : customerList) {
				if (cst.getReservationId() == foundRes.getId()) {
					customerCountry = cst.getCountry();
					nex.setRoomInfoTableRows(
							new Object[] { room.getNumber(), room.getType(), cst.getFirstName(), cst.getLastName() });
				}
			}
				
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

				nex.setRoomCountTableRows(new Object[] { room.getNumber(), room.getType(), room.getPersonCount(),
						room.getPrice(), room.getCurrency() });

				if (foundRes.getPaymentStatus()) {

					payment = paymentDaoImpl.getEarlyPaymentByRoomNumber(room.getNumber());
					nex.setEarlyPaymetTableRows(new Object[] { payment.getTitle(), payment.getPaymentType(),
							payment.getPrice(), payment.getCurrency(), payment.getExplanation(), payment.getDateTime() });
					final InformationFrame infoFrame = new InformationFrame();
					infoFrame.setMessage("Early payment : " + payment.getPrice() + payment.getCurrency());
					infoFrame.setVisible(true);
				}

				loggingEngine.setMessage("Reservation window is populated successfully.");
				nex.setVisible(true);
				
			}

		dateChooser.setDate(masterDate.getTime());
		dateChooser.revalidate();
		dateChooser.repaint();

		// refresh all tables to getting new informations
		populateTableHeaders();
		populateBlokajTable(blokajModel);
		populateMainTable(model);

	}
	
	//this listener for changing table headers when date chosen from date component.
	private PropertyChangeListener customPropListener() {
		final PropertyChangeListener propListener = new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if("date".equals(evt.getPropertyName())) {
					masterDate.setTime((Date) evt.getNewValue());
					populateTableHeaders();
					populateBlokajTable(blokajModel);
					populateMainTable(model);
				}
				
			}
		};
		return propListener;
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
}
