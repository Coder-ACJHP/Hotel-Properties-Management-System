package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.Reservation;

public interface ReservationDAO {

	public Reservation findReservationById(long Id);
	
	public Reservation findReservationByDate(String Date);
	
	public void saveReservation(Reservation reservation);
	
	public void cancelReservation(long reservationId);
	
	public List<Reservation> getAllReservations();
}
