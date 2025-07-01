package com.ngs.services;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ngs.DAO.IPatientDAO;
import com.ngs.DAO.PatientDAOImpl;
import com.ngs.VO.PatientRegistrationDetailsVO;
import com.ngs.model.PatientBookAppointmentDetail;
import com.ngs.model.PatientRegistrationDetails;

public class PatientServiceImpl implements IPatientServices {

    private static final Logger logger = Logger.getLogger(PatientServiceImpl.class);
    private IPatientDAO dao = new PatientDAOImpl();

//    static {
//        try {
//            PropertyConfigurator.configure(
//                PatientServiceImpl.class.getClassLoader().getResource("log4j.properties")
//            );
//            logger.info("Log4j configured in PatientServiceImpl");
//        } catch (Exception e) {
//            System.err.println("Log4j configuration error in PatientServiceImpl");
//            e.printStackTrace();
//        }
//    }

    public PatientServiceImpl() {
        super();
        logger.info("PatientServiceImpl instance created.");
    }

    @Override
    public String register(PatientRegistrationDetails patient) {
        try {
            if (patient != null) {
                logger.info("Calling DAO register() for patient: " + patient.getEmail());
                String register = dao.register(patient);
                return register;
            } else {
                logger.warn("Null patient object received in register()");
            }
        } catch (Exception e) {
            logger.error("Exception during patient registration", e);
            e.printStackTrace();
        }
        return "Registration Unsuccessful";
    }

    @Override
    public PatientRegistrationDetailsVO login(String Email_MobileNo, String password) {
        try {
            if (Email_MobileNo != null && password != null) {
                logger.info("Calling DAO login() for user: " + Email_MobileNo);
                PatientRegistrationDetails prd = dao.login(Email_MobileNo, password);

                if (prd != null) {
                    PatientRegistrationDetailsVO prdVO = new PatientRegistrationDetailsVO(
                            prd.getName(), prd.getEmail(), prd.getMobileNo(), prd.getDob());
                    logger.info("Login successful, returning VO");
                    return prdVO;
                } else {
                    logger.warn("DAO returned null for login");
                }
            } else {
                logger.warn("Null Email/Mobile or password received in login()");
            }
        } catch (Exception e) {
            logger.error("Exception during login", e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String bookAppointment(String patientName, String department, Date appointmentDate, Time appointmentTime) {
        try {
            if (patientName != null && department != null && appointmentDate != null && appointmentTime != null) {
                logger.info("Calling DAO to book appointment for: " + patientName);
                dao.bookAppointment(patientName, department, appointmentDate, appointmentTime);
                return "Your Appointment Booked";
            } else {
                logger.warn("Null values passed to bookAppointment()");
                throw new NullPointerException("Appointment details are missing");
            }
        } catch (Exception e) {
            logger.error("Error booking appointment", e);
            e.printStackTrace();
            return "Appointment booking failed";
        }
    }

    @Override
    public List<PatientBookAppointmentDetail> fetchAppointment() {
        try {
            logger.info("Calling DAO to fetch appointments");
            List<PatientBookAppointmentDetail> fetchAppointment = dao.fetchAppointment();
            if (fetchAppointment == null) {
                logger.warn("No appointments found (null returned)");
                throw new NullPointerException("No appointments found");
            }
            logger.info("Fetched " + fetchAppointment.size() + " appointments");
            return fetchAppointment;
        } catch (Exception e) {
            logger.error("Error fetching appointments", e);
            e.printStackTrace();
            return null;
        }
    }
}
