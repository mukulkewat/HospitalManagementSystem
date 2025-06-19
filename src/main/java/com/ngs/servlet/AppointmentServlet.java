package com.ngs.servlet;

import java.io.IOException;
import java.time.LocalTime;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AppointmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String patientName = req.getParameter("patientName");
        String time = req.getParameter("appointmentTime"); // expected format: "HH:mm:ss"

        // Parse time if needed
        LocalTime appointmentTime = LocalTime.parse(time);

        System.out.println("Patient: " + patientName);
        System.out.println("Time: " + appointmentTime);

        resp.setContentType("application/json");
        resp.getWriter().write("{\"status\":\"success\"}");
    }
}

