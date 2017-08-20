package com.coder.hms.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HotelSystemStatus")
public class HotelSystemStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@Column(name="dateTime")
	private LocalDate dateTime;
	
	@Column(name="isAuditted")
	private Boolean isAuditted;
	
	public HotelSystemStatus() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public LocalDate getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getIsAuditted() {
		return isAuditted;
	}

	public void setIsAuditted(Boolean isAuditted) {
		this.isAuditted = isAuditted;
	}

	public HotelSystemStatus(long id, LocalDate dateTime, Boolean isAuditted) {
		super();
		Id = id;
		this.dateTime = dateTime;
		this.isAuditted = isAuditted;
	}

	@Override
	public String toString() {
		return "HotelSystemStatus [Id=" + Id + ", dateTime=" + dateTime + ", isAuditted=" + isAuditted + "]";
	}

	
}
