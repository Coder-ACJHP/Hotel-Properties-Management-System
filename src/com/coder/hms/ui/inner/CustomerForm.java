package com.coder.hms.ui.inner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

public class CustomerForm {

	private JCheckBox chckbxCheckin;
	private JDateChooser dateOfBirthChooser;
	private final String[] GENDER = { "MAN", "WOMAN" };
	private final String[] TITLE = { "MR", "MS","MRS", "MISS" };
	private JTextField docNoField, lastNameField, firstNameField;
	private final String[] MARRIAGE_STATUS = { "SINGLE", "MARRIED" };
	private final String[] DOCUMENT_TYPES = { "PASSPORT", "IDENTITY CARD", "DRIVER LICENSE", "OTHER" };
	private JComboBox<String> customerCountryCmbBox, documentTypeCmbx, titleComboBox, marriageComboBox, genderComboBox;
	private final String[] COUNTRY_LIST = { "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola",
			"Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
			"Brazil", "Egypt", "Finland", "France", "Germany", "Hong Kong", "India", "Iran", "Iraq", "Ireland",
			"Israel", "Islands", "Italy", "Jamaica", "Japan", "Republic of Korea", "Kuwait", "Lebanon", "Malaysia",
			"Mexico", "Nigeria", "Poland", "Portugal", "Puerto Rico,PR", "Qatar", "Romania", "Russian Federation",
			"Saudi Arabia", "Singapore", "Spain", "Sweden", "Switzerland", "Syrian Arab Republic", "Thailand",
			"Tunisia", "Turkey", "Turkmenistan", "Ukraine", "United Arab Emirates", "United Kingdom", "United States",
			"Uruguay", "Uzbekistan", "Virgin Islands U.S.", "Yemen", "Zambia", "Zimbabwe" };


	public CustomerForm() {

	}

	public JPanel setCustomerDetailPanel() {

		final Dimension dimension = new Dimension(255, 10);

		JPanel customerDetailPanel = new JPanel();
		customerDetailPanel.setBackground(Color.decode("#066d95"));
		customerDetailPanel.setBorder(new LineBorder(Color.CYAN));
		customerDetailPanel.setPreferredSize(new Dimension(245, 402));
		customerDetailPanel.setMaximumSize(dimension);
		customerDetailPanel.setMinimumSize(dimension);
		customerDetailPanel.setLayout(null);

		JLabel countryLbl = new JLabel("Country : ");
		countryLbl.setFont(new Font("Verdana", Font.BOLD, 11));
		countryLbl.setBounds(10, 65, 105, 20);
		customerDetailPanel.add(countryLbl);

		chckbxCheckin = new JCheckBox("Checkin :  ");
		chckbxCheckin.setSelected(true);
		chckbxCheckin.setFont(new Font("Verdana", Font.BOLD, 11));
		chckbxCheckin.setOpaque(false);
		chckbxCheckin.setHorizontalTextPosition(SwingConstants.LEFT);
		chckbxCheckin.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxCheckin.setBounds(48, 19, 130, 23);
		chckbxCheckin.addActionListener(getCheckinStatus());
		customerDetailPanel.add(chckbxCheckin);

		customerCountryCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(COUNTRY_LIST));
		customerCountryCmbBox.setBounds(117, 65, 118, 20);
		customerDetailPanel.add(customerCountryCmbBox);

		JLabel lblDocumentType = new JLabel("Doc. type : ");
		lblDocumentType.setFont(new Font("Verdana", Font.BOLD, 11));
		lblDocumentType.setBounds(10, 96, 105, 20);
		customerDetailPanel.add(lblDocumentType);

		documentTypeCmbx = new JComboBox<String>(new DefaultComboBoxModel<>(DOCUMENT_TYPES));
		documentTypeCmbx.setBounds(117, 96, 118, 20);
		customerDetailPanel.add(documentTypeCmbx);

		JLabel lblDocumentNo = new JLabel("Document no : ");
		lblDocumentNo.setFont(new Font("Verdana", Font.BOLD, 11));
		lblDocumentNo.setBounds(10, 127, 105, 20);
		customerDetailPanel.add(lblDocumentNo);

