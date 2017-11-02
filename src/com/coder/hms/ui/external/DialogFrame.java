package com.coder.hms.ui.external;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class DialogFrame extends JFrame {

	/**
	 * 
	 */
	public JButton btnYes, btnNo;
	private JLabel lblIcon;
	private JTextArea textArea;
	private static final long serialVersionUID = 1L;
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";

	public DialogFrame() {
		
		setType(Type.POPUP);
		setResizable(false);
		
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		this.setTitle("Approving question");
		this.setPreferredSize(new Dimension(400, 190));
		this.setAlwaysOnTop(isAlwaysOnTopSupported());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation(screenSize.width / 2 - 150, screenSize.height / 2 - 75);
		
		this.setIconImage(Toolkit.getDefaultToolkit().
				getImage(getClass().getResource(LOGOPATH)));
		
		final JPanel panel = new JPanel();
		panel.setAutoscrolls(true);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		btnYes = new JButton("YES");
		btnYes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnYes.setBounds(291, 129, 91, 29);
		panel.add(btnYes);
		
		btnNo = new JButton("NO");
		btnNo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNo.setBounds(199, 129, 91, 29);
		panel.add(btnNo);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(DialogFrame.class.getResource("/com/coder/hms/icons/dialogPane_question.png")));
		lblIcon.setBounds(14, 40, 69, 70);
		panel.add(lblIcon);
		
		
		textArea = new JTextArea();
		textArea.setDisabledTextColor(new Color(153, 204, 255));
		textArea.setBounds(95, 32, 287, 85);
		textArea.setBackground(UIManager.getColor("ComboBox.background"));
		textArea.setBorder(null);
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setLineWrap(true);
		panel.add(textArea);
		
		this.pack();
	}
	
	public void setMessage(String message) {
		
		textArea.setText(message);
	}
}
