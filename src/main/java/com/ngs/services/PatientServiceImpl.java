package com.ngs.services;

import com.ngs.DAO.IPatientDAO;
import com.ngs.DAO.PatientDAOImpl;
import com.ngs.VO.PatientRegistrationDetailsVO;
import com.ngs.model.PatientRegistrationDetails;

public class PatientServiceImpl implements IPatientServices{
	private IPatientDAO dao = new PatientDAOImpl();
	public PatientServiceImpl() {
		super();
	}
	@Override
	public String register(PatientRegistrationDetails patient) {
		String register = dao.register(patient);
		return register;
	}
	@Override
	public PatientRegistrationDetailsVO login(String Email_MobileNo, String password) {
		PatientRegistrationDetails prd = dao.login(Email_MobileNo, password);
		PatientRegistrationDetailsVO prdVO = new PatientRegistrationDetailsVO(prd.getName(),
				prd.getEmail(),prd.getMobileNo(),prd.getDob());
		System.out.println("In PatientServiceImpl");
		return prdVO;
	}
	

}
