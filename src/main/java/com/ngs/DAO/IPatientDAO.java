package com.ngs.DAO;

import com.ngs.model.PatientRegistrationDetails;

public interface IPatientDAO {
	public String register(PatientRegistrationDetails patient);
	public PatientRegistrationDetails login(String username, String password);
}
