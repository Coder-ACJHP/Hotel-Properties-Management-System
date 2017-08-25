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
	
	public Reservation findSingleReservByThisDate(String Date);
		
	public void saveReservation(Reservation reservation);
		
	public boolean updateReservation(Reservation reservation);
	
	public Reservation findReservationByAgencyRefNo(String text);
	
	public Reservation findReservationByRefNo(String refNo);
	
	public List<Reservation> getAllReservations();
	
	public List<Reservation> getReservListByThisDate(String today);
	
	public List<Reservation> getReservsAsWaitlist(String reservDate);
	
	public Reservation getLastReservation();
	
	public void deleteReservation(long theId);
}
