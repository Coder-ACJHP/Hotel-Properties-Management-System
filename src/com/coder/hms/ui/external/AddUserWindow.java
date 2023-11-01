/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.external;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.daoImpl.UserDaoImpl;
import com.coder.hms.entities.User;
import com.coder.hms.utils.EmailValidator;

public class AddUserWindow extends JDialog {

	private boolean isValid = false;
	protected Object[] rowCol = null;
	private JButton btnClear, btnSave;
	private static final long serialVersionUID = 1L;
	private JComboBox<String> roleCmbBox;
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
	private final String[] ROLE_LIST = { "ROLE_USER", "ROLE_ADMIN"};
	private JTextField firstNameField, lastNameField, nickNameField, emailField;
	private JLabel lblPassword, lblRepeatPassword;
	private JPasswordField passwordField, repeatPwdField;
	private JLabel infoLabel, markerLbl;
	private JLabel pwdMarkerLbl;

	/**
	 * Create the dialog.
	 */
	public AddUserWindow() {
		setReadyPaymentWindow();
	}
	
	public void setReadyPaymentWindow() {
		
		
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

		this.setTitle("Coder HPMSA - [Add new user]");

		/* Set default size of frame */
		this.setSize(400, 448);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.decode("#066d95"));
		getContentPane().setLayout(null);
		
		final JLabel lblTitle = new JLabel("First name : ");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTitle.setBounds(27, 16, 134, 35);
		getContentPane().add(lblTitle);
		
		final JLabel emailLbl = new JLabel("Email : ");
		emailLbl.setForeground(Color.WHITE);
		emailLbl.setFont(new Font("Verdana", Font.BOLD, 14));
		emailLbl.setBounds(27, 248, 134, 35);
		getContentPane().add(emailLbl);
		
		final JLabel lblCurrency = new JLabel("Role : ");
		lblCurrency.setForeground(Color.WHITE);
		lblCurrency.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCurrency.setBounds(27, 291, 134, 35);
		getContentPane().add(lblCurrency);
		
