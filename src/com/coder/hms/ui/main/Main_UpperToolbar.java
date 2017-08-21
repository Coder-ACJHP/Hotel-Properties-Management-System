/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.ui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.coder.hms.beans.LocaleBean;
import com.coder.hms.ui.inner.AllRooms_ColorInfoTable;
import com.coder.hms.utils.ResourceControl;

public class Main_UpperToolbar {

	private JPanel panel;
	private Main_Audit audit;
	private Main_AllRooms theRooms;
	private Main_CashDesk cashdesk;
	private Main_Blockade blockadeFrame;
	private static LocaleBean bean;
	private Main_Reservations rezervFrame;
	private Main_RoomCleaning cleaningFrame;
	private Main_CustomersFrame customersFrame;
	private AllRooms_ColorInfoTable infoColorTable;
	private JButton roomsBtn, guestsBtn, rezervationBtn, blockadeBtn, roomCleaningBtn, cashBtn, auditBtn, refreshBtn;

	
	public Main_UpperToolbar(final JPanel mainPanel) {

		bean = LocaleBean.getInstance();
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.activeCaption);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAutoscrolls(true);
		panel.setPreferredSize(new Dimension(1224, 55));
		panel.setLayout(null);

		roomsBtn = new JButton("Rooms Plain");
		roomsBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		roomsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		roomsBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_room.png")));
		roomsBtn.setBounds(10, 7, 137, 40);
		roomsBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		roomsBtn.setFont(new Font("Dialog", Font.BOLD, 13));
		roomsBtn.setPreferredSize(new Dimension(200, 40));
		roomsBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(roomsBtn);

		guestsBtn = new JButton("Guests");
		guestsBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		guestsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		guestsBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_guests.png")));
		guestsBtn.setBounds(157, 7, 137, 40);
		guestsBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		guestsBtn.setFont(new Font("Dialog", Font.BOLD, 13));
		guestsBtn.setPreferredSize(new Dimension(200, 40));
		guestsBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(guestsBtn);

		rezervationBtn = new JButton("Reservations");
		rezervationBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		rezervationBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rezervationBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_rezerv.png")));
		rezervationBtn.setBounds(304, 7, 137, 40);
		rezervationBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		rezervationBtn.setFont(new Font("Dialog", Font.BOLD, 13));
		rezervationBtn.setPreferredSize(new Dimension(200, 40));
		rezervationBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(rezervationBtn);

		blockadeBtn = new JButton("Blockade");
		blockadeBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		blockadeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		blockadeBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_blockade.png")));
		blockadeBtn.setBounds(451, 7, 137, 40);
		blockadeBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		blockadeBtn.setFont(new Font("Dialog", Font.BOLD, 13));
		blockadeBtn.setPreferredSize(new Dimension(200, 40));
		blockadeBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(blockadeBtn);

		roomCleaningBtn = new JButton("Room Cleaning");
		roomCleaningBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		roomCleaningBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		roomCleaningBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/login_clear.png")));
		roomCleaningBtn.setBounds(599, 7, 137, 40);
		roomCleaningBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		roomCleaningBtn.setFont(new Font("Dialog", Font.BOLD, 13));
		roomCleaningBtn.setPreferredSize(new Dimension(200, 40));
		roomCleaningBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(roomCleaningBtn);

		cashBtn = new JButton("Cash Desk");
		cashBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		cashBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cashBtn.setIcon(new ImageIcon(MainFrame.class.getResource("/com/coder/hms/icons/main_cash.png")));
		cashBtn.setBounds(746, 7, 137, 40);
		cashBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		cashBtn.setFont(new Font("Dialog", Font.BOLD, 13));
		cashBtn.setPreferredSize(new Dimension(200, 40));
		cashBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(cashBtn);

		final JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(889, 6, 13, 43);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setFocusable(true);
		separator.setForeground(Color.DARK_GRAY);
		separator.setAutoscrolls(true);
		separator.setPreferredSize(new Dimension(10, 20));
		panel.add(separator);

		auditBtn = new JButton("Audit");
		auditBtn.setIcon(new ImageIcon(Main_UpperToolbar.class.getResource("/com/coder/hms/icons/main_audit.png")));
		auditBtn.setPreferredSize(new Dimension(200, 40));
		auditBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		auditBtn.setFont(new Font("Dialog", Font.BOLD, 13));
		auditBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		auditBtn.setBounds(906, 7, 137, 40);
		auditBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(auditBtn);
		
