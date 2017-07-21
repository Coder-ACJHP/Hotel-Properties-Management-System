package com.coder.hms.entities;

public class SessionBean {

	private static SessionBean SESSION_BEAN = null;

	private static long id;
	private static String name;
	private static String surname;
	private static String nickName;
	private static String password;
	private static String Date;
	private static int roomCount;

	public static SessionBean getSESSION_BEAN() {
		if(SESSION_BEAN == null) {
					
			SESSION_BEAN = new SessionBean();
		}
			return SESSION_BEAN;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		SessionBean.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		SessionBean.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		SessionBean.surname = surname;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		SessionBean.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		SessionBean.password = password;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return Date;
	}

	/**
	 * @param date the date to set
	 */
	public static void setDate(String date) {
		Date = date;
	}

	public static int getRoomCount() {
		return roomCount;
	}
	
	public static void setRoomCount(int roomCount) {
		// TODO Auto-generated method stub
		
	}
	
}
