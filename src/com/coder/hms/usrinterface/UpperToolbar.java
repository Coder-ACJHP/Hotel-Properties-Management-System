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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.daoImpl.HotelDaoImpl;


public class UpperToolbar {

	private JPanel panel;
	private CustomAllRooms theRooms;
	private CustomBlockade blockadeFrame;
	private CustomersFrame customersFrame;
	private CustomCashDesk cashdesk; 
	private CustomReservations rezervFrame;
	private CustomRoomCleaning cleaningFrame;
	private final HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
	private JButton roomsBtn, guestsBtn, rezervationBtn, blockadeBtn, roomCleaningBtn, cashBtn;
	private JButton refreshBtn;

	public JPanel getJPanel() {
		return this.panel;
	}

	public UpperToolbar(final JFrame mainFrame) {

		initializeAllFrames();
		
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

		roomCleaningBtn = new JButton("Room Cleaning");
		roomCleaningBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		roomCleaningBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		roomCleaningBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/login_clear.png")));
		roomCleaningBtn.setBounds(599, 7, 137, 40);
		roomCleaningBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		roomCleaningBtn.setFont(new Font("Arial", Font.BOLD, 12));
		roomCleaningBtn.setPreferredSize(new Dimension(200, 40));
		roomCleaningBtn.addActionListener(UpperToolbarActionListener(mainFrame));
		panel.add(roomCleaningBtn);
		
		cashBtn = new JButton("Cash Desk");
		cashBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		cashBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cashBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_cash.png")));
		cashBtn.setBounds(746, 7, 137, 40);
		cashBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		cashBtn.setFont(new Font("Arial", Font.BOLD, 12));
		cashBtn.setPreferredSize(new Dimension(200, 40));
		cashBtn.addActionListener(UpperToolbarActionListener(mainFrame));
		panel.add(cashBtn);	
		
		final JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(888, 6, 10, 43);
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
		refreshBtn.addActionListener(UpperToolbarActionListener(mainFrame));
		panel.add(refreshBtn);
		

	}

	private synchronized void initializeAllFrames() {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				final int roomCount =  hotelDaoImpl.getHotel().getRoomCapacity();
				theRooms = new CustomAllRooms(roomCount);
				customersFrame = new CustomersFrame();
				blockadeFrame = new CustomBlockade();
				rezervFrame = new CustomReservations();
				cashdesk = new CustomCashDesk();
				cleaningFrame = new CustomRoomCleaning();
			}
		});

	}
	
	public ActionListener UpperToolbarActionListener(final JFrame mainFrame){

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String command = e.getActionCommand();

				if (command.equalsIgnoreCase("Rooms Plain")) {
					
					mainFrame.remove(cashdesk);
					mainFrame.remove(rezervFrame);
					mainFrame.remove(cleaningFrame);
					mainFrame.remove(blockadeFrame);
					mainFrame.remove(customersFrame);
					mainFrame.getContentPane().add(theRooms.getWindow(), BorderLayout.WEST);
					SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane());

				}

				else if (command.equalsIgnoreCase("Guests")) {
					
					mainFrame.remove(cashdesk);
					mainFrame.remove(rezervFrame);
					mainFrame.remove(blockadeFrame);
					mainFrame.remove(cleaningFrame);
					mainFrame.remove(theRooms.getWindow());
					mainFrame.getContentPane().add(customersFrame, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(mainFrame);
				}

				else if (command.equalsIgnoreCase("Reservations")) {

					mainFrame.remove(cashdesk);
					mainFrame.remove(customersFrame);
					mainFrame.remove(blockadeFrame);
					mainFrame.remove(cleaningFrame);
					mainFrame.remove(theRooms.getWindow());
					
					//populate the reservation table when we opening it
					rezervFrame.populateMainTable();
					
					mainFrame.getContentPane().add(rezervFrame, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(mainFrame);
				}

				else if (command.equalsIgnoreCase("Blockade")) {
					
					mainFrame.remove(cashdesk);
					mainFrame.remove(rezervFrame);
					mainFrame.remove(customersFrame);
					mainFrame.remove(cleaningFrame);
					mainFrame.remove(theRooms.getWindow());
					mainFrame.getContentPane().add(blockadeFrame, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane());
					
				}
				
				else if (command.equalsIgnoreCase("Room Cleaning")) {
					
					mainFrame.remove(cashdesk);
					mainFrame.remove(rezervFrame);
					mainFrame.remove(blockadeFrame);
					mainFrame.remove(customersFrame);
					mainFrame.remove(theRooms.getWindow());
					mainFrame.getContentPane().add(cleaningFrame, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane());
				}
				
				else if (command.equalsIgnoreCase("Cash Desk")) {
					
					mainFrame.remove(rezervFrame);
					mainFrame.remove(blockadeFrame);
					mainFrame.remove(customersFrame);
					mainFrame.remove(cleaningFrame);
					mainFrame.remove(theRooms.getWindow());
					mainFrame.getContentPane().add(cashdesk, BorderLayout.CENTER);
					SwingUtilities.updateComponentTreeUI(mainFrame.getContentPane());
				}
				
				else if (command.equalsIgnoreCase("Refresh")) {
					DataSourceFactory dFactory = new DataSourceFactory();
					dFactory.getSessionFactory().openSession();
				}
				
			}
		};
		
		return actionListener;
	}

}
