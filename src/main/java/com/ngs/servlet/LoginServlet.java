package com.ngs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

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
public class LoginServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class);
    private IPatientServices service = new PatientServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try (JsonReader reader = Json.createReader(req.getReader())) {
            JsonObject json = reader.readObject();
            String Email_MobileNo = json.getString("email_mobileno");
            String password = json.getString("password");

            logger.info("Login attempt for user: " + Email_MobileNo);

            if (Email_MobileNo == null || password == null) {
                logger.warn("Null email/mobile or password received.");
                throw new NullPointerException("Email or password cannot be null.");
            }

            PatientRegistrationDetailsVO patientDetail = service.login(Email_MobileNo, password);

            HttpSession session = req.getSession();
            session.setAttribute("patientDetail", patientDetail);

            JsonObjectBuilder builder = Json.createObjectBuilder();

            if (patientDetail != null && patientDetail.getName() != null) {
                logger.info("Login successful for: " + Email_MobileNo);
                builder.add("status", "success").add("redirect", "index.html");
            } else {
                logger.warn("Invalid login for: " + Email_MobileNo);
                builder.add("status", "fail").add("message", "Invalid login");
            }

            try (PrintWriter out = res.getWriter()) {
                out.print(builder.build().toString());
            }

        } catch (Exception e) {
            logger.error("Exception during login process", e);
            JsonObject errorJson = Json.createObjectBuilder()
                .add("status", "fail")
                .add("message", "An error occurred during login.")
                .build();
            try (PrintWriter out = res.getWriter()) {
                out.print(errorJson.toString());
            }
        }
    }
}
