/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingUtilities;

import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Hotel;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.usrinterface.Reserved_CheckinWin;
import com.coder.hms.usrinterface.RoomExternalWindow;
import com.coder.hms.usrinterface.Walkin_CheckinWin;

public class RoomsAction {

	////////////////////////////////////////////////////////////////////////////
	// Created for calculating and adding numbers (button text) for room buttons 
	////////////////////////////////////////////////////////////////////////////
	
	private String roomText = "";
	private RoomDaoImpl roomDaoImpl;
	private HotelDaoImpl hotelDaoImpl;
	private ActionListener customActionListener;

	public RoomsAction() {

		hotelDaoImpl = new HotelDaoImpl();
		final Hotel hotel = hotelDaoImpl.getHotel();

		roomDaoImpl = new RoomDaoImpl();

		customActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int counter = 100;
				int lastNum = 0;
				String command = e.getActionCommand();

				final String innerDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				
				for (int i = 1; i <= hotel.getRoomCapacity(); i++) {
					++lastNum;

					roomText = counter + "" + lastNum;

					if (i % 6 == 0) {
						counter += 100;
						lastNum = 0;
					}

					if (command.equals(roomText)) {

						final Room theRoom = roomDaoImpl.getRoomByRoomNumber(roomText);
						final ReservationDaoImpl rImpl = new ReservationDaoImpl();
						final Reservation foundedReserv = rImpl.getReservationById(theRoom.getReservationId());
						
						if (theRoom.getUsageStatus().equals("FULL")) {
							SwingUtilities.invokeLater(new Runnable() {

								@Override
								public void run() {
									new RoomExternalWindow(roomText);

								}
							});
							break;
						} 
						
						else if (theRoom.getUsageStatus().equals("BLOCKED") && innerDate.equals(foundedReserv.getCheckinDate())) {

							SwingUtilities.invokeLater(new Runnable() {
								
								@Override
								public void run() {
									
									new Reserved_CheckinWin(theRoom.getNumber())
									.setVisible(true);
								
								}
							});
						}
						
						else if (theRoom.getUsageStatus().equals("EMPTY")) {
							
							SwingUtilities.invokeLater(new Runnable() {
								
								@Override
								public void run() {
									
									new Walkin_CheckinWin(theRoom.getNumber())
									.setVisible(true);
									
								}
							});
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
