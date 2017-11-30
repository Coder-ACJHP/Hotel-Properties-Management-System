/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coder.hms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dell-pc
 */
@Entity
@Table(name="Company")
public class Company implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long Id;
    
    @Column(name="kind")
    private String kind;
    
    @Column(name="title")
    private String title;
    
    @Column(name="taxAdministration")
    private String taxAdministration;
    
    @Column(name="taxNumber")
    private String taxNumber;
    
    @Column(name="province")
    private String province;
    
    @Column(name="district")
    private String district;
    
    @Column(name="email")
    private String email;
    
    @Column(name="address")
    private String address;
    
    @Column(name="phoneNumber")
    private String phoneNumber;
    
    @Column(name="faceColorRgb")
    private int faceColorRgb;
    
    @Column(name="activateStatus")
    private boolean activateStatus;

    public Company() {
    }

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTaxAdministration() {
		return taxAdministration;
	}

	public void setTaxAdministration(String taxAdministration) {
		this.taxAdministration = taxAdministration;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getFaceColorRgb() {
		return faceColorRgb;
	}

	public void setFaceColor(int faceColorRgb) {
		this.faceColorRgb = faceColorRgb;
	}

	public boolean isActivateStatus() {
		return activateStatus;
	}

	public void setActivateStatus(boolean activateStatus) {
		this.activateStatus = activateStatus;
	}

	public Company(long id, String kind, String title, String taxAdministration, String taxNumber, String province,
			String district, String email, String address, String phoneNumber, int faceColor,
			boolean activateStatus) {
		super();
		Id = id;
		this.kind = kind;
		this.title = title;
		this.taxAdministration = taxAdministration;
		this.taxNumber = taxNumber;
		this.province = province;
		this.district = district;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.faceColorRgb = faceColor;
		this.activateStatus = activateStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
		result = prime * result + (activateStatus ? 1231 : 1237);
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (faceColorRgb ^ (faceColorRgb >>> 32));
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((taxAdministration == null) ? 0 : taxAdministration.hashCode());
		result = prime * result + ((taxNumber == null) ? 0 : taxNumber.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (Id != other.Id)
			return false;
		if (activateStatus != other.activateStatus)
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (faceColorRgb != other.faceColorRgb) {
				return false;
		}
		if (kind == null) {
			if (other.kind != null)
				return false;
		} else if (!kind.equals(other.kind))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (taxAdministration == null) {
			if (other.taxAdministration != null)
				return false;
		} else if (!taxAdministration.equals(other.taxAdministration))
			return false;
		if (taxNumber == null) {
			if (other.taxNumber != null)
				return false;
		} else if (!taxNumber.equals(other.taxNumber))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Company [Id=" + Id + ", kind=" + kind + ", title=" + title + ", taxAdministration=" + taxAdministration
				+ ", taxNumber=" + taxNumber + ", province=" + province + ", district=" + district + ", email=" + email
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", faceColor=" + faceColorRgb
				+ ", activateStatus=" + activateStatus + "]";
	}

   
            
}
