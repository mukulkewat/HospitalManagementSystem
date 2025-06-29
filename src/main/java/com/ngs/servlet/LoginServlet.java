package com.ngs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.ngs.VO.PatientRegistrationDetailsVO;
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
import jakarta.servlet.http.HttpSession;
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private IPatientServices service = new PatientServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		  res.setContentType("application/json"); // Important!
		  res.setCharacterEncoding("UTF-8");
		JsonReader reader = Json.createReader(req.getReader());
        JsonObject json = reader.readObject();

        String Email_MobileNo = json.getString("email_mobileno");
        
        String password = json.getString("password");
		
		if(Email_MobileNo==null&&password==null) {
			throw new NullPointerException();
		}
		
		PatientRegistrationDetailsVO patientDetail = service.login(Email_MobileNo, password);
		HttpSession session = req.getSession();
		session.setAttribute("patientDetail", patientDetail);
		JsonObjectBuilder builder = Json.createObjectBuilder();
		if (patientDetail != null && patientDetail.getName() != null) {
		    builder.add("status", "success")
		           .add("redirect", "index.html"); // tell frontend to redirect
		} else {
		    builder.add("status", "fail").add("message", "Invalid login");
		}
		try (PrintWriter out = res.getWriter()) {
		    out.print(builder.build().toString());
		}
		
	}

}
