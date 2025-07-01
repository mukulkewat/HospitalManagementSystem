package com.ngs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
	private IPatientServices service =  new PatientServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("FetchAppointMentServlet.doGet()");
    	try {
    		response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            List<PatientBookAppointmentDetail> fetchAppointment = service.fetchAppointment();
            
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            
            fetchAppointment.forEach(patientAppointment->{
            	JsonObject patient = Json.createObjectBuilder()
                        .add("name", patientAppointment.getPatientName())
                        .add("department",patientAppointment.getDepartment())
                        .add("appointmentDate", patientAppointment.getAppointmentDate().toString())
                        .add("appointmentTime",patientAppointment.getAppointmentTime().toString())
                        .build();

                jsonArrayBuilder.add(patient);
            	System.out.println(jsonArrayBuilder);
            });
            response.getWriter().print(jsonArrayBuilder.build().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

}
