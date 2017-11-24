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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.coder.hms.daoImpl.PostingDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Posting;
import com.coder.hms.entities.Room;
import com.coder.hms.utils.GetLiveCurrencyRates;

public class PostingWindow extends JDialog {

	/**
	 * 
	 */
	private double value = 0.0;
	private JTextArea textPane;
	private JTable postingTable;
	private JTextField docNoField;
	private String roomNumber = "";
	protected Object[] rowCol = null;
	private boolean isPosted = false;
	private JButton btnClear, btnSave;
	private NumberFormat formatter;
	private JFormattedTextField priceField;
	private static final long serialVersionUID = 1L;
	private JComboBox<String> currencyCmbBox, titleCmbBox, typeCmbBox;
	private final GetLiveCurrencyRates rates = new GetLiveCurrencyRates();
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
	private final String[] POST_COLMNS = {"DOCUMENT NO", "TYPE", "TITLE", "PRICE", "CURRENCY", "EXPLANATION, DATE TIME"};
	private final DefaultTableModel model = new DefaultTableModel(POST_COLMNS, 0);
	private final String[] CURRENCY_LIST = { "TURKISH LIRA", "DOLLAR", "EURO", "POUND"};
	private final String[] TYPE_LIST = { "SYSTEM"};
	private final String[] TITLE_LIST = { "ROOM RATES", "RESTAURANT", "MINIBAR", "TELEPHONE", "MISCELLANEOUS"};

	/**
	 * Create the dialog.
	 */
	public PostingWindow() {
		// TODO Auto-generated constructor stub
	}
	
	public void setReadyPaymentWindow(String roomText)  {
		
		this.roomNumber = roomText;
		
		setPreferredSize(new Dimension(400, 500));
		
		// set upper icon for dialog frame
		this.setIconImage(Toolkit.getDefaultToolkit().
				getImage(getClass().getResource(LOGOPATH)));

		getContentPane().setForeground(new Color(255, 99, 71));
		getContentPane().setFocusCycleRoot(true);
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().setFont(new Font("Verdana", Font.BOLD, 12));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);

		this.setTitle("Coder HPMSA - [Posting]");

