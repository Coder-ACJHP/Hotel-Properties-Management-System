/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Hotel;
import com.coder.hms.entities.Room;
import com.coder.hms.usrinterface.RoomEx;


public class RoomsAction {

	private String roomText = "";
	private RoomDaoImpl roomDaoImpl;
	private HotelDaoImpl hotelDaoImpl;
	private ActionListener customActionListener;

	public RoomsAction() {
	
		hotelDaoImpl = new HotelDaoImpl();
		Hotel hotel = hotelDaoImpl.getHotel();
		
		roomDaoImpl = new RoomDaoImpl();
		
		customActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int counter = 100;
				int lastNum = 0;
				String command = e.getActionCommand();

				for (int i = 1; i <= hotel.getRoomCapacity(); i++) {
					++lastNum;
					
					roomText = counter + "" + lastNum;
					
					if (i % 6 == 0) {
						counter += 100;
						lastNum = 0;
					}

					if (command.equals(roomText)) {
						
						Room theRoom = roomDaoImpl.getRoomByRoomNumber(roomText);
						
						if(theRoom.getUsageStatus().equals("FULL")) {
							SwingUtilities.invokeLater(new Runnable() {
								
								@Override
								public void run() {
									new RoomEx(roomText);
									
								}
							});
							break;
						} else {
							JOptionPane.showMessageDialog(null, "NEW CHECK/IN PAGE UNDER DEVELOPMENT PHASE\n"
									+ "PLEASE BE PATIENT.", JOptionPane.MESSAGE_PROPERTY, JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}
				}
			}
		};
	}

	public ActionListener getActionListener() {
		return this.customActionListener;
	}
}
