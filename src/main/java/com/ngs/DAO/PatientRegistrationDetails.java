package com.ngs.DAO;

import java.time.LocalDate;

public class PatientRegistrationDetails {
	private String name;
	private String email;
	private Integer mobileNo;
	private LocalDate dob;
	private String password;
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
	public Integer getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Integer mobileNo) {
		this.mobileNo = mobileNo;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public PatientRegistrationDetails(String name, String email, Integer mobileNo, LocalDate dob, String password) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.dob = dob;
		this.password = password;
	}
	
	

}
