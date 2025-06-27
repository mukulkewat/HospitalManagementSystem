package com.ngs.DBconnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
public class DBConnection01 {
	static Properties ps = new Properties();
	static {
	try(InputStream input = DBConnection01.class.getClassLoader().getResourceAsStream("config.properties")) 
	{
		ps.load(input);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	final static String driverName = ps.getProperty("mysqldriverclass");
	final static String url = ps.getProperty("mysqlurl");
	final static String userName = ps.getProperty("mysqluser");
	final static String password = ps.getProperty("mysqlpass");
/*
 CREATE TABLE `mukul01`.`bankemployeedoc` (
  `DocumentID` VARCHAR(40) NOT NULL,
  `DocumentTitle` VARCHAR(40) NULL,
  `DocumentDate` VARCHAR(40) NULL,
  `DocumentVersion` INT NULL,
  `DocumentContent` VARCHAR(45) NULL,
  PRIMARY KEY (`DocumentID`));
 */
	public static String storeData(String patientName, String department, String appointmentDate, String appointmentTime) 
	{
		try {
			Class.forName(driverName);
			Connection con = DriverManager.getConnection(url, userName, password);
			PreparedStatement psm = con.prepareStatement("insert into hospitalappointmentform values(?,?,?,?)");
			psm.setString(1, patientName);
			psm.setString(2, department);
			psm.setString(3, appointmentDate);
			psm.setString(4, appointmentTime);
			
			int k = psm.executeUpdate();
			 if(k>0) {
				 System.out.println("Data Stored Successfully");
			 }
			 else System.err.println("Data Stored UnSuccessfully");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	

}
