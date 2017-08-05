/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.usrinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.coder.hms.daoImpl.CustomerDaoImpl;
import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.Hotel;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.utils.ReservationTableRenderer;
import com.coder.hms.utils.CustomTableHeaderRenderer;
import com.toedter.calendar.JDateChooser;

public class Main_Reservations extends JPanel {

	/**
	 * 
	 */
	private JTable table;
	private Date reservDate;
	private JPanel buttonPanel;
	private JTextField refNoField;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JTextField agencyRefField;
	private JButton newRezBtn, findBtn;
	private RoomDaoImpl roomDaoImpl;
	
	private HotelDaoImpl hotelDoaImpl;
	
	private CustomerDaoImpl customerDaoImpl;

	private NewReservExternalWindow newReservationEx;

	private ReservationDaoImpl reservationDaoImpl;
	
	private static final long serialVersionUID = 1L;
	private JDateChooser startDatePicker, endDatePicker;
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private JLabel startdateLbl, endDateLbl, referansNoLbl, agencyRefLbl;
	private final String[] rezColsName = {"DATE", "CAPASITE ", "FULL ", "EMPTY", "GARANTED", "WAITING"};
	private final ReservationTableRenderer customTCR = new ReservationTableRenderer();
	private final CustomTableHeaderRenderer THR = new CustomTableHeaderRenderer();
	
