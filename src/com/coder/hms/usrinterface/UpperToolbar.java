/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.usrinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


public class UpperToolbar {

	private JPanel panel;
	private CustomAllRooms theRooms;
	private CustomBlockade blockadeFrame;
	private CustomersFrame customersFrame;
	private CustomCashDesk cashdesk; 
	private CustomReservations rezervFrame;
	private CustomRoomCleaning cleaningFrame;
	private JButton roomsBtn, guestsBtn, rezervationBtn, blockadeBtn, roomCleaningBtn, cashBtn, refreshBtn;

	public JPanel getJPanel() {
		return this.panel;
	}

	public UpperToolbar(final JPanel mainPanel) {
				
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.activeCaption);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAutoscrolls(true);
		panel.setPreferredSize(new Dimension(1096, 55));
		panel.setLayout(null);

		roomsBtn = new JButton("Rooms Plain");
		roomsBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		roomsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		roomsBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_room.png")));
		roomsBtn.setBounds(10, 7, 137, 40);
		roomsBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		roomsBtn.setFont(new Font("Arial", Font.BOLD, 12));
		roomsBtn.setPreferredSize(new Dimension(200, 40));
		roomsBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(roomsBtn);

		guestsBtn = new JButton("Guests");
		guestsBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		guestsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		guestsBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_guests.png")));
		guestsBtn.setBounds(157, 7, 137, 40);
		guestsBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		guestsBtn.setFont(new Font("Arial", Font.BOLD, 12));
		guestsBtn.setPreferredSize(new Dimension(200, 40));
		guestsBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(guestsBtn);

		rezervationBtn = new JButton("Reservations");
		rezervationBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		rezervationBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rezervationBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_rezerv.png")));
		rezervationBtn.setBounds(304, 7, 137, 40);
		rezervationBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		rezervationBtn.setFont(new Font("Arial", Font.BOLD, 12));
		rezervationBtn.setPreferredSize(new Dimension(200, 40));
		rezervationBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(rezervationBtn);
		
		blockadeBtn = new JButton("Blockade");
		blockadeBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		blockadeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blockadeBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_blockade.png")));
		blockadeBtn.setBounds(451, 7, 137, 40);
		blockadeBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		blockadeBtn.setFont(new Font("Arial", Font.BOLD, 12));
		blockadeBtn.setPreferredSize(new Dimension(200, 40));
		blockadeBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(blockadeBtn);

		roomCleaningBtn = new JButton("Room Cleaning");
		roomCleaningBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		roomCleaningBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		roomCleaningBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/login_clear.png")));
		roomCleaningBtn.setBounds(599, 7, 137, 40);
		roomCleaningBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		roomCleaningBtn.setFont(new Font("Arial", Font.BOLD, 12));
		roomCleaningBtn.setPreferredSize(new Dimension(200, 40));
		roomCleaningBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(roomCleaningBtn);
		
		cashBtn = new JButton("Cash Desk");
		cashBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		cashBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cashBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_cash.png")));
		cashBtn.setBounds(746, 7, 137, 40);
		cashBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		cashBtn.setFont(new Font("Arial", Font.BOLD, 12));
		cashBtn.setPreferredSize(new Dimension(200, 40));
		cashBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(cashBtn);	
		
		final JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(895, 6, 13, 43);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setFocusable(true);
		separator.setForeground(Color.DARK_GRAY);
		separator.setAutoscrolls(true);
		separator.setPreferredSize(new Dimension(10, 20));
		panel.add(separator);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setIcon(new ImageIcon(UpperToolbar.class.getResource("/com/coder/hms/icons/cleaning-refresh.png")));
		refreshBtn.setPreferredSize(new Dimension(200, 40));
		refreshBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		refreshBtn.setFont(new Font("Arial", Font.BOLD, 12));
		refreshBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		refreshBtn.setBounds(905, 7, 137, 40);
		refreshBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(refreshBtn);
		

	}

	
	public ActionListener UpperToolbarActionListener(final JPanel mainPanel){

		
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String command = e.getActionCommand();

				if (command.equalsIgnoreCase("Rooms Plain")) {
					
					theRooms = new CustomAllRooms();
					mainPanel.removeAll();
					mainPanel.revalidate();
					mainPanel.repaint();
					mainPanel.add(theRooms.getWindow(), BorderLayout.WEST);
					mainPanel.add(new ColorInfoTable(), BorderLayout.EAST);

				}

				else if (command.equalsIgnoreCase("Guests")) {
					
					customersFrame = new CustomersFrame();
					mainPanel.removeAll();
					mainPanel.revalidate();
					mainPanel.repaint();
					mainPanel.add(customersFrame, BorderLayout.CENTER);
				}

				else if (command.equalsIgnoreCase("Reservations")) {

					rezervFrame = new CustomReservations();
					mainPanel.removeAll();
					mainPanel.revalidate();
					mainPanel.repaint();
					rezervFrame.populateMainTable();
					mainPanel.add(rezervFrame, BorderLayout.CENTER);
				}

				else if (command.equalsIgnoreCase("Blockade")) {
					
					blockadeFrame = new CustomBlockade();
					mainPanel.removeAll();
					mainPanel.revalidate();
					mainPanel.repaint();
					mainPanel.add(blockadeFrame, BorderLayout.CENTER);
					
				}
				
				else if (command.equalsIgnoreCase("Room Cleaning")) {
					
					cleaningFrame = new CustomRoomCleaning();
					mainPanel.removeAll();
					mainPanel.revalidate();
					mainPanel.repaint();
					mainPanel.add(cleaningFrame, BorderLayout.CENTER);
				}
				
				else if (command.equalsIgnoreCase("Cash Desk")) {
					
					cashdesk = new CustomCashDesk();
					mainPanel.removeAll();
					mainPanel.revalidate();
					mainPanel.repaint();
					mainPanel.add(cashdesk, BorderLayout.CENTER);
				}
				
				else if (command.equalsIgnoreCase("Refresh")) {
					mainPanel.removeAll();
					mainPanel.revalidate();
					mainPanel.repaint();

				}
				
			}
		};
		
		return actionListener;
	}

}
