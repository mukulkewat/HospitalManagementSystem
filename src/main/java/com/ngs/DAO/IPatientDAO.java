package com.ngs.DAO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.ngs.model.PatientBookAppointmentDetail;
import com.ngs.model.PatientRegistrationDetails;

public interface IPatientDAO {
	public String register(PatientRegistrationDetails patient);
	public PatientRegistrationDetails login(String username, String password);
	public String bookAppointment(String patientName, String department, Date appointmentDate, Time appointmentTime);
	public List<PatientBookAppointmentDetail> fetchAppointment();
}
