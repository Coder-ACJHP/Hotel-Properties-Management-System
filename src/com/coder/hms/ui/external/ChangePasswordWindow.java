/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.external;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.coder.hms.beans.SessionBean;
import com.coder.hms.daoImpl.UserDaoImpl;
import com.coder.hms.utils.LoggingEngine;

public class ChangePasswordWindow extends JDialog {

	/**
	 * 
	 */
	private int clicked = 0;
	private JLabel infoLabel, markerLbl;
	private JPasswordField passwordField;
	private static SessionBean sessionBean;
	private static final long serialVersionUID = 1L;
	private final UserDaoImpl userDaoImpl;
	private static LoggingEngine loggingEngine;
	private JPasswordField oldPasswordField, newPasswordField;
	private final JButton btnClear, btnUpdate, setPasswordVisible, capslockBtn;

	/**
	 * Create the dialog.
	 */
	public ChangePasswordWindow() {

		loggingEngine = LoggingEngine.getInstance();
		
		sessionBean = SessionBean.getSESSION_BEAN();
		userDaoImpl = new UserDaoImpl();
		
		loggingEngine.setMessage("Change password window is started....");
		loggingEngine.setMessage("User is : " + sessionBean.getNickName());
		
		this.getContentPane().setForeground(new Color(255, 99, 71));
		this.getContentPane().setFocusCycleRoot(true);
		this.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.getContentPane().setFont(new Font("Monospaced", Font.BOLD, 15));
		this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		this.setModal(true);
		this.setResizable(false);

		this.setTitle("Coder HPMSA - [Change Password]");

		this.setSize(410, 250);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.decode("#066d95"));
		this.getContentPane().setLayout(null);

		JLabel oldPwdLbl = new JLabel("Old password : ");
		oldPwdLbl.setForeground(new Color(255, 255, 255));
		oldPwdLbl.setBounds(27, 27, 156, 26);
		oldPwdLbl.setHorizontalAlignment(SwingConstants.LEFT);
		oldPwdLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		oldPwdLbl.setFont(new Font("Verdana", Font.BOLD, 13));
		getContentPane().add(oldPwdLbl);

		JLabel lblNewPassword = new JLabel("New password : ");
		lblNewPassword.setForeground(new Color(255, 255, 255));
		lblNewPassword.setBounds(27, 64, 156, 25);
		lblNewPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewPassword.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewPassword.setFont(new Font("Verdana", Font.BOLD, 13));
		getContentPane().add(lblNewPassword);

		JLabel lblConfirmNewPassword = new JLabel("Confirm new pwd : ");
		lblConfirmNewPassword.setBounds(27, 100, 156, 26);
		lblConfirmNewPassword.setForeground(new Color(255, 255, 255));
		lblConfirmNewPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmNewPassword.setHorizontalTextPosition(SwingConstants.CENTER);
		lblConfirmNewPassword.setFont(new Font("Verdana", Font.BOLD, 13));
		getContentPane().add(lblConfirmNewPassword);

		infoLabel = new JLabel("");
		infoLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		infoLabel.setForeground(new Color(220, 20, 60));
		infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		infoLabel.setAutoscrolls(true);
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoLabel.setFont(new Font("Consolas", infoLabel.getFont().getStyle(), 15));
		infoLabel.setBounds(7, 196, 397, 28);
		getContentPane().add(infoLabel);

		oldPasswordField = new JPasswordField();
		oldPasswordField.setHorizontalAlignment(SwingConstants.LEFT);
		oldPasswordField.setBounds(190, 27, 176, 26);
		keyListenerForFields(oldPasswordField);
		getContentPane().add(oldPasswordField);

