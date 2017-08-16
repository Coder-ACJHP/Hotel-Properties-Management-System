/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.beans;

import java.io.Serializable;

public class Blockade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Type;
	private String Number;
	private String Status;
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

	public void setStatus(String status) {
		this.Status = status;
	}
	
	public String getStatus() {
		return this.Status;
	}
	
	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public Blockade(String type, String number, String status, String groupName) {
		super();
		Type = type;
		Number = number;
		Status = status;
		GroupName = groupName;
	}

	@Override
	public String toString() {
		return "Blockade [Type=" + Type + ", Number=" + Number + ", Status=" + Status + ", GroupName=" + GroupName + "]";
	}
	
	
}
