package com.coder.hms.usrinterface;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class DialogFrame extends JFrame {

	/**
	 * 
	 */
	private JButton btnYes, btnNo;
	protected String answer = "";
	private JLabel lblMessage, lblIcon;
	private static final long serialVersionUID = 1L;

	public DialogFrame() {
		
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		this.setTitle("Approving question");
		this.setPreferredSize(new Dimension(340, 150));
		this.setAlwaysOnTop(isAlwaysOnTopSupported());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		btnYes = new JButton("YES");
		btnYes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnYes.setBounds(219, 82, 91, 29);
		btnYes.addActionListener(ActionListener -> {
			answer = "YES";
		});
		panel.add(btnYes);
		
		btnNo = new JButton("NO");
		btnNo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNo.setBounds(127, 82, 91, 29);
		btnNo.addActionListener(ActionListener -> {
			answer = "NO";
		});
		panel.add(btnNo);
		
		lblMessage = new JLabel("");
		lblMessage.setBounds(95, 33, 227, 37);
		panel.add(lblMessage);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(DialogFrame.class.getResource("/com/coder/hms/icons/if_warning.png")));
		lblIcon.setBounds(14, 33, 69, 58);
		panel.add(lblIcon);
		
		this.pack();
	}
	
	public void setMessage(String message) {
		lblMessage.setText(message);
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
}