		newPasswordField = new JPasswordField();
		newPasswordField.setHorizontalAlignment(SwingConstants.LEFT);
		newPasswordField.setBounds(190, 64, 176, 25);
		keyListenerForFields(newPasswordField);
		getContentPane().add(newPasswordField);

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(190, 101, 176, 25);
		keyListenerForFields(passwordField);
		getContentPane().add(passwordField);

		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(117, 142, 277, 49);
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
			oldPasswordField.setText("");
			newPasswordField.setText("");
			passwordField.setText("");
			markerLbl.setVisible(false);
			infoLabel.setForeground(Color.decode("#059046"));
			infoLabel.setText("All blanks are cleared.");
		});
		buttonsPanel.add(btnClear);

		btnUpdate = new JButton("UPDATE");
		btnUpdate.setToolTipText("Press ALT + ENTER keys for shortcut");
		btnUpdate.setSelectedIcon(null);
		btnUpdate.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/coder/hms/icons/login_key.png")));
		btnUpdate.setForeground(new Color(0, 191, 255));
		btnUpdate.setOpaque(true);
		btnUpdate.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnUpdate.setMnemonic(KeyEvent.VK_ENTER);
		btnUpdate.setPreferredSize(new Dimension(110, 40));
		btnUpdate.setFont(new Font("Verdana", Font.BOLD, 15));
		btnUpdate.addActionListener(updatePassword());
		buttonsPanel.add(btnUpdate);

		setPasswordVisible = new JButton("");
		setPasswordVisible.setHorizontalTextPosition(SwingConstants.CENTER);
		setPasswordVisible.setToolTipText("Make password visible");
		setPasswordVisible.setOpaque(false);
		setPasswordVisible
				.setIcon(new ImageIcon(ChangePasswordWindow.class.getResource("/com/coder/hms/icons/login_show_pwd.png")));
		setPasswordVisible.setPreferredSize(new Dimension(16, 16));
		setPasswordVisible.setMaximumSize(new Dimension(16, 16));
		setPasswordVisible.setMinimumSize(new Dimension(16, 16));
		setPasswordVisible.setBounds(376, 66, 16, 16);
		setPasswordVisible.addMouseListener(setVisible());

		getContentPane().add(setPasswordVisible);

		markerLbl = new JLabel("*");
		markerLbl.setVisible(false);
		markerLbl.setForeground(Color.RED);
		markerLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		markerLbl.setBounds(376, 33, 16, 16);
		getContentPane().add(markerLbl);
		
		capslockBtn = new JButton("");
		capslockBtn.setIcon(new ImageIcon(ChangePasswordWindow.class.getResource("/com/coder/hms/icons/login_capslock.png")));
		capslockBtn.setToolTipText("CAPS_LOCK status.");
		capslockBtn.setPreferredSize(new Dimension(16, 16));
		capslockBtn.setOpaque(false);
		capslockBtn.setMinimumSize(new Dimension(16, 16));
		capslockBtn.setMaximumSize(new Dimension(16, 16));
		capslockBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		capslockBtn.setBounds(378, 94, 16, 16);
		getContentPane().add(capslockBtn);

		final KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	private ActionListener updatePassword() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				infoLabel.setForeground(new Color(220, 20, 60));

				final char[] oldPwd = oldPasswordField.getPassword();
				final char[] newPwd = newPasswordField.getPassword();
				final char[] confirmPwd = passwordField.getPassword();

				if (oldPwd.length > 0 && newPwd.length > 0 && confirmPwd.length > 0) {

					final String oldPassword = new String(oldPwd);
					
					loggingEngine.setMessage("User : " + sessionBean.getNickName() + "old password is : " + oldPassword);
					
					if (checkPassword(oldPassword)) {
						
						final String newPassword = new String(newPwd);
						final String confirmPassword = new String(confirmPwd);
						
						if (newPassword.equals(confirmPassword)) {
							
							loggingEngine.setMessage("User : " + sessionBean.getNickName() + "new password is : " + newPassword);
							
							changePasswordWithNew(sessionBean.getNickName(), newPassword);
							infoLabel.setText("Your password changed successfully.");
							
							loggingEngine.setMessage("Password changed successfully.");
						}

						else {
							infoLabel.setText("New password and confirm must be same!");
						}
					}

					else {
						markerLbl.setVisible(true);
						infoLabel.setText("Could'nt found your old password!");
					}

				}

				else {
					infoLabel.setText("Please fill all blanks!");
				}

			}
		};
		return listener;
	}

	private MouseListener setVisible() {
		final MouseAdapter listener = new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(clicked != 0) {
					passwordField.setEchoChar('\u25CF');
					oldPasswordField.setEchoChar('\u25CF');
					newPasswordField.setEchoChar('\u25CF');
					clicked = 0;
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (clicked == 0) {
					passwordField.setEchoChar((char) 0);
					oldPasswordField.setEchoChar((char) 0);
					newPasswordField.setEchoChar((char) 0);
					clicked++;
				}
			}
		};
		return listener;
	}
	
	public void keyListenerForFields(JPasswordField passwordField) {
		KeyAdapter myAdapter = new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				infoLabel.setText(null);
				markerLbl.setVisible(false);
				super.keyTyped(e);
			}
		};
		passwordField.addKeyListener(myAdapter);
	}

	private void changePasswordWithNew(String nickName, String newPassword) {
		userDaoImpl.changePasswordOfUser(nickName, newPassword);
		infoLabel.setForeground(Color.decode("#059046"));
		infoLabel.setText("Your password changed successfully.");
	}

	private boolean checkPassword(String oldPassword) {
		// ask from database is correct?
		boolean correct = userDaoImpl.authentication(sessionBean.getNickName(), oldPassword);
		System.out.println("checkPassword(String oldPassword) : "+ correct);
		return correct;
	}
	
	  private class MyDispatcher implements KeyEventDispatcher {
	        @Override
	        public boolean dispatchKeyEvent(KeyEvent e) {
	            if (e.getID() == KeyEvent.KEY_PRESSED) {
	                boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
	                
	                if(isOn) {
	                	capslockBtn.setBackground(Color.RED);
	                	capslockBtn.revalidate();
	                	capslockBtn.repaint();
	                }
	                
	            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
	            	 boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
		                
		                if(isOn == false) {
		                	capslockBtn.setBackground(getBackground());
		                	capslockBtn.revalidate();
		                	capslockBtn.repaint();
		                }
	            	
	            }
	            return false;
	        }
	    }
}
