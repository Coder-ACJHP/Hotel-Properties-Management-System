/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.usrinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.coder.hms.daoImpl.CustomerDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.Room;
import com.coder.hms.utils.ApplicationLogoSetter;
import com.coder.hms.utils.RoomExternalTableHeaderRenderer;

public class RoomExternalWindow extends JDialog {

	/**
	 * 
	 */
	private JTextPane roomNote;
	private JTable postingTable;
	private JTable customerTable;
	private JTextField balanceField;
	private JTextField totalPriceField;
	private static final long serialVersionUID = 1L;
	private JButton postingBtn, paymentBtn, saveChangesBtn, checkoutBtn;
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
	private final ApplicationLogoSetter logoSetter = new ApplicationLogoSetter();
	private final RoomExternalTableHeaderRenderer THR = new RoomExternalTableHeaderRenderer();
	
	private final String[] customerColnames = new String[]{"INDEX", "FIRSTNAME", "LASTNAME"};
	private final DefaultTableModel customerModel = new DefaultTableModel(customerColnames, 0);
	
	private final String[] postingColnames = new String[]{"TITLE", "PAYMENT TYPE", "PRICE", "CURRENCY", "EXPLANATION"};
	private final DefaultTableModel postingModel = new DefaultTableModel(postingColnames, 0);


