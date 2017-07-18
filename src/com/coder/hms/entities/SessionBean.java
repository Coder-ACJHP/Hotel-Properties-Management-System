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
	
	public static SessionBean getInstance() {
		if(SESSION_BEAN == null) {
			
			SESSION_BEAN = new SessionBean();
		}
		return SESSION_BEAN;
	}
	
	public static long getId() {
		return id;
	}
	public static void setId(long id) {
		SessionBean.id = id;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		SessionBean.name = name;
	}
	public static String getSurname() {
		return surname;
	}
	public static void setSurname(String surname) {
		SessionBean.surname = surname;
	}
	public static String getNickName() {
		return nickName;
	}
	public static void setNickName(String nickName) {
		SessionBean.nickName = nickName;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		SessionBean.password = password;
	}

	/**
	 * @return the date
	 */
	public static String getDate() {
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
