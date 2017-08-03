/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.Reservation;

public interface ReservationDAO {

	public Reservation findReservationById(long Id);
	
	public Reservation findReservationByDate(String Date);
	
	public void saveReservation(Reservation reservation);
	
	public void cancelReservation(long reservationId);
	
	public boolean updateReservation(Reservation reservation);
	
	public List<Reservation> getAllReservations();
}