		/* Set default size of frame */
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.decode("#066d95"));
		getContentPane().setLayout(null);
		
		final JLabel lblTitle = new JLabel("Title : ");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTitle.setBounds(56, 55, 100, 20);
		getContentPane().add(lblTitle);
		
		final JLabel lblPaymentName = new JLabel("Document no : ");
		lblPaymentName.setForeground(Color.WHITE);
		lblPaymentName.setFont(new Font("Verdana", Font.BOLD, 13));
		lblPaymentName.setBounds(54, 20, 135, 20);
		getContentPane().add(lblPaymentName);
		
		docNoField = new JTextField();
		docNoField.setBounds(203, 22, 86, 20);
		getContentPane().add(docNoField);
		docNoField.setColumns(10);
		
		final JLabel lblPrice = new JLabel("Price : ");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Verdana", Font.BOLD, 14));
		lblPrice.setBounds(56, 116, 100, 20);
		getContentPane().add(lblPrice);
		
		priceField = new JFormattedTextField();
		priceField.setValue(new Double(value));
		priceField.setBounds(203, 114, 155, 20);
		getContentPane().add(priceField);
		
		final JLabel lblCurrency = new JLabel("Currency : ");
		lblCurrency.setForeground(Color.WHITE);
		lblCurrency.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCurrency.setBounds(56, 146, 100, 20);
		getContentPane().add(lblCurrency);
		
		currencyCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(CURRENCY_LIST));
		currencyCmbBox.addItemListener(currencyActionListener());
		currencyCmbBox.setBounds(203, 146, 155, 20);
		currencyCmbBox.setSelectedIndex(0);
		getContentPane().add(currencyCmbBox);
		
		JLabel lblExplain = new JLabel("Explanation");
		lblExplain.setForeground(Color.WHITE);
		lblExplain.setFont(new Font("Verdana", Font.BOLD, 14));
		lblExplain.setHorizontalAlignment(SwingConstants.CENTER);
		lblExplain.setBounds(136, 171, 107, 20);
		getContentPane().add(lblExplain);
		
		textPane = new JTextArea();
		textPane.setLineWrap(true);
		textPane.setLocale(new Locale("tr", "TR"));
		textPane.setBackground(UIManager.getColor("info"));
		textPane.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		textPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textPane.setBounds(42, 196, 316, 50);
		getContentPane().add(textPane);
		
		
		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(107, 409, 277, 49);
		buttonsPanel.setForeground(new Color(95, 158, 160));
		buttonsPanel.setBackground(Color.decode("#066d95"));
		getContentPane().add(buttonsPanel);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnClear = new JButton("CLEAR");
		btnClear.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/coder/hms/icons/login_clear.png")));
		btnClear.setForeground(new Color(220, 20, 60));
		btnClear.setOpaque(true);
		btnClear.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClear.setPreferredSize(new Dimension(110, 40));
		btnClear.setFont(new Font("Verdana", Font.BOLD, 15));
		btnClear.addActionListener(ActionListener -> {
			clear();
		});
		buttonsPanel.add(btnClear);

		btnSave = new JButton("POST");
		btnSave.addActionListener(payActionListener());
		btnSave.setToolTipText("Press ALT + ENTER keys for shortcut");
		btnSave.setSelectedIcon(null);
		btnSave.setIcon(new ImageIcon(PostingWindow.class.getResource("/com/coder/hms/icons/room_post.png")));
		btnSave.setForeground(new Color(0, 191, 255));
		btnSave.setOpaque(true);
		btnSave.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSave.setMnemonic(KeyEvent.VK_ENTER);
		btnSave.setPreferredSize(new Dimension(110, 40));
		btnSave.setFont(new Font("Verdana", Font.BOLD, 15));
		buttonsPanel.add(btnSave);
		
		titleCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(TITLE_LIST));
		titleCmbBox.setBounds(203, 54, 155, 20);
		getContentPane().add(titleCmbBox);
		
		JPanel tableHolderPanel = new JPanel();
		tableHolderPanel.setBounds(10, 258, 374, 140);
		getContentPane().add(tableHolderPanel);
		tableHolderPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane tableScrollPane = new JScrollPane();
		tableHolderPanel.add(tableScrollPane, BorderLayout.CENTER);
		
		postingTable = new JTable();
		postingTable.setModel(model);
		postingTable.getColumnModel().getColumn(0).setPreferredWidth(95);
		postingTable.setColumnSelectionAllowed(true);
		tableScrollPane.setViewportView(postingTable);
		
		JLabel lblType = new JLabel("Type : ");
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Verdana", Font.BOLD, 14));
		lblType.setBounds(55, 85, 100, 16);
		getContentPane().add(lblType);
		
		typeCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(TYPE_LIST));
		typeCmbBox.setBounds(202, 81, 156, 27);
		getContentPane().add(typeCmbBox);

		this.setVisible(true);
	}
	
	public ItemListener currencyActionListener() {
		ItemListener ac = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) {

				final String choosed = currencyCmbBox.getSelectedItem().toString();
				NumberFormatter nf = null;
				DefaultFormatterFactory dfc = null;
				priceField.removeAll();
				
					switch (choosed) {
					case "TURKISH LIRA":
						
						formatter.setCurrency(Currency.getInstance(Locale.getDefault()));
						nf = new NumberFormatter(formatter);
						dfc = new DefaultFormatterFactory(nf);
						priceField.setFormatterFactory(dfc);
						priceField.revalidate();
						priceField.repaint();
						break;
					case "DOLLAR":
						formatter.setCurrency(Currency.getInstance(Locale.US));
						nf = new NumberFormatter(formatter);
						dfc = new DefaultFormatterFactory(nf);
						priceField.setFormatterFactory(dfc);
						priceField.revalidate();
						priceField.repaint();
						break;
					case "EURO":
						formatter.setCurrency(Currency.getInstance(Locale.FRANCE));
						nf = new NumberFormatter(formatter);
						dfc = new DefaultFormatterFactory(nf);
						priceField.setFormatterFactory(dfc);
						priceField.revalidate();
						priceField.repaint();
						break;
					case "POUND":
						formatter.setCurrency(Currency.getInstance(Locale.UK));
						nf = new NumberFormatter(formatter);
						dfc = new DefaultFormatterFactory(nf);
						priceField.setFormatterFactory(dfc);
						priceField.revalidate();
						priceField.repaint();
						break;
					default:
						break;
					}
					repaint();
			}

		};
		return ac;
	}
	
	private void clear() {

		priceField.setText("");
		currencyCmbBox.setSelectedItem(0);
		textPane.setText("");
	}
	
	private ActionListener payActionListener() {
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				final Posting posting = new Posting();
				

				posting.setTitle(titleCmbBox.getSelectedItem().toString());
				posting.setPostType(typeCmbBox.getSelectedItem().toString());
				posting.setPrice(priceField.getValue() + "");
				posting.setCurrency(currencyCmbBox.getSelectedItem().toString());
				posting.setExplanation(textPane.getText());
				posting.setRoomNumber(roomNumber);
				
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				
				posting.setDateTime(date);
				
				rowCol = new Object[] {posting.getId(), posting.getTitle(), posting.getPostType(),
								posting.getPrice(), posting.getCurrency(), posting.getExplanation(), posting.getDateTime()};
				model.addRow(rowCol);
				
				//after adding the post in the table save it in database
				final PostingDaoImpl postingDaoImpl = new PostingDaoImpl();
				postingDaoImpl.savePosting(posting);
				
				final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
				final Room theRoom = roomDaoImpl.getRoomByRoomNumber(roomNumber);
				
				//here we have to check payment currency if other than TL thats mean to exchange.
				DecimalFormat decimalFormat = new DecimalFormat("#.####");
				double inputVal = Double.valueOf(posting.getPrice().toString());
				
				double balance = 0.0;
				double parsedVal = 0.0;
				String currency  = "";
				String trimmed = "";
				
				switch (currencyCmbBox.getItemAt(currencyCmbBox.getSelectedIndex())) {
				
				case "TURKISH LIRA":
					balance = inputVal;
					break;
				case "DOLLAR":
					currency = rates.getUSDToTRYLiveCurrency();
					trimmed  = currency.substring(currency.length() -6, currency.length());
					parsedVal = Double.parseDouble(trimmed);
					balance = parsedVal * inputVal;
					break;
				case "EURO":
					currency = rates.getEURToTRYLiveCurrency();
					trimmed  = currency.substring(currency.length() -6, currency.length());
					parsedVal = Double.parseDouble(trimmed);
					balance = parsedVal * inputVal;
					break;
				case "POUND":
					currency = rates.getGBPToTRYLiveCurrency();
					trimmed  = currency.substring(currency.length() -6, currency.length());
					parsedVal = Double.parseDouble(trimmed);
					balance = parsedVal * inputVal;
					break;
				default:
					break;
				}
				
				final double totalPrice = Double.parseDouble(theRoom.getTotalPrice()) + balance;
				theRoom.setTotalPrice(Double.valueOf(decimalFormat.format(totalPrice)) + "");
				roomDaoImpl.updateRoom(theRoom);
			}
		};
		isPosted = true;
		return listener;
	}
	
	public boolean getPostingStatus() {
		return this.isPosted;
	}
	
	public Object[] getTableRowData() {
		if(isPosted) {
			return this.rowCol;
		}
		return null;
	}

}