		JSeparator secondSeparator = new JSeparator();
		secondSeparator.setPreferredSize(new Dimension(10, 20));
		secondSeparator.setOrientation(SwingConstants.VERTICAL);
		secondSeparator.setForeground(Color.DARK_GRAY);
		secondSeparator.setFocusable(true);
		secondSeparator.setBackground(Color.DARK_GRAY);
		secondSeparator.setAutoscrolls(true);
		secondSeparator.setBounds(1049, 6, 13, 43);
		panel.add(secondSeparator);
		
		refreshBtn = new JButton("");
		refreshBtn.setToolTipText("Refresh the application main window.");
		refreshBtn.setMnemonic(KeyEvent.VK_F5);
		refreshBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		refreshBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		refreshBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		refreshBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		refreshBtn.setIcon(new ImageIcon(Main_UpperToolbar.class.getResource("/com/coder/hms/icons/menubar_exchange_calculate.png")));
		refreshBtn.setBounds(1066, 7, 55, 40);
		refreshBtn.setActionCommand("Refresh");
		refreshBtn.addActionListener(UpperToolbarActionListener(mainPanel));
		panel.add(refreshBtn);
		
		changeLanguage(bean.getLocale());

	}

	private void changeLanguage(Locale locale) {

		final ResourceBundle bundle = ResourceBundle
		.getBundle("com/coder/hms/languages/LocalizationBundle", locale, new ResourceControl());
		this.roomsBtn.setText(bundle.getString("RoomsPlan"));
		this.guestsBtn.setText(bundle.getString("Guests"));
		this.auditBtn.setText(bundle.getString("Audit"));
		this.blockadeBtn.setText(bundle.getString("Blockade"));
		this.rezervationBtn.setText(bundle.getString("Reservations"));
		this.roomCleaningBtn.setText(bundle.getString("RoomCleaning"));
		this.cashBtn.setText(bundle.getString("CashDesk"));

		panel.revalidate();
		panel.repaint();
	}
	
	public ActionListener UpperToolbarActionListener(final JPanel mainPanel) {

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == roomsBtn) {

					theRooms = new Main_AllRooms();
					infoColorTable = new AllRooms_ColorInfoTable();

					// Set the usage of room into info table
					infoColorTable.setCleanLabelCount(theRooms.cleanCounter);
					infoColorTable.setDirtyLabelCount(theRooms.dirtyCounter);
					infoColorTable.setDndLabelCount(theRooms.dndCounter);

					mainPanel.removeAll();
					mainPanel.add(theRooms.getWindow(), BorderLayout.WEST);
					mainPanel.add(infoColorTable, BorderLayout.EAST);
					mainPanel.revalidate();
					mainPanel.repaint();

				}

				else if (e.getSource() == guestsBtn) {

					customersFrame = new Main_CustomersFrame();
					mainPanel.removeAll();
					mainPanel.add(customersFrame, BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}

				else if (e.getSource() == rezervationBtn) {

					rezervFrame = new Main_Reservations();
					mainPanel.removeAll();
					rezervFrame.populateMainTable();
					mainPanel.add(rezervFrame, BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}

				else if (e.getSource() == blockadeBtn) {

					blockadeFrame = new Main_Blockade();
					mainPanel.removeAll();
					mainPanel.add(blockadeFrame, BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();

				}

				else if (e.getSource() == roomCleaningBtn) {

					cleaningFrame = new Main_RoomCleaning();
					mainPanel.removeAll();
					mainPanel.add(cleaningFrame, BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}

				else if (e.getSource() == cashBtn) {

					cashdesk = new Main_CashDesk();
					mainPanel.removeAll();
					mainPanel.add(cashdesk, BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();
				}

				else if (e.getSource() == auditBtn) {
					
					audit = new Main_Audit();
					audit.initializeAuditPane();
					mainPanel.removeAll();
					mainPanel.add(audit, BorderLayout.CENTER);
					mainPanel.revalidate();
					mainPanel.repaint();

				}
				
				else if(e.getSource() == refreshBtn) {
					mainPanel.removeAll();
					mainPanel.revalidate();
					mainPanel.repaint();
				}
			}
		};
		return actionListener;
	}

	public JPanel getJPanel() {
		return this.panel;
	}
	
}
