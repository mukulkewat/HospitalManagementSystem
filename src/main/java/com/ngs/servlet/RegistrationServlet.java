package com.ngs.servlet;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ngs.model.PatientRegistrationDetails;
import com.ngs.services.IPatientServices;
import com.ngs.services.PatientServiceImpl;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RegistrationServlet.class);
    private IPatientServices service = new PatientServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        JsonObjectBuilder responseBuilder = Json.createObjectBuilder();

        try (JsonReader reader = Json.createReader(req.getReader())) {
            JsonObject json = reader.readObject();

            String name = json.getString("name");
            String email = json.getString("email");
            String phoneStr = json.getString("phone");
            long phone = Long.parseLong(phoneStr);
            LocalDate dob = LocalDate.parse(json.getString("dob"));
            String password = json.getString("password");

            logger.info("Received registration request for: " + name + ", " + email);

            PatientRegistrationDetails patient = new PatientRegistrationDetails(name, email, phone, dob, password);
            String registerStatus = service.register(patient);

            if ("Registration Successful".equals(registerStatus)) {
                logger.info("User registration successful for: " + email);
                responseBuilder.add("status", "success")
                               .add("message", "Registration successful");
            } else {
                logger.warn("User registration failed for: " + email);
                responseBuilder.add("status", "fail")
                               .add("message", "Registration failed");
            }

        } catch (Exception e) {
            logger.error("Exception occurred during registration", e);
            responseBuilder.add("status", "error")
                           .add("message", "Server error during registration");
        }

        // Send response
        try (var out = res.getWriter()) {
            out.print(responseBuilder.build().toString());
        }
    }
}
