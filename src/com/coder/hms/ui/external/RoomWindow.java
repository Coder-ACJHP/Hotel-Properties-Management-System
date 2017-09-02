/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.coder.hms.beans.SessionBean;
import com.coder.hms.daoImpl.CustomerDaoImpl;
import com.coder.hms.daoImpl.HotelSystemStatusImpl;
import com.coder.hms.daoImpl.PaymentDaoImpl;
import com.coder.hms.daoImpl.PostingDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.HotelSystemStatus;
import com.coder.hms.entities.Payment;
import com.coder.hms.entities.Posting;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.ui.main.Main_AllRooms;
import com.coder.hms.utils.CustomersTableRenderer;
import com.coder.hms.utils.LoggingEngine;
import com.coder.hms.utils.PayPostTableCellRenderer;
import com.coder.hms.utils.RoomExternalTableHeaderRenderer;
import com.toedter.calendar.JDateChooser;

public class RoomWindow extends JDialog {

	/**
	 * 
	 */
	private double debtVal = 0.0;
	private JTextPane roomNote;
	private Customer theCustomer;
	private NumberFormat formatter;
	private static String roomNumber;
	private Reservation reservation;
	private static LoggingEngine loggingEngine;
	private JTable payPostTable, customerTable;
	private HotelSystemStatus hotelSystemStatus;
	private JDateChooser checkinDate, checkoutDate;
	private static final long serialVersionUID = 1L;
	final DialogFrame dialogFrame = new DialogFrame();
	private final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
	private JScrollPane postableScrollPane, cstTableScrollPane;
	private final CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
	final ReservationDaoImpl reservationDaoImpl = new ReservationDaoImpl();
	private static SessionBean sessionBean = SessionBean.getSESSION_BEAN();
	private final PaymentWindow payWin = new PaymentWindow();
	private final PostingWindow postWin = new PostingWindow();
	private final HotelSystemStatusImpl systemStatusImpl = new HotelSystemStatusImpl();
	private final static CustomersTableRenderer customerTableRenderer = new CustomersTableRenderer();
	private JButton postingBtn, paymentBtn, saveChangesBtn, checkoutBtn;
	final static CustomerDetailWindow custWindow = new CustomerDetailWindow();
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
	private final PayPostTableCellRenderer payPostRenderer = new PayPostTableCellRenderer();
	private JFormattedTextField priceField, totalPriceField, balanceField, remainDebtField;
	private final RoomExternalTableHeaderRenderer THR = new RoomExternalTableHeaderRenderer();

	private final String[] customerColnames = new String[] { "INDEX", "FIRSTNAME", "LASTNAME" };
	private final DefaultTableModel customerModel = new DefaultTableModel(customerColnames, 0);

	private final String[] postPayColnames = new String[] { "DOC. NO", "TYPE", "TITLE", "PRICE", "CURRENCY",
			"EXPLANATION", "DATE TIME" };
	private final DefaultTableModel postPayModel = new DefaultTableModel(postPayColnames, 0);
	private JTextField IdField, groupNameField, agencyField, currencyField, creditField, hostTypeField, totalDaysField;
	private JPanel upperPanel, buttonsPanel, pricePanel, reservInfoHolder, cusomerTableHolder, noteHolder, postTableHolder;
	private JLabel balanceLbl, totalLbl, lblReamainingDebt, lblReservatonInfo, IdLbl, lblNewLabel, lblCheckoutDate, lblGroup, 
	lblAgency, lblPrice, lblCreditType, lblHostType, lblTotalDays, lblAddSomeNote;

