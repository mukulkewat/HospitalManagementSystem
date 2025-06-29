package com.ngs.VO;

import java.time.LocalDate;

public class PatientRegistrationDetailsVO {
	private String name;
	private String email;
	private long mobileNo;
	private LocalDate dob;
	public PatientRegistrationDetailsVO() {
		super();
	}
	public PatientRegistrationDetailsVO(String name, String email, long mobileNo, LocalDate dob) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.dob = dob;
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
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	
	@Override
	public String toString() {
		return "PatientRegistrationDetailsVO [name=" + name + ", email=" + email + ", mobileNo=" + mobileNo + ", dob="
				+ dob + "]";
	}
	
	
	

}
