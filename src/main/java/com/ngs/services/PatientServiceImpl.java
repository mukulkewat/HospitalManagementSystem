package com.ngs.services;

import com.mukul.model.PatientRegistrationDetails;
import com.ngs.DAO.IPatientDAO;
import com.ngs.DAO.PatientDAOImpl;

public class PatientServiceImpl implements IPatientServices{
	private IPatientDAO dao = new PatientDAOImpl();

	@Override
	public String register(PatientRegistrationDetails patient) {
		String register = dao.register(patient);
		return register;
	}

}
