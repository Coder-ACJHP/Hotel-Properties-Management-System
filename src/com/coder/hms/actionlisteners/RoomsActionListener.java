package com.coder.hms.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.entities.Hotel;
import com.coder.hms.userinterface.RoomEx;

public class RoomsActionListener {

	private String roomText = "";
	private ActionListener customActionListener;

	public RoomsActionListener() {
	
		HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
		Hotel hotel = hotelDaoImpl.getHotel();
		
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
						
						SwingUtilities.invokeLater(new Runnable() {
							
							@Override
							public void run() {
								new RoomEx(roomText);
								
							}
						});
						break;
					}
				}
				

			}

		};
	}

	public ActionListener getActionListener() {
		return this.customActionListener;
	}
}
