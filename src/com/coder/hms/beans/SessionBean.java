/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.beans;

import java.io.Serializable;

public class SessionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	////////////////////////////////////////////////////////////////////////////
	// Created for transferring information between all classes.
	////////////////////////////////////////////////////////////////////////////
	
	private static SessionBean SESSION_BEAN = null;

	private static long id;
	private static String name;
	private static String surname;
	private static String nickName;
	private static String password;
	private static String Date;
	private static int roomCount;
	private static Object[] tableRowCol;

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

	public String getDate() {
		return Date;
	}

	public static void setDate(String date) {
		Date = date;
	}

	public static int getRoomCount() {
		return roomCount;
	}
	
	public static void setRoomCount(int roomCount) {
		SessionBean.roomCount = roomCount;
		
	}

	public static Object[] getTableRowCol() {
		return tableRowCol;
	}

	public static void setTableRowCol(Object[] tableRowCol) {
		SessionBean.tableRowCol = tableRowCol;
	}
	
}