	/**
	 * Create the dialog.
	 * 
	 * @param roomText
	 */
	public RoomWindow(String roomText) {

		RoomWindow.roomNumber = roomText;

		loggingEngine = LoggingEngine.getInstance();
		hotelSystemStatus = systemStatusImpl.getSystemStatus();
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setMinimumSize(new Dimension(1000, 700));
		setSize(new Dimension(1184, 700));
		setPreferredSize(new Dimension(1000, 700));
		// set upper icon for dialog frame
		this.setIconImage(Toolkit.getDefaultToolkit().
				getImage(getClass().getResource(LOGOPATH)));

		getContentPane().setForeground(new Color(255, 99, 71));
		getContentPane().setFocusCycleRoot(true);
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().setFont(new Font("Verdana", Font.BOLD, 12));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setResizable(false);

		this.setTitle("Coder for HMS - [RoomEx] : " + roomText);

		/* Set default size of frame */
		final Dimension computerScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		String opSystem = System.getProperty("os.name").toLowerCase();

		if (opSystem.contains("windows") || opSystem.contains("nux")) {

			this.setSize(computerScreenSize);
		} else {

			final Dimension wantedRoomFrameSize = new Dimension(computerScreenSize.width,
					computerScreenSize.height - 90);
			this.setSize(wantedRoomFrameSize);
		}

		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.decode("#066d95"));
		getContentPane().setLayout(new BorderLayout(0, 0));

		formatter = NumberFormat.getCurrencyInstance();
		formatter.setCurrency(Currency.getInstance(Locale.getDefault()));

		upperPanel = new JPanel();
		upperPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		upperPanel.setAutoscrolls(true);
		upperPanel.setPreferredSize(new Dimension(300, 55));
		upperPanel.setLayout(new BorderLayout());
		getContentPane().add(upperPanel, BorderLayout.NORTH);

		buttonsPanel = new JPanel();
		buttonsPanel.setBorder(null);
		buttonsPanel.setAutoscrolls(true);
		buttonsPanel.setPreferredSize(new Dimension(500, 54));
		buttonsPanel.setLayout(null);
		upperPanel.add(buttonsPanel, BorderLayout.WEST);
		
		postingBtn = new JButton("Posting");
		postingBtn.addActionListener(postingAction());
		postingBtn.setAutoscrolls(true);
		postingBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		postingBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		postingBtn.setIcon(new ImageIcon(RoomWindow.class.getResource("/com/coder/hms/icons/room_posting.png")));
		postingBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		postingBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		postingBtn.setBounds(10, 3, 125, 43);
		buttonsPanel.add(postingBtn);

		paymentBtn = new JButton("Payment");
		paymentBtn.addActionListener(paymentListener());
		paymentBtn.setAutoscrolls(true);
		paymentBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		paymentBtn.setIcon(new ImageIcon(RoomWindow.class.getResource("/com/coder/hms/icons/payment_cash.png")));
		paymentBtn.setBounds(142, 3, 125, 43);
		paymentBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		paymentBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonsPanel.add(paymentBtn);

		checkoutBtn = new JButton("Checkout");
		checkoutBtn.setAutoscrolls(true);
		checkoutBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		checkoutBtn.setIcon(new ImageIcon(RoomWindow.class.getResource("/com/coder/hms/icons/room_checkout.png")));
		checkoutBtn.setBounds(274, 3, 125, 43);
		checkoutBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		checkoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkoutBtn.addActionListener(checkoutListener());
		buttonsPanel.add(checkoutBtn);

		final Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setSize(new Dimension(5, 20));
		verticalStrut.setMinimumSize(new Dimension(5, 20));
		verticalStrut.setIgnoreRepaint(true);
		verticalStrut.setPreferredSize(new Dimension(5, 20));
		verticalStrut.setBackground(Color.BLACK);
		verticalStrut.setBounds(406, 5, 10, 43);
		buttonsPanel.add(verticalStrut);

		pricePanel = new JPanel();
		pricePanel.setBorder(null);
		pricePanel.setAutoscrolls(true);
		pricePanel.setPreferredSize(new Dimension(500, 54));
		pricePanel.setLayout(null);
		upperPanel.add(pricePanel, BorderLayout.EAST);
		
		totalPriceField = new JFormattedTextField(formatter);
		totalPriceField.setAlignmentY(Component.TOP_ALIGNMENT);
		totalPriceField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		totalPriceField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		totalPriceField.setFont(new Font("Arial", Font.BOLD, 15));
		totalPriceField.setBackground(new Color(240, 128, 128));
		totalPriceField.setEditable(false);
		totalPriceField.setBounds(294, 25, 86, 26);
		pricePanel.add(totalPriceField);
		totalPriceField.setColumns(10);