	public Main_Reservations() {
		
		setLayout(new BorderLayout(0, 0));
		
		buttonPanel = new JPanel();
		buttonPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonPanel.setAutoscrolls(true);
		buttonPanel.setPreferredSize(new Dimension(10, 65));
		add(buttonPanel, BorderLayout.NORTH);
		
		newRezBtn = new JButton("New Reservation");
		newRezBtn.setBounds(6, 12, 155, 45);
		newRezBtn.setIcon(new ImageIcon(Main_Blockade.class.getResource("/com/coder/hms/icons/main_new_rez.png")));
		newRezBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		newRezBtn.setPreferredSize(new Dimension(150, 33));
		newRezBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		newRezBtn.setFont(new Font("Arial", Font.BOLD, 12));
		newRezBtn.addActionListener(ActionListener ->{
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					
					newReservationEx = new NewReservExternalWindow();
					newReservationEx.setVisible(true);
				}
			});
		});
		buttonPanel.setLayout(null);
		buttonPanel.add(newRezBtn);
		
		final JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(175, 12, 13, 45);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setFocusable(true);
		separator.setForeground(Color.DARK_GRAY);
		separator.setAutoscrolls(true);
		separator.setPreferredSize(new Dimension(10, 20));
		buttonPanel.add(separator);
		
		startdateLbl = new JLabel("Start date : ");
		startdateLbl.setBounds(192, 8, 79, 26);
		buttonPanel.add(startdateLbl);
		
		startDatePicker = new JDateChooser();
		startDatePicker.setCalendar(Calendar.getInstance());
		startDatePicker.setDateFormatString("yyyy-MM-dd");
		startDatePicker.setBounds(275, 8, 155, 26);
		buttonPanel.add(startDatePicker);
		
		endDateLbl = new JLabel("End date : ");
		endDateLbl.setBounds(192, 35, 79, 26);
		buttonPanel.add(endDateLbl);
		
		endDatePicker = new JDateChooser();
		endDatePicker.setCalendar(Calendar.getInstance());
		endDatePicker.setDateFormatString("yyyy-MM-dd");
		endDatePicker.setBounds(275, 35, 155, 26);
		buttonPanel.add(endDatePicker);
		
		referansNoLbl = new JLabel("Referans No : ");
		referansNoLbl.setBounds(442, 6, 94, 26);
		buttonPanel.add(referansNoLbl);
		
		agencyRefLbl = new JLabel("Agency Ref : ");
		agencyRefLbl.setBounds(442, 33, 94, 26);
		buttonPanel.add(agencyRefLbl);
		
		findBtn = new JButton("Find");
		findBtn.setIcon(new ImageIcon(Main_Reservations.class.getResource("/com/coder/hms/icons/main_find.png")));
		findBtn.setPreferredSize(new Dimension(150, 33));
		findBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		findBtn.setFont(new Font("Arial", Font.BOLD, 12));
		findBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		findBtn.setBounds(690, 8, 114, 48);
		findBtn.addActionListener(findRezervation());
		buttonPanel.add(findBtn);
		
		refNoField = new JTextField();
		refNoField.setBounds(535, 6, 143, 26);
		refNoField.setFont(new Font("Arial", Font.BOLD, 13));
		refNoField.setColumns(10);
		buttonPanel.add(refNoField);
		
		agencyRefField = new JTextField();
		agencyRefField.setBounds(535, 33, 143, 26);
		agencyRefField.setFont(new Font("Arial", Font.BOLD, 13));
		agencyRefField.setColumns(10);
		buttonPanel.add(agencyRefField);
		
		model = new DefaultTableModel(rezColsName, 0);
		
		customTCR.setHorizontalAlignment(SwingConstants.CENTER);
		THR.setHorizontalAlignment(SwingConstants.CENTER);
		
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		table.getTableHeader().setDefaultRenderer(THR);
		table.setDefaultRenderer(Object.class, customTCR);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		add(scrollPane, BorderLayout.CENTER);
		
		getReadyForDataFlow();

	}
	
	private float calcFullnessPersentage(int count, int capasite) {
		float persentage = (100 * count) / capasite;
		return persentage;
	}
	
	public synchronized void getReadyForDataFlow() {
		
		roomDaoImpl = new RoomDaoImpl();
		hotelDoaImpl = new HotelDaoImpl();
		customerDaoImpl = new CustomerDaoImpl();
		reservationDaoImpl = new ReservationDaoImpl();
	}
	
	public void populateMainTable() {
		
		model.setRowCount(0);
	
		final Calendar c = Calendar.getInstance();
		reservDate = new Date();
		c.setTime(reservDate);
		c.add(Calendar.DATE, -1);
		
		//get hotel capasite.
		Hotel hotel = hotelDoaImpl.getHotel();
		
		for(int i = 0; i < 31; i++) {
			
			c.add(Calendar.DATE, 1);
			reservDate = c.getTime();
			String today = sdf.format(reservDate);
			
			List<Reservation> reservList = reservationDaoImpl.getReservsByDate(today); 
			List<Reservation> garanteedReservList = reservationDaoImpl.getGaranteedReservs(today);
		 	List<Reservation> reservsAsWaitList = reservationDaoImpl.getReservsAsWaitlist(today);
		 	
			final float fullnesPersentage = calcFullnessPersentage(reservList.size(), hotel.getRoomCapacity());
			final float emptyPersentage = 100f - fullnesPersentage;
			
			
			final Object[] colRowVect = new Object[] {today, hotel.getRoomCapacity(), fullnesPersentage + "%",
									emptyPersentage + "%", garanteedReservList.size(), reservsAsWaitList.size()};
			model.addRow(colRowVect);
		}
		
	}
	
	public ActionListener findRezervation() {
		
		final ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(agencyRefField.getText().length() > 0) {
					
					//search in database
					JOptionPane.showMessageDialog(null, "Agency field not null", 
							JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				}
				
				else if(refNoField.getText().length() > 0) {
					
					//search in database
					JOptionPane.showMessageDialog(null, "Referance Number not null", 
							JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				}
				
				else {
					//get dates from date pickers
					final Date startDate = startDatePicker.getDate();
					final Date endDate = endDatePicker.getDate();
					
					//add to calendar to be able get day of date and compare
					Calendar cs = Calendar.getInstance();
					cs.setTime(startDate);
					Calendar ce = Calendar.getInstance();
					ce.setTime(endDate);
					
						//compare if start date greater than end date
						if(cs.after(ce)) {
							JOptionPane.showMessageDialog(null, "Start date is after end date!", 
											JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
						}
						//or both is same date
						else if(cs.get(Calendar.DAY_OF_YEAR) == ce.get(Calendar.DAY_OF_YEAR)) {
							JOptionPane.showMessageDialog(null, "Start date equals end date!\nPlease be sure you're choose right date.", 
									JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
						}
						//other odds
						else {
														
							
							Reservation reservation = reservationDaoImpl.findReservationByDate(sdf.format(startDate));
							
							List<Customer> customerList = customerDaoImpl.getCustomerByReservId(reservation.getId());
							
							Room room = roomDaoImpl.getRoomByReservId(reservation.getId());
							
							String customerCountry = "";
							String customerName = "";
							String customerSurName = "";
							
							for(Customer cst: customerList) {
								customerCountry = cst.getCountry();
								customerName = cst.getFirstName();
								customerSurName = cst.getLastName();
							}
							
							NewReservExternalWindow reservPane = new NewReservExternalWindow();
							
							reservPane.setRezIdField(reservation.getId());
							reservPane.setNameSurnameField(reservation.getGroupName());
							reservPane.setCheckinDate(reservation.getCheckinDate());
							reservPane.setCheckoutDate(reservation.getCheckoutDate());
							reservPane.setTotalDaysField(reservation.getTotalDays());
							reservPane.setReservNote(reservation.getNote());
							reservPane.setAgency(reservation.getAgency());
							reservPane.setHostType(reservation.getHostType());
							reservPane.setCreditType(reservation.getCreditType());
							reservPane.setReservStatus(reservation.getBookStatus());
							reservPane.setRoomNumber(room.getNumber());
							reservPane.setRoomType(room.getType());
							reservPane.setPersonCountSpinner(room.getPersonCount());
							reservPane.setPriceOfRoom(room.getPrice());
							reservPane.setCurrency(room.getCurrency());
							reservPane.setCustomerCountry(customerCountry);
							
							
							reservPane.setRoomCountTableRows(new Object[]{room.getNumber(), room.getType(),
									room.getPersonCount(), room.getPrice(), room.getCurrency()});
							
							reservPane.setRoomInfoTableRows(new Object[]{room.getNumber(), room.getType(),
									customerName, customerSurName});
							
							reservPane.setVisible(true);
						}
				}

			}
		};
		return listener;
	}
}
