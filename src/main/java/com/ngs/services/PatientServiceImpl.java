package com.ngs.services;

import java.sql.Date;
import java.sql.Time;

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
		if(patient!=null) {
		String register = dao.register(patient);
		return register;
		}
		return "registration Unsuccessfull";
	}
	@Override
	public PatientRegistrationDetailsVO login(String Email_MobileNo, String password) {
		if(Email_MobileNo!=null && password!=null) {
		PatientRegistrationDetails prd = dao.login(Email_MobileNo, password);
		
		PatientRegistrationDetailsVO prdVO = new PatientRegistrationDetailsVO(prd.getName(),
				prd.getEmail(),prd.getMobileNo(),prd.getDob());
		System.out.println("In PatientServiceImpl");
		return prdVO;
		}
		return null;
	}
	@Override
	public String bookAppointment(String patientName, String department, Date appointmentDate, Time appointmentTime) {
		if(patientName!=null && department!=null && appointmentDate!=null && appointmentTime!=null) {
			dao.bookAppointment(patientName, department, appointmentDate, appointmentTime);
			return "Your Appointment Booked";
		}
		throw new NullPointerException();
		
	}
	

}
