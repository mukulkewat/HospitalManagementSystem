package com.ngs.DBconnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;





public class SingletonConnection {
	private static Connection con = null;
	private static Properties ps = new Properties();
	
	static{
		
		try(InputStream input = SingletonConnection.class.getClassLoader()
		        .getResourceAsStream("config.properties")) {
			ps.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnectionObject()  {
		try {
			if (con == null || con.isClosed()) {
				try {
					Class.forName(ps.getProperty("mysqldriverclass"));
					con = DriverManager.getConnection(ps.getProperty("mysqlurl"), ps.getProperty("mysqluser"),
							ps.getProperty("mysqlpass"));
					System.out.println(con);
					return con;

				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}
//	public static void main(String[] args) {
//		Connection connectionObject = SingletonConnection.getConnectionObject();
//		Connection connectionObject2 = SingletonConnection.getConnectionObject();
//		System.out.println(connectionObject.hashCode());
//		System.out.println(connectionObject2.hashCode());
//		
//	}
	
	
	

}
