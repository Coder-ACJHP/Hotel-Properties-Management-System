/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.coder.hms.daoImpl.PaymentDaoImpl;
import com.coder.hms.utils.BlockadeTableHeaderRenderer;

public class Main_CashDesk extends JPanel {

	/**
	 * 
	 */
	private JTable table;
	private JPanel centerPanel;
	private JScrollPane scrollPane;
	private static final long serialVersionUID = 1L;
	private double tlCashVal, tlCreditVal, tlCityLedgerVal;
	private double dlCashVal, dlCreditVal, dlCityLedgerVal;
	private double euCashVal, euCreditVal, euCityLedgerVal;
	private double poCashVal, poCreditVal, poCityLedgerVal;
	private JFormattedTextField tlCashField, tlCredit, tlCityLedger;
	private final String[] columnNames = new String[]{"ROOM", "PAYMENT TYPE", "AMOUNT", "CURRENCY", "TIME", "NOTE"};
	private DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	private JFormattedTextField dollarCashField, dollarCredit,dollarCityLedger;
	private JFormattedTextField euroCashField, euroCredit, euroCityLedger;
	private JFormattedTextField poundCashField, poundCredit,poundCityLedger;
	private NumberFormat tlFormatter = NumberFormat.getCurrencyInstance();
	private NumberFormat dlFormatter = NumberFormat.getCurrencyInstance();
	private NumberFormat euFormatter = NumberFormat.getCurrencyInstance();
	private NumberFormat poFormatter = NumberFormat.getCurrencyInstance();
	private final BlockadeTableHeaderRenderer THRC = new BlockadeTableHeaderRenderer();

