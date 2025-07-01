package com.ngs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.ngs.model.PatientBookAppointmentDetail;
import com.ngs.services.IPatientServices;
import com.ngs.services.PatientServiceImpl;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/getPatients")
public class FetchAppointMentServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FetchAppointMentServlet.class);
    private IPatientServices service = new PatientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Received request to fetch all appointment details.");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {

            List<PatientBookAppointmentDetail> fetchAppointment = service.fetchAppointment();

            if (fetchAppointment == null || fetchAppointment.isEmpty()) {
                logger.warn("No appointment data found.");
                out.print(Json.createObjectBuilder()
                        .add("status", "empty")
                        .add("message", "No appointments found")
                        .build()
                        .toString());
                return;
            }

            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

            fetchAppointment.forEach(patientAppointment -> {
                JsonObject patient = Json.createObjectBuilder()
                        .add("name", patientAppointment.getPatientName())
                        .add("department", patientAppointment.getDepartment())
                        .add("appointmentDate", patientAppointment.getAppointmentDate().toString())
                        .add("appointmentTime", patientAppointment.getAppointmentTime().toString())
                        .build();

                jsonArrayBuilder.add(patient);
            });

            logger.info("Fetched " + fetchAppointment.size() + " appointment(s) successfully.");
            out.print(jsonArrayBuilder.build().toString());

        } catch (Exception e) {
            logger.error("Error while fetching appointment records", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print(Json.createObjectBuilder()
                    .add("status", "error")
                    .add("message", "Server error while fetching appointments")
                    .build()
                    .toString());
        }
    }
}
