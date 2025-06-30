package com.ngs.services;

import java.sql.Date;
import java.sql.Time;

import com.ngs.VO.PatientRegistrationDetailsVO;
import com.ngs.model.PatientRegistrationDetails;

public interface IPatientServices {
	public String register(PatientRegistrationDetails patient);
	public PatientRegistrationDetailsVO login(String Email_MobileNo, String password);
	public String bookAppointment(String patientName, String department, Date appointmentDate, Time appointmentTime);
	

}
