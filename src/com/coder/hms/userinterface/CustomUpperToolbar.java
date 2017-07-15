package com.coder.hms.userinterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


public class CustomUpperToolbar {

	private JPanel panel;
	private Rooms theRooms;
	private BlockadeFrame blockadeFrame;
	private CustomersFrame customersFrame;
	private RezervationsFrame rezervFrame;
	private JButton roomsBtn, guestsBtn, rezervationBtn, blockadeBtn, cashBtn;

	public JPanel getJPanel() {
		return this.panel;
	}

	public CustomUpperToolbar(final JFrame mainFrame) {

		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.activeCaption);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAutoscrolls(true);
		panel.setPreferredSize(new Dimension(10, 55));
		panel.setLayout(null);

		roomsBtn = new JButton("Rooms Plain");
		roomsBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		roomsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		roomsBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_room.png")));
		roomsBtn.setBounds(10, 7, 137, 40);
		roomsBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		roomsBtn.setFont(new Font("Arial", Font.BOLD, 12));
		roomsBtn.setPreferredSize(new Dimension(200, 40));
		roomsBtn.addActionListener(UpperToolbarActionListener(mainFrame));
		panel.add(roomsBtn);

		guestsBtn = new JButton("Guests");
		guestsBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		guestsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		guestsBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_guests.png")));
		guestsBtn.setBounds(157, 7, 137, 40);
		guestsBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		guestsBtn.setFont(new Font("Arial", Font.BOLD, 12));
		guestsBtn.setPreferredSize(new Dimension(200, 40));
		guestsBtn.addActionListener(UpperToolbarActionListener(mainFrame));
		panel.add(guestsBtn);

		rezervationBtn = new JButton("Reservations");
		rezervationBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		rezervationBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rezervationBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_rezerv.png")));
		rezervationBtn.setBounds(304, 7, 137, 40);
		rezervationBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		rezervationBtn.setFont(new Font("Arial", Font.BOLD, 12));
		rezervationBtn.setPreferredSize(new Dimension(200, 40));
		rezervationBtn.addActionListener(UpperToolbarActionListener(mainFrame));
		panel.add(rezervationBtn);
		
		blockadeBtn = new JButton("Blockade");
		blockadeBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		blockadeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blockadeBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_blockade.png")));
		blockadeBtn.setBounds(451, 7, 137, 40);
		blockadeBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		blockadeBtn.setFont(new Font("Arial", Font.BOLD, 12));
		blockadeBtn.setPreferredSize(new Dimension(200, 40));
		blockadeBtn.addActionListener(UpperToolbarActionListener(mainFrame));
		panel.add(blockadeBtn);

		cashBtn = new JButton("Cash Desk");
		cashBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		cashBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cashBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_cash.png")));
		cashBtn.setBounds(599, 7, 137, 40);
		cashBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		cashBtn.setFont(new Font("Arial", Font.BOLD, 12));
		cashBtn.setPreferredSize(new Dimension(200, 40));
		cashBtn.addActionListener(UpperToolbarActionListener(mainFrame));
		panel.add(cashBtn);
		
		initializeAllFrames();

	}

	private synchronized void initializeAllFrames() {
		Thread multiFramesThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				theRooms = new Rooms(48);
				customersFrame = new CustomersFrame();
				blockadeFrame = new BlockadeFrame();
				rezervFrame = new RezervationsFrame();
			}
		});
		
		multiFramesThread.start();
	}
	
	public ActionListener UpperToolbarActionListener(final JFrame mainFrame){

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String command = e.getActionCommand();

				if (command.equalsIgnoreCase("Rooms Plain")) {
					
					mainFrame.remove(rezervFrame);
					mainFrame.remove(customersFrame);
					mainFrame.remove(blockadeFrame);
					mainFrame.getContentPane().add(theRooms.getWindow(), BorderLayout.WEST);
					SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane());

				}

				else if (command.equalsIgnoreCase("Guests")) {
					
					mainFrame.remove(rezervFrame);
					mainFrame.remove(blockadeFrame);
					mainFrame.remove(theRooms.getWindow());
					mainFrame.add(customersFrame, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(mainFrame);
				}

				else if (command.equalsIgnoreCase("Reservations")) {

					mainFrame.remove(customersFrame);
					mainFrame.remove(blockadeFrame);
					mainFrame.remove(theRooms.getWindow());
					mainFrame.add(rezervFrame, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(mainFrame);
				}

				else if (command.equalsIgnoreCase("Blockade")) {
					
					mainFrame.remove(rezervFrame);
					mainFrame.remove(customersFrame);
					mainFrame.remove(theRooms.getWindow());
					mainFrame.add(blockadeFrame, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane());
					
				}
				
				else if (command.equalsIgnoreCase("Cash Desk")) {
					
					mainFrame.remove(rezervFrame);
					mainFrame.remove(blockadeFrame);
					mainFrame.remove(customersFrame);
					mainFrame.remove(theRooms.getWindow());
				}
				
			}
		};
		
		return actionListener;
	}

}