	/**
	 * Create the dialog.
	 */
	public Main_CashDesk() {
		setAutoscrolls(true);
		setVisible(true);
		setBackground(Color.decode("#066d95"));
		setLayout(new BorderLayout(0, 0));
		
		tlFormatter.setCurrency(Currency.getInstance(Locale.getDefault()));
		dlFormatter.setCurrency(Currency.getInstance(Locale.US));
		euFormatter.setCurrency(Currency.getInstance(Locale.FRANCE));
		poFormatter.setCurrency(Currency.getInstance(Locale.UK));
		
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setAutoscrolls(true);
		buttonPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, SystemColor.controlShadow, null, null, null));
		buttonPanel.setPreferredSize(new Dimension(10, 40));
		buttonPanel.setBackground(new Color(173, 216, 230));
		add(buttonPanel, BorderLayout.NORTH);
		buttonPanel.setLayout(new BorderLayout(0, 0));
		
		final JLabel lblCashDesk = new JLabel("CASH DESK");
		lblCashDesk.setForeground(new Color(70, 130, 180));
		lblCashDesk.setHorizontalTextPosition(SwingConstants.LEADING);
		lblCashDesk.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashDesk.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 25));
		lblCashDesk.setAlignmentY(Component.TOP_ALIGNMENT);
		lblCashDesk.setPreferredSize(new Dimension(200, 40));
		buttonPanel.add(lblCashDesk);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setPreferredSize(new Dimension(10, 180));
		panel.setAutoscrolls(true);
		
		final JPanel pricePanel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pricePanel.setPreferredSize(new Dimension(1000, 165));
		pricePanel.setBackground(UIManager.getColor("textHighlight"));
		pricePanel.setLayout(null);
		
		panel.add(pricePanel);
		add(panel, BorderLayout.SOUTH);
		
		final JLabel lblTurkshLra = new JLabel("TURKISH LIRA : ");
		lblTurkshLra.setFont(new Font("Arial", Font.BOLD, 14));
		lblTurkshLra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTurkshLra.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTurkshLra.setForeground(new Color(0, 0, 0));
		lblTurkshLra.setBounds(223, 38, 113, 25);
		pricePanel.add(lblTurkshLra);
		
		final JLabel lblAmercanDollar = new JLabel("DOLLAR : ");
		lblAmercanDollar.setFont(new Font("Arial", Font.BOLD, 14));
		lblAmercanDollar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmercanDollar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAmercanDollar.setForeground(new Color(0, 0, 0));
		lblAmercanDollar.setBounds(223, 68, 113, 25);
		pricePanel.add(lblAmercanDollar);
		
		final JLabel lblEuro = new JLabel("EURO : ");
		lblEuro.setFont(new Font("Arial", Font.BOLD, 14));
		lblEuro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEuro.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEuro.setForeground(new Color(0, 0, 0));
		lblEuro.setBounds(223, 102, 113, 25);
		pricePanel.add(lblEuro);
		
		final JLabel lblPound = new JLabel("POUND : ");
		lblPound.setFont(new Font("Arial", Font.BOLD, 14));
		lblPound.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPound.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPound.setForeground(new Color(0, 0, 0));
		lblPound.setBounds(223, 133, 113, 25);
		pricePanel.add(lblPound);
		
		final JLabel lblCash = new JLabel("CASH");
		lblCash.setFont(new Font("Verdana", Font.BOLD, 15));
		lblCash.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCash.setHorizontalAlignment(SwingConstants.CENTER);
		lblCash.setBounds(352, 6, 141, 25);
		pricePanel.add(lblCash);
		
		final JLabel lblCredtCard = new JLabel("CREDIT CARD");
		lblCredtCard.setFont(new Font("Verdana", Font.BOLD, 15));
		lblCredtCard.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCredtCard.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredtCard.setBounds(524, 6, 141, 25);
		pricePanel.add(lblCredtCard);
		
		final JLabel lblCtyLedger = new JLabel("CITY LEDGER");
		lblCtyLedger.setFont(new Font("Verdana", Font.BOLD, 15));
		lblCtyLedger.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCtyLedger.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtyLedger.setBounds(696, 6, 141, 25);
		pricePanel.add(lblCtyLedger);
		
		tlCashField = new JFormattedTextField(tlFormatter);
		tlCashField.setBackground(SystemColor.controlLtHighlight);
		tlCashField.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tlCashField.setDragEnabled(true);
		tlCashField.setEditable(false);
		tlCashField.setFont(new Font("Arial", Font.PLAIN, 14));
		tlCashField.setBounds(352, 36, 141, 27);
		tlCashField.setValue(tlCashVal);
		pricePanel.add(tlCashField);
		tlCashField.setColumns(10);
		
		dollarCashField = new JFormattedTextField(dlFormatter);
		dollarCashField.setBackground(SystemColor.controlLtHighlight);
		dollarCashField.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		dollarCashField.setDragEnabled(true);
		dollarCashField.setEditable(false);
		dollarCashField.setFont(new Font("Arial", Font.PLAIN, 14));
		dollarCashField.setBounds(352, 69, 141, 27);
		dollarCashField.setValue(dlCashVal);
		pricePanel.add(dollarCashField);
		dollarCashField.setColumns(10);
		
		euroCashField = new JFormattedTextField(euFormatter);
		euroCashField.setBackground(SystemColor.controlLtHighlight);
		euroCashField.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		euroCashField.setDragEnabled(true);
		euroCashField.setEditable(false);
		euroCashField.setFont(new Font("Arial", Font.PLAIN, 14));
		euroCashField.setBounds(352, 102, 141, 27);
		euroCashField.setValue(euCashVal);
		pricePanel.add(euroCashField);
		euroCashField.setColumns(10);
		
		poundCashField = new JFormattedTextField(poFormatter);
		poundCashField.setBackground(SystemColor.controlLtHighlight);
		poundCashField.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		poundCashField.setDragEnabled(true);
		poundCashField.setEditable(false);
		poundCashField.setFont(new Font("Arial", Font.PLAIN, 14));
		poundCashField.setBounds(352, 135, 141, 27);
		poundCashField.setValue(poCashVal);
		pricePanel.add(poundCashField);
		poundCashField.setColumns(10);
		
		poundCredit = new JFormattedTextField(poFormatter);
		poundCredit.setBackground(SystemColor.controlLtHighlight);
		poundCredit.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		poundCredit.setDragEnabled(true);
		poundCredit.setEditable(false);
		poundCredit.setFont(new Font("Arial", Font.PLAIN, 14));
		poundCredit.setColumns(10);
		poundCredit.setBounds(524, 135, 141, 27);
		poundCredit.setValue(poCreditVal);
		pricePanel.add(poundCredit);
		
		euroCredit = new JFormattedTextField(euFormatter);
		euroCredit.setBackground(SystemColor.controlLtHighlight);
		euroCredit.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		euroCredit.setDragEnabled(true);
		euroCredit.setEditable(false);
		euroCredit.setFont(new Font("Arial", Font.PLAIN, 14));
		euroCredit.setColumns(10);
		euroCredit.setBounds(524, 102, 141, 27);
		euroCredit.setValue(euCreditVal);
		pricePanel.add(euroCredit);
		
		dollarCredit = new JFormattedTextField(dlFormatter);
		dollarCredit.setBackground(SystemColor.controlLtHighlight);
		dollarCredit.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		dollarCredit.setDragEnabled(true);
		dollarCredit.setEditable(false);
		dollarCredit.setFont(new Font("Arial", Font.PLAIN, 14));
		dollarCredit.setColumns(10);
		dollarCredit.setBounds(524, 69, 141, 27);
		dollarCredit.setValue(dlCreditVal);
		pricePanel.add(dollarCredit);
		
		tlCredit = new JFormattedTextField(tlFormatter);
		tlCredit.setBackground(SystemColor.controlLtHighlight);
		tlCredit.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tlCredit.setDragEnabled(true);
		tlCredit.setEditable(false);
		tlCredit.setFont(new Font("Arial", Font.PLAIN, 14));
		tlCredit.setColumns(10);
		tlCredit.setBounds(524, 36, 141, 27);
		tlCredit.setValue(tlCreditVal);
		pricePanel.add(tlCredit);
		
		poundCityLedger = new JFormattedTextField(poFormatter);
		poundCityLedger.setBackground(SystemColor.controlLtHighlight);
		poundCityLedger.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		poundCityLedger.setDragEnabled(true);
		poundCityLedger.setEditable(false);
		poundCityLedger.setFont(new Font("Arial", Font.PLAIN, 14));
		poundCityLedger.setColumns(10);
		poundCityLedger.setBounds(696, 135, 141, 27);
		poundCityLedger.setValue(poCityLedgerVal);
		pricePanel.add(poundCityLedger);
		
		euroCityLedger = new JFormattedTextField(euFormatter);
		euroCityLedger.setBackground(SystemColor.controlLtHighlight);
		euroCityLedger.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		euroCityLedger.setDragEnabled(true);
		euroCityLedger.setEditable(false);
		euroCityLedger.setFont(new Font("Arial", Font.PLAIN, 14));
		euroCityLedger.setColumns(10);
		euroCityLedger.setBounds(696, 102, 141, 27);
		euroCityLedger.setValue(euCityLedgerVal);
		pricePanel.add(euroCityLedger);
		
		dollarCityLedger = new JFormattedTextField(dlFormatter);
		dollarCityLedger.setBackground(SystemColor.controlLtHighlight);
		dollarCityLedger.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		dollarCityLedger.setDragEnabled(true);
		dollarCityLedger.setEditable(false);
		dollarCityLedger.setFont(new Font("Arial", Font.PLAIN, 14));
		dollarCityLedger.setColumns(10);
		dollarCityLedger.setBounds(696, 69, 141, 27);
		dollarCityLedger.setValue(dlCityLedgerVal);
		pricePanel.add(dollarCityLedger);
		
		tlCityLedger = new JFormattedTextField(tlFormatter);
		tlCityLedger.setBackground(SystemColor.controlLtHighlight);
		tlCityLedger.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tlCityLedger.setDragEnabled(true);
		tlCityLedger.setEditable(false);
		tlCityLedger.setFont(new Font("Arial", Font.PLAIN, 14));
		tlCityLedger.setColumns(10);
		tlCityLedger.setBounds(696, 36, 141, 27);
		tlCityLedger.setValue(tlCityLedgerVal);
		pricePanel.add(tlCityLedger);
		
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.decode("#066d95"));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(model);
		table.getTableHeader().setDefaultRenderer(THRC);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(2, 200));
		scrollPane.setMaximumSize(new Dimension(32767, 300));
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setViewportView(table);
		centerPanel.add(scrollPane, BorderLayout.NORTH);
	
		populateAllFields();
	}

	/*Getters and setters for access this values from another classes*/
	
	private void populateAllFields() {
		
		final PaymentDaoImpl paymentDaoImpl = new PaymentDaoImpl();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		final Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR_OF_DAY, 0);
		final String today = sdf.format(date.getTime());
		
		String val = paymentDaoImpl.getTotalLiraPaymentsForOneDay(today);
		tlCashVal =  val == null ? 0.0 : Double.parseDouble(val);
		tlCashField.setValue(tlCashVal);
		tlCashField.revalidate();
		tlCashField.repaint();

		
		val = paymentDaoImpl.getTotalDollarForOneDay(today);
		dlCashVal = val == null ? 0.0 : Double.parseDouble(val);
		dollarCashField.setValue(dlCashVal);
		dollarCashField.revalidate();
		dollarCashField.repaint();

		val = paymentDaoImpl.getTotalEuroPaymentsForOneDay(today);
		euCashVal = val == null ? 0.0 : Double.parseDouble(val);
		euroCashField.setValue(euCashVal);
		euroCashField.revalidate();
		euroCashField.repaint();
		
		val = paymentDaoImpl.getTotalPoundPaymentsForOneDay(today);
		poCashVal =  val == null ? 0.0 : Double.parseDouble(val);
		poundCashField.setValue(poCashVal);
		poundCashField.revalidate();
		poundCashField.repaint();
	}
	
	public double getTlCashVal() {
		return tlCashVal;
	}

	public void setTlCashVal(double tlCashVal) {
		this.tlCashVal = tlCashVal;
	}

	public double getTlCreditVal() {
		return tlCreditVal;
	}

	public void setTlCreditVal(double tlCreditVal) {
		this.tlCreditVal = tlCreditVal;
	}

	public double getTlCityLedgerVal() {
		return tlCityLedgerVal;
	}

	public void setTlCityLedgerVal(double tlCityLedgerVal) {
		this.tlCityLedgerVal = tlCityLedgerVal;
	}

	public double getDlCashVal() {
		return dlCashVal;
	}

	public void setDlCashVal(double dlCashVal) {
		this.dlCashVal = dlCashVal;
	}

	public double getDlCreditVal() {
		return dlCreditVal;
	}

	public void setDlCreditVal(double dlCreditVal) {
		this.dlCreditVal = dlCreditVal;
	}

	public double getDlCityLedgerVal() {
		return dlCityLedgerVal;
	}

	public void setDlCityLedgerVal(double dlCityLedgerVal) {
		this.dlCityLedgerVal = dlCityLedgerVal;
	}

	public double getEuCashVal() {
		return euCashVal;
	}

	public void setEuCashVal(double euCashVal) {
		this.euCashVal = euCashVal;
	}

	public double getEuCreditVal() {
		return euCreditVal;
	}

	public void setEuCreditVal(double euCreditVal) {
		this.euCreditVal = euCreditVal;
	}

	public double getEuCityLedgerVal() {
		return euCityLedgerVal;
	}

	public void setEuCityLedgerVal(double euCityLedgerVal) {
		this.euCityLedgerVal = euCityLedgerVal;
	}

	public double getPoCashVal() {
		return poCashVal;
	}

	public void setPoCashVal(double poCashVal) {
		this.poCashVal = poCashVal;
	}

	public double getPoCreditVal() {
		return poCreditVal;
	}

	public void setPoCreditVal(double poCreditVal) {
		this.poCreditVal = poCreditVal;
	}

	public double getPoCityLedgerVal() {
		return poCityLedgerVal;
	}

	public void setPoCityLedgerVal(double poCityLedgerVal) {
		this.poCityLedgerVal = poCityLedgerVal;
	}
	
	
}
