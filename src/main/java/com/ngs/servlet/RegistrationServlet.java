package com.ngs.servlet;

import java.io.IOException;
import java.time.LocalDate;

import com.ngs.model.PatientRegistrationDetails;
import com.ngs.services.IPatientServices;
import com.ngs.services.PatientServiceImpl;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet{
	private IPatientServices service = new PatientServiceImpl();
	/*
	    console.log(name);
        console.log(email);
        console.log(phone);
        console.log(dob);
        console.log(password);
	 */
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		JsonReader reader = Json.createReader(req.getReader());
        JsonObject json = reader.readObject();

        String name = json.getString("name");
        System.out.println(name);
        String email = json.getString("email");
        String phone1 = json.getString("phone");
        long phone = Long.parseLong(phone1);
        System.out.println(phone1);
        System.out.println(phone);
        LocalDate dob = LocalDate.parse(String.valueOf(json.getString("dob"))) ;
        String password = json.getString("password");

        // Now build your POJO manually
        PatientRegistrationDetails patient = new PatientRegistrationDetails(name,email, phone, dob,password);
        String register = service.register(patient);
        System.out.println(register);
	
	}
	
	

}
