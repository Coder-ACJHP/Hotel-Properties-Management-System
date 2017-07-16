package com.coder.hms.userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.coder.hms.actionlisteners.RoomsActionListener;
import com.coder.hms.utils.SpringUtilitiesLayout;

public class AllRoomsFrame {


	private JPanel contentPanel = new JPanel();
	private final RoomsActionListener theAction = new RoomsActionListener();
	int counter = 100;
	int lastNum = 0;
	/**
	 * Create the dialog.
	 */
	public AllRoomsFrame(int roomCount) {

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.decode("#066d95"));
		contentPanel.setLayout(new SpringLayout());
		
		for (int i = 1; i <= roomCount; i++) {
			++lastNum;
			JButton roomBtn = new JButton();
			roomBtn.setFont(new Font("Arial", Font.BOLD, 12));
			roomBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			roomBtn.setText(counter +""+ lastNum);
			
			if(i % 6 == 0) {
				counter+=100; 
				lastNum = 0;
			}
			
			roomBtn.setHorizontalTextPosition(SwingUtilities.LEFT);
			roomBtn.setVerticalAlignment(SwingUtilities.BOTTOM);
			roomBtn.setPreferredSize(new Dimension(100, 60));
			roomBtn.setMaximumSize(new Dimension(100, 60));
			roomBtn.addActionListener(theAction.getActionListener());
			contentPanel.add(roomBtn);
		}

		SpringUtilitiesLayout.makeGrid(contentPanel, 8, 6, 20, 20, 7, 7);
		contentPanel.setVisible(true);
	}
	
	public JPanel getWindow() {
		return this.contentPanel;
	}

	public void setWindow(JPanel thePanel) {
		this.contentPanel = thePanel;
	}

}
