/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.actionlisteners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.daoImpl.HotelSystemStatusImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Hotel;
import com.coder.hms.entities.HotelSystemStatus;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.ui.external.Reserved_CheckinWindow;
import com.coder.hms.ui.external.RoomWindow;
import com.coder.hms.ui.external.Walkin_CheckinWindow;

public class RoomsAction {

	////////////////////////////////////////////////////////////////////////////
	// Created for calculating and adding numbers (button text) for room buttons 
	////////////////////////////////////////////////////////////////////////////
	
	private String roomText = "";
	private RoomDaoImpl roomDaoImpl;
	private HotelDaoImpl hotelDaoImpl;
	private MouseAdapter customMouseListener;
	private HotelSystemStatus systemStatus;
	private HotelSystemStatusImpl statusImpl;
	
	public RoomsAction() {

		hotelDaoImpl = new HotelDaoImpl();
		final Hotel hotel = hotelDaoImpl.getHotel();
		
		
		statusImpl = new HotelSystemStatusImpl();
		systemStatus = statusImpl.getSystemStatus();
		
		roomDaoImpl = new RoomDaoImpl();

		customMouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//set the button when click one time just focus on
				if(e.getClickCount() == 1) {
					
					JButton comp = (JButton) e.getComponent();
					comp.setFocusPainted(true);
				}
				
				//in double click do some thing other
				else if(e.getClickCount() == 2) {
					
					int counter = 100;
					int lastNum = 0;
					
					//get the button text
					String command = (String) e.getSource().toString();
					
					//get new date as String 
					String innerDate = systemStatus.getDateTime().toString();
					
					//start loop to get all rooms as room capacity
					for (int i = 1; i <= hotel.getRoomCapacity(); i++) {
						++lastNum;

						//create the button text with same algorithm like in 'AllRooms.class'
						roomText = counter + "" + lastNum;

						if (i % 6 == 0) {
							counter += 100;
							lastNum = 0;
						}

						///////////////////////////////////////////////
						// check the clicked button if contains same // 
						// looping button text work with that button //
						///////////////////////////////////////////////
					
						// NOTE: Don't forget to add 'break' command when you finished 
						// your job to quit from the loop.
						if (command.contains(roomText)) {

							final Room theRoom = roomDaoImpl.getRoomByRoomNumber(roomText);
							final ReservationDaoImpl rImpl = new ReservationDaoImpl();
							final Reservation foundedReserv = rImpl.findReservationById(theRoom.getReservationId());
							
							if (theRoom.getUsageStatus().equals("FULL")) {
								SwingUtilities.invokeLater(new Runnable() {

									@Override
									public void run() {
										new RoomWindow(roomText);

									}
								});
								break;
							} 
							
							else if (theRoom.getUsageStatus().equals("BLOCKED") && 
											innerDate.equals(foundedReserv.getCheckinDate())) {

								SwingUtilities.invokeLater(new Runnable() {
									
									@Override
									public void run() {
										
										new Reserved_CheckinWindow(theRoom.getNumber())
										.setVisible(true);
									
									}
								});
								break;
							}
							
							else if (theRoom.getUsageStatus().equals("EMPTY")) {
								
								SwingUtilities.invokeLater(new Runnable() {
									
									@Override
									public void run() {
										
										new Walkin_CheckinWindow(theRoom.getNumber())
										.setVisible(true);
										
									}
								});
								break;
							}
						}
					}
				}
				super.mouseClicked(e);
			}
		};
	}

	public MouseAdapter getActionListener() {
		return this.customMouseListener;
	}
}
