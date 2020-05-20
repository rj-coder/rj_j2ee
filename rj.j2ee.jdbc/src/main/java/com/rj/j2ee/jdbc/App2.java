package com.rj.j2ee.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App2 
{
    public static void main( String[] args ) throws Exception
    {	
        String url ="jdbc:odbc:mstxtdb;";
    	Connection con = DriverManager.getConnection(url);
    	Statement st = con.createStatement();
    	//int count = st.executeUpdate("create table studentcourse.csv (student_id integer,course_id integer)");
    	//System.out.println(count);
    	ResultSet rs =st.executeQuery("SELECT * FROM student.csv");
    	while(rs.next()) {
    		System.out.println(rs.getInt(1)+" | "+rs.getString(2));
    	}
    }    
}

	
