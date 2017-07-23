/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.usrinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import com.coder.hms.daoImpl.UserDaoImpl;
import com.coder.hms.entities.SessionBean;
import com.coder.hms.utils.ApplicationLogo;

/**
 * @author Coder ACJHP
 *
 */
public class LoginFrame extends JDialog {

	/**
	 * 
	 */
	private JLabel infoLabel;
	private String newDate;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private static SessionBean sessionBean;
	private static final long serialVersionUID = 1L;
	private final ApplicationLogo logoSetter = new ApplicationLogo();
	
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";

	// Set some basic properties
	public LoginFrame() {
		
		sessionBean = SessionBean.getSESSION_BEAN();
		//set upper icon for dialog frame
		logoSetter.setApplicationLogoJDialog(this, LOGOPATH);
		
		final Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY EEEE");
		newDate = sdf.format(today);
		
		getContentPane().setForeground(new Color(255, 99, 71));
		getContentPane().setFocusCycleRoot(true);
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().setFont(new Font("Monospaced", Font.BOLD, 15));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setResizable(false);


		this.setTitle("Coder for HMS - [Login] - (" + newDate +")");
		SessionBean.setDate(newDate);
		
		/* Set default size of frame */
		this.setSize(428, 262);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.decode("#066d95"));
		getContentPane().setLayout(null);

		final JLabel userNameLabel = new JLabel("User name : ");
		userNameLabel.setForeground(new Color(255, 255, 255));
		userNameLabel.setBounds(29, 79, 113, 18);
		userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		userNameLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		getContentPane().add(userNameLabel);

		userNameField = new JTextField();
		
		userNameField.setToolTipText("Enter your user name");
		userNameField.setLocation(143, 74);
		userNameField.setBackground(new Color(255, 255, 255));
		userNameField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		userNameField.setMinimumSize(new Dimension(10, 40));
		userNameField.setSize(new Dimension(218, 31));
		userNameField.setPreferredSize(new Dimension(10, 44));
		userNameField.setFont(new Font("Monospaced", Font.PLAIN, 17));
		userNameField.setEditable(true);
		userNameLabel.setLabelFor(userNameField);
		getContentPane().add(userNameField);
		userNameField.setColumns(10);

		final JLabel passwordLabel = new JLabel("Password : ");
		passwordLabel.setForeground(new Color(255, 255, 255));
		passwordLabel.setBounds(30, 122, 113, 18);
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Verdana", Font.BOLD, 16));
		getContentPane().add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter your password");
		passwordField.setLocation(143, 118);
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setMinimumSize(new Dimension(10, 40));
		passwordField.setSize(new Dimension(218, 29));
		passwordField.setPreferredSize(new Dimension(10, 44));
		passwordField.setFont(new Font("Monospaced", Font.PLAIN, 17));
		passwordLabel.setLabelFor(passwordField);
		getContentPane().add(passwordField);
		
		//add listener for both fields to clear info label when key typed
		keyListenerForFields(userNameField, passwordField);

		/* 
		*	need to new panel for adding our buttons
		*/
		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(112, 168, 277, 49);
		buttonsPanel.setForeground(new Color(95, 158, 160));
		buttonsPanel.setBackground(Color.decode("#066d95"));
		getContentPane().add(buttonsPanel);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		final JButton btnClear = new JButton("CLEAR");
		btnClear.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/coder/hms/icons/login_clear.png")));
		btnClear.setForeground(new Color(220, 20, 60));
		btnClear.setOpaque(true);
		btnClear.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClear.setPreferredSize(new Dimension(110, 40));
		btnClear.setFont(new Font("Verdana", Font.BOLD, 15));
		mouseListenerForButtons(btnClear);
		actionListenerForButtons(btnClear);
		buttonsPanel.add(btnClear);

		final JButton btnLogin = new JButton("LOGIN");
		btnLogin.setToolTipText("Press ALT + ENTER keys for shortcut");
		btnLogin.setSelectedIcon(null);
		btnLogin.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/coder/hms/icons/login_key.png")));
		btnLogin.setForeground(new Color(0, 191, 255));
		btnLogin.setOpaque(true);
		btnLogin.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLogin.setMnemonic(KeyEvent.VK_ENTER);
		btnLogin.setPreferredSize(new Dimension(110, 40));
		btnLogin.setFont(new Font("Verdana", Font.BOLD, 15));
		mouseListenerForButtons(btnLogin);
		actionListenerForButtons(btnLogin);
		buttonsPanel.add(btnLogin);

		//header label
		final JLabel jumbotronLabel = new JLabel("HOTEL MANAGEMENT SYSTEM");
		jumbotronLabel.setForeground(new Color(255, 255, 255));
		jumbotronLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(70, 130, 180)));
		jumbotronLabel.setBackground(new Color(135, 206, 235));
		jumbotronLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		jumbotronLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jumbotronLabel.setOpaque(true);
		jumbotronLabel.setBounds(28, 11, 358, 47);
		getContentPane().add(jumbotronLabel);
		
		//information label
		infoLabel = new JLabel("");
		infoLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		infoLabel.setForeground(new Color(220, 20, 60));
		infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		infoLabel.setAutoscrolls(true);
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoLabel.setFont(new Font("Consolas", infoLabel.getFont().getStyle(), 15));
		infoLabel.setBounds(1, 218, 428, 18);
		getContentPane().add(infoLabel);

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(false);
		this.setVisible(true);

	}

	public void mouseListenerForButtons(final JButton jButton) {
		
		//change opaque property for making button color changeable
		jButton.setOpaque(true);
		//add listener for buttons when mouse hover, active.
		final MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jButton.setBackground(Color.decode("#8be9fd"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jButton.setBackground(getBackground());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				jButton.setBackground(Color.decode("#0fd0f9"));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				jButton.setBackground(Color.decode("#0fd0f9"));
			}
		};
		//set the button mouse listener this listener
		jButton.addMouseListener(ma);
	}

	public void keyListenerForFields(JTextField textField, JPasswordField passwordField) {
		
		KeyAdapter myAdapter = new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				infoLabel.setText(null);
				super.keyTyped(e);
			}
		};
		
		textField.addKeyListener(myAdapter);
		passwordField.addKeyListener(myAdapter);
		
	}
	
	public void actionListenerForButtons(final JButton jButton) {
		
		final ActionListener myListner = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String userName = userNameField.getText().toLowerCase();
				String userPswrd = String.valueOf(passwordField.getPassword());

				
				if (e.getActionCommand().equalsIgnoreCase("LOGIN")) {
					
					if(userName.length() > 0 || userPswrd.length() > 0) {
						
							final UserDaoImpl userDaoImpl = new UserDaoImpl();
							final boolean check = userDaoImpl.authentication(userName, userPswrd);
							
							if(check) {
								//store informations in bean to use it in another frames.
								sessionBean.setNickName(userName);
								sessionBean.setPassword(userPswrd);
								
								//close this frame
								dispose();
								//open main application frame
								new MainFrame();
								
							}else {
								infoLabel.setText("INFO :BAD CREDENTIALS! Sorry username and password does'nt match !");
							}
					}else {
						//change label font color and warn the user.						
						infoLabel.setForeground(new Color(220, 20, 60));
						infoLabel.setText("INFO : All fields required !");
					}
					
					
				} else if (e.getActionCommand().equalsIgnoreCase("CLEAR")) {
					// clear all fields
					userNameField.setText(null);
					passwordField.setText(null);
					infoLabel.setForeground(Color.decode("#059046"));
					infoLabel.setText("INFO : All fields cleared.");

				}

			}
		};

		jButton.addActionListener(myListner);
	}

}