	/**
	 * Create the dialog.
	 * @param roomText 
	 */
	public RoomExternalWindow(String roomText) {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setMinimumSize(new Dimension(1000, 700));
		setSize(new Dimension(1121, 700));
		setPreferredSize(new Dimension(1000, 700));
		// set upper icon for dialog frame
		logoSetter.setApplicationLogoJDialog(this, LOGOPATH);

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
		}else {
			
			final Dimension wantedRoomFrameSize = new Dimension(computerScreenSize.width, computerScreenSize.height -90);
			this.setSize(wantedRoomFrameSize);
		}
		
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.decode("#066d95"));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setAutoscrolls(true);
		panel.setPreferredSize(new Dimension(10, 55));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		postingBtn = new JButton("Posting");
		postingBtn.setAutoscrolls(true);
		postingBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		postingBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		postingBtn.setIcon(new ImageIcon(RoomExternalWindow.class.getResource("/com/coder/hms/icons/room_posting.png")));
		postingBtn.setBounds(10, 5, 125, 43);
		panel.add(postingBtn);
		
		paymentBtn = new JButton("Payment");
		paymentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new PaymentExternalWindow();
						
					}
				});
			}
		});
		paymentBtn.setAutoscrolls(true);
		paymentBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		paymentBtn.setIcon(new ImageIcon(RoomExternalWindow.class.getResource("/com/coder/hms/icons/payment_cash.png")));
		paymentBtn.setBounds(142, 5, 125, 43);
		panel.add(paymentBtn);
		
		checkoutBtn = new JButton("Checkout");
		checkoutBtn.setAutoscrolls(true);
		checkoutBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		checkoutBtn.setIcon(new ImageIcon(RoomExternalWindow.class.getResource("/com/coder/hms/icons/room_checkout.png")));
		checkoutBtn.setBounds(274, 5, 125, 43);
		panel.add(checkoutBtn);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setSize(new Dimension(5, 20));
		verticalStrut.setMinimumSize(new Dimension(5, 20));
		verticalStrut.setIgnoreRepaint(true);
		verticalStrut.setPreferredSize(new Dimension(5, 20));
		verticalStrut.setBackground(Color.BLACK);
		verticalStrut.setBounds(406, 5, 10, 43);
		panel.add(verticalStrut);
		
		totalPriceField = new JTextField();
		totalPriceField.setAlignmentY(Component.TOP_ALIGNMENT);
		totalPriceField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		totalPriceField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		totalPriceField.setFont(new Font("Arial", Font.BOLD, 15));
		totalPriceField.setBackground(new Color(240, 128, 128));
		totalPriceField.setEditable(false);
		totalPriceField.setBounds(1024, 28, 86, 26);
		panel.add(totalPriceField);
		totalPriceField.setColumns(10);
		
		JLabel balanceLbl = new JLabel("Balance : ");
		balanceLbl.setAutoscrolls(true);
		balanceLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		balanceLbl.setAlignmentX(Component.RIGHT_ALIGNMENT);
		balanceLbl.setFont(new Font("Arial", Font.BOLD, 13));
		balanceLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		balanceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		balanceLbl.setBounds(907, 5, 114, 20);
		panel.add(balanceLbl);
		
		balanceField = new JTextField();
		balanceField.setAlignmentY(Component.TOP_ALIGNMENT);
		balanceField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		balanceField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		balanceField.setFont(new Font("Arial", Font.BOLD, 15));
		balanceField.setBackground(new Color(102, 205, 170));
		balanceField.setEditable(false);
		balanceField.setBounds(1024, 1, 86, 26);
		panel.add(balanceField);
		balanceField.setColumns(10);
		
		JLabel totalLbl = new JLabel(" Total account : ");
		totalLbl.setAutoscrolls(true);
		totalLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		totalLbl.setAlignmentX(Component.RIGHT_ALIGNMENT);
		totalLbl.setFont(new Font("Arial", Font.BOLD, 13));
		totalLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		totalLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLbl.setBounds(907, 30, 114, 20);
		panel.add(totalLbl);
		
		JPanel postTableHolder = new JPanel();
		postTableHolder.setPreferredSize(new Dimension(10, 300));
		getContentPane().add(postTableHolder, BorderLayout.SOUTH);
		postTableHolder.setLayout(new BorderLayout(0, 0));
		
		JScrollPane postableScrollPane = new JScrollPane();
		postableScrollPane.setBackground(Color.decode("#e1fcff"));
		postableScrollPane.setBackground(new Color(230, 230, 250));
		postableScrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		postTableHolder.add(postableScrollPane, BorderLayout.CENTER);
		
		postingTable = new JTable(postingModel);
		postingTable.getTableHeader().setDefaultRenderer(THR);
		postingTable.setBackground(new Color(255, 182, 193));
		postableScrollPane.setViewportView(postingTable);
		postTableHolder.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{postingTable, postableScrollPane}));
		
		JPanel reservInfoHolder = new JPanel();
		reservInfoHolder.setAlignmentY(Component.TOP_ALIGNMENT);
		reservInfoHolder.setAlignmentX(Component.RIGHT_ALIGNMENT);
		reservInfoHolder.setAutoscrolls(true);
		reservInfoHolder.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		reservInfoHolder.setBackground(new Color(176, 196, 222));
		reservInfoHolder.setPreferredSize(new Dimension(220, 10));
		getContentPane().add(reservInfoHolder, BorderLayout.EAST);
		reservInfoHolder.setLayout(null);
		
		JLabel lblReservatonInfo = new JLabel("RESERVATION INFO");
		lblReservatonInfo.setFont(new Font("Verdana", Font.BOLD, 14));
		lblReservatonInfo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReservatonInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservatonInfo.setBounds(2, 4, 216, 29);
		reservInfoHolder.add(lblReservatonInfo);
		
		saveChangesBtn = new JButton("SAVE CHANGES");
		saveChangesBtn.setIcon(new ImageIcon(RoomExternalWindow.class.getResource("/com/coder/hms/icons/reserv_save.png")));
		saveChangesBtn.setAutoscrolls(true);
		saveChangesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//SAVE CHANGES
			}
		});
		saveChangesBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		saveChangesBtn.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		saveChangesBtn.setBounds(2, 291, 216, 29);
		reservInfoHolder.add(saveChangesBtn);
		
		JPanel cusomerTableHolder = new JPanel();
		cusomerTableHolder.setBackground(Color.decode("#066d95"));
		cusomerTableHolder.setAutoscrolls(true);
		getContentPane().add(cusomerTableHolder, BorderLayout.CENTER);
		cusomerTableHolder.setLayout(new BorderLayout(0, 0));
		
		roomNote = new JTextPane();
		roomNote.setToolTipText("Write some note.");
		roomNote.setMargin(new Insets(5, 5, 5, 5));
		roomNote.setPreferredSize(new Dimension(0, 45));
		roomNote.setBackground(new Color(255, 255, 224));
		roomNote.setAlignmentX(Component.LEFT_ALIGNMENT);
		roomNote.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		roomNote.setFont(new Font("Arial", Font.BOLD, 15));
		roomNote.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cusomerTableHolder.add(roomNote, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.decode("#e1fcff"));
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cusomerTableHolder.add(scrollPane, BorderLayout.NORTH);
		
		
		populateCustomerTable(roomText, customerModel);
		
		customerTable = new JTable(customerModel);
		customerTable.setCellSelectionEnabled(false);
		customerTable.getTableHeader().setDefaultRenderer(THR);
		customerTable.addMouseListener(openCustomerListener());
		scrollPane.setViewportView(customerTable);
		
		this.setAlwaysOnTop(false);
		this.setVisible(true);
	}

	public void populateCustomerTable(String roomText, DefaultTableModel model) {
	
		//clean table model
		model.setRowCount(0);
		
		//import all customers from database
		final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
		final Room foundedRoom = roomDaoImpl.getRoomByRoomNumber(roomText);
		
		final CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		final List<Customer> custmerList = customerDaoImpl.getCustomerByReservId(foundedRoom.getReservationId());
		
		int index = 0;
		//populate table model with loop
		for(Customer cst: custmerList) {
			index++;
			final Object[] rowData = new Object[]{index, cst.getFirstName(), cst.getLastName()};
			model.addRow(rowData);
		}
	}
	
	private MouseListener openCustomerListener() {
		final MouseAdapter adapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(e.getClickCount() == 2) {
//					final int rowIndex = customerTable.getSelectedRow();
//					final String name = customerTable.getValueAt(rowIndex, 1).toString();
//					final String lastname = customerTable.getValueAt(rowIndex, 2).toString();
					
					/* 1- Create Customer detail window and populate it.
					 * 2- Show customer if changed any detail saveOrUpdate it.
					*/
					final JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "PLEASE BE PATIENT\nRequested page is under development phase",
							JOptionPane.MESSAGE_PROPERTY, JOptionPane.INFORMATION_MESSAGE);
				}
				
				super.mousePressed(e);
			}
		};
		return adapter;
	}
}







