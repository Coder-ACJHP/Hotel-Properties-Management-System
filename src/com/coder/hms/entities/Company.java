/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coder.hms.entities;

import java.awt.Color;
import java.io.Serializable;
import java.util.Objects;
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
    
    @Column(name="faceColor")
    private Color faceColor;

    public Company() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
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

    public Color getFaceColor() {
        return faceColor;
    }

    public void setFaceColor(Color faceColor) {
        this.faceColor = faceColor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.Id ^ (this.Id >>> 32));
        hash = 53 * hash + Objects.hashCode(this.kind);
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.taxAdministration);
        hash = 53 * hash + Objects.hashCode(this.taxNumber);
        hash = 53 * hash + Objects.hashCode(this.province);
        hash = 53 * hash + Objects.hashCode(this.district);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.address);
        hash = 53 * hash + Objects.hashCode(this.phoneNumber);
        hash = 53 * hash + Objects.hashCode(this.faceColor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Company other = (Company) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.kind, other.kind)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.taxAdministration, other.taxAdministration)) {
            return false;
        }
        if (!Objects.equals(this.taxNumber, other.taxNumber)) {
            return false;
        }
        if (!Objects.equals(this.province, other.province)) {
            return false;
        }
        if (!Objects.equals(this.district, other.district)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.faceColor, other.faceColor)) {
            return false;
        }
        return true;
    }

    public Company(long Id, String kind, String title, String taxAdministration, String taxNumber, String province, String district, String email, String address, String phoneNumber, Color faceColor) {
        this.Id = Id;
        this.kind = kind;
        this.title = title;
        this.taxAdministration = taxAdministration;
        this.taxNumber = taxNumber;
        this.province = province;
        this.district = district;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.faceColor = faceColor;
    }

    @Override
    public String toString() {
        return "Company{" + "Id=" + Id + ", kind=" + kind + ", title=" + title + ", taxAdministration=" + taxAdministration + ", taxNumber=" + taxNumber + ", province=" + province + ", district=" + district + ", email=" + email + ", address=" + address + ", phoneNumber=" + phoneNumber + ", faceColor=" + faceColor + '}';
    }
    
    
            
}
