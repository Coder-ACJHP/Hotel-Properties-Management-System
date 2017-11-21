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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.daoImpl.UserDaoImpl;
import com.coder.hms.entities.User;
import com.coder.hms.utils.EmailValidator;
import com.coder.hms.utils.SendEmailToUser;

public class SendEmailWindow extends JDialog {

	/**
	 * 
	 */
	private boolean isValid = false;
	private JLabel infoLabel, markerLbl;
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDaoImpl;
	private final JButton btnCancel, sendeEmail;
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public SendEmailWindow() {
		
		this.getContentPane().setForeground(new Color(255, 99, 71));
		this.getContentPane().setFocusCycleRoot(true);
		this.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.getContentPane().setFont(new Font("Monospaced", Font.BOLD, 15));
		this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		this.setModal(true);
		this.setResizable(false);

		this.setTitle("Coder HPMSA - [Send Email For Password]");

		this.setSize(410, 173);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.decode("#066d95"));
		this.getContentPane().setLayout(null);

		JLabel oldPwdLbl = new JLabel("Your email : ");
		oldPwdLbl.setForeground(new Color(255, 255, 255));
		oldPwdLbl.setBounds(27, 27, 107, 26);
		oldPwdLbl.setHorizontalAlignment(SwingConstants.LEFT);
		oldPwdLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		oldPwdLbl.setFont(new Font("Verdana", Font.BOLD, 13));
		getContentPane().add(oldPwdLbl);

		infoLabel = new JLabel("");
		infoLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		infoLabel.setForeground(new Color(220, 20, 60));
		infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		infoLabel.setAutoscrolls(true);
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoLabel.setFont(new Font("Consolas", infoLabel.getFont().getStyle(), 15));
		infoLabel.setBounds(0, 118, 410, 28);
		getContentPane().add(infoLabel);

		final JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(133, 67, 277, 49);
		buttonsPanel.setForeground(new Color(95, 158, 160));
		buttonsPanel.setBackground(Color.decode("#066d95"));
		getContentPane().add(buttonsPanel);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnCancel = new JButton("CANCEL");
		btnCancel.setIcon(new ImageIcon(LoginWindow.class.getResource("/com/coder/hms/icons/login_clear.png")));
		btnCancel.setForeground(new Color(220, 20, 60));
		btnCancel.setOpaque(true);
		btnCancel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancel.setPreferredSize(new Dimension(110, 40));
		btnCancel.setFont(new Font("Verdana", Font.BOLD, 15));
		btnCancel.addActionListener(ActionListener -> {
			dispose();
		});
		buttonsPanel.add(btnCancel);

		sendeEmail = new JButton("SEND EMAIL");
		sendeEmail.setToolTipText("Press ALT + ENTER keys for shortcut");
		sendeEmail.setSelectedIcon(null);
		sendeEmail.setIcon(new ImageIcon(SendEmailWindow.class.getResource("/com/coder/hms/icons/manubar_sendMail.png")));
		sendeEmail.setForeground(new Color(0, 191, 255));
		sendeEmail.setOpaque(true);
		sendeEmail.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		sendeEmail.setMnemonic(KeyEvent.VK_ENTER);
		sendeEmail.setPreferredSize(new Dimension(140, 40));
		sendeEmail.setFont(new Font("Verdana", Font.BOLD, 15));
		sendeEmail.addActionListener(sendEmailListener());
		buttonsPanel.add(sendeEmail);

		markerLbl = new JLabel("*");
		markerLbl.setVisible(false);
		markerLbl.setForeground(Color.RED);
		markerLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		markerLbl.setBounds(376, 33, 16, 16);
		getContentPane().add(markerLbl);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				final EmailValidator validator = new EmailValidator();
				if(validator.validate(textField.getText())) {
					markerLbl.setVisible(false);
					infoLabel.setText("");
					isValid = true;
				}
				else {
					markerLbl.setVisible(true);
					infoLabel.setText("Invalid email format!");
				}
			}
		});
		textField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField.setBounds(133, 28, 231, 26);
		getContentPane().add(textField);
		textField.setColumns(10);

		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	private ActionListener sendEmailListener() {
		ActionListener listener = (ActionEvent e) -> {
                    final String theEmail = textField.getText();
                    
                    if(theEmail.length() > 0 && isValid) {
                        
                        userDaoImpl = new UserDaoImpl();
                        final User user = userDaoImpl.getUserByEmail(theEmail);
                        
                        if(user.getId() > 0) {
                            
                            try {
                                
                                final SendEmailToUser sendEmail = new SendEmailToUser();
                                sendEmail.setReadyForEmail("java.arabic.community@gmail.com", "community");
                                sendEmail.setFrom("java.arabic.community@gmail.com", user.getEmail());
                                sendEmail.setEmailBody("Remind of password", "Your password is : " + user.getPassword());
                                sendEmail.sendTheEmail();
                                
                            } catch (RuntimeException ex) {
                                final InformationFrame dialog = new InformationFrame();
                                dialog.setMessage("Email sending error!Please again later.");
                                dialog.setVisible(true);
                                new DataSourceFactory().getTransaction().rollback();
                            }
                            
                            cleanFields();
                            infoLabel.setForeground(Color.GREEN);
                            infoLabel.setText("Your password has been sent to your e-mail address, please check your mail box.");
                        }
                        else {
                            
                            infoLabel.setText("Email address does not match your account!");
                            return;
                        }
                    }
                    
                    else {
                        infoLabel.setText("Email address field must be filled!");
                        return;
                    }
                };
		return listener;
	}

	private void cleanFields() {
		textField.setText(" ");
	}
}
