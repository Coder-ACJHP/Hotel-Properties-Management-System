/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.usrinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.coder.hms.actionlisteners.RoomsAction;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Room;

public class CustomAllRooms {


	private final List<Room> roomList;
	private final RoomDaoImpl roomDaoImpl;
	private JPanel contentPanel = new JPanel();
	private final RoomsAction theAction = new RoomsAction();
	int counter = 100;
	int lastNum = 0;
	/**
	 * Create the dialog.
	 */
	public CustomAllRooms(int roomCount) {

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.decode("#066d95"));
		contentPanel.setPreferredSize(new Dimension(700, 1000));
		contentPanel.setLayout(new FlowLayout());
		
		roomDaoImpl = new RoomDaoImpl();
		roomList = roomDaoImpl.getAllRooms();
		
		for (int i = 1; i <= roomCount; i++) {
			++lastNum;
			final JButton roomBtn = new JButton();

			roomBtn.setFont(new Font("Arial", Font.BOLD, 12));
			roomBtn.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			roomBtn.setText(counter +""+ lastNum);
			
			for(Room room: roomList) {
				if(room.getNumber().equals(counter +""+ lastNum))
					roomBtn.setToolTipText(room.getType());
			}
			
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

		contentPanel.setVisible(true);
	}
	
	public JPanel getWindow() {
		return this.contentPanel;
	}

	public void setWindow(JPanel thePanel) {
		this.contentPanel = thePanel;
	}

}
