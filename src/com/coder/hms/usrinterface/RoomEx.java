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

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.coder.hms.utils.ApplicationLogo;

public class RoomEx extends JDialog {

	/**
	 * 
	 */
	private JTextPane roomNote;
	private JTable postingTable;
	private JTable customerTable;
	private JTextField balanceField;
	private JTextField totalPriceField;
	private static final long serialVersionUID = 1L;
	private final ApplicationLogo logoSetter = new ApplicationLogo();
	private JButton postingBtn, paymentBtn, saveChangesBtn, checkoutBtn;
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
	
	private final String[] customerColnames = new String[]{"FIRSTNAME", "LASTNAME"};
	private final DefaultTableModel customerModel = new DefaultTableModel(customerColnames, 0);
	
	private final String[] postingColnames = new String[]{"TITLE", "PAYMENT TYPE", "PRICE", "CURRENCY", "EXPLANATION"};
	private final DefaultTableModel postingModel = new DefaultTableModel(postingColnames, 0);


	/**
	 * Create the dialog.
	 * @param roomText 
	 */
	public RoomEx(String roomText) {
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
			
			final Dimension wantedRoomFrameSize = new Dimension(computerScreenSize.width - 60, computerScreenSize.height -100);
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
		postingBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		postingBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		postingBtn.setIcon(new ImageIcon(RoomEx.class.getResource("/com/coder/hms/icons/room_posting.png")));
		postingBtn.setBounds(10, 5, 103, 43);
		panel.add(postingBtn);
		
		paymentBtn = new JButton("Payment");
		paymentBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		paymentBtn.setIcon(new ImageIcon(RoomEx.class.getResource("/com/coder/hms/icons/payment_cash.png")));
		paymentBtn.setBounds(123, 5, 103, 43);
		panel.add(paymentBtn);
		
		checkoutBtn = new JButton("Checkout");
		checkoutBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		checkoutBtn.setIcon(new ImageIcon(RoomEx.class.getResource("/com/coder/hms/icons/room_checkout.png")));
		checkoutBtn.setBounds(237, 5, 103, 43);
		panel.add(checkoutBtn);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setSize(new Dimension(5, 20));
		verticalStrut.setMinimumSize(new Dimension(5, 20));
		verticalStrut.setIgnoreRepaint(true);
		verticalStrut.setPreferredSize(new Dimension(5, 20));
		verticalStrut.setBackground(Color.BLACK);
		verticalStrut.setBounds(351, 5, 10, 43);
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
		postableScrollPane.setBackground(new Color(230, 230, 250));
		postableScrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		postTableHolder.add(postableScrollPane, BorderLayout.CENTER);
		
		postingTable = new JTable(postingModel);
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
		saveChangesBtn.setIcon(new ImageIcon(RoomEx.class.getResource("/com/coder/hms/icons/reserv_save.png")));
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
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cusomerTableHolder.add(scrollPane, BorderLayout.NORTH);
		
		customerTable = new JTable(customerModel);
		customerTable.setBackground(new Color(175, 238, 238));
		scrollPane.setViewportView(customerTable);
		
		this.setVisible(true);
	}
}
