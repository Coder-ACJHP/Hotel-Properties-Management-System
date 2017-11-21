/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.coder.hms.utils.GetLiveCurrencyRates;

public class ExchangeWindow extends JDialog {

	/**
	 * 
	 */
	private JTable table;
	private double value = 0.0;
	private JComboBox<String> comboBox;
	private JButton btnUpdate, btnClear;
	private JFormattedTextField formattedTextField;
	private static final long serialVersionUID = 1L;
	private final String[] colNames = {"EXCHANGE", "RATE"};
	private final GetLiveCurrencyRates rates = new GetLiveCurrencyRates();
	private final DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private final String[] cmbList = { "TURKISH LIRA", "$ DOLLAR", "€ EURO", "£ POUND"};

	public ExchangeWindow() {
		
		this.getContentPane().setForeground(new Color(255, 99, 71));
		this.getContentPane().setFocusCycleRoot(true);
		this.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.getContentPane().setFont(new Font("Monospaced", Font.BOLD, 15));
		this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		this.setModal(true);
		this.setResizable(false);
		
		this.setTitle("Coder HPMSA - [Exchange]");
		
		this.setSize(410, 340);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.decode("#066d95"));
		this.getContentPane().setLayout(null);
		
		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(112, 248, 276, 49);
		buttonsPanel.setForeground(new Color(95, 158, 160));
		buttonsPanel.setBackground(Color.decode("#066d95"));
		getContentPane().add(buttonsPanel);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearFields();
			}
		});
		btnClear.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/coder/hms/icons/login_clear.png")));
		btnClear.setForeground(new Color(220, 20, 60));
		btnClear.setOpaque(true);
		btnClear.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClear.setPreferredSize(new Dimension(110, 40));
		btnClear.setFont(new Font("Verdana", Font.BOLD, 15));
		buttonsPanel.add(btnClear);

		btnUpdate = new JButton("CALCULATE");
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		btnUpdate.setMaximumSize(new Dimension(120, 23));
		btnUpdate.setMinimumSize(new Dimension(120, 23));
		btnUpdate.setToolTipText("Press ALT + ENTER keys for shortcut");
		btnUpdate.setSelectedIcon(null);
		btnUpdate.setIcon(new ImageIcon(ExchangeWindow.class.getResource("/com/coder/hms/icons/menubar_exchange_calculate.png")));
		btnUpdate.setForeground(new Color(0, 191, 255));
		btnUpdate.setOpaque(true);
		btnUpdate.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnUpdate.setMnemonic(KeyEvent.VK_ENTER);
		btnUpdate.setPreferredSize(new Dimension(130, 40));
		btnUpdate.setFont(new Font("Verdana", Font.BOLD, 15));
		buttonsPanel.add(btnUpdate);
		
		JLabel lblNewLabel = new JLabel("Currency : ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(60, 32, 89, 29);
		getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox<>();
		comboBox.setBorder(UIManager.getBorder("ComboBox.border"));
		comboBox.setFont(new Font("Arial", Font.BOLD, 12));
		for(String str: cmbList) {
			comboBox.addItem(str);
		}
		comboBox.setBounds(159, 34, 167, 25);
		getContentPane().add(comboBox);
		
		JLabel lblAmount = new JLabel("Amount : ");
		lblAmount.setForeground(Color.WHITE);
		lblAmount.setFont(new Font("Arial", Font.BOLD, 15));
		lblAmount.setBounds(60, 66, 89, 29);
		getContentPane().add(lblAmount);
		
		NumberFormat formatter = NumberFormat.getInstance();
		formatter.setMinimumFractionDigits(2);
		
		formattedTextField = new JFormattedTextField(formatter);
		formattedTextField.setValue(new Double(value));
		formattedTextField.setFont(new Font("Arial", Font.PLAIN, 14));
		formattedTextField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		formattedTextField.setColumns(10);
		formattedTextField.setBounds(159, 70, 167, 25);
		formattedTextField.addKeyListener(getKeyListener());
		formattedTextField.addPropertyChangeListener(getPropListener());
		getContentPane().add(formattedTextField);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 104, 384, 133);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setGridColor(UIManager.getColor("InternalFrame.inactiveTitleForeground"));
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		scrollPane.setViewportView(table);
		
		this.setVisible(true);
	}
	
	private KeyListener getKeyListener() {
		KeyAdapter listener = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c) && !e.isAltDown()) {
					e.consume();
				}
			}
		};
		return listener;
	}

	private PropertyChangeListener getPropListener() {
		PropertyChangeListener theListener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				Object source = e.getSource();
				if(source == formattedTextField) {
					value = Double.parseDouble(formattedTextField.getValue().toString());
				}
			}
		};
		return theListener;
	}
	
	private void clearFields() {
		
		formattedTextField.setText("");
		int rowCount = model.getRowCount();
		if(rowCount > 0) {
			model.setRowCount(0);
		}else {
			return;
		}
	}
	
	private void calculate() {
		
		double result = 0.0;
		double parsedVal = 0.0;
		String currency  = "";
		String choosen = comboBox.getSelectedItem().toString();
		
		model.setRowCount(0);
		
		if(choosen.contains("DOLLAR")) {
			
			currency = rates.getUSDToTRYLiveCurrency().substring(rates.
					getUSDToTRYLiveCurrency().length() -5, rates.getUSDToTRYLiveCurrency().length());
			parsedVal = Double.parseDouble(currency);
			result = parsedVal * value;
			model.addRow(new String[] {"TL",""+result});
			model.fireTableRowsInserted(1, 1);
			
		}
		else if(choosen.contains("EURO")) {
			
			currency = rates.getEURToTRYLiveCurrency().substring(rates.
					getEURToTRYLiveCurrency().length() -5, rates.getEURToTRYLiveCurrency().length());
			parsedVal = Double.parseDouble(currency);
			result = parsedVal * value;
			model.addRow(new String[] {"TL",""+result});
			model.fireTableRowsInserted(1, 1);
			
		}
		else if(choosen.contains("POUND")) {
			
			currency = rates.getGBPToTRYLiveCurrency().substring(rates.
					getGBPToTRYLiveCurrency().length() -5, rates.getGBPToTRYLiveCurrency().length());
			parsedVal = Double.parseDouble(currency);
			result = parsedVal * value;
			model.addRow(new String[] {"TL",""+result});
			model.fireTableRowsInserted(1, 1);
			
		}
		else {
			
			currency = rates.getUSDToTRYLiveCurrency().substring(rates.
					getUSDToTRYLiveCurrency().length() -5, rates.getUSDToTRYLiveCurrency().length());
			parsedVal = Double.parseDouble(currency);
			result = value / parsedVal;
			model.addRow(new String[] {"USD",""+result});
			
			currency = rates.getEURToTRYLiveCurrency().substring(rates.
					getEURToTRYLiveCurrency().length() -5, rates.getEURToTRYLiveCurrency().length());
			parsedVal = Double.parseDouble(currency);
			result = value / parsedVal;
			model.addRow(new String[] {"EURO",""+result});
			
			currency = rates.getGBPToTRYLiveCurrency().substring(rates.
					getGBPToTRYLiveCurrency().length() -5, rates.getGBPToTRYLiveCurrency().length());
			parsedVal = Double.parseDouble(currency);
			result = value / parsedVal;
			model.addRow(new String[] {"POUND",""+result});
			
			model.fireTableRowsInserted(3, 3);
		}
		
	}
}
