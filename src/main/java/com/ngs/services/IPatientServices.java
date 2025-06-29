package com.ngs.services;

import com.ngs.VO.PatientRegistrationDetailsVO;
import com.ngs.model.PatientRegistrationDetails;

public interface IPatientServices {
	public String register(PatientRegistrationDetails patient);
	public PatientRegistrationDetailsVO login(String Email_MobileNo, String password);

}
