package com.ngs.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ngs.DBconnection.SingletonConnection;
import com.ngs.model.PatientBookAppointmentDetail;
import com.ngs.model.PatientRegistrationDetails;

public class PatientDAOImpl implements IPatientDAO {
    static Logger logger = Logger.getLogger(PatientDAOImpl.class);
    Connection con = SingletonConnection.getConnectionObject();

//    static {
//    	PropertyConfigurator.configure(
//    			  PatientDAOImpl.class.getClassLoader().getResource("log4j.properties")
//    			);
//    }

    public PatientDAOImpl() {
        logger.info("PatientDAOImpl instance created.");
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

            logger.info("Executing patient registration for: " + patient.getName());
            k = registerPQ.executeUpdate();

            if (k > 0) {
                logger.info("Registration successful for: " + patient.getEmail());
                return "Registration Successful";
            } else {
                logger.warn("Registration failed for: " + patient.getEmail());
                return "Registration Unsuccessful";
            }

        } catch (Exception e) {
            logger.error("Error during registration: ", e);
        }
        return "";
    }

    @Override
    public PatientRegistrationDetails login(String Email_Mobile, String password) {
        final String loginQY = "SELECT * FROM PATIENTREGISTERDETAIL WHERE (MobileNo = ? OR Email = ?) AND PASSWORD = ?";
        PatientRegistrationDetails prd = new PatientRegistrationDetails();

        if (Email_Mobile != null && password != null) {
            try (PreparedStatement loginPQ = con.prepareStatement(loginQY)) {
                if (Email_Mobile.matches("^\\d{10}$")) {
                    long mobile = Long.parseLong(Email_Mobile);
                    loginPQ.setLong(1, mobile);
                    loginPQ.setString(2, "");
                } else {
                    loginPQ.setLong(1, 0);
                    loginPQ.setString(2, Email_Mobile);
                }
                loginPQ.setString(3, password);

                logger.info("Attempting login for user: " + Email_Mobile);

                ResultSet rs = loginPQ.executeQuery();
                if (rs.next()) {
                    prd.setName(rs.getString(1));
                    prd.setEmail(rs.getString(2));
                    prd.setMobileNo(rs.getLong(3));
                    prd.setDob(rs.getDate(4).toLocalDate());

                    logger.info("Login successful for: " + Email_Mobile);
                    return prd;
                } else {
                    logger.warn("Login failed. Invalid credentials for: " + Email_Mobile);
                }

            } catch (Exception e) {
                logger.error("Error during login: ", e);
            }
        } else {
            logger.warn("Login failed. Email/Mobile or password is null.");
        }

        return prd;
    }

    @Override
    public String bookAppointment(String patientName, String department, Date appointmentDate, Time appointmentTime) {
        final String bookingQY = "INSERT INTO PATIENTBOOKAPPOINTMENT VALUES(?,?,?,?)";

        try (PreparedStatement psm = con.prepareStatement(bookingQY)) {

            if (patientName != null && department != null && appointmentDate != null && appointmentTime != null) {
                psm.setString(1, patientName);
                psm.setString(2, department);
                psm.setDate(3, appointmentDate);
                psm.setTime(4, appointmentTime);

                logger.info("Booking appointment for: " + patientName);

                int k = psm.executeUpdate();
                if (k > 0) {
                    logger.info("Appointment booked successfully for: " + patientName);
                    return "Appointment booked successfully";
                } else {
                    logger.warn("Failed to book appointment for: " + patientName);
                }
            } else {
                logger.error("Null values provided for appointment booking.");
                throw new NullPointerException("Appointment details cannot be null.");
            }

        } catch (Exception e) {
            logger.error("Error booking appointment: ", e);
            return e.getMessage();
        }

        return "Appointment booking failed";
    }

    @Override
    public List<PatientBookAppointmentDetail> fetchAppointment() {
        final String fetchBookingQY = "SELECT NAME, DEPARTMENT, APPIONTMENTDATE, APPOINTMENTTIME FROM PATIENTBOOKAPPOINTMENT";
        List<PatientBookAppointmentDetail> listOfAppointmentPatient;

        try (PreparedStatement psm = con.prepareStatement(fetchBookingQY)) {
            ResultSet rs = psm.executeQuery();
            listOfAppointmentPatient = new ArrayList<>();

            while (rs.next()) {
                PatientBookAppointmentDetail bookDetail = new PatientBookAppointmentDetail();
                bookDetail.setPatientName(rs.getString(1));
                bookDetail.setDepartment(rs.getString(2));
                bookDetail.setAppointmentDate(rs.getDate(3));
                bookDetail.setAppointmentTime(rs.getTime(4));

                listOfAppointmentPatient.add(bookDetail);
            }

            logger.info("Fetched " + listOfAppointmentPatient.size() + " appointment records.");
            return listOfAppointmentPatient;

        } catch (Exception e) {
            logger.error("Error fetching appointment records: ", e);
        }

        return null;
    }
}
