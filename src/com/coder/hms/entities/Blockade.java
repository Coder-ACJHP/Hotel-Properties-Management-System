package com.coder.hms.entities;

import java.io.Serializable;

public class Blockade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Type;
	private String Number;
	private String GroupName;

	public Blockade() {
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public Blockade(String type, String number, String groupName) {
		super();
		Type = type;
		Number = number;
		GroupName = groupName;
	}

	@Override
	public String toString() {
		return "Blockade [Type=" + Type + ", Number=" + Number + ", GroupName=" + GroupName + "]";
	}
	
	
}
