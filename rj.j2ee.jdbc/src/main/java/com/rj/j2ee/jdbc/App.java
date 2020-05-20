package com.rj.j2ee.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Enumeration;


/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * @param args
     * @throws Exception
     */
    public static void main( String[] args ) throws Exception
    {	
    	Enumeration<Driver> drivers = DriverManager.getDrivers();
        while(drivers.hasMoreElements()) {
        	DriverManager.deregisterDriver(drivers.nextElement());
        }
        
        Driver driver = (Driver)Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
        DriverManager.registerDriver(driver);
        String url ="jdbc:odbc:sqlite";
    	Connection con = DriverManager.getConnection(url);
    	con.setAutoCommit(false);
    
        int studentId = 2;
        int count = con.createStatement().executeUpdate("DELETE FROM STUDENT WHERE STUDENT_ID ="+studentId);
        System.out.println(count);
        con.rollback();
    	ResultSet rs = con.createStatement().executeQuery("SELECT student_name,student_id FROM STUDENT");
        while(rs.next())
        	System.out.println(rs.getString(1)+" | "+rs.getInt(2));
        
        rs.close();
    }
    
}

	
