package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class InformationFrame extends JFrame {

	/**
	 * 
	 */
	public JButton okBtn;
	private JLabel lblMessage, lblIcon;
	private static final long serialVersionUID = 1L;
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";

	public InformationFrame() {
		
		setType(Type.POPUP);
		setResizable(false);
		
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		this.setTitle("Approving question");
		this.setPreferredSize(new Dimension(340, 150));
		this.setAlwaysOnTop(isAlwaysOnTopSupported());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation(screenSize.width / 2 - 150, screenSize.height / 2 - 75);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(InformationFrame.class.getResource(LOGOPATH)));
		
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		okBtn = new JButton("OK");
		okBtn.setIcon(new ImageIcon(InformationFrame.class.getResource("/com/coder/hms/icons/info_ok.png")));
		okBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		okBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		okBtn.setBounds(121, 82, 132, 35);
		okBtn.addActionListener(getAction());
		panel.add(okBtn);
		
		lblMessage = new JLabel("");
		lblMessage.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMessage.setHorizontalAlignment(SwingConstants.LEFT);
		lblMessage.setBounds(95, 21, 227, 49);
		panel.add(lblMessage);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(InformationFrame.class.getResource("/com/coder/hms/icons/dialogPane_question.png")));
		lblIcon.setBounds(14, 21, 69, 70);
		panel.add(lblIcon);
		
		this.pack();
	}
	
	public void setMessage(String message) {
		
		if(message.length() > 34) {
			int flag = 0;
			final StringBuilder sb = new StringBuilder();
			char[] arr = message.toCharArray();
			
			for (int i = 0; i < arr.length; i++) {
				
				sb.append(arr[i]);
				
				if(i >= 34) {
					if(arr[i] == ' ' && flag != 1) {
						sb.append("<br>");
						flag++;
					}else {
						continue;
					}
				}
			}
			message = sb.toString();
			lblMessage.setText("<html>" + message + "</html>");
		}
		
		else {
			lblMessage.setText(message);
		}
	}
	
	private ActionListener getAction() {
		final ActionListener abstractAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
		return abstractAction;
	}
}
