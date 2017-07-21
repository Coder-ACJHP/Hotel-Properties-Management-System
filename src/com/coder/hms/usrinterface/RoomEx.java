package com.coder.hms.usrinterface;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;

import com.coder.hms.utils.ApplicationLogo;

public class RoomEx extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ApplicationLogo logoSetter = new ApplicationLogo();
	private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";


	/**
	 * Create the dialog.
	 * @param roomText 
	 */
	public RoomEx(String roomText) {
		// set upper icon for dialog frame
		logoSetter.setApplicationLogoJDialog(this, LOGOPATH);

		getContentPane().setForeground(new Color(255, 99, 71));
		getContentPane().setFocusCycleRoot(true);
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getContentPane().setFont(new Font("Verdana", Font.BOLD, 12));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setResizable(true);

		this.setTitle("Coder for HMS - [RoomEx] : " + roomText);

		/* Set default size of frame */
		final Dimension computerScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		String opSystem = System.getProperty("os.name").toLowerCase();

		if (opSystem.contains("windows") || opSystem.contains("nux")) {
			
			this.setSize(computerScreenSize);
		}else {
			
			final Dimension wantedRoomFrameSize = new Dimension(computerScreenSize.width - 60, computerScreenSize.height -100);
			this.setSize(wantedRoomFrameSize);
		}
		
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.decode("#066d95"));
		getContentPane().setLayout(null);
		
		JLabel lblTheNewRoom = new JLabel("");
		lblTheNewRoom.setText("<html>The new room frame"+"<br/>"+"(in development phase!)</html>");
		lblTheNewRoom.setFont(new Font("Lucida Grande", Font.PLAIN, 44));
		lblTheNewRoom.setForeground(Color.WHITE);
		lblTheNewRoom.setBounds(196, 126, 565, 280);
		getContentPane().add(lblTheNewRoom);
		
		this.setVisible(true);
	}
}
