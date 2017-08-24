package com.coder.hms.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.coder.hms.ui.external.DialogFrame;
import com.coder.hms.utils.LoggingEngine;

public class DialogTest extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblKj;
	private static LoggingEngine branch;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		branch = LoggingEngine.getInstance();
		branch.setReady(DialogTest.class.getName());
		branch.changeLoggingLevel(Level.FINE);
		branch.setConsoleLogging(false);
		
		
		try {
			DialogTest dialog = new DialogTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			branch.setMessage("I'mjkahskdjhajksdh");
		} catch (Exception e) {
			e.printStackTrace();
			branch.changeLoggingLevel(Level.WARNING);
			branch.setMessage(e.getMessage());
		}
		branch.setMessage("I'm 27638476ng...");
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
