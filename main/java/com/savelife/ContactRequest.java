package com.savelife;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact_us")
public class ContactRequest {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long sno;
	
	String name;
	String email;
	long mobile;
	String description;
	boolean isActive;
	
	public Long getSno() {
		return sno;
	}
	public void setSno(Long sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "ContactRequest [sno=" + sno + ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", description=" + description + ", isActive=" + isActive + "]";
	}

	
	
	
	
}
