package com.ngs.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ngs.services.IPatientServices;
import com.ngs.services.PatientServiceImpl;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AppointmentServlet.class);
    private IPatientServices service = new PatientServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8"); 

        JsonObjectBuilder responseBuilder = Json.createObjectBuilder();

        try (JsonReader reader = Json.createReader(req.getReader())) {
            JsonObject json = reader.readObject();

            String patientName = json.getString("Name");
            String department = json.getString("Department");
            Date appointmentDate = Date.valueOf(LocalDate.parse(json.getString("AppointmentDate")));
            Time appointmentTime = Time.valueOf(LocalTime.parse(json.getString("AppointmentTime")));

            logger.info("Received appointment request for " + patientName + " - " + department + " on " + appointmentDate + " at " + appointmentTime);

            String result = service.bookAppointment(patientName, department, appointmentDate, appointmentTime);
            logger.info("Appointment booking result: " + result);

            responseBuilder.add("status", "success").add("message", result);

        } catch (Exception e) {
            logger.error("Error while booking appointment", e);
            responseBuilder.add("status", "error").add("message", "Server error occurred during appointment booking.");
        }

        try (var out = resp.getWriter()) {
            out.print(responseBuilder.build().toString());
        }
    }
}