		docNoField = new JTextField();
		docNoField.setBounds(117, 127, 118, 20);
		customerDetailPanel.add(docNoField);
		docNoField.setColumns(10);

		JLabel lblTitle = new JLabel("Title : ");
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 11));
		lblTitle.setBounds(10, 158, 105, 20);
		customerDetailPanel.add(lblTitle);

		titleComboBox = new JComboBox<String>(new DefaultComboBoxModel<>(TITLE));
		titleComboBox.setBounds(117, 158, 118, 20);
		titleComboBox.addItemListener(changeTitleListener());
		customerDetailPanel.add(titleComboBox);

		JLabel lastNameLbl = new JLabel("Last name : ");
		lastNameLbl.setFont(new Font("Verdana", Font.BOLD, 11));
		lastNameLbl.setBounds(10, 189, 105, 20);
		customerDetailPanel.add(lastNameLbl);

		lastNameField = new JTextField();
		lastNameField.setBounds(117, 189, 118, 20);
		lastNameField.setColumns(10);
		customerDetailPanel.add(lastNameField);

		JLabel lblFirstName = new JLabel("First name : ");
		lblFirstName.setFont(new Font("Verdana", Font.BOLD, 11));
		lblFirstName.setBounds(10, 220, 105, 20);
		customerDetailPanel.add(lblFirstName);

		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(117, 220, 118, 20);
		customerDetailPanel.add(firstNameField);

		JLabel lblDateOfBirth = new JLabel("Date of birth : ");
		lblDateOfBirth.setFont(new Font("Verdana", Font.BOLD, 11));
		lblDateOfBirth.setBounds(10, 251, 105, 20);
		customerDetailPanel.add(lblDateOfBirth);

		dateOfBirthChooser = new JDateChooser();
		dateOfBirthChooser.setDateFormatString("dd/MM/yyyy");
		dateOfBirthChooser.setBounds(117, 251, 118, 20);
		dateOfBirthChooser.addPropertyChangeListener(checkDates());
		customerDetailPanel.add(dateOfBirthChooser);

		JLabel lblMarriageStatus = new JLabel("Marriage sts. : ");
		lblMarriageStatus.setFont(new Font("Verdana", Font.BOLD, 11));
		lblMarriageStatus.setBounds(10, 282, 105, 20);
		customerDetailPanel.add(lblMarriageStatus);

		marriageComboBox = new JComboBox<String>(new DefaultComboBoxModel<>(MARRIAGE_STATUS));
		marriageComboBox.setBounds(117, 282, 118, 20);
		customerDetailPanel.add(marriageComboBox);

		JLabel lblSex = new JLabel("Gender : ");
		lblSex.setFont(new Font("Verdana", Font.BOLD, 11));
		lblSex.setBounds(10, 314, 105, 20);
		customerDetailPanel.add(lblSex);

		genderComboBox = new JComboBox<String>(new DefaultComboBoxModel<>(GENDER));
		genderComboBox.setBounds(117, 314, 118, 20);
		customerDetailPanel.add(genderComboBox);

		customerDetailPanel.setSize(dimension);
		
		
		return customerDetailPanel;
	}

	private PropertyChangeListener checkDates() {
		final PropertyChangeListener propListener = new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				
				// here leave ancestors empty because we need dates not ancestor
				if (evt.getPropertyName().equals("ancestor")) {
					return;
				}
				
				final Date today = new Date();
				final Date birthDate = dateOfBirthChooser.getDate();
				
				final Calendar todayCal = Calendar.getInstance();
				todayCal.setTime(today);
				final Calendar birthCal = Calendar.getInstance();
				birthCal.setTime(birthDate);
				
				if(todayCal.get(Calendar.DAY_OF_YEAR) == birthCal.get(Calendar.DAY_OF_YEAR)) {
					JOptionPane.showMessageDialog(null, "Are you sure you are born today!",
							JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
					return;
				}	
			}
		};
		return propListener;
	}

	private ItemListener changeTitleListener() {
		final ItemListener listener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				final String title = titleComboBox.getSelectedItem().toString();
				
				if(title.equalsIgnoreCase("Mr")) {
					genderComboBox.setSelectedIndex(0);
					genderComboBox.revalidate();
					genderComboBox.repaint();
				}
				
				else {
					genderComboBox.setSelectedIndex(1);
					genderComboBox.revalidate();
					genderComboBox.repaint();
				}
			}
		};
		return listener;
	}

	/* for making components disable when chkBox unchecked */
	private void setEnableStatus(Boolean status) {
		genderComboBox.setEnabled(status);
		marriageComboBox.setEnabled(status);
		dateOfBirthChooser.setEnabled(status);
		firstNameField.setEnabled(status);
		lastNameField.setEnabled(status);
		titleComboBox.setEnabled(status);
		docNoField.setEnabled(status);
		documentTypeCmbx.setEnabled(status);
		customerCountryCmbBox.setEnabled(status);
	}

	/* action listener for chkBox */
	private ActionListener getCheckinStatus() {
		final ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean isSelected = abstractButton.getModel().isSelected();
				setEnableStatus(isSelected);
			}
		};
		return listener;
	}

	public String getGenderComboxValue() {
		return genderComboBox.getSelectedItem().toString();
	}
	
	public void setGenderComboBoxValue(String gender) {
		for(int index=0; index < genderComboBox.getItemCount(); index++) {
			if(genderComboBox.getItemAt(index).equals(gender)) {
				genderComboBox.setSelectedIndex(index);
			}
		}
	}
	
	public String getMarriageComboBoxValue() {
		return marriageComboBox.getSelectedItem().toString();
	}
	
	public void setMarriageComboBoxValue(String mariage) {
		for(int index=0; index < marriageComboBox.getItemCount(); index++) {
			if(marriageComboBox.getItemAt(index).equals(mariage)) {
				marriageComboBox.setSelectedIndex(index);
			}
		}
	}
	
	public Date getDateOfBirthChooserValue() {
		return dateOfBirthChooser.getDate();
	}
	
	public void setDateOfBirth(Date date) {
		dateOfBirthChooser.setDate(date);
	}
	
	public String getFirstNameFieldValue() {
		return firstNameField.getText();
	}
	
	public void setFirstNameValue(String name) {
		this.firstNameField.setText(name);
	}
	
	public String getLastNameFieldValue() {
		return lastNameField.getText();
	}
	
	
	public void setLastNameValue(String lastName) {
		this.lastNameField.setText(lastName);
	}
	
	public String getTitleComboBoxValue() {
		return titleComboBox.getSelectedItem().toString();
	}
	
	public void setTitleValue(String title) {
		for(int index=0; index < titleComboBox.getItemCount(); index++) {
			if(titleComboBox.getItemAt(index).equals(title)) {
				titleComboBox.setSelectedIndex(index);
			}
		}
	}
	
	public String getDocNoFieldValue() {
		return docNoField.getText();
	}
	
	public void setDocumentNumber(String docNumber) {
		this.docNoField.setText(docNumber);
	}
	
	public String getDocumentTypeCmbxValue() {
		return documentTypeCmbx.getSelectedItem().toString();
	}
	
	public void setDocumentTypeCmbxValue(String docType) {
		for(int index=0; index < documentTypeCmbx.getItemCount(); index++) {
			if(documentTypeCmbx.getItemAt(index).equals(docType)) {
				documentTypeCmbx.setSelectedIndex(index);
			}
		}
	}
	
	public String getCustomerCountryCmbBoxValue() {
		return customerCountryCmbBox.getSelectedItem().toString();
	}
	
	public void setCustomerCountryCmbBoxValue(String country) {
		for(int index=0; index < customerCountryCmbBox.getItemCount(); index++) {
			if(customerCountryCmbBox.getItemAt(index).equals(country)) {
				customerCountryCmbBox.setSelectedIndex(index);
			}
		}
	}
}