		balanceLbl = new JLabel("Balance : ");
		balanceLbl.setAutoscrolls(true);
		balanceLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		balanceLbl.setAlignmentX(Component.RIGHT_ALIGNMENT);
		balanceLbl.setFont(new Font("Arial", Font.BOLD, 13));
		balanceLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		balanceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		balanceLbl.setBounds(168, 4, 114, 20);
		pricePanel.add(balanceLbl);

		balanceField = new JFormattedTextField(formatter);
		balanceField.setAlignmentY(Component.TOP_ALIGNMENT);
		balanceField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		balanceField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		balanceField.setFont(new Font("Arial", Font.BOLD, 15));
		balanceField.setBackground(new Color(102, 205, 170));
		balanceField.setEditable(false);
		balanceField.setBounds(294, 0, 86, 26);
		balanceField.setColumns(10);
		pricePanel.add(balanceField);

		totalLbl = new JLabel(" Total account : ");
		totalLbl.setAutoscrolls(true);
		totalLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		totalLbl.setAlignmentX(Component.RIGHT_ALIGNMENT);
		totalLbl.setFont(new Font("Arial", Font.BOLD, 13));
		totalLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		totalLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLbl.setBounds(168, 25, 114, 20);
		pricePanel.add(totalLbl);

		lblReamainingDebt = new JLabel("Remaining debt");
		lblReamainingDebt.setAutoscrolls(true);
		lblReamainingDebt.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblReamainingDebt.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReamainingDebt.setFont(new Font("Arial", Font.BOLD, 13));
		lblReamainingDebt.setHorizontalAlignment(SwingConstants.CENTER);
		lblReamainingDebt.setBounds(384, 3, 114, 16);
		pricePanel.add(lblReamainingDebt);

		remainDebtField = new JFormattedTextField(formatter);
		remainDebtField.setAlignmentY(Component.TOP_ALIGNMENT);
		remainDebtField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		remainDebtField.setFont(new Font("Arial", Font.BOLD, 15));
		remainDebtField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		remainDebtField.setBackground(Color.ORANGE);
		remainDebtField.setBounds(400, 20, 86, 26);
		remainDebtField.setEditable(false);
		remainDebtField.setValue(debtVal);
		pricePanel.add(remainDebtField);

		reservInfoHolder = new JPanel();
		reservInfoHolder.setAlignmentY(Component.TOP_ALIGNMENT);
		reservInfoHolder.setAlignmentX(Component.RIGHT_ALIGNMENT);
		reservInfoHolder.setAutoscrolls(true);
		reservInfoHolder.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		reservInfoHolder.setBackground(new Color(176, 196, 222));
		reservInfoHolder.setPreferredSize(new Dimension(250, 10));
		getContentPane().add(reservInfoHolder, BorderLayout.EAST);
		reservInfoHolder.setLayout(null);

		lblReservatonInfo = new JLabel("RESERVATION INFO");
		lblReservatonInfo.setFont(new Font("Verdana", Font.BOLD, 14));
		lblReservatonInfo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReservatonInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservatonInfo.setBounds(2, 4, 248, 29);
		reservInfoHolder.add(lblReservatonInfo);

