package com.coder.hms.usrinterface;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.coder.hms.utils.ApplicationLogo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentEx extends JDialog {

	/**
	 * 
	 */
	private double value = 0.0;
	private JTextArea textPane;
	private JTextField titleField;
	private JButton btnClear, btnSave;
	private JFormattedTextField priceField;
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox, currencyCmbBox;
	private final ApplicationLogo logoSetter = new ApplicationLogo();
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
	private final String[] PAYMENT_TYPE = {"CASH PAYMENT", "CREDIT CARD", "CITY LEDGER"};
	private final String[] CURRENCY_LIST = { "TURKISH LIRA", "$ DOLLAR", "� EURO", "� POUND"};

	/**
	 * Create the dialog.
	 */
	public PaymentEx() {
		
		// set upper icon for dialog frame
		logoSetter.setApplicationLogoJDialog(this, LOGOPATH);

		getContentPane().setForeground(new Color(255, 99, 71));
		getContentPane().setFocusCycleRoot(true);
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().setFont(new Font("Verdana", Font.BOLD, 12));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setResizable(false);

		this.setTitle("Coder for HMS - [Payment]");

		/* Set default size of frame */
		this.setSize(400, 350);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.decode("#066d95"));
		getContentPane().setLayout(null);
		
		final JLabel lblTitle = new JLabel("Title : ");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTitle.setBounds(56, 31, 100, 20);
		getContentPane().add(lblTitle);
		
		titleField = new JTextField();
		titleField.setBounds(203, 30, 155, 20);
		getContentPane().add(titleField);
		titleField.setColumns(10);
		
		final JLabel lblPaymentName = new JLabel("Type of payment : ");
		lblPaymentName.setForeground(Color.WHITE);
		lblPaymentName.setFont(new Font("Verdana", Font.BOLD, 13));
		lblPaymentName.setBounds(56, 68, 135, 20);
		getContentPane().add(lblPaymentName);
		
		comboBox = new JComboBox<String>(new DefaultComboBoxModel<>(PAYMENT_TYPE));
		comboBox.setBounds(203, 68, 155, 20);
		getContentPane().add(comboBox);
		
		final JLabel lblPrice = new JLabel("Price : ");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Verdana", Font.BOLD, 14));
		lblPrice.setBounds(56, 104, 100, 20);
		getContentPane().add(lblPrice);
		
		final NumberFormat formatter = NumberFormat.getCurrencyInstance();
		formatter.setCurrency(Currency.getInstance(Locale.getDefault()));
		priceField = new JFormattedTextField(formatter);
		priceField.setValue(new Double(value));
		priceField.setBounds(203, 102, 155, 20);
		getContentPane().add(priceField);
		
		final JLabel lblCurrency = new JLabel("Currency : ");
		lblCurrency.setForeground(Color.WHITE);
		lblCurrency.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCurrency.setBounds(56, 142, 100, 20);
		getContentPane().add(lblCurrency);
		
		currencyCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(CURRENCY_LIST));
		currencyCmbBox.setBounds(203, 142, 155, 20);
		currencyCmbBox.setSelectedIndex(0);
		getContentPane().add(currencyCmbBox);
		
		JLabel lblExplain = new JLabel("Explanation");
		lblExplain.setForeground(Color.WHITE);
		lblExplain.setFont(new Font("Verdana", Font.BOLD, 14));
		lblExplain.setHorizontalAlignment(SwingConstants.CENTER);
		lblExplain.setBounds(136, 175, 107, 20);
		getContentPane().add(lblExplain);
		
		textPane = new JTextArea();
		textPane.setBackground(UIManager.getColor("info"));
		textPane.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		textPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textPane.setBounds(42, 200, 316, 50);
		getContentPane().add(textPane);
		
		
		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(108, 261, 277, 49);
		buttonsPanel.setForeground(new Color(95, 158, 160));
		buttonsPanel.setBackground(Color.decode("#066d95"));
		getContentPane().add(buttonsPanel);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnClear = new JButton("CLEAR");
		btnClear.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/coder/hms/icons/login_clear.png")));
		btnClear.setForeground(new Color(220, 20, 60));
		btnClear.setOpaque(true);
		btnClear.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClear.setPreferredSize(new Dimension(110, 40));
		btnClear.setFont(new Font("Verdana", Font.BOLD, 15));
		btnClear.addActionListener(ActionListener -> {
			clear();
		});
		buttonsPanel.add(btnClear);

		btnSave = new JButton("PAY");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//payment processes
			}
		});
		btnSave.setToolTipText("Press ALT + ENTER keys for shortcut");
		btnSave.setSelectedIcon(null);
		btnSave.setIcon(new ImageIcon(PaymentEx.class.getResource("/com/coder/hms/icons/payment_cash.png")));
		btnSave.setForeground(new Color(0, 191, 255));
		btnSave.setOpaque(true);
		btnSave.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSave.setMnemonic(KeyEvent.VK_ENTER);
		btnSave.setPreferredSize(new Dimension(110, 40));
		btnSave.setFont(new Font("Verdana", Font.BOLD, 15));
		buttonsPanel.add(btnSave);

		this.setVisible(true);
	}
	
	private void clear() {
		titleField.setText("");
		comboBox.setSelectedItem(0);
		priceField.setText("");
		currencyCmbBox.setSelectedItem(0);
		textPane.setText("");
	}
}
