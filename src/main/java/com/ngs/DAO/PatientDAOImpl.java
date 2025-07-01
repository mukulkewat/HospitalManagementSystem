package com.ngs.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.ngs.DBconnection.SingletonConnection;
import com.ngs.model.PatientBookAppointmentDetail;
import com.ngs.model.PatientRegistrationDetails;

public class PatientDAOImpl implements IPatientDAO {
	Connection con = SingletonConnection.getConnectionObject();;

	public PatientDAOImpl() {
		System.out.println(con);
	}

	@Override
	public String register(PatientRegistrationDetails patient) {

		final String registerQY = "INSERT INTO PATIENTREGISTERDETAIL VALUES(?,?,?,?,?)";
		int k = 0;
		try (PreparedStatement registerPQ = con.prepareStatement(registerQY);) {
			registerPQ.setString(1, patient.getName());
			registerPQ.setString(2, patient.getEmail());
			registerPQ.setLong(3, patient.getMobileNo());
			registerPQ.setDate(4, Date.valueOf(patient.getDob()));
			registerPQ.setString(5, patient.getPassword());
			k = registerPQ.executeUpdate();
			if (k > 0) {
				return "Registeration Succesfull";
			} else
				return "Registeration UnSuccessfull";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@Override
	public PatientRegistrationDetails login(String Email_Mobile, String password) {
		final String loginQY = "SELECT * FROM PATIENTREGISTERDETAIL WHERE (MobileNo =? OR Email=?) AND PASSWORD=?";
		PatientRegistrationDetails prd = new PatientRegistrationDetails(); 
		
		if(Email_Mobile!=null && password!=null) {
		try(PreparedStatement loginPQ = con.prepareStatement(loginQY);) {
			 if (Email_Mobile.matches("^\\d{10}$")) { // assuming 10-digit mobile number
	                long mobile = Long.parseLong(Email_Mobile);
	                loginPQ.setLong(1, mobile);
	                loginPQ.setString(2, ""); // No email
	            } else {
	                loginPQ.setLong(1, 0); // No mobile
	                loginPQ.setString(2, Email_Mobile);
	            }
	            loginPQ.setString(3, password); // password
			ResultSet rs = loginPQ.executeQuery();
			if(rs.next()) {
				prd.setName(rs.getString(1));
				prd.setEmail(rs.getString(2));
				prd.setMobileNo(rs.getLong(3));
				Date date = rs.getDate(4);
				prd.setDob(date.toLocalDate());
				System.out.println(prd+" in DAOIMPL");
				return prd;
			}
			else {System.out.println("Invalid Password");return prd;}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	return prd;
	}

	
	
	
	
	
	
	
	
	
	@Override
	public String bookAppointment(String patientName, String department, Date appointmentDate, Time appointmentTime) {
		 final String bookingQY="INSERT INTO PATIENTBOOKAPPOINTMENT  VALUES(?,?,?,?)";
		try(PreparedStatement psm = con.prepareStatement(bookingQY);) {
			
			if(patientName!=null && department!=null&& appointmentDate!=null&& appointmentTime!=null) {
			 // java.sql.Time
			psm.setString(1, patientName);
			psm.setString(2, department);
			psm.setDate(3, appointmentDate);
			psm.setTime(4, appointmentTime);
			
			int k = psm.executeUpdate();
			 if(k>0) {
				 System.out.println("Data Stored Successfully");
			 }
			 else System.err.println("Data Stored UnSuccessfully");
			}else throw new NullPointerException();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
			
		}
		return "";
	}

	@Override
	public List<PatientBookAppointmentDetail> fetchAppointment() {
		final String fetchBookingQY = "SELECT NAME,DEPARTMENT, 	APPIONTMENTDATE, APPOINTMENTTIME FROM PATIENTBOOKAPPOINTMENT";
		List<PatientBookAppointmentDetail> listOfAppointmentPatient;
		try(PreparedStatement psm = con.prepareStatement(fetchBookingQY)) {
			ResultSet rs = psm.executeQuery();
			if(rs!=null) {
			listOfAppointmentPatient = new ArrayList<PatientBookAppointmentDetail>();
			while(rs.next()) {
				PatientBookAppointmentDetail bookDetail = new PatientBookAppointmentDetail();
				bookDetail.setPatientName(rs.getString(1));
				bookDetail.setDepartment(rs.getString(2));
				bookDetail.setAppointmentDate(rs.getDate(3));
				bookDetail.setAppointmentTime(rs.getTime(4));
				listOfAppointmentPatient.add(bookDetail);
			}
			return listOfAppointmentPatient;
			}
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
