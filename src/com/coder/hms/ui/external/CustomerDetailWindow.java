package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.toedter.calendar.JDateChooser;

public class CustomerDetailWindow extends JDialog {

	/**
	 * 
	 */
	private JButton chancelBtn, saveBtn;
	private JDateChooser dateOfBirthChooser;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel, titlePanel;
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
	private JComboBox<String> countryCmbBox, documentCmbBox, genderCmbBox, marriageStatusCmbBox;
	private JLabel lblName, lblSurname, lblDateOfBirth, lblCountry, lblFatherName, lblNewLabel, infoLabel,
	lblPhoneNumber, lblEmail, lblId, lblDocument, lblDocumentNo, lblGender, lblMarriageStatus, lblReservationId;
	private JTextField nameField, surnameField, fatherNameField, motherNameField, phoneField, emailField, idField, 
	docNoField, reservationIdField;
	private final String[] GENDER = { "MAN", "WOMAN" };
	private final String[] MARRIAGE_STATUS = { "SINGLE", "MARRIED" };
	private final String[] DOCUMENT_TYPES = { "PASSPORT", "IDENTITY CARD", "DRIVER LICENSE", "OTHER" };
	private final String[] COUNTRY_LIST = {"Afghanistan", "Albania","Algeria", "American Samoa", "Andorra",
			"Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
			 "Brazil", "Egypt", "Finland", "France", "Germany", "Hong Kong", "India", "Iran", "Iraq", "Ireland", "Israel", "Islands",
			 "Italy", "Jamaica", "Japan", "Republic of Korea", "Kuwait", "Lebanon", "Malaysia", "Mexico", "Nigeria", "Poland",
			 "Portugal", "Puerto Rico,PR", "Qatar", "Romania", "Russian Federation", "Saudi Arabia", "Singapore", "Spain", "Sweden",
			 "Switzerland", "Syrian Arab Republic", "Thailand", "Tunisia", "Turkey", "Turkmenistan", "Ukraine", "United Arab Emirates",
			 "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Virgin Islands U.S.", "Yemen", "Zambia", "Zimbabwe"};


