package com.coder.hms.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.coder.hms.usrinterface.DialogFrame;

public class DialogTest extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblKj;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogTest dialog = new DialogTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogTest() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblKj = new JLabel("kj<hxzh<kzjx");
			lblKj.setHorizontalTextPosition(SwingConstants.CENTER);
			lblKj.setHorizontalAlignment(SwingConstants.CENTER);
			lblKj.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblKj.setPreferredSize(new Dimension(200, 50));
			contentPanel.add(lblKj);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						DialogFrame dialog = new DialogFrame();
						dialog.setMessage("Are you sure?");
						dialog.setVisible(true);
						dialog.btnYes.addActionListener(ActionListener ->{
							setLabelText("yes");
							dialog.dispose();
						});
						dialog.btnNo.addActionListener(ActionListener -> {
							setLabelText("no");
							dialog.dispose();
						});
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private void setLabelText(String text) {
		lblKj.setText(text);
		lblKj.revalidate();
		lblKj.repaint();
	}

}
