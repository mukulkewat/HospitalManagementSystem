package com.ngs.model;

import java.sql.Date;
import java.sql.Time;

public class PatientBookAppointmentDetail {
	private String patientName;
	private String department;
	private Date   appointmentDate;
	private Time appointmentTime;
	
	public PatientBookAppointmentDetail() {
		super();
	}
	public PatientBookAppointmentDetail(String patientName, String department, Date appointmentDate,
			Time appointmentTime) {
		super();
		this.patientName = patientName;
		this.department = department;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public Time getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	

}
