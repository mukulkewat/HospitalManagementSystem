package com.ngs.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.JSONObject; // You must add org.json or another JSON lib

import com.ngs.DBconnection.DBConnection01;

public class AppointmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Read JSON body
        StringBuilder jsonBuffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }

        // Parse JSON
        JSONObject json = new JSONObject(jsonBuffer.toString());

        String patientName = json.getString("Name");
        String department = json.getString("Department");
        String appointmentDate = json.getString("AppointmentDate");
        String appointmentTime = json.getString("AppointmentTime");
        try {
        	 DBConnection01.storeData(patientName, department, appointmentDate, appointmentTime);
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
