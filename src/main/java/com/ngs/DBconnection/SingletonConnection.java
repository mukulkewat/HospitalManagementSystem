package com.ngs.DBconnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SingletonConnection {

    private static final Logger logger = Logger.getLogger(SingletonConnection.class);

    private static Properties ps = new Properties();
    private static Connection con;

    static {
        // Load DB config and establish initial connection
        try (InputStream input = SingletonConnection.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties file not found in classpath.");
            }

            ps.load(input);
            logger.info("Database properties loaded from config.properties");

            Class.forName(ps.getProperty("driverclass"));
            con = DriverManager.getConnection(
                    ps.getProperty("url"),
                    ps.getProperty("user"),
                    ps.getProperty("pass"));

            logger.info("Initial database connection established successfully to: " + ps.getProperty("dbName"));

        } catch (Exception e) {
            logger.error("Error during initial database connection setup: ", e);
            e.printStackTrace();
        }
    }

    public static Connection getConnectionObject() {
        try {
            if (con == null || con.isClosed()) {
                logger.warn("Database connection is null or closed. Attempting to reconnect...");
                try {
                    Class.forName(ps.getProperty("driverclass"));
                    con = DriverManager.getConnection(
                            ps.getProperty("url"),
                            ps.getProperty("user"),
                            ps.getProperty("pass"));

                    logger.info("New database connection re-established.");
                } catch (Exception e) {
                    logger.error("Error while re-establishing database connection: ", e);
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            logger.error("SQL Exception while checking connection state: ", e);
            e.printStackTrace();
        }

        return con;
    }
}