	/**
	 * Create the dialog.
	 */
	public CustomerDetailWindow() {
				
		setBounds(100, 100, 710, 460);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		getContentPane().setFont(new Font("Verdana", Font.BOLD, 12));
		setTitle("Coder for HMS - [Customer detail]");
		this.setIconImage(Toolkit.getDefaultToolkit().
				getImage(getClass().getResource(LOGOPATH)));
		
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(173, 216, 230));
		titlePanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		titlePanel.setPreferredSize(new Dimension(10, 30));
		getContentPane().add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CUSTOMER DETAILS");
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(199, 0, 312, 30);
		titlePanel.add(lblNewLabel_1);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.decode("#066d95"));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		
		lblId = new JLabel("Id : ");
		lblId.setBounds(35, 18, 102, 16);
		contentPanel.add(lblId);
		
		idField = new JTextField();
		idField.setBounds(152, 13, 192, 26);
		idField.setEnabled(false);
		contentPanel.add(idField);
		idField.setColumns(10);
		
		lblDocument = new JLabel("Document : ");
		lblDocument.setBounds(377, 18, 109, 16);
		contentPanel.add(lblDocument);
		
		documentCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(DOCUMENT_TYPES));
		documentCmbBox.setBounds(494, 13, 192, 27);
		contentPanel.add(documentCmbBox);
		
		lblName = new JLabel("Name : ");
		lblName.setBounds(35, 56, 102, 16);
		contentPanel.add(lblName);
		
		nameField = new JTextField();
		nameField.setBounds(152, 51, 192, 26);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		lblDocumentNo = new JLabel("Document no : ");
		lblDocumentNo.setBounds(377, 56, 109, 16);
		contentPanel.add(lblDocumentNo);
		
		docNoField = new JTextField();
		docNoField.setBounds(494, 51, 192, 26);
		contentPanel.add(docNoField);
		docNoField.setColumns(10);
		
		lblSurname = new JLabel("Surname : ");
		lblSurname.setBounds(35, 94, 102, 16);
		contentPanel.add(lblSurname);
		
		surnameField = new JTextField();
		surnameField.setBounds(152, 89, 192, 26);
		contentPanel.add(surnameField);
		surnameField.setColumns(10);
		
		lblGender = new JLabel("Gender : ");
		lblGender.setBounds(377, 94, 109, 16);
		contentPanel.add(lblGender);
		
		genderCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(GENDER));
		genderCmbBox.setBounds(494, 89, 192, 27);
		contentPanel.add(genderCmbBox);
		
		lblDateOfBirth = new JLabel("Date of birth : ");
		lblDateOfBirth.setBounds(35, 132, 102, 16);
		contentPanel.add(lblDateOfBirth);
		
		dateOfBirthChooser = new JDateChooser();
		dateOfBirthChooser.setBounds(152, 127, 192, 26);
		dateOfBirthChooser.setCalendar(Calendar.getInstance());
		dateOfBirthChooser.setDateFormatString("yyyy-MM-dd");
		contentPanel.add(dateOfBirthChooser);
		
		lblMarriageStatus = new JLabel("Marriage status : ");
		lblMarriageStatus.setBounds(377, 132, 109, 16);
		contentPanel.add(lblMarriageStatus);
		
		marriageStatusCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(MARRIAGE_STATUS));
		marriageStatusCmbBox.setBounds(494, 127, 192, 27);
		contentPanel.add(marriageStatusCmbBox);
		
		lblCountry = new JLabel("Country : ");
		lblCountry.setBounds(35, 170, 102, 16);
		contentPanel.add(lblCountry);
		
		countryCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(COUNTRY_LIST));
		countryCmbBox.setBounds(152, 165, 192, 27);
		contentPanel.add(countryCmbBox);
		
		lblReservationId = new JLabel("Reservation id : ");
		lblReservationId.setBounds(377, 170, 109, 16);
		contentPanel.add(lblReservationId);
		
		reservationIdField = new JTextField();
		reservationIdField.setBounds(494, 165, 192, 26);
		contentPanel.add(reservationIdField);
		reservationIdField.setColumns(10);
		
		lblFatherName = new JLabel("Father name : ");
		lblFatherName.setBounds(35, 208, 102, 16);
		contentPanel.add(lblFatherName);
		
		fatherNameField = new JTextField();
		fatherNameField.setBounds(152, 203, 192, 26);
		contentPanel.add(fatherNameField);
		fatherNameField.setColumns(10);
		
		lblNewLabel = new JLabel("Mother name : ");
		lblNewLabel.setBounds(35, 246, 102, 16);
		contentPanel.add(lblNewLabel);
		
		motherNameField = new JTextField();
		motherNameField.setBounds(152, 241, 192, 26);
		contentPanel.add(motherNameField);
		motherNameField.setColumns(10);
		
		lblPhoneNumber = new JLabel("Phone number : ");
		lblPhoneNumber.setBounds(35, 284, 102, 16);
		contentPanel.add(lblPhoneNumber);
		
		phoneField = new JTextField();
		phoneField.setBounds(152, 279, 192, 26);
		contentPanel.add(phoneField);
		phoneField.setColumns(10);
		
		lblEmail = new JLabel("Email : ");
		lblEmail.setBounds(35, 322, 102, 16);
		contentPanel.add(lblEmail);
		
		emailField = new JTextField();
		emailField.setBounds(152, 317, 192, 26);
		contentPanel.add(emailField);
		emailField.setColumns(10);
		
		infoLabel = new JLabel();
		infoLabel.setFont(new Font("Verdana", Font.BOLD, 28));
		infoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(410, 228, 261, 96);
		contentPanel.add(infoLabel);
		
		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(10, 50));
		buttonsPanel.setBounds(341, 463, 423, 49);
		buttonsPanel.setForeground(new Color(95, 158, 160));
		buttonsPanel.setBackground(Color.decode("#066d95"));
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

		chancelBtn = new JButton("CANCEL");
		chancelBtn.setBounds(430, 5, 110, 40);
		chancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
		
			}
		});
		
		buttonsPanel.setLayout(null);
		chancelBtn.setIcon(new ImageIcon(NewReservationWindow.class.getResource("/com/coder/hms/icons/login_clear.png")));
		chancelBtn.setForeground(new Color(220, 20, 60));
		chancelBtn.setOpaque(true);
		chancelBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		chancelBtn.setPreferredSize(new Dimension(110, 40));
		chancelBtn.setFont(new Font("Verdana", Font.BOLD, 15));
		buttonsPanel.add(chancelBtn);
		
		saveBtn = new JButton("SAVE");
		saveBtn.setBounds(545, 5, 130, 40);
		saveBtn.setMaximumSize(new Dimension(120, 23));
		saveBtn.setMinimumSize(new Dimension(120, 23));
		saveBtn.setToolTipText("Press ALT + ENTER keys for shortcut");
		saveBtn.setSelectedIcon(null);
		saveBtn.setIcon(new ImageIcon(NewReservationWindow.class.getResource("/com/coder/hms/icons/reserv_save.png")));
		saveBtn.setForeground(new Color(0, 191, 255));
		saveBtn.setOpaque(true);
		saveBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		saveBtn.setMnemonic(KeyEvent.VK_ENTER);
		saveBtn.setPreferredSize(new Dimension(130, 40));
		saveBtn.setFont(new Font("Verdana", Font.BOLD, 15));
		buttonsPanel.add(saveBtn);
		
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public String getId() {
		return idField.getText();
	}
	
	public void setId(String Id) {
		idField.setText(Id);
	}
	
	public String getDocument() {
		return documentCmbBox.getItemAt(documentCmbBox.getSelectedIndex());
	}
	
	public void setDocument(String document) {
		for(int index=0; index < documentCmbBox.getItemCount(); index++) {
			if(documentCmbBox.getItemAt(index).toString().equalsIgnoreCase(document)) {
				this.documentCmbBox.setSelectedItem(document);
				break;
			}
		}
	}
	
	public String getDocNo() {
		return this.docNoField.getText();
	}
	
	public void setDocNo(String DocumentNo) {
		this.docNoField.setText(DocumentNo);
	}
	
	@Override
	public String getName() {
		return this.nameField.getText();
	}
	
	@Override
	public void setName(String Name) {
		this.nameField.setText(Name);
	}
	
	public String getSurname() {
		return this.surnameField.getText();
	}
	
	public void setSurname(String lastName) {
		this.surnameField.setText(lastName);
	}
	
	public String getGender() {
		return this.genderCmbBox.getItemAt(genderCmbBox.getSelectedIndex());
	}
	
	public void setGender(String Gender) {
		for(int index=0; index < genderCmbBox.getItemCount(); index++) {
			if(genderCmbBox.getItemAt(index).toString().equalsIgnoreCase(Gender)) {
				this.genderCmbBox.setSelectedItem(Gender);
				break;
			}
		}
	}

	public Date getDateOfBirth() {
		return this.dateOfBirthChooser.getDate();
	}
	
	public void setDateOfBirth(Date date) {
		dateOfBirthChooser.setDate(date);
	}
	
	public String getMariageStatus() {
		return this.marriageStatusCmbBox.getItemAt(marriageStatusCmbBox.getSelectedIndex());
	}
	
	public void setMariaggeStaus(String MariaggeStatus) {
		for(int index=0; index < marriageStatusCmbBox.getItemCount(); index++) {
			if(marriageStatusCmbBox.getItemAt(index).toString().equalsIgnoreCase(MariaggeStatus)) {
				this.marriageStatusCmbBox.setSelectedItem(MariaggeStatus);
				break;
			}
		}
	}
	
	public String getCountry() {
		return this.countryCmbBox.getItemAt(countryCmbBox.getSelectedIndex());
	}
	
	public void setCountry(String Country) {
		for(int index=0; index < countryCmbBox.getItemCount(); index++) {
			if(countryCmbBox.getItemAt(index).toString().equalsIgnoreCase(Country)) {
				this.countryCmbBox.setSelectedItem(Country);
				break;
			}
		}
	}
	
	public String getReservationId() {
		return reservationIdField.getText();
	}
	
	public void setReservationId(String ReservationId) {
		this.reservationIdField.setText(ReservationId);
	}
	
	public String getFatherName() {
		return fatherNameField.getText();
	}
	
	public void setFatherName(String FatherName) {
		this.fatherNameField.setText(FatherName);
	}
	
	public String getMotherName() {
		return motherNameField.getText();
	}
	
	public void setMotherName(String MotherName) {
		this.motherNameField.setText(MotherName);
	}
	
	public String getPhone() {
		return this.phoneField.getText();
	}
	
	public void setPhone(String PhoneNum) {
		this.phoneField.setText(PhoneNum);
	}
	
	public String getEmail() {
		return this.emailField.getText();
	}
	
	public void setEmail(String Email) {
		this.emailField.setText(Email);
	}

	public void setActionListener(ActionListener theActionListener) {
		saveBtn.addActionListener(theActionListener);
	}
	
	public void setInfoMessage(String INFO_MESSAGE) {
		this.infoLabel.setText(INFO_MESSAGE);
	}
	
	//for info label just add settr method
	public void setInfoLabelColor(Color color) {
		infoLabel.setForeground(color);
	}
}