		roleCmbBox = new JComboBox<String>(new DefaultComboBoxModel<>(ROLE_LIST));
		roleCmbBox.setBounds(173, 292, 202, 35);
		roleCmbBox.setSelectedIndex(0);
		getContentPane().add(roleCmbBox);
		
		
		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(131, 341, 263, 49);
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
			clearFields();
		});
		buttonsPanel.add(btnClear);

		btnSave = new JButton("SAVE");
		btnSave.setSelectedIcon(null);
		btnSave.setIcon(new ImageIcon(AddUserWindow.class.getResource("/com/coder/hms/icons/reserv_save.png")));
		btnSave.setForeground(new Color(0, 191, 255));
		btnSave.setOpaque(true);
		btnSave.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSave.setMnemonic(KeyEvent.VK_ENTER);
		btnSave.setPreferredSize(new Dimension(110, 40));
		btnSave.setFont(new Font("Verdana", Font.BOLD, 15));
		btnSave.addActionListener(addUserActionListener());
		buttonsPanel.add(btnSave);
		
		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Verdana", Font.PLAIN, 15));
		firstNameField.setBounds(173, 16, 202, 35);
		getContentPane().add(firstNameField);
		firstNameField.setColumns(10);
		
		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Verdana", Font.PLAIN, 15));
		lastNameField.setColumns(10);
		lastNameField.setBounds(173, 62, 202, 35);
		getContentPane().add(lastNameField);
		
		JLabel lblLastName = new JLabel("Last name : ");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Verdana", Font.BOLD, 14));
		lblLastName.setBounds(27, 62, 134, 35);
		getContentPane().add(lblLastName);
		
		JLabel lblNickName = new JLabel("Nick name : ");
		lblNickName.setForeground(Color.WHITE);
		lblNickName.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNickName.setBounds(27, 109, 134, 35);
		getContentPane().add(lblNickName);
		
		nickNameField = new JTextField();
		nickNameField.setFont(new Font("Verdana", Font.PLAIN, 15));
		nickNameField.setColumns(10);
		nickNameField.setBounds(173, 109, 202, 35);
		getContentPane().add(nickNameField);
		
		markerLbl = new JLabel("*");
		markerLbl.setVisible(false);
		markerLbl.setForeground(Color.RED);
		markerLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		markerLbl.setBounds(378, 257, 16, 16);
		getContentPane().add(markerLbl);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Verdana", Font.PLAIN, 15));
		emailField.setColumns(10);
		emailField.setBounds(173, 248, 202, 35);
		emailField.addKeyListener(validatorListener());
		getContentPane().add(emailField);
		
		lblPassword = new JLabel("Password : ");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Verdana", Font.BOLD, 14));
		lblPassword.setBounds(27, 156, 134, 35);
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField(10);
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 15));
		passwordField.setColumns(10);
		passwordField.setBounds(173, 156, 202, 35);
		getContentPane().add(passwordField);
		
		pwdMarkerLbl = new JLabel("*");
		pwdMarkerLbl.setVisible(false);
		pwdMarkerLbl.setForeground(Color.RED);
		pwdMarkerLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		pwdMarkerLbl.setBounds(378, 165, 16, 16);
		getContentPane().add(pwdMarkerLbl);
		
		lblRepeatPassword = new JLabel("Repeat pwd : ");
		lblRepeatPassword.setForeground(Color.WHITE);
		lblRepeatPassword.setFont(new Font("Verdana", Font.BOLD, 14));
		lblRepeatPassword.setBounds(27, 203, 134, 35);
		getContentPane().add(lblRepeatPassword);
		
		repeatPwdField = new JPasswordField(10);
		repeatPwdField.setFont(new Font("Verdana", Font.PLAIN, 15));
		repeatPwdField.setColumns(10);
		repeatPwdField.setBounds(173, 203, 202, 35);
		getContentPane().add(repeatPwdField);
		
		infoLabel = new JLabel("");
		infoLabel.setForeground(new Color(255, 0, 0));
		infoLabel.setBounds(6, 394, 388, 28);
		infoLabel.setAutoscrolls(true);
		infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		infoLabel.setFont(new Font("Consolas", infoLabel.getFont().getStyle() | Font.BOLD, 15));
		getContentPane().add(infoLabel);

		this.setVisible(true);
	}

	private ActionListener addUserActionListener() {
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkFields()) {
					
					final InformationFrame infoFrame = new InformationFrame();
					
					if(isValid && Arrays.equals(passwordField.getPassword(), repeatPwdField.getPassword())) {
						
						pwdMarkerLbl.setVisible(false);
						markerLbl.setVisible(false);
						infoLabel.setText("");
						
						final User user = new User();
						user.setFirstName(firstNameField.getText().trim());
						user.setLastName(lastNameField.getText().trim());
						user.setNickName(nickNameField.getText().trim());
						user.setEmail(emailField.getText().trim());
						user.setPassword(new String(passwordField.getPassword()));
						user.setRole(roleCmbBox.getItemAt(roleCmbBox.getSelectedIndex()));
						
						try {
							
							final UserDaoImpl userDaoImpl = new UserDaoImpl();
							Object object = (Object) user;
							userDaoImpl.save("user", object);
							infoFrame.setMessage("New user saved successfully.");
							infoFrame.setVisible(true);
							clearFields();
							
						} catch (RuntimeException ex) {
							new DataSourceFactory().getTransaction().rollback();
							infoLabel.setText("Error! Cannot save user at this time.");
							return;
						}
						
					} else {
						pwdMarkerLbl.setVisible(true);
						infoLabel.setText("Passwords must be same!");
						return;
					}
				} else {
					pwdMarkerLbl.setVisible(true);
					markerLbl.setVisible(true);
					infoLabel.setText("All blanks must be filled!");
					return;
				}
			}
		};
		return listener;
	}
	
	private KeyListener validatorListener() {
		final KeyAdapter adapter = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				final EmailValidator validator = new EmailValidator();
				if(validator.validate(emailField.getText())) {
					markerLbl.setVisible(false);
					infoLabel.setText("");
					isValid = true;
				}
				else {
					markerLbl.setVisible(true);
					infoLabel.setText("Invalid email format!");
				}
			}
		};
		return adapter;
	}
	
	private boolean checkFields() {
		boolean fieldsFilled = false;
		
		if(firstNameField.getText().trim().length() > 0 && lastNameField.getText().trim().length() > 0 &&
				nickNameField.getText().trim().length() > 0 && emailField.getText().trim().length() > 0 && 
				passwordField.getPassword().length > 0 && repeatPwdField.getPassword().length > 0) {
			fieldsFilled = true;
		}
		
		return fieldsFilled;
	}
	
	private void clearFields() {
		
		firstNameField.setText("");
		lastNameField.setText("");
		nickNameField.setText("");
		emailField.setText("");
		passwordField.setText("");
		repeatPwdField.setText("");
		
	}
}
