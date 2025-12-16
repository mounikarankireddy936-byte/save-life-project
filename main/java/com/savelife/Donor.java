package com.savelife;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "donor_users")
public class Donor {

	
	//sno,donor_name,email,password,mobile,blood_group
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long sno;
	
	@Column(name = "donor_name")
	String donorName;
	
	String email;
	String password;
	long mobile;
	
	@Column(name = "blood_group")
	String bloodGroup;

	public Long getSno() {
		return sno;
	}

	public void setSno(Long sno) {
		this.sno = sno;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@Override
	public String toString() {
		return "Donor [sno=" + sno + ", donorName=" + donorName + ", email=" + email + ", password=" + password
				+ ", mobile=" + mobile + ", bloodGroup=" + bloodGroup + "]";
	}
	
	
	
	
}
