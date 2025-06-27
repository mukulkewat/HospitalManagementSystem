package com.ngs.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import com.mukul.model.PatientRegistrationDetails;
import com.ngs.DBconnection.SingletonConnection;

public class PatientDAOImpl implements IPatientDAO{
	 Connection con = null;
	
	public PatientDAOImpl() {
        // ✅ Get the connection from Singleton
        this.con = SingletonConnection.getConnectionObject();
    }
	@Override
	public String register(PatientRegistrationDetails patient) {
		
		String register = "INSERT INTO patientregisterdetail VALUES(?,?,?,?,?)";
		int k = 0;
		try(PreparedStatement registerQ = con.prepareStatement(register);){
			registerQ.setString(1, patient.getName());
			registerQ.setString(2, patient.getEmail());
			registerQ.setLong(3,patient.getMobileNo());
			registerQ.setDate(4, Date.valueOf(patient.getDob()));
			registerQ.setString(5, patient.getPassword());
			k = registerQ.executeUpdate();
			if(k>0) {
				return "Registeration Succesfull";
			}
			else return "Registeration UnSuccessfull";
			
		}catch (Exception e) {
			   e.printStackTrace();
		}
		
		
		return "";
	}

}
