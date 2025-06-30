package com.ngs.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import com.ngs.services.IPatientServices;
import com.ngs.services.PatientServiceImpl;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AppointmentServlet extends HttpServlet {
	private IPatientServices service = new PatientServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Read JSON body
    	JsonReader reader = Json.createReader(req.getReader());
        JsonObject json = reader.readObject();
       

        String patientName = json.getString("Name");
        String department = json.getString("Department");
        Date appointmentDate = Date.valueOf(LocalDate.parse(json.getString("AppointmentDate"))) ; // example: "2025-06-30"
         // parses the date string
         // converts LocalDate to java.sql.Date
        Time appointmentTime = Time.valueOf(LocalTime.parse(json.getString("AppointmentTime"))) ; ;// e.g., "07:15" or "07:15:00"
        
        
        
        try {
        	service.bookAppointment(patientName, department, appointmentDate, appointmentTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
       
        

        System.out.println("Patient: " + patientName);
        System.out.println("Department: " + department);
        System.out.println("Date: " + appointmentDate);
        System.out.println("Time: " + appointmentTime);

        // Respond to client
        resp.setContentType("application/json");
        resp.getWriter().write("{\"status\":\"success\"}");
    }
}