		saveChangesBtn = new JButton("SAVE CHANGES");
		saveChangesBtn.setIcon(new ImageIcon(RoomWindow.class.getResource("/com/coder/hms/icons/reserv_save.png")));
		saveChangesBtn.setAutoscrolls(true);
		saveChangesBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		saveChangesBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveChangesBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		saveChangesBtn.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		saveChangesBtn.setBounds(16, 288, 218, 29);
		saveChangesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				changeReservationDate();
			}
		});
		reservInfoHolder.add(saveChangesBtn);

		IdLbl = new JLabel("Id : ");
		IdLbl.setBounds(12, 42, 88, 14);
		reservInfoHolder.add(IdLbl);

		IdField = new JTextField();
		IdField.setEditable(false);
		IdField.setBounds(101, 36, 86, 20);
		reservInfoHolder.add(IdField);
		IdField.setColumns(10);

		lblNewLabel = new JLabel("Checkin : ");
		lblNewLabel.setBounds(12, 205, 88, 14);
		reservInfoHolder.add(lblNewLabel);

		checkinDate = new JDateChooser();
		checkinDate.setEnabled(false);
		checkinDate.setDateFormatString("yyyy-MM-dd");
		checkinDate.setBounds(101, 202, 138, 20);
		reservInfoHolder.add(checkinDate);

		lblCheckoutDate = new JLabel("Checkout : ");
		lblCheckoutDate.setBounds(12, 233, 88, 14);
		reservInfoHolder.add(lblCheckoutDate);

		checkoutDate = new JDateChooser();
		checkoutDate.setDateFormatString("yyyy-MM-dd");
		checkoutDate.setBounds(101, 230, 138, 20);
		reservInfoHolder.add(checkoutDate);

		lblGroup = new JLabel("Group : ");
		lblGroup.setBounds(12, 65, 88, 14);
		reservInfoHolder.add(lblGroup);

		groupNameField = new JTextField();
		groupNameField.setEditable(false);
		groupNameField.setBounds(101, 62, 138, 20);
		reservInfoHolder.add(groupNameField);
		groupNameField.setColumns(10);

		lblAgency = new JLabel("Agency : ");
		lblAgency.setBounds(12, 93, 88, 14);
		reservInfoHolder.add(lblAgency);

		agencyField = new JTextField();
		agencyField.setEditable(false);
		agencyField.setBounds(101, 90, 138, 20);
		reservInfoHolder.add(agencyField);
		agencyField.setColumns(10);

		lblPrice = new JLabel("Price : ");
		lblPrice.setBounds(12, 121, 88, 14);
		reservInfoHolder.add(lblPrice);

		final NumberFormat format = NumberFormat.getInstance();
		format.setMinimumIntegerDigits(2);
		priceField = new JFormattedTextField(format);
		priceField.setEditable(true);
		priceField.setBounds(101, 118, 64, 20);
		reservInfoHolder.add(priceField);

		currencyField = new JTextField();
		currencyField.setEditable(false);
		currencyField.setBounds(166, 118, 71, 20);
		reservInfoHolder.add(currencyField);
		currencyField.setColumns(10);

		lblCreditType = new JLabel("Credit type : ");
		lblCreditType.setBounds(12, 150, 88, 14);
		reservInfoHolder.add(lblCreditType);

		creditField = new JTextField();
		creditField.setBounds(101, 146, 138, 20);
		reservInfoHolder.add(creditField);
		creditField.setColumns(10);

		lblHostType = new JLabel("Host type : ");
		lblHostType.setBounds(12, 177, 88, 14);
		reservInfoHolder.add(lblHostType);

		hostTypeField = new JTextField();
		hostTypeField.setBounds(101, 174, 138, 20);
		reservInfoHolder.add(hostTypeField);
		hostTypeField.setColumns(10);

		lblTotalDays = new JLabel("Total days : ");
		lblTotalDays.setBounds(12, 260, 88, 14);
		reservInfoHolder.add(lblTotalDays);

		totalDaysField = new JTextField();
		totalDaysField.setEditable(false);
		totalDaysField.setBounds(101, 257, 86, 20);
		reservInfoHolder.add(totalDaysField);
		totalDaysField.setColumns(10);

		cusomerTableHolder = new JPanel();
		cusomerTableHolder.setBackground(Color.decode("#066d95"));
		cusomerTableHolder.setAutoscrolls(true);
		getContentPane().add(cusomerTableHolder, BorderLayout.CENTER);
		cusomerTableHolder.setLayout(new BorderLayout(0, 0));

		noteHolder = new JPanel();
		noteHolder.setBorder(new LineBorder(new Color(0, 0, 0)));
		noteHolder.setAutoscrolls(true);
		noteHolder.setLayout(new BorderLayout());
		cusomerTableHolder.add(noteHolder, BorderLayout.SOUTH);
		
		roomNote = new JTextPane();
		roomNote.setLocale(new Locale("tr", "TR"));
		roomNote.setToolTipText("Write some note.");
		roomNote.setMargin(new Insets(5, 5, 5, 5));
		roomNote.setPreferredSize(new Dimension(700, 45));
		roomNote.setBackground(new Color(255, 255, 224));
		roomNote.setAlignmentX(Component.LEFT_ALIGNMENT);
		roomNote.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		roomNote.setFont(new Font("Arial", Font.BOLD, 15));
		roomNote.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		noteHolder.add(roomNote, BorderLayout.CENTER);
		
		lblAddSomeNote = new JLabel("Note : ");
		lblAddSomeNote.setIcon(new ImageIcon(RoomWindow.class.getResource("/com/coder/hms/icons/room_note.png")));
		lblAddSomeNote.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblAddSomeNote.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddSomeNote.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblAddSomeNote.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblAddSomeNote.setBackground(new Color(176, 196, 222));
		lblAddSomeNote.setAutoscrolls(true);
		lblAddSomeNote.setOpaque(true);
		noteHolder.add(lblAddSomeNote, BorderLayout.WEST);

		cstTableScrollPane = new JScrollPane();
		cstTableScrollPane.setBackground(Color.decode("#e1fcff"));
		cstTableScrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		cstTableScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		cstTableScrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cusomerTableHolder.add(cstTableScrollPane, BorderLayout.NORTH);

		populateCustomerTable(roomText, customerModel);

		customerTable = new JTable(customerModel);
		customerTable.setRowHeight(20);
		customerTable.setDefaultRenderer(Object.class, customerTableRenderer);
		customerTable.setCellSelectionEnabled(false);
		customerTable.setColumnSelectionAllowed(false);
		customerTable.setRowSelectionAllowed(true);
		customerTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		customerTable.getTableHeader().setDefaultRenderer(THR);
		customerTable.addMouseListener(openCustomerListener());
		cstTableScrollPane.setViewportView(customerTable);

		postTableHolder = new JPanel();
		postTableHolder.setPreferredSize(new Dimension(10, 300));
		getContentPane().add(postTableHolder, BorderLayout.SOUTH);
		postTableHolder.setLayout(new BorderLayout(0, 0));

		postableScrollPane = new JScrollPane();
		postableScrollPane.setBackground(Color.decode("#e1fcff"));
		postableScrollPane.setBackground(new Color(230, 230, 250));
		postableScrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		postTableHolder.add(postableScrollPane, BorderLayout.CENTER);

		populatePostPayTable(postPayModel);

		final JPopupMenu popupMenu = new JPopupMenu();
		final JMenuItem menuItem = new JMenuItem("Delete");
		menuItem.setIcon(new ImageIcon(Main_AllRooms.class.getResource("/com/coder/hms/icons/room_checkout.png")));
		menuItem.addActionListener(ActionListener -> {
			
			deleteRowsListener();
		});
		popupMenu.add(menuItem);

		payPostTable = new JTable(postPayModel);
		payPostTable.setRowHeight(20);
		payPostTable.setDefaultRenderer(Object.class, payPostRenderer);
		payPostTable.setCellSelectionEnabled(false);
		payPostTable.setRowSelectionAllowed(true);
		payPostTable.setAutoCreateRowSorter(true);
		payPostTable.getTableHeader().setDefaultRenderer(THR);
		postableScrollPane.setViewportView(payPostTable);
		payPostTable.setComponentPopupMenu(popupMenu);
		populateReservationDetail();

		loggingEngine.setMessage("User is : " +sessionBean.getNickName());
		
		custWindow.setActionListener(saveChanges());
		this.setVisible(true);
	}
	
	private ActionListener paymentListener() {
		final ActionListener theListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						payWin.setReadyPaymentWindow(RoomWindow.roomNumber);
						if (payWin.getPaymentStatus()) {
							loggingEngine.setMessage("Adding payment to room : " + roomNumber);
							populateReservationDetail();
						}
						populatePostPayTable(postPayModel);
					}
				});
				
			}
		};
		return theListener;
	}

	private ActionListener postingAction() {
		final ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						postWin.setReadyPaymentWindow(RoomWindow.roomNumber);
						if (postWin.getPostingStatus()) {
							loggingEngine.setMessage("Posting to room : " + roomNumber);
							populateReservationDetail();
						}
						populatePostPayTable(postPayModel);
					}
				});
				
			}
		};
		return listener;
	}

	private ActionListener checkoutListener() {
		final ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				final Room checkingRoom = roomDaoImpl.getRoomByRoomNumber(RoomWindow.roomNumber);
				
				dialogFrame.setMessage("Are you sure to checkout this room?");
				dialogFrame.setVisible(true);
				dialogFrame.btnYes.addActionListener(ActionListener -> {

				final double price = Math.ceil(checkingRoom.getRemainingDebt());
				
					if (price == 0) {

						roomDaoImpl.setRoomCheckedOut(RoomWindow.roomNumber);
						dialogFrame.dispose();
						dispose();

					} else {
						roomNote.setFont(new Font("Arial", Font.BOLD, 27));
						roomNote.setForeground(Color.RED);
						roomNote.setText("Room balance and Remaining debt must be zero!");
						roomNote.revalidate();
						roomNote.repaint();
						dialogFrame.dispose();
						return;
					}
					
					loggingEngine.setMessage("The room checkedout is : " + roomNumber);
				});
				
				dialogFrame.btnNo.addActionListener(ActionListener -> {
					loggingEngine.setMessage("Checkedout is cancelled : ");
					dialogFrame.dispose();
					return;
				});
			}
		};
		return listener;
	}

	private void deleteRowsListener() {

		final int rowIndex = payPostTable.getSelectedRow();

		final String theId = payPostTable.getValueAt(rowIndex, 0).toString();
		final String type = payPostTable.getValueAt(rowIndex, 1).toString();
		final String amount = payPostTable.getValueAt(rowIndex, 3).toString();

		final Room theRoom = roomDaoImpl.getRoomByRoomNumber(roomNumber);

		loggingEngine.setMessage("Deleting posting or payment from room : " + roomNumber);
		loggingEngine.setMessage("Selected object details : " + "Id : " + theId + "Type : " + type + "Amount : " + amount);
		
		double finalBalance = 0.0;

		if (type.equalsIgnoreCase("System")) {

			final PostingDaoImpl postImpl = new PostingDaoImpl();
			final boolean result = postImpl.deletePosting(Long.parseLong(theId));

			if (result) {

				finalBalance = Double.parseDouble(theRoom.getTotalPrice()) - Double.parseDouble(amount);
				theRoom.setTotalPrice(finalBalance + "");
				totalPriceField.setValue(finalBalance);
				totalPriceField.revalidate();
				totalPriceField.repaint();
				
				loggingEngine.setMessage("Posting is deleted.");
			}
		}

		else if (type.equalsIgnoreCase("CASH PAYMENT") || type.equalsIgnoreCase("CREDIT CARD")) {

			final PaymentDaoImpl payImpl = new PaymentDaoImpl();
			final boolean result = payImpl.deletePayment(Long.parseLong(theId));

			if (result) {

				finalBalance = Double.parseDouble(theRoom.getBalance()) - Double.parseDouble(amount);
				theRoom.setBalance(finalBalance + "");
				balanceField.setValue(finalBalance);
				balanceField.revalidate();
				balanceField.repaint();
				
				loggingEngine.setMessage("Payment is deleted.");
			}
		}

		debtVal = Double.parseDouble(theRoom.getTotalPrice()) - Double.parseDouble(theRoom.getBalance());
		remainDebtField.setValue(debtVal);
		remainDebtField.revalidate();
		remainDebtField.repaint();

		theRoom.setRemainingDebt(debtVal);
		roomDaoImpl.updateRoom(theRoom);

		populatePostPayTable(postPayModel);

	}

	private void populateReservationDetail() {

		final Room theRoom = roomDaoImpl.getRoomByRoomNumber(roomNumber);
		reservation = reservationDaoImpl.findReservationById(theRoom.getReservationId());

		IdField.setText(reservation.getId() + "");

		groupNameField.setText(reservation.getGroupName());

		agencyField.setText(reservation.getAgency());

		priceField.setValue(theRoom.getPrice());

		if (theRoom.getCurrency().equalsIgnoreCase("TURKISH LIRA")) {
			currencyField.setText("TL");
		}

		else {
			currencyField.setText(theRoom.getCurrency());
		}

		creditField.setText(reservation.getCreditType());

		hostTypeField.setText(reservation.getHostType());

		LocalDate localDate = LocalDate.parse(reservation.getCheckinDate());
		Date date = java.sql.Date.valueOf(localDate);
		checkinDate.setDate(date);

		localDate = LocalDate.parse(reservation.getCheckoutDate());
		date = java.sql.Date.valueOf(localDate);
		checkoutDate.setDate(date);

		totalDaysField.setText(reservation.getTotalDays() + "");

		final double totalPrice = Double.parseDouble(theRoom.getTotalPrice());
		totalPriceField.setValue(totalPrice);

		final double roombalance = Double.parseDouble(theRoom.getBalance());
		balanceField.setValue(roombalance);

		final DecimalFormat decimalFormat = new DecimalFormat("#.####");
		remainDebtField.setValue(totalPrice - roombalance);
		theRoom.setRemainingDebt(Double.valueOf(decimalFormat.format(totalPrice - roombalance)));
		
		roomNote.setText(reservation.getNote());
		roomDaoImpl.updateRoom(theRoom);
	}

	private void changeReservationDate() {

		loggingEngine.setMessage("Updating reservation...");
		
		LocalDate lic = LocalDate.parse(reservation.getCheckinDate());
		Date oldDate = java.sql.Date.valueOf(lic);

		LocalDate loc = LocalDate.parse(reservation.getCheckoutDate());
		Date updateDate = java.sql.Date.valueOf(loc);

		int totalDayResult = (int) ((updateDate.getTime() - oldDate.getTime()) / (1000 * 60 * 60 * 24));

		reservation.setTotalDays(Math.abs(totalDayResult));
		String resultDate = new SimpleDateFormat("yyyy-MM-dd").format(checkoutDate.getDate());
		reservation.setCheckoutDate(resultDate);

		final double priceVal = Double.valueOf(priceField.getValue().toString());

		final Room foundedRoom = roomDaoImpl.getRoomByRoomNumber(reservation.getTheNumber());
		foundedRoom.setPrice(priceVal);
		roomDaoImpl.updateRoom(foundedRoom);

		if (!roomNote.getText().isEmpty())
			reservation.setNote(roomNote.getText());

		reservationDaoImpl.updateReservation(reservation);
		
		loggingEngine.setMessage("Updated reservation details : " + reservation.toString());
	}

	public void populateCustomerTable(String roomText, DefaultTableModel model) {

		// clean table model
		model.setRowCount(0);

		// import all customers from database
		final Room foundedRoom = roomDaoImpl.getRoomByRoomNumber(roomText);

		final CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		final List<Customer> custmerList = customerDaoImpl.getCustomerByReservId(foundedRoom.getReservationId());

		int index = 0;
		// populate table model with loop
		for (Customer cst : custmerList) {
			index++;
			final Object[] rowData = new Object[] { index, cst.getFirstName(), cst.getLastName() };
			model.addRow(rowData);
		}
	}

	private MouseListener openCustomerListener() {
		final MouseAdapter adapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (e.getClickCount() == 2) {

					final int rowIndex = customerTable.getSelectedRow();
					final String name = customerTable.getValueAt(rowIndex, 1).toString();
					final String lastname = customerTable.getValueAt(rowIndex, 2).toString();

					theCustomer = customerDaoImpl.findCustomerByName(name, lastname);

					custWindow.setId(theCustomer.getCustomerId() + "");
					custWindow.setName(theCustomer.getFirstName());
					custWindow.setSurname(theCustomer.getLastName());
					custWindow.setDocument(theCustomer.getDocument());
					custWindow.setDocNo(theCustomer.getDocumentNo());
					custWindow.setCountry(theCustomer.getCountry());
					custWindow.setDateOfBirth(theCustomer.getDateOfBirth());
					custWindow.setEmail(theCustomer.getEmail());
					custWindow.setFatherName(theCustomer.getFatherName());
					custWindow.setMotherName(theCustomer.getMotherName());
					custWindow.setGender(theCustomer.getGender());
					custWindow.setPhone(theCustomer.getPhone());
					custWindow.setMariaggeStaus(theCustomer.getMaritalStatus());
					custWindow.setReservationId(theCustomer.getReservationId() + "");
					custWindow.setInfoMessage(" ");
					
					custWindow.setVisible(true);
					
					loggingEngine.setMessage("Displaying customer...");
					loggingEngine.setMessage("Displayed customer details : " + theCustomer.toString());
				}

				super.mousePressed(e);
			}
		};
		return adapter;
	}

	// save all changed properties in customer table
	private ActionListener saveChanges() {
		final ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				theCustomer.setCountry(custWindow.getCountry());
				theCustomer.setFirstName(custWindow.getName());
				theCustomer.setLastName(custWindow.getSurname());
				theCustomer.setDocument(custWindow.getDocument());
				theCustomer.setDocumentNo(custWindow.getDocNo());
				theCustomer.setCountry(custWindow.getCountry());
				theCustomer.setDateOfBirth(custWindow.getDateOfBirth());
				theCustomer.setEmail(custWindow.getEmail());
				theCustomer.setFatherName(custWindow.getFatherName());
				theCustomer.setMotherName(custWindow.getMotherName());
				theCustomer.setGender(custWindow.getGender());
				theCustomer.setPhone(custWindow.getPhone());
				theCustomer.setMaritalStatus(custWindow.getMariageStatus());
				theCustomer.setReservationId(Long.parseLong(custWindow.getReservationId()));

				boolean success = customerDaoImpl.update(theCustomer);

				if (success) {
					
					custWindow.setInfoMessage("<html>SUCCESSFULLY ACCOMPLISHED</html>");
					custWindow.setInfoLabelColor(Color.decode("#00FF00"));
					loggingEngine.setMessage("Customer details updated : " + theCustomer.toString());
					success = false;
				} else {
					custWindow.setInfoMessage("<html>OPERTION IS FAILED!</html>");
					custWindow.setInfoLabelColor(Color.decode("#cd2626"));
				}
				
				theCustomer = null;
			}
		};
		return listener;
	}

	private void populatePostPayTable(DefaultTableModel model) {

		// import all customers from database
		final PostingDaoImpl postingDaoImpl = new PostingDaoImpl();
		List<Posting> postingList = postingDaoImpl.getAllPostingsByRoomNumber(roomNumber, hotelSystemStatus.getDateTime().toString());

		final PaymentDaoImpl paymentDaoImpl = new PaymentDaoImpl();
		List<Payment> paymentlist = paymentDaoImpl.getAllPaymentsByRoomNumber(roomNumber, hotelSystemStatus.getDateTime().toString());

		// clean table model
		model.setRowCount(0);

		for (Posting pos : postingList) {

			model.addRow(new Object[] { pos.getId(), pos.getPostType(), pos.getTitle(), pos.getPrice(),
					pos.getCurrency(), pos.getExplanation(), pos.getDateTime() });
		}

		for (Payment pay : paymentlist) {

			model.addRow(new Object[] { pay.getId(), pay.getPaymentType(), pay.getTitle(), pay.getPrice(),
					pay.getCurrency(), pay.getExplanation(), pay.getDateTime() });
		}
	}
}
