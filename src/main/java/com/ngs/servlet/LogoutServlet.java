package com.ngs.servlet;

import java.io.IOException;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LogoutServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("LogoutServlet.doPost()");
        try {
        	logger.info("User session invalidated successfully.");
            request.getSession().invalidate();
            logger.info("User session invalidated successfully.");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\":\"success\", \"message\":\"Logged out successfully\"}");
        } catch (Exception e) {
            logger.error("Error during logout process", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"Logout failed\"}");
        }
    }
}
